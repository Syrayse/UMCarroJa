package UMCarroJa.Comparators;

import UMCarroJa.Veiculos.Veiculo;

import java.util.Comparator;

public class VeiculoCostComparator implements Comparator<Veiculo> {

    @Override
    public int compare(Veiculo v1, Veiculo v2) {
        return Double.compare(v1.getPrecoPorKM(),v2.getPrecoPorKM());
    }

}
