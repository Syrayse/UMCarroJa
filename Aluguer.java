import java.time.LocalDateTime;

/**
 * Classe Aluguer.
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class Aluguer implements Comparable<Aluguer> {

    private long idCliente;
    private long idProprietario;
    private long idVeiculo;
    private double distanciaPercorrida;
    private double totalPago;
    private LocalDateTime data;
    /**
     * Construtor vazio para objetos da classe Aluguer
     */
    public Aluguer() {
        idCliente = -1;
        idProprietario = -1;
        idVeiculo = -1;
        distanciaPercorrida = 0.0;
        totalPago = 0.0;
        data = LocalDateTime.now();
    }
    /**
     * Construtor de Parametro data para objetos da classe Aluguer
     */
    public Aluguer(LocalDateTime data) {
        idCliente = -1;
        idProprietario = -1;
        idVeiculo = -1;
        distanciaPercorrida = 0.0;
        totalPago = 0.0;
        this.data = data;
    }
    /**
     * Construtor Parametrizado para objetos da classe Aluguer
     */
    public Aluguer(long idCliente, long idProprietario, long idVeiculo,
                double distanciaPercorrida, double totalPago, LocalDateTime data) {
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.idVeiculo = idVeiculo;
        this.distanciaPercorrida = distanciaPercorrida;
        this.totalPago = totalPago;
        this.data = data;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public long getIdProprietario() {
        return idProprietario;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public LocalDateTime getData() {
        return data;
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
                this.data.equals(aluguer.getData());
    }

    public int compareTo(Aluguer outroAluguer) {
        int result = this.data.compareTo(outroAluguer.getData());

        if(result == 0)
            result = this.equals(outroAluguer) ? 0 : 1;

        return result;

    }
}
