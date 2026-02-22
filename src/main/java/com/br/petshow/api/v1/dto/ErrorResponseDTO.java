package com.br.petshow.api.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.Map;

@Schema(description = "Erro padrão da API")
public class ErrorResponseDTO {
    @Schema(example = "2026-01-31T12:34:56Z")
    private OffsetDateTime timestamp;

    @Schema(description = "Código HTTP", example = "400")
    private Integer status;

    @Schema(description = "Título resumido do erro", example = "Dados inválidos")
    private String error;

    @Schema(description = "Mensagem detalhada", example = "Verifique os campos obrigatórios e os formatos")
    private String message;

    @Schema(description = "Mapa de erros por campo")
    private Map<String, String> fields;

    @Schema(description = "Caminho da requisição", example = "/v1/bichos")
    private String path;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(OffsetDateTime timestamp, Integer status, String error, String message,
                            Map<String, String> fields, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.fields = fields;
        this.path = path;
    }

    // getters/setters
    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Map<String, String> getFields() { return fields; }
    public void setFields(Map<String, String> fields) { this.fields = fields; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}