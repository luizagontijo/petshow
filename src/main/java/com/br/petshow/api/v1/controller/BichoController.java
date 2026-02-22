package com.br.petshow.api.v1.controller;

import com.br.petshow.api.v1.dto.AnimalDTO;
import com.br.petshow.api.v1.dto.CadastroRequestDTO;
import com.br.petshow.api.v1.dto.ErrorResponseDTO;
import com.br.petshow.api.v1.enums.SexoEnum;
import com.br.petshow.api.v1.samples.AnimalSamples;
import com.br.petshow.config.swagger.SwaggerExampleConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.extensions.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * GET  /v1/bichos   -> retorna animal genérico (mock)
 * POST /v1/bichos   -> cria animal com nome e tipo obrigatórios; demais campos opcionais
 */
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated // ativa validação também em @PathVariable, @RequestParam, @RequestHeader, mas ainda nao uso
@Tag(
        name = "Bichos",
        description = "Operações de consulta e cadastro de animais",
        extensions = @Extension(name = "x-order", properties = @ExtensionProperty(name = "order", value = "1"))
        //configuração para ordenar ou priorizar a exibição da tag "Bichos"
        //"1" indica que esta tag deve aparecer antes de outras tags com valores maiores ou sem a propriedade
)

public class BichoController {

    @GetMapping("/bichos")
    @Operation(
            summary = "Busca um bicho de exemplo",
            description = "Retorna um animal genérico para fins de teste.",
            operationId = "consultarBicho",
            tags = {"Bichos"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal retornado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnimalDTO.class),
                            examples = @ExampleObject(
                                    name = "cachorro",
                                    summary = "Exemplo de cachorro",
                                    value = """
                                    {
                                      "tipo": "Cachorro",
                                      "nome": "Bila Bilu2",
                                      "raca": "Poodle",
                                      "cor": "Branco",
                                      "sexo": "FEMEA",
                                      "idade": 10,
                                      "dataNascimento": "2015-03-28",
                                      "dono": "Luiza",
                                      "dataCadastro": "2026-01-31T10:15:30"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = SwaggerExampleConstants.DESC_500,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "erroPadrao",
                                    value = SwaggerExampleConstants.ERRO_500_EXAMPLE
                            )
                    )
            )
    })
    public AnimalDTO getBicho() {
        return AnimalSamples.cachorroGenerico();
    }

    @PostMapping(value = "/bichos", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Cadastra um novo bicho",
            description = "Cria um animal com nome e tipo obrigatórios; demais campos são opcionais. " +
                    "O sexo padrão é NAO_INFORMADO quando omitido.",
            operationId = "adicionarBicho",
            tags = {"Bichos"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Animal criado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnimalDTO.class),
                            examples = @ExampleObject(
                                    name = "criado",
                                    summary = "Exemplo de resposta com animal criado",
                                    value = """
                                    {
                                      "tipo": "Papagaio",
                                      "nome": "Helder",
                                      "raca": "Sem Raça Definida",
                                      "cor": "Verde",
                                      "sexo": "NAO_INFORMADO",
                                      "idade": 52,
                                      "dataNascimento": "2074-08-10",
                                      "dono": "Renata",
                                      "dataCadastro": "2026-02-21T09:30:00"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = SwaggerExampleConstants.DESC_400,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "dadosInvalidos",
                                    value = SwaggerExampleConstants.ERRO_400_EXAMPLE
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = SwaggerExampleConstants.DESC_500,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "erroPadrao",
                                    value = SwaggerExampleConstants.ERRO_500_EXAMPLE
                            )
                    )
            )
    })
    public ResponseEntity<AnimalDTO> adicionarBicho(
            @Valid
            //exemplos de request
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados mínimos para cadastro do animal",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CadastroRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "minimo",
                                            summary = "Exemplo mínimo válido",
                                            value = """
                                            {
                                              "tipo": "Gato",
                                              "nome": "Morgana"
                                            }
                                            """
                                    ),
                                    @ExampleObject(
                                            name = "completo",
                                            summary = "Exemplo completo",
                                            value = """
                                            {
                                              "tipo": "Cachorro",
                                              "nome": "Rex",
                                              "raca": "Labrador",
                                              "cor": "Caramelo",
                                              "sexo": "MACHO",
                                              "dataNascimento": "2020-05-20",
                                              "dono": "Luiza"
                                            }
                                            """
                                    )
                            }
                    )
            )

            @RequestBody CadastroRequestDTO req) {
        // Defaults quando campos opcionais vierem ausentes
        var sexo = req.getSexo() != null ? req.getSexo() : SexoEnum.NAO_INFORMADO;

        // Monta o AnimalDto de resposta
        AnimalDTO criado = new AnimalDTO(
                req.getTipo(),            // obrigatorio
                req.getNome(),             // obrigatorio
                req.getRaca(),            // opcional
                req.getCor(),             // opcional
                sexo,                     // default se null
                req.getDataNascimento(),  // opcional; idade derivada
                req.getDono(),            // opcional
                LocalDateTime.now()       // dataCadastro
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
}
