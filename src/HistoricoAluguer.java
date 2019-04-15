import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HistoricoAluguer {

    private Set<Aluguer> historico;

    public HistoricoAluguer() {
        historico = new TreeSet<>();
    }

    public HistoricoAluguer(Set<Aluguer> historico) {
        this.historico = new TreeSet<>(historico);
    }

    public HistoricoAluguer(HistoricoAluguer historico) {
        this.historico = historico.getHistorico();
    }

    public Set<Aluguer> getHistorico() {
        return new TreeSet<>(this.historico);
    }

    public List<Aluguer> getSubSet(LocalDateTime from, LocalDateTime to) {
        Aluguer fromAluguer = new Aluguer(from);
        Aluguer toAluguer = new Aluguer(to);
        return new ArrayList<Aluguer>(((TreeSet<Aluguer>) this.historico).subSet(fromAluguer, toAluguer));
    }

    public void addAluguer(Aluguer aluguer) {
        this.historico.add(aluguer);
    }

    public HistoricoAluguer clone() {
        return new HistoricoAluguer(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoAluguer that = (HistoricoAluguer) o;
        return this.historico.equals(that.getHistorico());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HistoricoAluguer{");
        sb.append("historico=").append(historico.toString());
        sb.append('}');
        return sb.toString();
    }
}
