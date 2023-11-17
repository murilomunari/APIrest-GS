package br.com.fiap.domain.entity;

public class Funcionarios {

    private Long id;

    private String nome;

    private String cpf;

    private String setor;

    private EmpresaTerciera empresaTerciera;

    public Funcionarios() {
    }

    public Funcionarios(Long id, String nome, String cpf, String setor, EmpresaTerciera empresaTerciera) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.setor = setor;
        this.empresaTerciera = empresaTerciera;
    }

    public Long getId() {
        return id;
    }

    public Funcionarios setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Funcionarios setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Funcionarios setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getSetor() {
        return setor;
    }

    public Funcionarios setSetor(String setor) {
        this.setor = setor;
        return this;
    }

    public EmpresaTerciera getEmpresaTerciera() {
        return empresaTerciera;
    }

    public Funcionarios setEmpresaTerciera(EmpresaTerciera empresaTerciera) {
        this.empresaTerciera = empresaTerciera;
        return this;
    }

    @Override
    public String toString() {
        return "Funcionarios{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", setor='" + setor + '\'' +
                ", empresaTerciera=" + empresaTerciera +
                '}';
    }
}
