import java.util.Comparator;
public class CompVeiculoProx implements Comparator<Veiculo> {

    private Localizacao spot;
    
    public CompVeiculoProx() {
        super();
        spot = new Localizacao();
    }
    
    public CompVeiculoProx(Localizacao l) {
        super();
        spot = l;
    }
    
    public int compare(Veiculo v1, Veiculo v2) {
        double d1 = v1.getLocalizacao().getDistancia(spot);
        double d2 = v2.getLocalizacao().getDistancia(spot);
        return Double.compare(d1, d2);
    }
    
}