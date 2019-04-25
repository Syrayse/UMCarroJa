package UMCarroJa.PublicInfo;

public class InfoPublicaVeiculo extends InfoPublica
{
    private long idProprietario;
    private double velocidadePorKM;
    private String matricula;
    private String marca;
    private String modelo;
    private int ano;
    private int numLugares;

    public InfoPublicaVeiculo() {
        super();
        idProprietario = -1;
        velocidadePorKM = 0.0;
        matricula = "";
        marca = "";
        modelo = "";
        ano=0;
        numLugares=0;
    }

    public InfoPublicaVeiculo(long idVeiculo, long idProprietario,
                              double velocidadePorKM, String matricula, String marca, String modelo,int ano,int numLugares) {
        super(idVeiculo);
        this.idProprietario = idProprietario;
        this.velocidadePorKM = velocidadePorKM;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.ano=ano;
        this.numLugares=numLugares;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id_proprietario= ").append(idProprietario);
        sb.append(", velocidade_por_km= ").append(velocidadePorKM);
        sb.append(", matricula = ").append(matricula);
        sb.append(", marca= ").append(marca);
        sb.append(", modelo= ").append(modelo);
        sb.append(", ano= ").append(ano);
        sb.append(", Lugares= ").append(numLugares);
        return sb.toString();
    }
}
