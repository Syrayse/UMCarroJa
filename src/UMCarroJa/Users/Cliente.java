package UMCarroJa.Users;

import UMCarroJa.lib.Localizacao;

import java.time.LocalDate;

public class Cliente extends Pessoa {

    private Localizacao localizacao;
    private int destreza;

    public Cliente (){
        super();
        this.localizacao = new Localizacao();
        this.destreza = 0;
    }

    public Cliente (long id, String email, String nome, String password, String morada, LocalDate dataNascimento, int x, int y, double totalKm){
        super(id, email, nome, password, morada, dataNascimento);
        this.localizacao = new Localizacao(x,y);
        this.destreza = 100;
    }

    public Cliente (Cliente cliente){
        super(cliente);
        this.localizacao= cliente.getLocalizacao();
    }

    public Localizacao getLocalizacao(){ return localizacao.clone(); }

    public int getDestreza() {return this.destreza; }

    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }


    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
       sb.append("Localização: ").append(this.localizacao.toString());
       sb.append("Destreza: ").append(this.destreza);
       return (sb.toString());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return super.equals(cliente) &&
                localizacao.equals(cliente.getLocalizacao()) &&
                destreza==cliente.getDestreza();
    }

    public Cliente clone(){
        return new Cliente(this);
    }
}
