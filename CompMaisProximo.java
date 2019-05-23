import java.util.Comparator;

public class CompMaisProximo implements Comparator<Veiculo>
{
    private Localizacao loc;
    
    public CompMaisProximo() {
        loc = new Localizacao();
    }
    
    public CompMaisProximo(Localizacao loc) {
        this.loc = loc.clone();
    }
    
    public int compare(Veiculo v1, Veiculo v2) {
        return Double.compare(v1.getLocalizacao().getDistancia(loc), v2.getLocalizacao().getDistancia(loc));
    }
}
