import java.io.Serializable;

public abstract class Veiculo implements Classificavel, Serializable {

    private String tipo;
    private String marca;
    private String matricula;
    private double velocidadeAv;
    private double precoPorKm;
    private double consumoPorKm;
    private double autonomia;
    private long nClassificacoes;
    private double classificacao;
    private Localizacao localizao;
    private HistoricoAluguer historico;

    public Veiculo() {
        tipo = "";
        marca = "";
        matricula = "";
        velocidadeAv = -1.0;
        precoPorKm = -1.0;
        consumoPorKm = -1.0;
        autonomia = -1.0;
        nClassificacoes = -1;
        classificacao = -1.0;
        localizao = new Localizacao();
        historico = new HistoricoAluguer();
    }

    public Veiculo(String tipo, String marca, String matricula, double velocidadeAv, double precoPorKm, double consumoPorKm, double autonomia, double x, double y) {
        this.tipo = tipo;
        this.marca = marca;
        this.matricula = matricula;
        this.velocidadeAv = velocidadeAv;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        this.autonomia = autonomia;
        nClassificacoes = 0;
        classificacao = 100;
        localizao = new Localizacao(x, y);
        historico = new HistoricoAluguer();
    }

    public Veiculo(Veiculo veiculo) {
        tipo = veiculo.getTipo();
        marca = veiculo.getMarca();
        matricula = veiculo.getMatricula();
        velocidadeAv = veiculo.getVelocidadeAv();
        precoPorKm = veiculo.getPrecoPorKm();
        consumoPorKm = veiculo.getPrecoPorKm();
        autonomia = veiculo.getAutonomia();
        nClassificacoes = veiculo.getnClassificacoes();
        classificacao = veiculo.getClassificacao();
        localizao = veiculo.getLocalizacao();
        historico = veiculo.getHistorico();
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getVelocidadeAv() {
        return velocidadeAv;
    }

    public double getPrecoPorKm() {
        return precoPorKm;
    }
    
    public void setPrecoPorKm(double precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    public double getConsumoPorKm() {
        return consumoPorKm;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public long getnClassificacoes() {
        return nClassificacoes;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public Localizacao getLocalizacao() {
        return localizao.clone();
    }

    public HistoricoAluguer getHistorico() {
        return historico.clone();
    }

    public void classifica(double classificacao) {
        long nAtual = this.nClassificacoes;
        double clAtual = this.classificacao;
        this.classificacao = ((nAtual*clAtual + classificacao) / (nAtual + 1));
        this.nClassificacoes++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return tipo.equals(veiculo.getTipo()) &&
                marca.equals(veiculo.getMarca()) &&
                matricula.equals(veiculo.getMatricula()) &&
                Double.compare(velocidadeAv, veiculo.getVelocidadeAv()) == 0 &&
                Double.compare(precoPorKm, veiculo.getPrecoPorKm()) == 0 &&
                Double.compare(consumoPorKm, veiculo.getConsumoPorKm()) == 0 &&
                Double.compare(autonomia, veiculo.getAutonomia()) == 0 &&
                nClassificacoes == veiculo.getnClassificacoes() &&
                Double.compare(classificacao, veiculo.getClassificacao()) == 0 &&
                localizao.equals(veiculo.getLocalizacao()) &&
                historico.equals(veiculo.getHistorico());
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Veiculo{");
        sb.append("tipo=").append(tipo);
        sb.append(", marca=").append(marca);
        sb.append(", matricula=").append(matricula);
        sb.append(", velocidadeAv=").append(velocidadeAv);
        sb.append(", precoPorKm=").append(precoPorKm);
        sb.append(", consumoPorKm=").append(consumoPorKm);
        sb.append(", autonomia=").append(autonomia);
        sb.append(", nClassificacoes=").append(nClassificacoes);
        sb.append(", classificacao=").append(classificacao);
        sb.append(", ").append(localizao.toString());
        sb.append(", ").append(historico.toString());
        sb.append('}');
        return sb.toString();
    }
}
