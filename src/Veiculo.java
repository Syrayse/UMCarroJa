import java.util.List;
import java.util.stream.Collectors;

public class Veiculo {

    private Proprietario proprietario;
    private Localizacao posicao;
    private double velocidade;
    private double pricePorKM;
    private double classificacao;
    private List<Aluguer> historico;

    public Veiculo(Proprietario proprietario, Localizacao posicao, double velocidade, double pricePorKM, double classificacao) {
        this.proprietario = proprietario.clone();
        this.posicao = posicao.clone();
        this.velocidade = velocidade;
        this.pricePorKM = pricePorKM;
        this.classificacao = classificacao;
    }

    public Veiculo(Veiculo veiculo) {
        this.proprietario = veiculo.getProprietario();
        this.posicao = veiculo.getPosicao();
        this.velocidade = veiculo.getVelocidade();
        this.pricePorKM = veiculo.getPricePorKM();
        this.classificacao = veiculo.getClassificacao();
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario.clone();
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

    public List<Aluguer> getHistorico() {
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setHistorico(List<Aluguer> historico) {
        this.historico = historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Veiculo{");
        sb.append("proprietario=").append(proprietario.toString());
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
                this.proprietario.equals(veiculo.getProprietario()) &&
                this.posicao.equals(veiculo.getPosicao()) &&
                this.historico.equals(veiculo.getHistorico());
    }

}
