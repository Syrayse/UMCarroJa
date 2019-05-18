package UMCarroJa.Model.Veiculos;

import UMCarroJa.Model.Aluguer.Aluguer;
import UMCarroJa.Model.Aluguer.HistoricoAluguer;
import UMCarroJa.Model.Aluguer.PedidoAluguer;
import UMCarroJa.Model.Interfaces.Classificavel;
import UMCarroJa.Model.PublicInfo.InfoPublicaVeiculo;
import UMCarroJa.Model.lib.Localizacao;

import java.io.Serializable;

public abstract class Veiculo implements Serializable, Classificavel {

    private InfoPublicaVeiculo info;
    private long nClassificacoes;
    private double precoPorKM;
    private double fiabilidade;
    private double classificacao;
    private boolean ocupacao;
    private Localizacao localizao;
    private HistoricoAluguer historico;

    public Veiculo() {
        info = new InfoPublicaVeiculo();
        nClassificacoes = 0;
        precoPorKM = -1.0;
        fiabilidade = -1.0;
        classificacao = -1.0;
        ocupacao = false;
        localizao = new Localizacao();
        historico = new HistoricoAluguer();
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
    }

    private Veiculo(Veiculo veiculo) {
        info = veiculo.getInfo();
        nClassificacoes = veiculo.getnClassificacoes();
        precoPorKM = veiculo.getPrecoPorKM();
        ocupacao = veiculo.isOcupado();
        localizao = veiculo.getLocalizao();
        historico = veiculo.getHistorico();
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
                this.historico.equals(veiculo.getHistorico());
    }

}
