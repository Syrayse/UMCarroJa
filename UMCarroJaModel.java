import java.io.Serializable;
import java.util.*;

public class UMCarroJaModel implements Serializable {

    private String login;
    private Map<String, Cliente> clientes;
    private Map<String, Proprietario> proprietarios;
    private Map<String, Veiculo> veiculos;
    
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
    
    public void alteraPasswordP(String novaPass) {
        getP().setPassword(novaPass);
    }

    public void alteraPasswordC(String novaPass) {
        this.clientes.get(login).setPassword(novaPass);
    }    
    
    public void alteraMoradaP(String novaMorada) {
        getP().setMorada(novaMorada);
    }
    
    public void alteraMoradaC(String novaMorada) {
        this.clientes.get(login).setMorada(novaMorada);
    }
    
    public void logoff() {
        this.login = "";
    }
    
    public void alteraPrecoPorKm(String matricula, double preco) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        this.veiculos.get(matricula).setPrecoPorKm(preco);
    }
    
    public void removeVeiculo(String matricula) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        this.getP().removeVeiculo(matricula);
        this.veiculos.remove(matricula);
        
    }
    
    public double indicaClassificacaoP() {
        return getP().getClassificacao();
    }
    
    public double indicaClassificacaoC() {
        return this.clientes.get(login).getClassificacao();
    }
    
    public double indicaClassificacao(String matricula) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        return veiculos.get(matricula).getClassificacao();
    }
    
    public void classifica(String id, double classificacao) throws ClassificacaoInvalidException, EntidadeInexistenteException {
        Veiculo v;
        Proprietario p;
        Cliente c;
        
        if((v = this.veiculos.get(id)) != null){
            v.classifica(classificacao);
            return;
        }
            
        if((p = this.proprietarios.get(id)) != null){
            p.classifica(classificacao);
            return;
        }

        if((c = this.clientes.get(id)) != null){
            c.classifica(classificacao);
            return;
        }        
        
        throw new EntidadeInexistenteException("Nao foi encontrada nenhuma entidade com o id: " + id);
    }
    
    public void addVeiculo(String tipo, String marca, String matricula, String nif, double vMedia,
        double precoPorKm, double consumoPorKm, double autonomia, double x, double y) 
                        throws VeiculoInvalidoException, PessoaInvalidException {
        if(veiculos.containsKey(matricula))
            throw new VeiculoInvalidoException("Ja existe um veiculo com a matricula " + matricula);
        
        if(!proprietarios.containsKey(nif))
            throw new PessoaInvalidException("Nao existe proprietario com NIF " + nif);
            
        Veiculo v;
            
        if(tipo.equals("Hibrido"))
            v = new CarroHibrido(tipo, marca, matricula, vMedia, precoPorKm, consumoPorKm, autonomia, x, y);
        else
            v = new CarroSimplex(tipo, marca, matricula, vMedia, precoPorKm, consumoPorKm, autonomia, x, y);
            
        veiculos.put(matricula, v);
        proprietarios.get(nif).addVeiculo(matricula);
    }
    
    private Proprietario getP() {
        return this.proprietarios.get(login);
    }
    
    private void safeGuardVeiculo(String matricula) throws VeiculoInvalidoException {
        if(!getP().temVeiculo(matricula))
            throw new VeiculoInvalidoException("O veiculo com a matricula " + matricula + " ao qual pretende aceder nao lhe esta associado");
    }    
}
