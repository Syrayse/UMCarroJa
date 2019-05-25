package Model.Users;

import Model.Localizacao;

import java.io.Serializable;

/**
 * Classe Cliente (Sub-Classe da Classe Pessoa).
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class Cliente extends Pessoa implements Serializable {

    private Localizacao localizacao;
    /**
     * Construtor vazio para objetos da classe Cliente
     */
    public Cliente (){
        super();
        this.localizacao = new Localizacao();
    }
    /**
     * Construtor Parametrizado para objetos da classe Cliente
     */
    public Cliente (String nif, String email, String nome, String password, String morada, double x, double y){
        super(nif, email, nome, password, morada);
        this.localizacao = new Localizacao(x,y);
    }
    /**
     * Construtor de cópia para objetos da classe Cliente
     */
    public Cliente (Cliente cliente){
        super(cliente);
        this.localizacao= cliente.getLocalizacao();
    }

    public Localizacao getLocalizacao(){ return localizacao.clone(); }

    public void move(double x, double y) {
        localizacao.move(x,y);
    }
    
    /**
     * Implementação do método toString de um Cliente.
     *
     * @return String.
     */
    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Cliente{");
       sb.append(super.toString());
       sb.append(",").append(localizacao.toString());
       sb.append("}");
       return sb.toString();
    }
    /**
     * Implementação do método equals de um Cliente.
     *
     * @return Boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return super.equals(cliente) &&
                localizacao.equals(cliente.getLocalizacao());
    }
    /**
     * Implementação do método Clone de um Cliente.
     *
     * @return Objecto do tipo Cliente.
     */
    public Cliente clone(){
        return new Cliente(this);
    }
}
