import java.time.LocalDateTime;

public class Aluguer implements Comparable<Aluguer> {

    private String clienteEmail;
    private String proprietarioEmail;
    private int distanciaPercorrida;
    private LocalDateTime data;
    private double totalPago;
    private long idVeiculo;

    public Aluguer() {
        this.clienteEmail = "";
        this.proprietarioEmail = "";
        this.distanciaPercorrida = 0;
        this.data = LocalDateTime.now();
        this.totalPago = 0.0;
        this.idVeiculo = -1;
    }

    public Aluguer(LocalDateTime data) {
        this.clienteEmail = "";
        this.proprietarioEmail = "";
        this.distanciaPercorrida = 0;
        this.data = data;
        this.totalPago = 0.0;
        this.idVeiculo = -1;
    }


    public Aluguer(String clienteEmail, String proprietarioEmail, int distanciaPercorrida, LocalDateTime data, double totalPago, long idVeiculo) {
        this.clienteEmail = clienteEmail;
        this.proprietarioEmail = proprietarioEmail;
        this.distanciaPercorrida = distanciaPercorrida;
        this.data = data;
        this.totalPago = totalPago;
        this.idVeiculo = idVeiculo;
    }

    public Aluguer(Aluguer aluguer) {
        this.clienteEmail = aluguer.getClienteEmail();
        this.proprietarioEmail = aluguer.getProprietarioEmail();
        this.distanciaPercorrida = aluguer.getDistanciaPercorrida();
        this.data = aluguer.getData();
        this.totalPago = aluguer.getTotalPago();
        this.idVeiculo = aluguer.getIdVeiculo();
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public String getProprietarioEmail() {
        return proprietarioEmail;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public Aluguer clone() {
        return new Aluguer(this);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Aluguer{");
        sb.append("clienteEmail='").append(clienteEmail).append('\'');
        sb.append(", proprietarioEmail='").append(proprietarioEmail).append('\'');
        sb.append(", distanciaPercorrida=").append(distanciaPercorrida);
        sb.append(", data=").append(data.toString());
        sb.append(", totalPago=").append(totalPago);
        sb.append(", idVeiculo=").append(idVeiculo);
        sb.append('}');
        return sb.toString();
    }

    public int compareTo(Aluguer outroAluguer) {
        return this.data.compareTo(outroAluguer.getData());
    }
}