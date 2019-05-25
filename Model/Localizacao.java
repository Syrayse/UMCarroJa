package Model;

import java.io.Serializable;

/**
 * Classe Localizacao
 *
 * @author (Rui Reis (A84930), Filipe Fernandes (A83996), António Guerra (A81032))
 * @version (2019-05-21)
 */
public class Localizacao implements Serializable {

    private double x;
    private double y;

    /**
     * Construtor vazio para objetos da classe Localizacao
     */
    public Localizacao(){
        this.x = 0;
        this.y = 0;
    }
    /**
     * Construtor parametrizado para objetos da classe Localizacao
     */
    public Localizacao(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Construtor de cópia para objetos da classe Localizacao
     */
    public Localizacao(Localizacao outraLoc){
        this.x = outraLoc.getX();
        this.y = outraLoc.getY();
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistancia(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    /**
     * Implementação do método getDistancia entre duas Localizacoes.
     *
     * @return double.
     */
    public double getDistancia(Localizacao outraLoc) {
        return this.getDistancia(outraLoc.getX(), outraLoc.getY());
    }
    /**
     * Implementação do método equals de uma Localizacao.
     *
     * @return Boolean
     */
    @Override
    public boolean equals(Object o){
        if (o==this) return true;
        if(o.getClass()!=this.getClass() || o==null) return false;
        Localizacao k = (Localizacao) o;
        return (k.getX()==this.x && k.getY()==this.y);
    }
    /**
     * Implementação do método toString de uma Localizacao.
     *
     * @return String.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(x=").append(this.x);
        sb.append(",y=").append(this.y);
        sb.append(")");
        return sb.toString();
    }
    /**
     * Implementação do método Clone de uma Localizacao.
     *
     * @return Objecto do tipo Localizacao.
     */
    public Localizacao clone(){
        return new Localizacao(this);
    }






}
