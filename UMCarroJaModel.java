import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class UMCarroJaModel implements Serializable {

    private String login;
    private Map<String, Cliente> clientes;
    private Map<String, Proprietario> proprietarios;
    private Map<Integer, Veiculo> veiculos;

    public UMCarroJaModel createData() {
        return null;
    }
    
    public UMCarroJaModel() {
        login = "";
        clientes = new HashMap<>();
        proprietarios = new HashMap<>();
        veiculos = new HashMap<>();
    }
    
    public void loginProprietario(String nif, String password) throws PessoaInvalidException {
        if(!this.proprietarios.containsKey(nif))
            throw new PessoaInvalidException("Nao existe nenhum proprietario com o nif " + nif);
        
        if(this.proprietarios.get(nif).getPassword().equals(password))
            this.login = nif;
        else
            throw new PessoaInvalidException("A password que inseriu para o proprietario " + nif + " esta errada");
    }

    public void loginCliente(String nif, String password) throws PessoaInvalidException {
        if(!this.clientes.containsKey(nif))
            throw new PessoaInvalidException("Nao existe nenhum cliente com o nif " + nif);
        
        if(this.clientes.get(nif).getPassword().equals(password))
            this.login = nif;
        else
            throw new PessoaInvalidException("A password que inseriu para o cliente " + nif + " esta errada");
    }
    
    public void registarProprietario(String nome, String nif, String email, String morada, String password) throws PessoaInvalidException {
        if(this.proprietarios.containsKey(nif))
            throw new PessoaInvalidException("O proprietario com o nif " + nif + " ja se encontra no sistema");
        proprietarios.put(nif, new Proprietario(nif, email, nome, password, morada));
    }
    
    public void registarCliente(String nome, String nif, String email, String morada, double x, double y, String password) throws PessoaInvalidException {
        if(this.clientes.containsKey(nif))
            throw new PessoaInvalidException("O cliente com o nif " + nif + " ja se encontra no sistema");
        clientes.put(nif, new Cliente(nif, email, nome, password, morada, x, y));                
    }
    
    public void logoff() {
        this.login = "";
    }
}
