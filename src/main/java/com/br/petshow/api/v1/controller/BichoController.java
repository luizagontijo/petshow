
package com.br.petshow.api.v1.controller;

import com.br.petshow.api.v1.dto.AnimalDTO;
import com.br.petshow.api.v1.dto.CadastroRequestDTO;
import com.br.petshow.api.v1.enums.Sexo;
import com.br.petshow.api.v1.samples.AnimalSamples;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * GET  /api/v1/bicho            -> retorna animal genérico (mock)
 * POST /api/v1/adicionarBicho   -> cria animal com nome e tipo obrigatórios; demais campos opcionais
 */
@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class BichoController {

    @GetMapping("/bicho")
    public AnimalDTO getBicho() {
        return AnimalSamples.cachorroGenerico();
    }

    @PostMapping(value = "/adicionarBicho", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDTO> adicionarBicho(@Valid @RequestBody CadastroRequestDTO req) {
        // Defaults quando campos opcionais vierem ausentes
        var sexo = req.getSexo() != null ? req.getSexo() : Sexo.NAO_INFORMADO;

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
