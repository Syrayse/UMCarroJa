public class Automovel extends Veiculo {

    private double consumoPorKM;
    private double capacidadeMaxima;

    public Automovel(Proprietario proprietario, Localizacao posicao, double velocidade, double pricePorKM, double classificacao, double consumoPorKM, double capacidadeMaxima) {
        super(proprietario, posicao, velocidade, pricePorKM, classificacao);
        this.consumoPorKM = consumoPorKM;
        this.capacidadeMaxima = capacidadeMaxima;
    }
}
