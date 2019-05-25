package Comparators;

import Model.Users.Pessoa;
import Model.Alugueres.HistoricoAluguer;

import java.util.Comparator;

public class CompMelhorPessoa implements Comparator<Pessoa>
{
    public int compare(Pessoa p1, Pessoa p2) {
        int r;
        HistoricoAluguer h1, h2;
        h1 = p1.getHistorico();
        h2 = p2.getHistorico();
        
        r = (-1) * Integer.compare(h1.size(), h2.size());
        
        if(r == 0)
            r = (-1) * Double.compare(h1.getNumKms(), h2.getNumKms());
            
        return r;
    }
}
