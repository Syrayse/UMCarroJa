import java.time.LocalDateTime;
import java.util.Objects;

public class Cliente extends Pessoa {

    private Localizacao localizacao;
    private double destreza;
    private double numeroTotalKmPercorridos;

    public Cliente(long id, String mail, String nome, String password, String morada, LocalDateTime dataNascimento, Localizacao localizacao) {
        super(id, mail, nome, password, morada, dataNascimento);
        this.localizacao = localizacao;
        this.destreza = 100.0;
        this.numeroTotalKmPercorridos = 0.0;
    }

    public Cliente(Cliente cliente) {
        super(cliente);
        this.localizacao = cliente.getLocalizacao();
        this.destreza = cliente.getDestreza();
        this.numeroTotalKmPercorridos = cliente.getNumeroTotalKmPercorridos();
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public double getDestreza() {
        return destreza;
    }

    public double getNumeroTotalKmPercorridos() {
        return numeroTotalKmPercorridos;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cliente{");
        sb.append("info=").append(super.toString());
        sb.append(", localizacao=").append(localizacao.toString());
        sb.append(", destreza=").append(destreza);
        sb.append(", numeroTotalKmPercorridos=").append(numeroTotalKmPercorridos);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return super.equals(cliente) &&
                Double.compare(cliente.getDestreza(), this.destreza) == 0 &&
                Double.compare(cliente.getNumeroTotalKmPercorridos(), this.numeroTotalKmPercorridos) == 0 &&
                this.localizacao.equals(cliente.getLocalizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocalizacao(), getDestreza(), getNumeroTotalKmPercorridos());
    }

    @Override
    public Cliente clone() {
        return new Cliente(this);
    }
}
