package com.br.petshow.api.v1.dto;

import com.br.petshow.api.v1.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat; // opcional
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

/**
 * Request para criar um cadastro de animal.
 * - nome: obrigatório
 * - tipo: obrigatório (ex.: "Cachorro", "Gato", "Pássaro", etc.)
 * - demais campos: opcionais
 */

@Schema(description = "Dados para cadastro de um bicho")
public class CadastroRequestDTO {

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Nome do animal", example = "Scooby", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String nome;

    @NotBlank
    @Size(max = 30)
    @Schema(description = "Tipo do animal (ex.: Cachorro, Gato, Pássaro)", example = "CACHORRO", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 30)
    private String tipo;          // obrigatório: tipo do animal

    @Size(max = 50)
    @Schema(description = "Raça do animal", example = "Vira-lata", maxLength = 50)
    private String raca;          // opcional

    @Size(max = 30)
    @Schema(description = "Cor do animal", example = "Caramelo", maxLength = 30)
    private String cor;           // opcional

    @Schema(description = "Sexo do animal", example = "MACHO", implementation = Sexo.class)
    private Sexo sexo;            // opcional (MACHO, FEMEA, NAO_INFORMADO)

    @PastOrPresent
    @Schema(description = "Data de nascimento (não pode ser no futuro)", type = "string", format = "date", example = "2025-11-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento; // opcional

    @Size(max = 60)
    @Schema(description = "Nome do dono", example = "Luiza", maxLength = 60)
    private String dono;          // opcional

    public CadastroRequestDTO() {
    }

    public CadastroRequestDTO(String nome, String tipo, String raca, String cor,
                              Sexo sexo, LocalDate dataNascimento, String dono) {
        this.nome = nome;
        this.tipo = tipo;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dono = dono;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

}
