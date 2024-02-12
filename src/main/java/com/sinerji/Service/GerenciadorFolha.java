package com.sinerji.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.sinerji.Model.Cargo;
import com.sinerji.Model.Cargo.Cargos;
import com.sinerji.Model.Funcionario;
import com.sinerji.Model.Venda;
import com.sinerji.Model.Vendedor;

public class GerenciadorFolha {

    public GerenciadorFolha() {

    }

    public List<Funcionario> getFunComBeneficio(List<Funcionario> funcionarios) {
        List<Funcionario> funComBeneficio = new ArrayList<Funcionario>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCargo().getBeneficio() > 0) {
                funComBeneficio.add(funcionario);
            }
        }
        return funComBeneficio;
    }

    public Vendedor funMaiorVenda(List<Vendedor> funcionarios, int mes, int ano) {

        BigDecimal totalFun = new BigDecimal(0);
        BigDecimal maior = new BigDecimal(0);
        Funcionario funMaior = new Funcionario();

        for (Funcionario funcionario : funcionarios) {
            totalFun = new BigDecimal(0);
            totalFun = totalFun.add(calcBeneficio(funcionario, mes, ano));

            if (totalFun.compareTo(maior) > 0) {
                maior = totalFun;
                funMaior = funcionario;
            }

        }

        if (funMaior.getNome() != null) {
            return (Vendedor) funMaior;
        } else {
            return new Vendedor();
        }

    }

    public String funMaiorBeneficio(List<Funcionario> funcionarios, int mes, int ano) {

        BigDecimal totalFun = new BigDecimal(0);
        BigDecimal maior = new BigDecimal(0);
        Funcionario funMaior = new Funcionario();

        for (Funcionario funcionario : funcionarios) {
            totalFun = new BigDecimal(0);
            totalFun = totalFun.add(calcBeneficio(funcionario, mes, ano));

            if (totalFun.compareTo(maior) > 0) {
                maior = totalFun;
                funMaior = funcionario;
            }

        }

        return funMaior.getNome();

    }

    public Funcionario maiorSalario(List<Funcionario> funcionarios, int mes, int ano) {

        BigDecimal totalFun = new BigDecimal(0);
        BigDecimal maior = new BigDecimal(0);
        Funcionario funMaior = new Funcionario();

        for (Funcionario funcionario : funcionarios) {
            totalFun = new BigDecimal(0);
            totalFun = totalFun.add(calcSalario(funcionario, mes, ano));
            totalFun = totalFun.add(calcBeneficio(funcionario, mes, ano));

            if (totalFun.compareTo(maior) > 0) {
                maior = totalFun;
                funMaior = funcionario;
            }

        }

        return funMaior;

    }

    public BigDecimal beneficiosTotal(List<Funcionario> funcionarios, int mes, int ano) {

        BigDecimal total = new BigDecimal(0);
        BigDecimal totalFun = new BigDecimal(0);

        for (Funcionario funcionario : funcionarios) {

            totalFun = totalFun.add(calcBeneficio(funcionario, mes, ano));
            total = total.add(totalFun);
            totalFun = BigDecimal.valueOf(0);

        }
        return total;
    }

    public BigDecimal folhaSalariosTotal(List<Funcionario> funcionarios, int mes, int ano) {

        BigDecimal total = new BigDecimal(0);
        BigDecimal totalFun = new BigDecimal(0);

        for (Funcionario funcionario : funcionarios) {
            totalFun = totalFun.add(calcSalario(funcionario, mes, ano));
            total = total.add(totalFun);
            totalFun = BigDecimal.valueOf(0);
        }
        System.out.println("\n" + //
                "********************************");
        System.out.println(total + " Foi o Total pago de salarios aos funcionarios em: " + mes + "/" + ano);
        System.out.println("********************************");

        return total;

    }

    public BigDecimal folhaTotal(List<Funcionario> funcionarios, int mes, int ano) {

        BigDecimal total = new BigDecimal(0);
        BigDecimal totalFun = new BigDecimal(0);

        for (Funcionario funcionario : funcionarios) {
            totalFun = totalFun.add(calcSalario(funcionario, mes, ano));
            totalFun = totalFun.add(calcBeneficio(funcionario, mes, ano));
            total = total.add(totalFun);
            totalFun = BigDecimal.valueOf(0);
        }
        System.out.println("\n" + //
                "********************************");
        System.out
                .println(total + " foi o total pago de salario e beneficiois aos funcionarios em: " + mes + "/" + ano);
        System.out.println("********************************");
        return total;

    }

    private BigDecimal calcSalario(Funcionario funcionario, int mes, int ano) {

        Cargo cargo = funcionario.getCargo();

        BigDecimal salarioBase = cargo.getSalario();

        BigDecimal salario = new BigDecimal(0);

        if (cargo.getNome() == Cargos.SECRETARIO) {
            Long adicional = Funcionario.adicSecretario * tempoServico(funcionario.getContratacao(), mes, ano);
            salario = salarioBase.add(BigDecimal.valueOf(adicional));

        }
        if (cargo.getNome() == Cargos.VENDEDOR) {
            Long adicional = Funcionario.adicVendedor * tempoServico(funcionario.getContratacao(), mes, ano);
            salario = salarioBase.add(BigDecimal.valueOf(adicional));

        }
        if (cargo.getNome() == Cargos.GERENTE) {
            Long adicional = Funcionario.adicGerente * tempoServico(funcionario.getContratacao(), mes, ano);
            salario = salarioBase.add(BigDecimal.valueOf(adicional));

        }

        return salario;

    }

    public BigDecimal calcBeneficio(Funcionario funcionario, int mes, int ano) {

        BigDecimal beneficio = new BigDecimal(0);

        BigDecimal salario = calcSalario(funcionario, mes, ano);

        if (funcionario.getCargo().getNome() == Cargos.SECRETARIO) {

            beneficio = beneficio.add(salario.multiply(BigDecimal.valueOf(0.20)));

        }

        if (funcionario.getCargo().getNome() == Cargos.VENDEDOR) {

            BigDecimal valorVendido = totalEmVendas((Vendedor) funcionario, mes, ano);

            beneficio = beneficio.add(valorVendido.multiply(BigDecimal.valueOf(0.30)));

        }

        return beneficio;

    }

    private long tempoServico(LocalDate contratacao, int mes, int ano) {

        LocalDate dataRef = LocalDate.of(ano, mes, 1);

        long diferencaAnos = ChronoUnit.YEARS.between(contratacao, dataRef);

        return diferencaAnos;
    }

    public BigDecimal totalEmVendas(Vendedor vend, int mes, int ano) {
        List<Venda> vendas = vend.getVendas();

        BigDecimal total = new BigDecimal(0);

        LocalDate mesAnoRef = LocalDate.of(ano, mes, 1);

        if (vendas != null)
            for (Venda venda : vendas) {

                if (venda.getFechamento().getYear() == mesAnoRef.getYear()
                        && venda.getFechamento().getMonth() == mesAnoRef.getMonth()) {
                    total = total.add(venda.getValor());
                }

            }
        return total;
    }
}