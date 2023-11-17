package br.com.fiap.domain.entity;

public class Servicos {

    private Long id;

    private String descricao;

    private String tipo;

    public Servicos() {
    }

    public Servicos(Long id, String descricao, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Servicos setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Servicos setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Servicos setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public String toString() {
        return "Servicos{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
