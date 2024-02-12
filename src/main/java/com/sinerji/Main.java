package com.sinerji;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sinerji.Model.Cargo;
import com.sinerji.Model.Funcionario;
import com.sinerji.Model.Venda;
import com.sinerji.Model.Vendedor;
import com.sinerji.Model.Cargo.Cargos;
import com.sinerji.Service.GerenciadorFolha;

public class Main {

    private static GerenciadorFolha gFolha = new GerenciadorFolha();

    private static int mes = 2;
    private static int ano = 2022;

    public static void main(String[] args) {

        Cargo secretario = new Cargo(Cargos.SECRETARIO, BigDecimal.valueOf(7000), 20);
        Cargo vendedor = new Cargo(Cargos.VENDEDOR, BigDecimal.valueOf(12000), 30);
        Cargo gerente = new Cargo(Cargos.GERENTE, BigDecimal.valueOf(7000), 0);

        Funcionario fun1 = new Funcionario("Jorge Carvalho ", secretario, LocalDate.of(2018, 1, 1));
        Funcionario fun2 = new Funcionario("Maria Souza", secretario, LocalDate.of(2015, 12, 1));
        Vendedor fun3 = new Vendedor("Ana Silva ", vendedor, LocalDate.of(2021, 12, 1));
        Vendedor fun4 = new Vendedor("João Mendes", vendedor, LocalDate.of(2021, 12, 1));
        Funcionario fun5 = new Funcionario("Juliana Alves ", gerente, LocalDate.of(2017, 07, 1));
        Funcionario fun6 = new Funcionario("Bento Albino ", gerente, LocalDate.of(2014, 3, 1));

        fun3.addVenda(new Venda(LocalDate.of(2021, 12, 1), BigDecimal.valueOf(5200)));
        fun3.addVenda(new Venda(LocalDate.of(2022, 01, 1), BigDecimal.valueOf(4000)));
        fun3.addVenda(new Venda(LocalDate.of(2022, 02, 1), BigDecimal.valueOf(4200)));
        fun3.addVenda(new Venda(LocalDate.of(2022, 03, 1), BigDecimal.valueOf(5850)));
        fun3.addVenda(new Venda(LocalDate.of(2022, 04, 1), BigDecimal.valueOf(7000)));

        fun4.addVenda(new Venda(LocalDate.of(2021, 12, 1), BigDecimal.valueOf(3400)));
        fun4.addVenda(new Venda(LocalDate.of(2022, 1, 1), BigDecimal.valueOf(7700)));
        fun4.addVenda(new Venda(LocalDate.of(2022, 2, 1), BigDecimal.valueOf(5000)));
        fun4.addVenda(new Venda(LocalDate.of(2022, 3, 1), BigDecimal.valueOf(5900)));
        fun4.addVenda(new Venda(LocalDate.of(2022, 4, 1), BigDecimal.valueOf(6500)));

        List<Funcionario> funcionarios = Arrays.asList(fun1, fun2, fun3, fun4, fun5, fun6);

        // 1 - valor total pago (salário e benefício)
        gFolha.folhaTotal(funcionarios, mes, ano);

        // 2 - total pago somente em salários no mês
        gFolha.folhaSalariosTotal(funcionarios, mes, ano);

        // 3 - somente com os funcionários que recebem benefícios, mês e ano e retorne o
        // total pago em benefícios no mês.

        List<Funcionario> funComBeneficios = gFolha.getFunComBeneficio(funcionarios);

        BigDecimal totalEmBeneficios = gFolha.beneficiosTotal(funComBeneficios, mes, ano);
        System.out
                .println(totalEmBeneficios + " Foi o Total pago aos funcionarios de Beneficios em:" + mes + "/" + ano);

        // 4 - o que recebeu o valor mais alto no mês
        Funcionario maiorRecebe = gFolha.maiorSalario(funcionarios, mes, ano);

        System.out.println("\n********************************");
        System.out.println("O funcionario que recebeu mais foi: " + maiorRecebe.getNome());
        System.out.println("********************************");

        // 5 - retorne o nome do funcionário que recebeu o valor mais alto em benefícios
        // no mês
        String funMaiorBeneficio = gFolha.funMaiorBeneficio(funcionarios, mes, ano);

        System.out.println("\n********************************");
        System.out.println(
                "O funcionario que recebeu mais em Beneficios foi: " + funMaiorBeneficio);
        System.out.println("********************************");

        // 6 - Um método que receba uma lista de vendedores, mês e ano e retorne o que
        // mais vendeu no mês.

        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getCargo().getNome() == Cargos.VENDEDOR) {
                vendedores.add((Vendedor) funcionario);
            }

        }

        Vendedor funMaiorVenda = gFolha.funMaiorVenda(vendedores, mes, ano);
        if (funMaiorVenda.getNome() != null) {
            System.out.println("O vendendor que mais vendeu foi: " + funMaiorVenda.getNome());
        } else {
            System.out.println("Não ocorreu venda no periodo de: " + mes + "/" + ano);
        }

    }

}