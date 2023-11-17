package br.com.fiap.domain.entity;

public class EmpresaTerciera {

    private Long id;

    private String nome;

    private String cnpj;

    private String email;

    private Ramo ramo;

    public EmpresaTerciera() {
    }

    public EmpresaTerciera(Long id, String nome, String cnpj, String email, Ramo ramo) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.ramo = ramo;
    }

    public Long getId() {
        return id;
    }

    public EmpresaTerciera setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public EmpresaTerciera setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public EmpresaTerciera setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmpresaTerciera setEmail(String email) {
        this.email = email;
        return this;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public EmpresaTerciera setRamo(Ramo ramo) {
        this.ramo = ramo;
        return this;
    }

    @Override
    public String toString() {
        return "EmpresaTerciera{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", email='" + email + '\'' +
                ", ramo=" + ramo +
                '}';
    }
}
