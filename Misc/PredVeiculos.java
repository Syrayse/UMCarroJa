package Misc;

import Model.Veiculos.*;
import Model.Localizacao;

import java.util.function.Predicate;

public class PredVeiculos
{
    public static Predicate<Veiculo> noRaioDe(Localizacao loc, double raio) {
        return v -> v.getLocalizacao().getDistancia(loc) <= raio;
    }
    
    public static Predicate<Veiculo> autonomiaDe(double autonomia) {
        return v -> (v instanceof Carro) && ((Carro)v).getAutonomia() >= autonomia;
    }
    
    public static Predicate<Veiculo> doTipo(String tipo) {
        return v -> v.getTipo().equals(tipo);
    }
}
