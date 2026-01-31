package com.br.petshow.config.swagger;

public final class SwaggerExampleConstants {

    private SwaggerExampleConstants() {}

    public static final String DESC_500 = "Erro interno";
    public static final String DESC_400 = "Erro de validação";

    public static final String ERRO_500_EXAMPLE = """
        {
          "timestamp": "2026-01-31T12:34:56Z",
          "status": 500,
          "error": "Internal Server Error",
          "message": "Ocorreu um erro inesperado."
        }
        """;

    public static final String ERRO_400_EXAMPLE = """
        {
          "timestamp": "2026-01-31T12:00:00Z",
          "status": 400,
          "error": "Dados inválidos",
          "message": "Verifique os campos obrigatórios e os formatos",
          "fields": { "nome": "não deve estar em branco" },
          "path": "/api/v1/adicionarBicho"
        }
        """;
}