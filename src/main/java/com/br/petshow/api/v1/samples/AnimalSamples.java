package com.br.petshow.api.v1.samples;

import com.br.petshow.api.v1.dto.AnimalDTO;
import com.br.petshow.api.v1.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Fornece objetos de exemplo para desenvolvimento inicial sem BD.
 */
public final class AnimalSamples {

    private AnimalSamples() {}

    //cria o animal "cachorroGenerico" usando o construtor com argumentos
    public static AnimalDTO cachorroGenerico() {
        return new AnimalDTO(
                "Cachorro",
                "Bila Bilu",
                "Poodle",
                "Branco",
                Sexo.FEMEA,
                LocalDate.of(2015, 3, 28),
                "Luiza",
                LocalDateTime.now()    // data do cadastro é a data atual
        );
    }
}