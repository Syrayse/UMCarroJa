package UMCarroJa.Model.Users;

import UMCarroJa.Model.lib.Localizacao;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {

    private Localizacao localizacao;

    public Cliente (){
        super();
        this.localizacao = new Localizacao();
    }

    public Cliente (long nif, String email, String nome, String password, String morada, double x, double y, double totalKm){
        super(nif, email, nome, password, morada);
        this.localizacao = new Localizacao(x,y);
    }

    public Cliente (Cliente cliente){
        super(cliente);
        this.localizacao= cliente.getLocalizacao();
    }

    public Localizacao getLocalizacao(){ return localizacao.clone(); }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Cliente{");
       sb.append(super.toString());
       sb.append(",").append(localizacao.toString());
       sb.append("}");
       return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return super.equals(cliente) &&
                localizacao.equals(cliente.getLocalizacao());
    }

    public Cliente clone(){
        return new Cliente(this);
    }
}
