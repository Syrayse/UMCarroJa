package UMCarroJa.Model.Comparators;

import UMCarroJa.Model.Users.Pessoa;

import java.io.Serializable;
import java.util.Comparator;

public class PessoaAlugueresComparator implements Serializable, Comparator<Pessoa> {

    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        return Integer.compare(p1.getNAlugueres(),p2.getNAlugueres());
    }

}
