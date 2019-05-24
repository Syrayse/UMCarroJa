import java.util.Comparator;

public class CompAutonomiaDec implements Comparator<Veiculo>
{
    public int compare(Veiculo v1, Veiculo v2) {
        int r = 0;
        
        if(v1 instanceof Carro && v2 instanceof Carro)
            r = (-1) * Double.compare(((Carro)v1).getAutonomia(), ((Carro)v2).getAutonomia());
    
        return r;
    }

}