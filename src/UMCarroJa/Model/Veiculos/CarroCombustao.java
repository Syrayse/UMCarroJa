package UMCarroJa.Model.Veiculos;

import UMCarroJa.Model.lib.Localizacao;

import java.io.Serializable;

public class CarroCombustao extends Carro implements Serializable {

    private int qtdActual;
    private int qtdMax;

    public CarroCombustao(){
        super();
        this.qtdActual=0;
        this.qtdMax=0;
    }

    public CarroCombustao (long idVeiculo, long idProprietario, double velocidadePorKM,
                           String matricula, String marca, String modelo, double precoPorKM,
                           Localizacao localizacao, boolean temEspera, String fazmovvv, int qtdActual, int qtdMax) {

        super(idVeiculo,idProprietario, velocidadePorKM,
        matricula,  marca, modelo,precoPorKM,
        localizacao, temEspera,fazmovvv);
        this.qtdMax=qtdMax;
        this.qtdActual=qtdActual;

    }

    public CarroCombustao(CarroCombustao outro){
        super(outro);
        this.qtdActual=outro.getQtdActual();
        this.qtdMax=outro.getQtdMax();
    }


    public int getQtdActual(){ return this.qtdActual; }
    public int getQtdMax(){ return this.qtdMax;}

    public void setQtdActual(int x){ this.qtdActual=x;}
    public void setQtdMax(int x){ this.qtdMax=x;}

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Qtd actual de combustivel = ").append(this.qtdActual);
        sb.append("Qtd Max de combustivel = ").append(this.qtdMax);

        return sb.toString();
    }

    public boolean equals(Object o) {
        if(this==o) return true;
        if(this.getClass()!=o.getClass() || o==null) return false;

        CarroCombustao k = (CarroCombustao) o;

        return super.equals(k) && this.qtdActual==k.getQtdActual() && this.qtdMax==k.getQtdMax();
    }

    public CarroCombustao clone(){
        return new CarroCombustao(this);
    }

}
