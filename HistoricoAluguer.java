import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 * Classe HistoricoAluguer.
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class HistoricoAluguer implements Serializable {

    private Set<Aluguer> historico;
    /**
     * Construtor vazio para objetos da classe HistoricoAluguer
     */
    public HistoricoAluguer() {
        historico = new TreeSet<>();
    }
    /**
     * Construtor Parametrizado para objetos da classe HistoricoAluguer
     */
    public HistoricoAluguer(Set<Aluguer> historico) {
        this.historico = new TreeSet<>(historico);
    }
    /**
     * Construtor de cópia para objetos da classe HistoricoAluguer
     */
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

    public int size() {
        return historico.size();
    }
    /**
     * Implementação do método Clone de um HistoricoAluguer.
     *
     * @return Objecto do tipo HistoricoAluguer.
     */
    public HistoricoAluguer clone() {
        return new HistoricoAluguer(this);
    }
    /**
     * Implementação do método equals de um  HistoricoAluguer.
     *
     * @return Boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoAluguer that = (HistoricoAluguer) o;
        return this.historico.equals(that.getHistorico());
    }
    /**
     * Implementação do método toString de um HistoricoAluguer.
     *
     * @return String.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HistoricoAluguer{");
        sb.append(historico.toString());
        sb.append('}');
        return sb.toString();
    }
}
