package Model.Veiculos;

import Model.Interfaces.Classificavel;
import Model.Alugueres.*;
import Model.Localizacao;
import Exceptions.*;

import java.io.Serializable;
import java.util.Random;
/**
 * Classe Veiculo
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public abstract class Veiculo implements Classificavel, Serializable {

    private static final double PROB_DE_ACIDENTE = 0.01;
    
    private String nifDono;
    private boolean disponivel;
    private String tipo;
    private String marca;
    private String matricula;
    private double velocidadeAv;
    private double precoPorKm;
    private long nClassificacoes;
    private double classificacao;
    private Localizacao localizao;
    private HistoricoAluguer historico;
    /**
     * Construtor vazio para objetos da classe Veiculo
     */
    public Veiculo() {
        nifDono = "";
        disponivel = false;
        tipo = "";
        marca = "";
        matricula = "";
        velocidadeAv = -1.0;
        precoPorKm = -1.0;
        nClassificacoes = -1;
        classificacao = -1.0;
        localizao = new Localizacao();
        historico = new HistoricoAluguer();
    }
    /**
     * Construtor parametrizado para objetos da classe Veiculo
     */
    public Veiculo(String nif, String tipo, String marca, String matricula, double velocidadeAv, double precoPorKm, double x, double y) {
        this.nifDono = nif;
        disponivel = true;
        this.tipo = tipo;
        this.marca = marca;
        this.matricula = matricula;
        this.velocidadeAv = velocidadeAv;
        this.precoPorKm = precoPorKm;
        nClassificacoes = 0;
        classificacao = 100;
        localizao = new Localizacao(x, y);
        historico = new HistoricoAluguer();
    }
    /**
     * Construtor de cópia para objetos da classe Veiculo
     */
    public Veiculo(Veiculo veiculo) {
        nifDono = veiculo.getNifDono();
        disponivel = veiculo.estaDisponivel();
        tipo = veiculo.getTipo();
        marca = veiculo.getMarca();
        matricula = veiculo.getMatricula();
        velocidadeAv = veiculo.getVelocidadeAv();
        precoPorKm = veiculo.getPrecoPorKm();
        nClassificacoes = veiculo.getnClassificacoes();
        classificacao = veiculo.getClassificacao();
        localizao = veiculo.getLocalizacao();
        historico = veiculo.getHistorico();
    }
    
    public String getNifDono() {
        return nifDono;
    }
    
    public boolean estaDisponivel() {
        return disponivel;
    }

    public void indisponivel() {
        disponivel = false;
    }

    public void disponivel() {
        disponivel = true;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getVelocidadeAv() {
        return velocidadeAv;
    }

    public double getPrecoPorKm() {
        return precoPorKm;
    }
    
    public void setPrecoPorKm(double precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    public long getnClassificacoes() {
        return nClassificacoes;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public Localizacao getLocalizacao() {
        return localizao.clone();
    }

    public void setLocalizacao(Localizacao l) {
        this.localizao = l.clone();
    }
    
    public HistoricoAluguer getHistorico() {
        return historico.clone();
    }

    public void addAluguer(Aluguer al) {
        historico.addAluguer(al);
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
     * Implementação do método equals de um Veiculo
     *
     * @return Boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return  nifDono.equals(veiculo.getNifDono()) &&
                tipo.equals(veiculo.getTipo()) &&
                marca.equals(veiculo.getMarca()) &&
                matricula.equals(veiculo.getMatricula()) &&
                Double.compare(velocidadeAv, veiculo.getVelocidadeAv()) == 0 &&
                Double.compare(precoPorKm, veiculo.getPrecoPorKm()) == 0 &&
                nClassificacoes == veiculo.getnClassificacoes() &&
                Double.compare(classificacao, veiculo.getClassificacao()) == 0 &&
                localizao.equals(veiculo.getLocalizacao()) &&
                historico.equals(veiculo.getHistorico());
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
    /**
     * Implementação do método toString de um Veiculo.
     *
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tipo=").append(tipo);
        sb.append(", marca=").append(marca);
        sb.append(", matricula=").append(matricula);
        sb.append(", velocidadeAv=").append(velocidadeAv);
        sb.append(", precoPorKm=").append(precoPorKm);
        sb.append(", nClassificacoes=").append(nClassificacoes);
        sb.append(", classificacao=").append(classificacao);
        sb.append(", ").append(localizao.toString());
        return sb.toString();
    }
    
    public double viagem(Localizacao newLoc) throws AcidenteOcorreuException, AutonomiaInsuficienteException {
        double dist = localizao.getDistancia(newLoc);
        double prob = Veiculo.PROB_DE_ACIDENTE;
        
        if(new Random().nextInt(101) *  prob >= prob*100)
            throw new AcidenteOcorreuException("Um acidente ocorreu ao viajar no carro com a matricula " +  matricula);
        
        this.setLocalizacao(newLoc);
        
        return dist;
    }

    public void move(double x, double y) {
        localizao.move(x,y);
    }

    public abstract Veiculo clone();
}
