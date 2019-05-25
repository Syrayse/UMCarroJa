package Model.Veiculos;

import Model.Interfaces.BiAbastecivel;
import Model.Localizacao;
import Exceptions.*;

import java.io.Serializable;

public class CarroHibrido extends Carro implements BiAbastecivel, Serializable {

    private static final double MAX_KWH = 300;
    private static final double MAX_GAS = 300;
    
    private static final double KWH_IMP = 0.4;
    private static final double GAS_IMP = 0.6;
    
    private double totalKwh;
    private double totalGas;
    
    public CarroHibrido() {
        super();
        totalKwh = -1.0;
        totalGas = -1.0;
    }
    
    public CarroHibrido(String nif, String tipo, String marca, String matricula, double velocidadeAv, double precoPorKm, double consumoPorKm, double autonomia, double x, double y) {
        super(nif, tipo, marca, matricula, velocidadeAv, precoPorKm, consumoPorKm, autonomia, x, y);
        double total = autonomia * consumoPorKm;
        this.totalKwh = CarroHibrido.KWH_IMP * total;
        this.totalGas = CarroHibrido.GAS_IMP * total;
    }
    
    public CarroHibrido(CarroHibrido ch) {
        super(ch);
        totalKwh = ch.getTotalKwh();
        totalGas = ch.getTotalGas();
    }
    
    public void abastecerKwh(double quant) throws DepositoCheioException {
        if(totalKwh + quant > CarroHibrido.MAX_KWH)
            throw new DepositoCheioException("A quantidade de KWH que pretende abastecer ultrapassa a capacidade maxima do veiculo que e " + CarroHibrido.MAX_KWH);
        totalKwh += quant;
    }
    
    public void abastecerGas(double quant) throws DepositoCheioException {
        if(totalGas + quant > CarroHibrido.MAX_GAS)
            throw new DepositoCheioException("A quantidade de GAS que pretende abastecer ultrapassa a capacidade maxima do veiculo que e " + CarroHibrido.MAX_GAS);
        totalGas += quant;
    }

    public double getTotalKwh() {
        return totalKwh;
    }
    
    public double getTotalGas() {
        return totalGas;
    }
    
    @Override
    public double getAutonomia() {
        double c = this.getConsumoPorKm();
        double cKwh = CarroHibrido.KWH_IMP*(totalKwh / c);
        double cGas = CarroHibrido.GAS_IMP*(totalGas / c);
        return cKwh + cGas;
    }
    
    public void gasta(Localizacao loc) {
        double total, dist = this.getLocalizacao().getDistancia(loc);
        
        total = dist * this.getConsumoPorKm();
        
        totalKwh -= CarroHibrido.KWH_IMP * total;
        
        totalGas -= CarroHibrido.GAS_IMP * total;
    }
    
    @Override
    public CarroHibrido clone() {
        return new CarroHibrido(this);
    }
    
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        CarroHibrido cs = (CarroHibrido)o;
        return super.equals(cs) &&
                totalKwh == cs.getTotalKwh() &&
                totalGas == cs.getTotalGas();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", totalKwh=").append(totalKwh); 
        sb.append(", totalGas=").append(totalGas);
        return sb.toString();
    }
}
