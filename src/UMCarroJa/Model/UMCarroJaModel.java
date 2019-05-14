package UMCarroJa.Model;

import UMCarroJa.Users.Cliente;
import UMCarroJa.Users.Proprietario;
import UMCarroJa.Veiculos.Veiculo;

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
