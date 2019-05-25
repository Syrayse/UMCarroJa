package Model.Users;

import Model.Interfaces.Classificavel;
import Model.Alugueres.*;
import Exceptions.ClassificacaoInvalidException;

import java.io.Serializable;

/**
 * Classe abstracta Pessoa
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public abstract class Pessoa implements Classificavel, Serializable {

    private String nif;
    private String email;
    private String nome;
    private String password;
    private String morada;
    private HistoricoAluguer historico;
    private long nClassificacoes;
    private double classificacao;
    /**
     * Construtor vazio para objetos da classe Pessoa
     */
    public Pessoa() {
        nif = "";
        email = "";
        nome = "";
        password = "";
        morada = "";
        historico = new HistoricoAluguer();
        nClassificacoes = -1;
        classificacao = -1;
    }
    /**
     * Construtor parametrizado para objetos da classe Pessoa
     */
    public Pessoa(String nif, String email, String nome, String password, String morada) {
        this.nif = nif;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        historico = new HistoricoAluguer();
        nClassificacoes = 0;
        classificacao = 100;
    }
    /**
     * Construtor de cópia para objetos da classe Pessoa
     */
    public Pessoa(Pessoa p){
        nif = p.getNif();
        email = p.getEmail();
        nome = p.getNome();
        password = p.getPassword();
        morada = p.getMorada();
        historico = p.getHistorico();
    }

    public String getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public long getnClassificacoes() {
        return nClassificacoes;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public HistoricoAluguer getHistorico() {
        return historico.clone();
    }

    public void addAluguer(Aluguer aluguer) {
        historico.addAluguer(aluguer);
    }
    /**
     * Implementação do método equals de uma Pessoa.
     *
     * @return Boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return nif.equals(pessoa.getNif()) &&
                email.equals(pessoa.getNif()) &&
                password.equals(pessoa.getPassword()) &&
                morada.equals(pessoa.getMorada()) &&
                historico.equals(pessoa.getHistorico());
    }

    public int hashCode() {
        return Long.valueOf(nif).hashCode();
    }

    public void classifica(double classificacao) throws ClassificacaoInvalidException {
        if(classificacao < 0.0 || classificacao > 100.0)
            throw new ClassificacaoInvalidException("Intervalo de classificaçoes e [0.0;100.0], foi dado classificacao de " + classificacao);
            
        long nAtual = this.nClassificacoes;
        double clAtual = this.classificacao;
        this.classificacao = ((nAtual*clAtual + classificacao) / (nAtual + 1));
        this.nClassificacoes++;
    }
    /**
     * Implementação do método toString de uma Pessoa.
     *
     * @return String.
     */
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pessoa{");
        sb.append("nif=").append(nif);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", morada=").append(morada);
        sb.append(", historico=").append(historico.toString());
        sb.append(", nClassificacoes=").append(nClassificacoes);
        sb.append(", classificao=").append(classificacao);
        sb.append('}');
        return sb.toString();
    }
    /**
     * Implementação do método Clone de uma Pessoa.
     *
     * @return Objecto do tipo Pessoa.
     */
    public abstract Pessoa clone();
}
