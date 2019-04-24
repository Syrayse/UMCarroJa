package UMCarroJa.Users;

import java.time.LocalDate;
import UMCarroJa.Aluguer.*;

public abstract class Pessoa {

    private long id;
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate dataNascimento;
    private HistoricoAluguer historico;

    public Pessoa() {
        id = -1;
        email = "";
        nome = "";
        password = "";
        morada = "";
        dataNascimento = LocalDate.now();
        historico = new HistoricoAluguer();
    }

    public Pessoa(long id, String email, String nome, String password, String morada, LocalDate dataNascimento){
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.historico = new HistoricoAluguer();
    }

    public Pessoa(Pessoa pessoa) {
        id = pessoa.getId();
        email = pessoa.getEmail();
        nome = pessoa.getNome();
        password = pessoa.getPassword();
        morada = pessoa.getMorada();
        dataNascimento = pessoa.getDataNascimento();
        historico = pessoa.getHistorico();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
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
                email.equals(pessoa.getEmail()) &&
                nome.equals(pessoa.getNome()) &&
                password.equals(pessoa.getPassword()) &&
                morada.equals(pessoa.getMorada()) &&
                dataNascimento.equals(pessoa.getDataNascimento()) &&
                historico.equals(pessoa.getHistorico());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
