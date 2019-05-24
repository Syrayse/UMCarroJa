import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class UMCarroJaModel implements Serializable {

    private Pessoa login;
    private Map<String, Cliente> clientes;
    private Map<String, Proprietario> proprietarios;
    private Map<String, Veiculo> veiculos;
    
    public UMCarroJaModel createData() {
        return null;
    }
    
    public UMCarroJaModel() {
        login = null;
        clientes = new HashMap<>();
        proprietarios = new HashMap<>();
        veiculos = new HashMap<>();
    }
    
    public void loginProprietario(String nif, String password) throws PessoaInvalidException {
        if(!this.proprietarios.containsKey(nif))
            throw new PessoaInvalidException("Nao existe nenhum proprietario com o nif " + nif);
        
        Proprietario prop = this.proprietarios.get(nif);
            
        if(prop.getPassword().equals(password))
            this.login = prop;
        else
            throw new PessoaInvalidException("A password que inseriu para o proprietario " + nif + " esta errada");
    }

    public void loginCliente(String nif, String password) throws PessoaInvalidException {
        if(!this.clientes.containsKey(nif))
            throw new PessoaInvalidException("Nao existe nenhum cliente com o nif " + nif);
        
        Cliente cliente = this.clientes.get(nif);
            
        if(cliente.getPassword().equals(password))
            this.login = cliente;
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
    
    public void alteraPassword(String novaPass) {
        login.setPassword(novaPass);
    }

    public void alteraMorada(String novaMorada) {
        login.setMorada(novaMorada);
    }
    
    public void logoff() {
        this.login = null;
    }
    
    public void alteraPrecoPorKm(String matricula, double preco) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        this.veiculos.get(matricula).setPrecoPorKm(preco);
    }
    
    public void removeVeiculo(String matricula) throws VeiculoInvalidoException {
        this.safeGuardVeiculo(matricula);
        ((Proprietario)login).removeVeiculo(matricula);
        this.veiculos.remove(matricula);
        
    }
    
    public double indicaClassificacao() {
        return login.getClassificacao();
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
    
    public List<Cliente> getTop10Clientes() {
        return clientes.values()
                       .stream()
                       .sorted(new CompMelhorPessoa())
                       .limit(10)
                       .map(Cliente::clone)
                       .collect(Collectors.toList());                      
    }
    
    public List<Veiculo> getVeiculos() {
        return ((Proprietario)login).getVeiculos()
                                    .stream()
                                    .map(s -> veiculos.get(s).clone())
                                    .collect(Collectors.toList());
    }
    
    public List<Aluguer> getActAlugueres(LocalDate inicio, LocalDate fim) {
        return login.getHistorico().getSubSet(inicio, fim);
    }
    
    public List<Aluguer> getActAlugueres(LocalDate inicio, LocalDate fim, String matricula) throws SemPermissaoException {
        if(!((Proprietario)login).temVeiculo(matricula))
            throw new SemPermissaoException("Nao possui permissao para aceder ao veiculo com matricula " + matricula);
        
        return veiculos.get(matricula).getHistorico().getSubSet(inicio,fim);
    }
    
    private void safeGuardVeiculo(String matricula) throws VeiculoInvalidoException {
        if(!((Proprietario)login).temVeiculo(matricula))
            throw new VeiculoInvalidoException("O veiculo com a matricula " + matricula + " ao qual pretende aceder nao lhe esta associado");
    }    
}
