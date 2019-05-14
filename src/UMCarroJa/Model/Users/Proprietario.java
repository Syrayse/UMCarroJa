package UMCarroJa.Model.Users;

import UMCarroJa.Model.Interfaces.Classificavel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Proprietario extends Pessoa implements Serializable, Classificavel {

    private long nClassificacao;
    private double classificacao;
    private Set<Integer> veiculos;

    public Proprietario(){
        super();
        this.nClassificacao=0;
        this.classificacao=0;
    }

    public Proprietario(long id, String email, String nome, String password, String morada, LocalDate dataNascimento){
        super(id, email, nome, password, morada, dataNascimento);
        this.nClassificacao= 0;
        this.classificacao= 100;
    }

    public Proprietario(Proprietario proprietario){
        super(proprietario);
        this.nClassificacao=proprietario.getnClassifiacacao();
        this.classificacao=proprietario.getClassificacao();
    }

    public double getClassificacao() {
        return this.classificacao;
    }

    public long getnClassifiacacao(){
        return this.nClassificacao;
    }

    public Set<Integer> getVeiculos() {
    	return new HashSet(veiculos);
    }


    @Override
    public void classifica(double classificacao) {
        long nAtual = this.nClassificacao;
        double clAtual = this.classificacao;
        this.classificacao = ((nAtual*clAtual + classificacao) / (nAtual + 1));
        this.nClassificacao++;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Classificação: ").append(this.classificacao);
        sb.append("Nº de classificações: ").append(this.nClassificacao);
        return (sb.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario proprietario = (Proprietario) o;
        return super.equals(proprietario) &&
                classificacao==proprietario.getClassificacao() &&
                nClassificacao==proprietario.getnClassifiacacao();
    }

    public Proprietario clone(){
        return new Proprietario(this);
    }
}
