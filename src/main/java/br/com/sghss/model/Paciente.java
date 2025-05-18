package br.com.sghss.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

// Declaração da classe como tabela
@Entity
@Table(name = "pacientes")
public class Paciente {
    // Atributos privados da classe anotados como coluna na tabela com suas 
    // regras de persistência e mensagens de feedback
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Informe o nome.")
    @Size(min = 3, max = 50, message = "O tamanho deve estar entre 3 e 50.")
    private String nome;

    @Column(length = 14)
    @NotBlank(message = "Informe o CPF.")
    private String cpf;

    @Column(name = "data_nascimento", columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Informe a data de nascimento.")
    private LocalDate dataDeNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o sexo.")
    private Sexo sexo;

    @Column(length = 15)
    private String telefone;

    @Column(length = 50)
    @Email(message = "Deve ser um endereço de email bem formatado.")
    private String email;

    private boolean ativo;

    // Construtor padrão definindo novo paciente como ativo
    public Paciente() {
        this.ativo = true;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}