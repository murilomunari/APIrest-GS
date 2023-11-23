package br.com.fiap.domain.entity;

import java.time.LocalDate;

public class Paciente {

    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;



    public Paciente() {
    }

    public Paciente(Long id, String nome, String cpf, LocalDate dataNascimento) {
        this.setId(id);
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);

    }


    public Long getId() {
        return id;
    }

    public Paciente setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Paciente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Paciente setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Paciente setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}