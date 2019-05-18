package UMCarroJa.Model.Users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Proprietario extends Pessoa implements Serializable {

    private Set<String> veiculos;

    public Proprietario(){
        super();
        veiculos = new HashSet<>();
    }

    public Proprietario(long nif, String email, String nome, String password, String morada){
        super(nif, email, nome, password, morada);
        veiculos = new HashSet<>();
    }

    public Proprietario(Proprietario proprietario){
        super(proprietario);
        veiculos = proprietario.getVeiculos();
    }

    public Set<String> getVeiculos() {
    	return new HashSet(veiculos);
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Proprietario{");
        sb.append(super.toString());
        sb.append(veiculos.toString());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario proprietario = (Proprietario) o;
        return super.equals(proprietario) &&
                veiculos.equals(proprietario.getVeiculos());
    }

    public Proprietario clone(){
        return new Proprietario(this);
    }
}
