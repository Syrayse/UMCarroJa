public class InfoPublicaVeiculo extends InfoPublica
{    
    private long idProprietario;
    private double velocidadePorKM;
    private String matricula;
    private String marca;
    private String modelo;
    
    public InfoPublicaVeiculo() {
        super();
        idProprietario = -1;
        velocidadePorKM = 0.0;
        matricula = "";
        marca = "";
        modelo = "";
    }
    
    public InfoPublicaVeiculo(long idVeiculo, long idProprietario,
            double velocidadePorKM, String matricula, String marca, String modelo) {
        super(idVeiculo);
        this.idProprietario = idProprietario;
        this.velocidadePorKM = velocidadePorKM;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;                                           
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id_proprietario= ").append(idProprietario);
        sb.append(", velocidade_por_km= ").append(velocidadePorKM);
        sb.append(", matricula = ").append(matricula);
        sb.append(", marca= ").append(marca);
        sb.append(", modelo= ").append(modelo);
        return sb.toString();
    }
}
