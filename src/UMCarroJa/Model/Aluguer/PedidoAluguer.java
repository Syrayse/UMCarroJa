package UMCarroJa.Aluguer;

import UMCarroJa.PublicInfo.InfoPublicaPessoa;
import UMCarroJa.lib.Localizacao;

public class PedidoAluguer
{
    private InfoPublicaPessoa informacaoCliente;
    private long idVeiculo;
    private Localizacao origem;
    private Localizacao destino;
    private Localizacao localizaoCliente;

    public PedidoAluguer() {
        informacaoCliente = new InfoPublicaPessoa();
        idVeiculo = -1;
        origem = new Localizacao();
        destino = new Localizacao();
        localizaoCliente = new Localizacao();
    }

    public PedidoAluguer(InfoPublicaPessoa informacaoCliente, long idVeiculo, Localizacao origem, Localizacao destino, Localizacao localizaoCliente) {
        this.informacaoCliente = informacaoCliente.clone();
        this.idVeiculo = idVeiculo;
        this.origem = origem.clone();
        this.destino = destino.clone();
        this.localizaoCliente = localizaoCliente.clone();
    }

    public PedidoAluguer(PedidoAluguer pedido) {
        informacaoCliente = pedido.getInformacaoCliente();
        idVeiculo = pedido.getIdVeiculo();
        origem = pedido.getOrigem();
        destino = pedido.getDestino();
        localizaoCliente = pedido.getLocalizaoCliente();
    }

    public InfoPublicaPessoa getInformacaoCliente() {
        return informacaoCliente.clone();
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
        sb.append("informacaoCliente=").append(informacaoCliente.toString());
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
                informacaoCliente.equals(that.getInformacaoCliente()) &&
                origem.equals(that.getOrigem()) &&
                destino.equals(that.getDestino()) &&
                localizaoCliente.equals(that.getLocalizaoCliente());
    }

}
