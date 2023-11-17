package br.com.fiap.domain.entity;

import java.time.LocalDate;

public class Paciente {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String laudo;

    public Paciente() {
    }

    public Paciente(Long id, String nome, LocalDate dataNascimento, String laudo) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.laudo = laudo;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Paciente setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public String getLaudo() {
        return laudo;
    }

    public Paciente setLaudo(String laudo) {
        this.laudo = laudo;
        return this;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", laudo='" + laudo + '\'' +
                '}';
    }
}
