package com.sinerji.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {

    private LocalDate fechamento;
    private BigDecimal valor;

    public Venda() {
    }

    public Venda(LocalDate fechamento, BigDecimal valor) {
        this.fechamento = fechamento;
        this.valor = valor;
    }

    public LocalDate getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDate fechamento) {
        this.fechamento = fechamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
