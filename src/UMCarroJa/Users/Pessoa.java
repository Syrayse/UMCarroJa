package UMCarroJa.Users;

import java.time.LocalDate;
import UMCarroJa.Aluguer.*;
import UMCarroJa.PublicInfo.InfoPublicaPessoa;

public abstract class Pessoa {

    private long id;
    private InfoPublicaPessoa infoPublic;
    private String password;
    private String morada;
    private HistoricoAluguer historico;

    public Pessoa() {
        id = -1;
        infoPublic = new InfoPublicaPessoa();
        password = "";
        morada = "";
        historico = new HistoricoAluguer();
    }

    public Pessoa(long id, String email, String nome, String password, String morada, LocalDate dataNascimento){
        this.id = id;
        this.infoPublic = new InfoPublicaPessoa(id,email,nome,dataNascimento);
        this.password = password;
        this.morada = morada;
        this.historico = new HistoricoAluguer();
    }

    public Pessoa(Pessoa pessoa) {
        InfoPublicaPessoa info = this.infoPublic;
        id = pessoa.getId();
        infoPublic = new InfoPublicaPessoa(info.getId(),info.getEmail(),info.getNome(),info.getDataNascimento());
        password = pessoa.getPassword();
        morada = pessoa.getMorada();
        historico = pessoa.getHistorico();
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.getId() &&
                infoPublic.equals(pessoa.getInfoPublic()) &&
                password.equals(pessoa.getPassword()) &&
                morada.equals(pessoa.getMorada()) &&
                historico.equals(pessoa.getHistorico());
    }

    @Override
    public int hashCode() {
        return infoPublic.getEmail().hashCode();
    }
}
