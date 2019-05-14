package UMCarroJa.Model.Veiculos;

import UMCarroJa.lib.Localizacao;

import java.io.Serializable;

public class CarroHibrido extends Carro implements Serializable {

    private int qtdActualgas;
    private int qtdMaxgas;
    private int qtdActualElec;
    private int qtdMaxElec;

    public CarroHibrido(){
        super();
        this.qtdActualgas=0;
        this.qtdMaxgas=0;
        this.qtdActualElec=0;
        this.qtdMaxElec=0;
    }

    public CarroHibrido(long idVeiculo, long idProprietario, double velocidadePorKM,
                        String matricula, String marca, String modelo, double precoPorKM,
                        Localizacao localizacao, boolean temEspera, String fazmovvv, int qtdActualgas,
                        int qtdMaxgas ,int qtdActualElec,int qtdMaxElec ){

        super(idVeiculo,idProprietario, velocidadePorKM,
                matricula,  marca, modelo,precoPorKM,
                localizacao, temEspera,fazmovvv);
        this.qtdActualgas=qtdActualgas;
        this.qtdMaxgas=qtdMaxgas;
        this.qtdActualElec=qtdActualElec;
        this.qtdMaxElec=qtdMaxElec;
    }

    public CarroHibrido(CarroHibrido outro){
        super(outro);
        this.qtdActualgas=outro.getQtdActualgas();
        this.qtdMaxgas=outro.getQtdMaxgas();
        this.qtdActualElec=outro.getQtdActualElec();
        this.qtdMaxElec=outro.getQtdMaxElec();

    }

    public int getQtdActualgas(){ return this.qtdActualgas; }
    public int getQtdMaxgas(){ return this.qtdMaxgas;}

    public int getQtdActualElec(){ return this.qtdActualElec; }
    public int getQtdMaxElec(){ return this.qtdMaxElec;}

    public void setQtdActualgas(int x){ this,qtdActualgas=x;}
    public void setQtdMaxgas(int x){ this.qtdMaxgas=x;}

    public void setQtdActualElec(int x){ this,qtdActualElec=x;}
    public void setQtdMaxElec(int x){ this.qtdMaxElec=x;}


    public  String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Qtd actual de combustivel = ").append(this.qtdActualgas);
        sb.append("Qtd Max de combustivel = ").append(this.qtdMaxgas);
        sb.append("Qtd actual de energia electrica = ").append(this.qtdActualElec);
        sb.append("Qtd Max de energia electrica = ").append(this.qtdMaxElec);

        return sb.toString();
    }

    public boolean equals(Object o){
        if(this==o) return true;
        if(this.getClass()!=o.getClass() || o==null) return false;

        CarroHibrido k = (CarroHibrido) o;
        return super.equals(k) && this.qtdActualgas==k.getQtdActualgas() && this.qtdMaxgas==k.getQtdMaxgas()
                && this.qtdActualElec==k.getQtdActualElec() && this.qtdMaxElec=k.getQtdMaxElec();
    }

    public CarroHibrido clone(){
        return new CarroHibrido(this);
    }





















}
