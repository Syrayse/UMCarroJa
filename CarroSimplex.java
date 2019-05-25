import java.io.Serializable;

public class CarroSimplex extends Carro implements MonoAbastecivel, Serializable {
    
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
    
    public void abastecer(double quant) {
        total_dep += quant;
    }
    
    @Override
    public double getAutonomia() {
        return total_dep / this.getConsumoPorKm();
    }
    
    public double getTotalDep() {
        return total_dep;
    }

    @Override
    public double viagem(Localizacao newLoc) throws AcidenteOcorreuException, AutonomiaInsuficienteException {
        double dist = this.getLocalizacao().getDistancia(newLoc);
        
        if(dist < this.getAutonomia())
            throw new AutonomiaInsuficienteException("Autonomia atual:" + this.getAutonomia() + ", necessaria: " + dist);
        
        super.viagem(newLoc);
        
        total_dep -= dist * this.getConsumoPorKm();
        
        return dist;
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
