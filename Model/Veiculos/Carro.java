package Model.Veiculos;

import Model.Localizacao;
import Exceptions.*;

import java.io.Serializable;

public abstract class Carro extends Veiculo implements Serializable {

    private double consumoPorKm;
    private double autonomia;

    public Carro() {
        super();
        consumoPorKm = -1.0;
        autonomia = -1.0;
    }
    
    public Carro(String nif, String tipo, String marca, String matricula, double velocidadeAv, double precoPorKm, double consumoPorKm, double autonomia, double x, double y) {
        super(nif, tipo, marca, matricula, velocidadeAv, precoPorKm, x, y);
        this.consumoPorKm = consumoPorKm;
        this.autonomia = autonomia;    
    }
    
    public Carro(Carro c) {
        super(c);
        this.consumoPorKm = c.getConsumoPorKm();
        this.autonomia = c.getAutonomia();
    }
    
    public void move(double x, double y) {
        this.gasta(new Localizacao(x, y));
        super.move(x, y);
    }
    
    public double getConsumoPorKm() {
        return consumoPorKm;
    }

    public double getAutonomia() {
        return autonomia;
    }
    
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Carro c = (Carro)o;
        return super.equals(c) &&
                Double.compare(consumoPorKm, c.getConsumoPorKm()) == 0 &&
                Double.compare(autonomia, c.getAutonomia()) == 0;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", consumoPorKm=").append(consumoPorKm);
        sb.append(", autonomia=").append(this.getAutonomia());
        return sb.toString();
    }
    
    @Override
    public double viagem(Localizacao newLoc) throws AcidenteOcorreuException, AutonomiaInsuficienteException {
        double dist = this.getLocalizacao().getDistancia(newLoc);
        
        if(dist > this.getAutonomia())
            throw new AutonomiaInsuficienteException("Impossivel realizar viagem, autonomia necessaria " + dist + ", mas so possui autonomia de " + this.getAutonomia());
    
        this.gasta(newLoc);
            
        return super.viagem(newLoc);
    }
    
    public abstract void gasta(Localizacao loc);
}
