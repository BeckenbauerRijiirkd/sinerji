package com.sinerji.Model;

import java.time.LocalDate;

public class Funcionario {
    private String nome;
    private Cargo cargo;
    private LocalDate contratacao;

    public static final Long adicSecretario = 1000L;
    public static final Long adicVendedor = 1800L;
    public static final Long adicGerente = 3000L;

    public Funcionario() {

    }

    public Funcionario(String nomeFunc, Cargo cargo, LocalDate contratacao) {
        this.nome = nomeFunc;
        this.cargo = new Cargo(cargo.getNome(), cargo.getSalario(), cargo.getBeneficio());
        this.contratacao = contratacao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getContratacao() {
        return contratacao;
    }

    public void setContratacao(LocalDate contratacao) {
        this.contratacao = contratacao;
    }

    public String toString() {
        return "Funcionario{" +
                "Nome='" + nome + '\'' +
                "\n" + cargo.toString() +
                "\nContratacao=" + contratacao +
                '}';
    }

}