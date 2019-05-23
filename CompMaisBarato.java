import java.util.Comparator;

public class CompMaisBarato implements Comparator<Veiculo>
{
    public int compare(Veiculo v1, Veiculo v2) {
        return Double.compare(v1.getPrecoPorKm(), v2.getPrecoPorKm());
    }
}