package UMCarroJa.Comparators;

import UMCarroJa.Users.Pessoa;

import java.util.Comparator;

public class PessoaAlugueresComparator implements Comparator<Pessoa> {

    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        return Integer.compare(p1.getNAlugueres(),p2.getNAlugueres());
    }

}
