import java.io.Serializable;
import java.util.Map;

public class UMCarroJaModel implements Serializable {

    private Map<Long, Cliente> clientes;
    private Map<Long, Proprietario> proprietarios;
    private Map<Integer, Veiculo> veiculos;

    public UMCarroJaModel createData() {
        return null;
    }
    
    public void loginProprietario(String username, String password) throws PessoaInvalidException {
    
    }

    public void loginCliente(String username, String password) throws PessoaInvalidException {
    
    }
    
    public void logoff() {
    
    }
}
