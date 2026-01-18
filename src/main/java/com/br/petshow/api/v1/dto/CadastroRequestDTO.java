package com.br.petshow.api.v1.dto;

import com.br.petshow.api.v1.enums.Sexo;
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
public class CadastroRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    @Size(max = 30)
    private String tipo;          // obrigatório: tipo do animal

    @Size(max = 50)
    private String raca;          // opcional

    @Size(max = 30)
    private String cor;           // opcional

    private Sexo sexo;            // opcional (MACHO, FEMEA, NAO_INFORMADO)

    @PastOrPresent
    private LocalDate dataNascimento; // opcional

    @Size(max = 60)
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
