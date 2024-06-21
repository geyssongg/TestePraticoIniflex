package iniflex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private String nome;
    private LocalDate dtnasc;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(LocalDate dtnasc) {
        this.dtnasc = dtnasc;
    }

    public String formatarData (LocalDate date){
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtFormat);
    }

    public Pessoa(){
    }
    public Pessoa(String nome, LocalDate dtnasc) {
        this.nome = nome;
        this.dtnasc = dtnasc;
    }
}
