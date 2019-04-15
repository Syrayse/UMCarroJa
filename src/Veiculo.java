import java.util.Set;

public class Veiculo {

    private long idVeiculo;
    private long idProprietario;
    private Localizacao posicao;
    private double velocidade;
    private double pricePorKM;
    private double classificacao;
    private HistoricoAluguer historico;

    public Veiculo() {
        idVeiculo = -1;
        idProprietario = -1;
        posicao = new Localizacao();
        velocidade = -1.0;
        pricePorKM = -1.0;
        classificacao = -1.0;
        historico = new HistoricoAluguer();
    }

    public Veiculo(long idVeiculo, long idProprietario, Localizacao posicao, double velocidade, double pricePorKM, double classificacao) {
        this.idVeiculo = idVeiculo;
        this.idProprietario = idProprietario;
        this.posicao = posicao.clone();
        this.velocidade = velocidade;
        this.pricePorKM = pricePorKM;
        this.classificacao = classificacao;
    }

    public Veiculo(Veiculo veiculo) {
        this.idVeiculo = veiculo.getIdVeiculo();
        this.idProprietario = veiculo.getIdProprietario();
        this.posicao = veiculo.getPosicao();
        this.velocidade = veiculo.getVelocidade();
        this.pricePorKM = veiculo.getPricePorKM();
        this.classificacao = veiculo.getClassificacao();
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Localizacao getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Localizacao posicao) {
        this.posicao = posicao.clone();
    }

    public double getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getPricePorKM() {
        return this.pricePorKM;
    }

    public void setPricePorKM(double pricePorKM) {
        this.pricePorKM = pricePorKM;
    }

    public double getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public Set<Aluguer> getHistorico() {
        return this.historico.getHistorico();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Veiculo{");
        sb.append("idVeiculo=").append(idVeiculo);
        sb.append("idProprietario=").append(idProprietario);
        sb.append(", posicao=").append(posicao.toString());
        sb.append(", velocidade=").append(velocidade);
        sb.append(", pricePorKM=").append(pricePorKM);
        sb.append(", classificacao=").append(classificacao);
        sb.append(", historico=").append(historico.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Veiculo clone() {
        return new Veiculo(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Double.compare(veiculo.velocidade, velocidade) == 0 &&
                Double.compare(veiculo.pricePorKM, pricePorKM) == 0 &&
                Double.compare(veiculo.classificacao, classificacao) == 0 &&
                this.idVeiculo == veiculo.getIdVeiculo() &&
                this.idProprietario == veiculo.getIdProprietario() &&
                this.posicao.equals(veiculo.getPosicao()) &&
                this.historico.equals(veiculo.getHistorico());
    }

}
