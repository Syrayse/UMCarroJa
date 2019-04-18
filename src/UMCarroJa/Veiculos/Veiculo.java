package UMCarroJa.Veiculos;

import UMCarroJa.lib.Localizacao;
import UMCarroJa.PublicInfo.InfoPublicaVeiculo;
import UMCarroJa.Interfaces.Classificavel;
import UMCarroJa.Aluguer.HistoricoAluguer;

public abstract class Veiculo implements Classificavel
{
    InfoPublicaVeiculo info;
    private long nClassificacoes;
    private double precoPorKM;
    private double fiabilidade;
    private double classificacao;
    private boolean ocupacao;
    private Localizacao localizao;
    private HistoricoAluguer historico;
    
    
}
