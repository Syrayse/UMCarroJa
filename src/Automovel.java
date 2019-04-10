import java.util.Objects;

public class Automovel extends Veiculo {

    private double consumoPorKM;
    private double capacidadeMaxima;

    public Automovel(Proprietario proprietario, Localizacao posicao, double velocidade, double pricePorKM, double classificacao, double consumoPorKM, double capacidadeMaxima) {
        super(proprietario, posicao, velocidade, pricePorKM, classificacao);
        this.consumoPorKM = consumoPorKM;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Automovel(Automovel automovel) {
        super(automovel.getProprietario(), automovel.getPosicao(), automovel.getVelocidade(), automovel.getPricePorKM(), automovel.getClassificacao());
        this.consumoPorKM = automovel.getConsumoPorKM();
        this.capacidadeMaxima = automovel.getCapacidadeMaxima();
    }

    public double getConsumoPorKM() {
        return consumoPorKM;
    }

    public void setConsumoPorKM(double consumoPorKM) {
        this.consumoPorKM = consumoPorKM;
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Automovel{");
        sb.append(super.toString());
        sb.append("consumoPorKM=").append(consumoPorKM);
        sb.append(", capacidadeMaxima=").append(capacidadeMaxima);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Automovel clone() {
        return new Automovel((this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Automovel automovel = (Automovel) o;
        return Double.compare(automovel.getConsumoPorKM(), getConsumoPorKM()) == 0 &&
                Double.compare(automovel.getCapacidadeMaxima(), getCapacidadeMaxima()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getConsumoPorKM(), getCapacidadeMaxima());
    }
}
