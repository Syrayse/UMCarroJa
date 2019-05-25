package Model.Veiculos;

import Model.Interfaces.MonoAbastecivel;
import Model.Localizacao;
import Exceptions.*;

import java.io.Serializable;

public class CarroSimplex extends Carro implements MonoAbastecivel, Serializable {
    
    private static final double MAX_DEP = 600;
    
    private double total_dep;
    
    public CarroSimplex() {
        super();
        total_dep = -1.0;
    }
    
    public CarroSimplex(String nif, String tipo, String marca, String matricula, double velocidadeAv, double precoPorKm, double consumoPorKm, double autonomia, double x, double y) {
        super(nif, tipo, marca, matricula, velocidadeAv, precoPorKm, consumoPorKm, autonomia, x, y);
        total_dep = autonomia * consumoPorKm;
    }
    
    public CarroSimplex(CarroSimplex ce) {
        super(ce);
        total_dep = ce.getTotalDep();
    }
    
    public void abastecer(double quant) throws DepositoCheioException {
        if(total_dep + quant > CarroSimplex.MAX_DEP)
            throw new DepositoCheioException("A quantidade de combutivel que pretende abastecer ultrapassa a capacidade maxima do veiculo que e " + CarroSimplex.MAX_DEP);
        total_dep += quant;
    }
    
    @Override
    public double getAutonomia() {
        return total_dep / this.getConsumoPorKm();
    }
    
    public double getTotalDep() {
        return total_dep;
    }
    
    public void gasta(Localizacao loc) {
        double dist = this.getLocalizacao().getDistancia(loc);
        total_dep -= dist * this.getConsumoPorKm();
    }
    
    public CarroSimplex clone() {
        return new CarroSimplex(this);
    }
    
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        CarroSimplex cs = (CarroSimplex)o;
        return super.equals(cs) &&
                total_dep == cs.getTotalDep();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", totalDep=").append(total_dep);
        return sb.toString();
    }
}
