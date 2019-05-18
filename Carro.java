import java.io.Serializable;

public abstract class Carro extends Veiculo implements Abastecivel, Serializable {


    public abstract Carro clone();
}
