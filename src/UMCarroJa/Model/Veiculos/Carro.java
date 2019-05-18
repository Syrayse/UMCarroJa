package UMCarroJa.Model.Veiculos;

import UMCarroJa.Model.Interfaces.Abastecivel;
import UMCarroJa.Model.lib.Localizacao;

import java.io.Serializable;

public abstract class Carro extends Veiculo implements Serializable, Abastecivel {

    private String fazMOVER;

    public Carro(){
        super();
        this.fazMOVER="N/d";
    }

    public Carro(long idVeiculo, long idProprietario, double velocidadePorKM,
                 String matricula, String marca, String modelo, double precoPorKM,
                 Localizacao localizacao, boolean temEspera, String fazmovvv) {
        super(idVeiculo, idProprietario, velocidadePorKM,
        matricula,marca,modelo, precoPorKM, localizacao, temEspera);
        this.fazMOVER=fazmovvv;
    }

    public Carro(Carro outro){
        super(outro);
        this.fazMOVER=outro.getFazMOVER();
    }

    public String getFazMOVER(){
        return this.fazMOVER;
    }

    public void setFazMOVER(String x){
        this.fazMOVER=x;
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Fonte de energia = ").append(this.fazMOVER);

        return sb.toString();

    }

    public boolean equals(Object o){

        if(this==o) return true;
        if(this.getClass()!=o.getClass() || o==null) return false;

        Carro k = (Carro) o;
        return this.fazMOVER.equals(k.getFazMOVER()) && super.equals(k);

    }


















}
