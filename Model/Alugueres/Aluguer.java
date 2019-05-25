package Model.Alugueres;

import Model.Localizacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * Classe Aluguer.
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class Aluguer implements Comparable<Aluguer>, Serializable {

    private String idCliente;
    private String idProprietario;
    private String idVeiculo;
    private double distanciaPercorrida;
    private double totalPago;
    private double tempoUtilizado;
    private LocalDateTime data;
    private Localizacao destino;
    
    /**
     * Construtor vazio para objetos da classe Aluguer
     */
    public Aluguer() {
        idCliente = "";
        idProprietario = "";
        idVeiculo = "";
        distanciaPercorrida = 0.0;
        totalPago = 0.0;
        tempoUtilizado = 0.0;
        data = LocalDateTime.now();
    }
    /**
     * Construtor de Parametro data para objetos da classe Aluguer
     */
    public Aluguer(LocalDate data) {
        idCliente = "";
        idProprietario = "";
        idVeiculo = "";
        distanciaPercorrida = 0.0;
        totalPago = 0.0;
        tempoUtilizado = 0.0;
        this.data = data.atStartOfDay();
    }
    /**
     * Construtor Parametrizado para objetos da classe Aluguer
     */
    public Aluguer(String idCliente, String idProprietario, String idVeiculo,
                double distanciaPercorrida, double totalPago, double tempoUtilizado, Localizacao destino) {
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.idVeiculo = idVeiculo;
        this.distanciaPercorrida = distanciaPercorrida;
        this.totalPago = totalPago;
        this.tempoUtilizado = tempoUtilizado;
        this.data = LocalDateTime.now();
        this.destino = destino.clone();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdProprietario() {
        return idProprietario;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public double getTotalPago() {
        return totalPago;
    }
    
    public double getTempoUtilizado() {
        return tempoUtilizado;
    }

    public LocalDateTime getData() {
        return data;
    }
    
    public Localizacao getDestino() {
        return destino.clone();
    }
    /**
     * Implementação do método toString de um Aluguer.
     *
     * @return String.
     */
    public String toString() {
        final StringBuffer sb = new StringBuffer("Aluguer{");
        sb.append("idCliente='").append(idCliente).append('\'');
        sb.append(", idProprietario='").append(idProprietario).append('\'');
        sb.append(", idVeiculo=").append(idVeiculo);
        sb.append(", distanciaPercorrida=").append(distanciaPercorrida);
        sb.append(", totalPago=").append(totalPago);
        sb.append(", data=").append(data.toString());
        sb.append(", destino=").append(destino.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Implementação do método equals de um Aluguer.
     *
     * @return Boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return this.idCliente == aluguer.getIdCliente() &&
                this.idProprietario == aluguer.getIdProprietario() &&
                this.idVeiculo == aluguer.getIdVeiculo() &&
                Double.compare(aluguer.getDistanciaPercorrida(), this.distanciaPercorrida) == 0 &&
                Double.compare(aluguer.getTotalPago(), this.totalPago) == 0 &&
                this.data.equals(aluguer.getData()) &&
                destino.equals(aluguer.getDestino());
    }

    public int compareTo(Aluguer outroAluguer) {
        int result = this.data.compareTo(outroAluguer.getData());

        if(result == 0)
            result = this.equals(outroAluguer) ? 0 : 1;

        return result;

    }
}
