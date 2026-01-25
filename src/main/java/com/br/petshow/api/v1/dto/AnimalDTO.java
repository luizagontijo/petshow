package com.br.petshow.api.v1.dto;

import com.br.petshow.api.v1.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonInclude; //configurar como os campos de um objeto serão serializados em JSON
import com.fasterxml.jackson.annotation.JsonFormat; // opcional
import io.swagger.v3.oas.annotations.media.Schema;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Representa um animal (Cachorro) para a resposta do endpoint /bicho.
 * - idade é derivada de dataNascimento, se presente.
 * - sexo é um enum (MACHO, FEMEA, NAO_INFORMADO).
 */
@Schema(description = "Dados de um animal retornado pela API")
@JsonInclude(JsonInclude.Include.NON_NULL) //so mostra o que não é nulo
public class AnimalDTO {

    @Schema(description = "Tipo do animal", example = "Cachorro")
    private String tipo;

    @Schema(description = "Nome do animal", example = "Bidu")
    private String nome;

    @Schema(description = "Raça do animal", example = "Vira-lata")
    private String raca;

    @Schema(description = "Cor do animal", example = "Caramelo")
    private String cor;

    @Schema(description = "Sexo do animal", example = "MACHO", implementation = Sexo.class)
    private Sexo sexo;

    @Schema(description = "Idade em anos (derivada de dataNascimento)", example = "2", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idade;


    @Schema(description = "Data de nascimento (YYYY-MM-DD)", type = "string", format = "date", example = "2024-01-10")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Schema(description = "Nome do dono", example = "Mônica")
    private String dono;

    @Schema(description = "Data/hora do cadastro (YYYY-MM-DDTHH:mm:ss)", type = "string", format = "date-time", example = "2026-01-25T10:15:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCadastro;

    public AnimalDTO() {}
    //construtor padrão, usado para serialização/deserialização JSON
    //permite criar um objeto vazio e preencher seus campos posteriormente

    //construtor com argumentos, facilita a criação de objetos completos
    public AnimalDTO(String tipo, String nome, String raca, String cor, Sexo sexo,
                     LocalDate dataNascimento, String dono, LocalDateTime dataCadastro) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dono = dono;
        this.dataCadastro = dataCadastro;
        this.idade = calcularIdade(dataNascimento);
    }

    private Integer calcularIdade(LocalDate nascimento) {
        if (nascimento == null) return null;
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    // Getters e Setters
    //Getters: Permitir o acesso aos valores dos atributos privados da classe.
    //Setters: Permitir a modificação dos valores dos atributos privados da classe.
    //permite que veja os valores das variaveis de um animal e que configure outros valores

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade(dataNascimento);
    }

    public String getDono() { return dono; }
    public void setDono(String dono) { this.dono = dono; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}
