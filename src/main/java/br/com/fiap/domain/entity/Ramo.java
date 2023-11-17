package br.com.fiap.domain.entity;

public class Ramo {

    private Long id;

    private String nome;

    public Ramo() {
    }

    public Ramo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Ramo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Ramo setTipo(String tipo) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "Ramo{" +
                "id=" + id +
                ", tipo='" + nome + '\'' +
                '}';
    }
}
