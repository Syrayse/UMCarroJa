package UMCarroJa.Model.Comparators;

import UMCarroJa.Model.Veiculos.Veiculo;

import java.io.Serializable;
import java.util.Comparator;

public class VeiculoCostComparator implements Serializable, Comparator<Veiculo> {

    @Override
    public int compare(Veiculo v1, Veiculo v2) {
        return Double.compare(v1.getPrecoPorKM(),v2.getPrecoPorKM());
    }

}
