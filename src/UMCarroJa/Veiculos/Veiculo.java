package UMCarroJa.Veiculos;

import UMCarroJa.Aluguer.Aluguer;
import UMCarroJa.Aluguer.PedidoAluguer;
import UMCarroJa.lib.Localizacao;
import UMCarroJa.PublicInfo.InfoPublicaVeiculo;
import UMCarroJa.Interfaces.Classificavel;
import UMCarroJa.Aluguer.HistoricoAluguer;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class Veiculo implements Classificavel
{

    InfoPublicaVeiculo info;
    private long nClassificacoes;
    private double precoPorKM;
    private double fiabilidade;
    private double classificacao;
    private boolean ocupacao;
    private Localizacao localizao;
    private HistoricoAluguer historico;
    private boolean temEspera;
    private Queue<PedidoAluguer> filaEspera;

    public Veiculo() {
        info = new InfoPublicaVeiculo();
        nClassificacoes = 0;
        precoPorKM = -1.0;
        fiabilidade = -1.0;
        classificacao = -1.0;
        ocupacao = false;
        localizao = new Localizacao();
        historico = new HistoricoAluguer();
        filaEspera = new LinkedList<>();
    }

    public Veiculo(long idVeiculo, long idProprietario, double velocidadePorKM,
                   String matricula, String marca, String modelo, double precoPorKM,
                   Localizacao localizacao, boolean temEspera) {
        this.info = new InfoPublicaVeiculo(idVeiculo,idProprietario,velocidadePorKM,matricula,marca,modelo);
        this.nClassificacoes = 0;
        this.precoPorKM = precoPorKM;
        this.fiabilidade = 100;
        this.classificacao = 100;
        this.ocupacao = false;
        this.localizao = localizacao.clone();
        this.historico = new HistoricoAluguer();
        this.temEspera = temEspera;
        this.filaEspera = (temEspera) ? new LinkedList<>() : null;
    }

    private Veiculo(Veiculo veiculo) {
        info = veiculo.getInfo();
        nClassificacoes = veiculo.getnClassificacoes();
        precoPorKM = veiculo.getPrecoPorKM();
        ocupacao = veiculo.isOcupado();
        localizao = veiculo.getLocalizao();
        historico = veiculo.getHistorico();
        temEspera = veiculo.isTemEspera();
        filaEspera = veiculo.getFilaEspera();
    }

    public InfoPublicaVeiculo getInfo() {
        return info;
    }

    public long getnClassificacoes() {
        return nClassificacoes;
    }

    public double getPrecoPorKM() {
        return precoPorKM;
    }

    public double getFiabilidade() {
        return fiabilidade;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public boolean isOcupado() {
        return ocupacao;
    }

    public Localizacao getLocalizao() {
        return localizao.clone();
    }

    private HistoricoAluguer getHistorico() {
        return historico.clone();
    }

    public boolean isTemEspera() {
        return temEspera;
    }

    private Queue<PedidoAluguer> getFilaEspera() {
        Queue<PedidoAluguer> q =  (temEspera) ? new LinkedList<>() : null;
        if(q != null)
            filaEspera.stream().map(q::add);
        return q;
    }

    public void classifica(double classificacao) {
        long nAtual = this.nClassificacoes;
        double clAtual = this.classificacao;
        this.classificacao = ((nAtual*clAtual + classificacao) / (nAtual + 1));
        this.nClassificacoes++;
    }

    public Aluguer aluga(PedidoAluguer pd) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return this.info.equals(veiculo.getInfo()) &&
                this.nClassificacoes == veiculo.getnClassificacoes() &&
                this.precoPorKM == veiculo.getPrecoPorKM() &&
                this.fiabilidade == veiculo.getFiabilidade() &&
                this.classificacao == veiculo.getClassificacao() &&
                this.ocupacao == veiculo.isOcupado() &&
                this.localizao.equals(veiculo.getLocalizao()) &&
                this.historico.equals(veiculo.getHistorico()) &&
                this.temEspera == veiculo.isTemEspera() &&
                (this.temEspera && this.filaEspera.equals(veiculo.getFilaEspera()));
    }

}
