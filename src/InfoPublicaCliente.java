import java.time.LocalDate;

public class InfoPublicaCliente extends InfoPublicaPessoa
{
    public InfoPublicaCliente() {
        super();
    }

    public InfoPublicaCliente(long id, String email, String nome, LocalDate dataNascimento) {
        super(id, email,nome,dataNascimento);
    }
}
