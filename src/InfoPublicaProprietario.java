import java.time.LocalDate;

public class InfoPublicaProprietario extends InfoPublicaPessoa
{
    public InfoPublicaProprietario() {
        super();
    }

    public InfoPublicaProprietario(long id, String email, String nome, LocalDate dataNascimento) {
        super(id, email,nome,dataNascimento);
    }
}
