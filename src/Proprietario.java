import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Proprietario extends Pessoa {

    private int classificao;
    private Map<Long,Veiculo> frota;

    public Proprietario() {
        super();
        classificao = -1;
        frota = new HashMap<>();
    }

    public Proprietario(long id, String mail, String nome, String password, String morada, LocalDateTime dataNascimento) {
        super(id, mail, nome, password, morada, dataNascimento);
        this.classificao = 0;
        this.frota = new HashMap<>();
    }

    public void addVeiculo(Veiculo veiculo) {
        this.frota.put(veiculo.getIdVeiculo(), veiculo.clone());
    }

    public int getClassificao() {
        return classificao;
    }

    public List<Veiculo> getFrota() {
        return this.frota.values().stream().map(Veiculo::clone).collect(Collectors.toList());
    }

}
