package iniflex;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Funcionario(String nome, LocalDate dtnasc, BigDecimal salario , String funcao) {
        super(nome, dtnasc);
        this.salario = salario.round(new MathContext(6, RoundingMode.HALF_UP));
        this.funcao = funcao;
    }

    public String converteSalario (BigDecimal salario){
        MathContext round = new MathContext(6);
        return salario.round(round).toString().replace(".",",");
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                " nome= " + getNome()+
                " dtnasc= " + formatarData(getDtnasc())+
                " salario= " + converteSalario(salario) +
                "  funcao= " + funcao +
                '}';
    }
}
