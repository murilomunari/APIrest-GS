package br.com.fiap.domain.entity;

import java.time.LocalDate;


public class Doacao {

    private Long id;

    private double valor;

    private String tipo;

    private LocalDate data;

    private EmpresaTerciera empresa;

    public Doacao() {
    }

    public Doacao(Long id, double valor, String tipo, LocalDate data, EmpresaTerciera empresa) {
        this.setId(id);
        this.setValor(valor);
        this.setTipo(tipo);
        this.setData(data);
        this.setEmpresa(empresa);
    }


    public Long getId() {
        return id;
    }

    public Doacao setId(Long id) {
        this.id = id;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public Doacao setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Doacao setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public LocalDate getData() {
        return data;
    }

    public Doacao setData(LocalDate data) {
        this.data = data;
        return this;
    }

    public EmpresaTerciera getEmpresa() {
        return empresa;
    }

    public Doacao setEmpresa(EmpresaTerciera empresa) {
        this.empresa = empresa;
        return this;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "id=" + id +
                ", valor=" + valor +
                ", tipo='" + tipo + '\'' +
                ", data=" + data +
                ", empresa=" + empresa +
                '}';
    }
}