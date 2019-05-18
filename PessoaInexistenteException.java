import java.io.Serializable;

public class PessoaInexistenteException extends Exception implements Serializable {

    public PessoaInexistenteException(String message) {
        super(message);
    }

}
