package UMCarroJa.Model.Aluguer;

import UMCarroJa.Model.lib.Localizacao;

import java.io.Serializable;

public class PedidoAluguer implements Serializable {
    private long nifCliente;
    private long idVeiculo;
    private Localizacao origem;
    private Localizacao destino;
    private Localizacao localizaoCliente;

    public PedidoAluguer() {
        nifCliente = -1;
        idVeiculo = -1;
        origem = new Localizacao();
        destino = new Localizacao();
        localizaoCliente = new Localizacao();
    }

    public PedidoAluguer(long nifCliente, long idVeiculo, Localizacao origem, Localizacao destino, Localizacao localizaoCliente) {
        this.nifCliente = nifCliente;
        this.idVeiculo = idVeiculo;
        this.origem = origem.clone();
        this.destino = destino.clone();
        this.localizaoCliente = localizaoCliente.clone();
    }

    public PedidoAluguer(PedidoAluguer pedido) {
        nifCliente = pedido.getNifCliente();
        idVeiculo = pedido.getIdVeiculo();
        origem = pedido.getOrigem();
        destino = pedido.getDestino();
        localizaoCliente = pedido.getLocalizaoCliente();
    }

    public long getNifCliente() {
        return nifCliente;
    }

    public long getIdVeiculo() {
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

    public String toString() {
        final StringBuffer sb = new StringBuffer("PedidoAluguer{");
        sb.append("nifCliente=").append(nifCliente);
        sb.append(", idVeiculo=").append(idVeiculo);
        sb.append(", origem=").append(origem.toString());
        sb.append(", destino=").append(destino.toString());
        sb.append(", localizaoCliente=").append(localizaoCliente.toString());
        sb.append('}');
        return sb.toString();
    }

    public PedidoAluguer clone() {
        return new PedidoAluguer(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoAluguer that = (PedidoAluguer) o;
        return idVeiculo == that.getIdVeiculo() &&
                nifCliente == that.getNifCliente() &&
                origem.equals(that.getOrigem()) &&
                destino.equals(that.getDestino()) &&
                localizaoCliente.equals(that.getLocalizaoCliente());
    }

}
