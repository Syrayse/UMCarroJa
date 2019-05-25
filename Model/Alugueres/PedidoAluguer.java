package Model.Alugueres;

import Model.Localizacao;

import java.io.Serializable;
/**
 * Classe PedidoAluguer
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class PedidoAluguer implements Serializable {
    private String nifCliente;
    private String idVeiculo;
    private Localizacao origem;
    private Localizacao destino;
    private Localizacao localizaoCliente;
    private double custoEstimado;
    private double tempoChegar;
    private double velocidade;
    
    /**
     * Construtor vazio para objetos da classe PedidoAluguer
     */
    public PedidoAluguer() {
        nifCliente = "";
        idVeiculo = "";
        origem = new Localizacao();
        destino = new Localizacao();
        localizaoCliente = new Localizacao();
        custoEstimado = 0.0;
        tempoChegar = 0.0;
        velocidade = 0.0;
    }
    /**
     * Construtor parametrizado para objetos da classe PedidoAluguer
     */
    public PedidoAluguer(String nifCliente, String idVeiculo, Localizacao origem, Localizacao destino, Localizacao localizaoCliente, double custoEstimado, double tempoChegar, double velocidade) {
        this.nifCliente = nifCliente;
        this.idVeiculo = idVeiculo;
        this.origem = origem.clone();
        this.destino = destino.clone();
        this.localizaoCliente = localizaoCliente.clone();
        this.custoEstimado = custoEstimado;
        this.tempoChegar = tempoChegar;
        this.velocidade = velocidade;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public Localizacao getOrigem() {
        return origem.clone();
    }

    public Localizacao getDestino() {
        return destino.clone();
    }

    public Localizacao getLocalizaoCliente() {
        return localizaoCliente.clone();
    }
    
    public double getCustoEstimado() {
        return custoEstimado;
    }
    
    public double getTempoChegar() {
        return tempoChegar;
    }
    
    public double getVelocidade() {
        return velocidade;
    }
    
    /**
     * Implementação do método toString de um PedidoAluguer.
     *
     * @return String.
     */
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("nifCliente=").append(nifCliente);
        sb.append(", idVeiculo=").append(idVeiculo);
        sb.append(", origem=").append(origem.toString());
        sb.append(", destino=").append(destino.toString());
        sb.append(", localizaoCliente=").append(localizaoCliente.toString());
        sb.append(", custoEstimado=").append(custoEstimado);
        sb.append(", tempoChegar=").append(tempoChegar);
        sb.append(", velocidade=").append(velocidade);
        return sb.toString();
    }
    
    /**
     * Implementação do método equals de um PedidoAluguer.
     *
     * @return Boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoAluguer that = (PedidoAluguer) o;
        return idVeiculo.equals(that.getIdVeiculo()) &&
                nifCliente.equals(that.getNifCliente()) &&
                origem.equals(that.getOrigem()) &&
                destino.equals(that.getDestino()) &&
                localizaoCliente.equals(that.getLocalizaoCliente()) &&
                custoEstimado == that.getCustoEstimado() &&
                tempoChegar == that.getTempoChegar() &&
                velocidade == that.getVelocidade();
    }

}
