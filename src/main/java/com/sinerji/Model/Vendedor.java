package com.sinerji.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sinerji.Model.Cargo.Cargos;

public class Vendedor extends Funcionario {
    private List<Venda> vendas;

    public Vendedor() {

    }

    public Vendedor(String nomeFunc, Cargo cargo, LocalDate contratacao) {
        super(nomeFunc, cargo, contratacao);
    }

    public Vendedor(String nomeFunc, Cargo cargo, LocalDate contratacao, List<Venda> vendas) {
        super(nomeFunc, cargo, contratacao);
        this.vendas = vendas;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public Vendedor(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public void setVendas(List<Venda> vendas) {
        if (this.getCargo().getNome() == Cargos.VENDEDOR) {
            this.vendas = vendas;
        } else {
            System.out.println("Não Foi possivel adicionar a venda");
        }
    }

    public void addVenda(Venda venda) {
        if (this.vendas == null) {
            this.vendas = new ArrayList<Venda>();
        }
        this.vendas.add(venda);

    }

}