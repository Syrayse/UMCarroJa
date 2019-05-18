import java.io.Serializable;

public class PessoaInvalidException extends Exception implements Serializable {

    public PessoaInvalidException(String message) {
        super(message);
    }

}