package UMCarroJa.Model.Veiculos;

import UMCarroJa.Model.Interfaces.Abastecivel;

import java.io.Serializable;

public abstract class Carro extends Veiculo implements Abastecivel, Serializable {


    public abstract Carro clone();
}
