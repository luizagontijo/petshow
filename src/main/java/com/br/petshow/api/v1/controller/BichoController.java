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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * GET  /v1/bichos   -> retorna animal genérico (mock)
 * POST /v1/bichos   -> cria animal com nome e tipo obrigatórios; demais campos opcionais
 */
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
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
            operationId = "getBicho",
            tags = {"Bichos"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalDTO.class),
                            examples = @ExampleObject(name = "cachorro", value = """
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
            @ApiResponse(responseCode = "201", description = "Animal criado",
                    content = @Content(schema = @Schema(implementation = AnimalDTO.class)))
            ,
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
    public ResponseEntity<AnimalDTO> adicionarBicho(@Valid @RequestBody CadastroRequestDTO req) {
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
