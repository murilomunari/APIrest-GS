package br.com.fiap.domain.entity;

public class Areas {

    private Long id;

    private String regiao;

    private int cep;

    public Areas() {
    }

    public Areas(Long id, String regiao, int cep) {
        this.setId(id);
        this.setRegiao(regiao);
        this.setCep(cep);
    }

    public Long getId() {
        return id;
    }

    public Areas setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRegiao() {
        return regiao;
    }

    public Areas setRegiao(String regiao) {
        this.regiao = regiao;
        return this;
    }

    public int getCep() {
        return cep;
    }

    public Areas setCep(int cep) {
        this.cep = cep;
        return this;
    }

    @Override
    public String toString() {
        return "Areas{" +
                "id=" + id +
                ", regiao='" + regiao + '\'' +
                ", cep=" + cep +
                '}';
    }
}