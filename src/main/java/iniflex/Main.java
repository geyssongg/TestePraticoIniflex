package iniflex;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
        List<Funcionario> funcionarioList  = new ArrayList<>();
        funcionarioList.add( new Funcionario ("Maria"   ,LocalDate.of(2000,10,02),new BigDecimal(2009.44)  , "Operador"      ));
        funcionarioList.add( new Funcionario ("João"    ,LocalDate.of(1990,05,24),new BigDecimal(2284.38)  , "Operador"      ));
        funcionarioList.add( new Funcionario ("Caio"    ,LocalDate.of(1961,05,8),new BigDecimal(9836.14)   , "Coordenador"   ));
        funcionarioList.add( new Funcionario ("Miguel"  ,LocalDate.of(1988,10,31),new BigDecimal(19119.88) , "Diretor"       ));
        funcionarioList.add( new Funcionario ("Alice"   ,LocalDate.of(1995,01,19),new BigDecimal(2234.68)  , "Recepcionista" ));
        funcionarioList.add( new Funcionario ("Heitor"  ,LocalDate.of(1999,11,5),new BigDecimal(1582.72)   , "Contador"      ));
        funcionarioList.add( new Funcionario ("Arthur"  ,LocalDate.of(1993,03,14),new BigDecimal(4071.84)  , "Contador"      ));
        funcionarioList.add( new Funcionario ("Laura"   ,LocalDate.of(1994,07,02),new BigDecimal(3017.45)  , "Gerente"       ));
        funcionarioList.add( new Funcionario ("Heloísa" ,LocalDate.of(2003,05,12),new BigDecimal(1606.85)  , "Eletricista"   ));
        funcionarioList.add( new Funcionario ("Helena"  ,LocalDate.of(1996,9,18),new BigDecimal(2799.93)   , "Gerente"       ));

        //3.2 – Remover o funcionário “João” da lista
        funcionarioList.removeIf(func -> func.getNome().equals("João"));

        /* 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
                • informação de data deve ser exibido no formato dd/mm/aaaa;
                • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
                As regras para a formatação de data e valor númerico foram realizadas dentro do toString da classe Funcionario
                */
        funcionarioList.stream().forEach(System.out::println);

        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarioList.stream().forEach(func -> func.setSalario(func.getSalario().multiply(new BigDecimal(1.10))));
        funcionarioList.stream().forEach(System.out::println);

        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String,List<Funcionario>> funcionarioFuncaoMap = funcionarioList.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários agrupados por função:");
        funcionarioFuncaoMap.forEach((funcao, listFunc) -> {
            System.out.println("\nFunção: " + funcao);
            listFunc.forEach(System.out::println);
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nAniversariantes de outubro e dezembro");
        funcionarioList.stream().filter(func -> func.getDtnasc().getMonthValue() == 12 || func.getDtnasc().getMonthValue() == 10)
                .forEach(System.out::println);

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario funcVelho = funcionarioList.stream()
                .min(Comparator.comparing(Funcionario::getDtnasc))
                .orElse(null);
        if (funcVelho != null) {
            long anos = funcVelho.getDtnasc().until(LocalDateTime.now(),ChronoUnit.YEARS);
            System.out.println("\nFuncionário mais velho ");
            System.out.println("Nome: " + funcVelho.getNome() + ", Idade: " + anos);
        }

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarioList.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(System.out::println);

        //3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal salarioTotal;
        salarioTotal = funcionarioList.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,BigDecimal::add).round(new MathContext(7
        ));
        System.out.println("O a soma de todos os salários é de: R$ " + salarioTotal);

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        funcionarioList.stream().forEach( func -> {
                BigDecimal salariosMinimos = func.getSalario().divide(new BigDecimal("1212.00"),0).round(new MathContext(4, RoundingMode.HALF_UP));
                System.out.println("Funcionario: " + func.getNome() +
                                    "\nSalarios minimos: " +salariosMinimos );
                                    });

    }
}