package Model.Users;

import Model.Alugueres.*;
import Exceptions.*;

import java.io.Serializable;
import java.util.*;

/**
 * Classe Proprietario (Sub-Classe da Classe Pessoa).
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class Proprietario extends Pessoa implements Serializable {

    private Set<String> veiculos;
    private List<PedidoAluguer> pedidos;
    
    /**
     * Construtor vazio para objetos da classe Proprietario
     */
    public Proprietario(){
        super();
        veiculos = new HashSet<>();
        pedidos = new ArrayList<>();
    }
    /**
     * Construtor parametrizado para objetos da classe Proprietario
     */
    public Proprietario(String nif, String email, String nome, String password, String morada){
        super(nif, email, nome, password, morada);
        veiculos = new HashSet<>();
        pedidos = new ArrayList<>();
    }
    /**
     * Construtor de cópia para objetos da classe Proprietario
     */
    public Proprietario(Proprietario proprietario){
        super(proprietario);
        veiculos = proprietario.getVeiculos();
        pedidos = proprietario.getPedidos();
    }
    
    public Aluguer aceitaPedido() throws PedidoInvalidoException {
        if(pedidos.size() == 0)
            throw new PedidoInvalidoException("Ja nao ha mais pedidos de aluguer!");
        PedidoAluguer pa = pedidos.remove(0);
        double dist = pa.getOrigem().getDistancia(pa.getDestino());
        double tempoViagem = dist / pa.getVelocidade();
        double tempoChegar = pa.getTempoChegar();
        return new Aluguer(pa.getNifCliente(), this.getNif(), pa.getIdVeiculo(),
                            dist, pa.getCustoEstimado() + tempoChegar*100, tempoChegar + tempoViagem, pa.getDestino());
        
    }

    public void removePedido() throws PedidoInvalidoException {
        if(pedidos.size() == 0)
            throw new PedidoInvalidoException("Ja nao ha mais pedidos de aluguer!");
        pedidos.remove(0);        
    }

    public void addPedido(PedidoAluguer pa) {
        pedidos.add(pa);
    }
    
    public List<PedidoAluguer> getPedidos() {
        return new ArrayList<>(pedidos);
    }
    
    public Set<String> getVeiculos() {
        return new HashSet(veiculos);
    }
    
    public int getNumVeiculos() {
        return veiculos.size();
    }
    
    public void addVeiculo(String veiculo) {
        veiculos.add(veiculo);
    }
    
    public void removeVeiculo(String veiculo) {
        veiculos.remove(veiculo);
    }

    public boolean temVeiculo(String matricula) {
        return veiculos.contains(matricula);
    }
    
    /**
     * Implementação do método toString de um Proprietário.
     *
     * @return String.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Proprietario{");
        sb.append(super.toString());
        sb.append(veiculos.toString());
        sb.append("}");
        return sb.toString();
    }
    /**
     * Implementação do método equals de um Proprietário.
     *
     * @return Boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario proprietario = (Proprietario) o;
        return super.equals(proprietario) &&
                veiculos.equals(proprietario.getVeiculos());
    }
    /**
     * Implementação do método Clone de um Proprietário.
     *
     * @return Objecto do tipo Proprietário.
     */
    public Proprietario clone(){
        return new Proprietario(this);
    }
}
