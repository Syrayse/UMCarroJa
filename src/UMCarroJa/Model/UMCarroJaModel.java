package UMCarroJa.Model;

import UMCarroJa.Model.Users.Cliente;
import UMCarroJa.Model.Users.Proprietario;
import UMCarroJa.Model.Veiculos.Veiculo;

import java.io.Serializable;
import java.util.Map;

public class UMCarroJaModel implements Serializable {

    private Map<String, Cliente> clientes;
    private Map<String, Proprietario> proprietarios;
    private Map<Integer, Veiculo> veiculos;


    public UMCarroJaModel createData() {
        return null;
    }
}
