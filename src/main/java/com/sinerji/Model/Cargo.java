package com.sinerji.Model;

import java.math.BigDecimal;

public class Cargo {
    private Cargos nome;
    private BigDecimal salario;
    private Integer beneficio;

    public enum Cargos {
        SECRETARIO,
        VENDEDOR,
        GERENTE
    }

    public Cargo() {
    }

    public Cargo(Cargos nome, BigDecimal salario, Integer beneficio) {
        this.nome = nome;
        this.salario = salario;
        this.beneficio = beneficio;
    }

    public Cargo(Cargos nome, BigDecimal salario, Integer beneficio, BigDecimal tempoServico) {
        this.nome = nome;
        this.salario = salario;
        this.beneficio = beneficio;
    }

    public Cargos getNome() {
        return nome;
    }

    public void setNome(Cargos nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Integer getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Integer beneficioDecimal) {
        this.beneficio = beneficioDecimal;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "Nome=" + nome +
                ", Salario=" + salario +
                ", Beneficio=" + beneficio +
                '}';
    }
}