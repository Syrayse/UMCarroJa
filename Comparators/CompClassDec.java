package Comparators;

import Model.Veiculos.Veiculo;

import java.util.Comparator;

public class CompClassDec implements Comparator<Veiculo>
{
    public int compare(Veiculo v1, Veiculo v2) {
        double c1 = v1.getnClassificacoes() == 0 ? 0.0 : 1.0;
        double c2 = v2.getnClassificacoes() == 0 ? 0.0 : 1.0;
        
        return (-1)*Double.compare(c1*v1.getClassificacao(), c2*v2.getClassificacao());
    }
}
