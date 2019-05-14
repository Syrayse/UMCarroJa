package UMCarroJa.Model.Users;

import UMCarroJa.Model.Aluguer.Aluguer;
import UMCarroJa.Model.Aluguer.HistoricoAluguer;
import UMCarroJa.Model.PublicInfo.InfoPublicaPessoa;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Pessoa implements Serializable {

    private long id;
    private double lucro;
    private InfoPublicaPessoa infoPublic;
    private String password;
    private String morada;
    private HistoricoAluguer historico;

    public Pessoa() {
        id = -1;
        this.lucro = 0.0;
        infoPublic = new InfoPublicaPessoa();
        password = "";
        morada = "";
        historico = new HistoricoAluguer();
    }

    public Pessoa(long id, String email, String nome, String password, String morada, LocalDate dataNascimento){
        this.id = id;
        this.lucro = 0.0;
        this.infoPublic = new InfoPublicaPessoa(id,email,nome,dataNascimento);
        this.password = password;
        this.morada = morada;
        this.historico = new HistoricoAluguer();
    }

    public Pessoa(Pessoa pessoa) {
        InfoPublicaPessoa info = this.infoPublic;
        id = pessoa.getId();
        lucro = pessoa.getLucro();
        infoPublic = new InfoPublicaPessoa(info.getId(),info.getEmail(),info.getNome(),info.getDataNascimento());
        password = pessoa.getPassword();
        morada = pessoa.getMorada();
        historico = pessoa.getHistorico();
    }

    public long getId() {
        return id;
    }

    public double getLucro() {
        return lucro;
    }

    public int getNAlugueres() {
        return historico.size();
    }

    public InfoPublicaPessoa getInfoPublic() {
        return this.infoPublic;
    }

    public String getPassword() {
        return password;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public HistoricoAluguer getHistorico() {
        return historico.clone();
    }

    public void addAluguer(Aluguer aluguer) {
        historico.addAluguer(aluguer);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.getId() &&
                Double.compare(lucro,pessoa.getLucro()) == 0 &&
                infoPublic.equals(pessoa.getInfoPublic()) &&
                password.equals(pessoa.getPassword()) &&
                morada.equals(pessoa.getMorada()) &&
                historico.equals(pessoa.getHistorico());
    }

    public int hashCode() {
        return infoPublic.getEmail().hashCode();
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Pessoa{");
        sb.append("id=").append(id);
        sb.append(", lucro=").append(lucro);
        sb.append(", infoPublic=").append(infoPublic.toString());
        sb.append(", password='").append(password).append('\'');
        sb.append(", morada='").append(morada).append('\'');
        sb.append(", historico=").append(historico.toString());
        sb.append('}');
        return sb.toString();
    }
}
