package UMCarroJa.Model.lib;

import java.io.Serializable;

public class Localizacao implements Serializable {

    private double x;
    private double y;


    public Localizacao(){
        this.x = 0;
        this.y = 0;
    }

    public Localizacao(double x, double y) {
        this.x = x;
        this.y = y;
    }

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

    public double getDistance(Localizacao outraLoc) {
        return Math.sqrt(Math.pow(this.x - outraLoc.getX(), 2) + Math.pow(this.y - outraLoc.getY(), 2));
    }

    @Override
    public boolean equals(Object o){
        if (o==this) return true;
        if(o.getClass()!=this.getClass() || o==null) return false;
        Localizacao k = (Localizacao) o;
        return (k.getX()==this.x && k.getY()==this.y);
    }

    @Override
    public String toString(){
        return ("X = " + this.getX() + " Y = " + this.getY() +"\n" );
    }

    public Localizacao clone(){
        return new Localizacao(this);
    }






}
