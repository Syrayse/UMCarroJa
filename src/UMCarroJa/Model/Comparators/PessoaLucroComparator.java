package UMCarroJa.Comparators;

import UMCarroJa.Users.Pessoa;

import java.util.Comparator;

public class PessoaLucroComparator implements Comparator<Pessoa> {

    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        return Double.compare(p1.getLucro(),p2.getLucro());
    }

}
