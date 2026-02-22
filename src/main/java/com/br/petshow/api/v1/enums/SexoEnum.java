package com.br.petshow.api.v1.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Sexo do animal")
public enum SexoEnum {
    MACHO,
    FEMEA,
    NAO_INFORMADO
}

