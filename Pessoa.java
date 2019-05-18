import java.io.Serializable;

public abstract class Pessoa implements Classificavel, Serializable {

    private long nif;
    private String email;
    private String nome;
    private String password;
    private String morada;
    private HistoricoAluguer historico;
    private long nClassificacoes;
    private double classificacao;

    public Pessoa() {
        nif = -1;
        email = "";
        nome = "";
        password = "";
        morada = "";
        historico = new HistoricoAluguer();
        nClassificacoes = -1;
        classificacao = -1;
    }

    public Pessoa(long nif, String email, String nome, String password, String morada) {
        this.nif = nif;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        historico = new HistoricoAluguer();
        nClassificacoes = 0;
        classificacao = 100;
    }

    public Pessoa(Pessoa p){
        nif = p.getNif();
        email = p.getEmail();
        nome = p.getNome();
        password = p.getPassword();
        morada = p.getMorada();
        historico = p.getHistorico();
    }

    public long getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public long getnClassificacoes() {
        return nClassificacoes;
    }

    public double getClassificacao() {
        return classificacao;
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
        return nif == pessoa.getNif() &&
                email.equals(pessoa.getNif()) &&
                password.equals(pessoa.getPassword()) &&
                morada.equals(pessoa.getMorada()) &&
                historico.equals(pessoa.getHistorico());
    }

    public int hashCode() {
        return Long.valueOf(nif).hashCode();
    }

    public final void classifica(double classificacao) {
        long nAtual = this.nClassificacoes;
        double clAtual = this.classificacao;
        this.classificacao = ((nAtual*clAtual + classificacao) / (nAtual + 1));
        this.nClassificacoes++;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Pessoa{");
        sb.append("nif=").append(nif);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", morada=").append(morada);
        sb.append(", historico=").append(historico.toString());
        sb.append(", nClassificacoes=").append(nClassificacoes);
        sb.append(", classificao=").append(classificacao);
        sb.append('}');
        return sb.toString();
    }

    public abstract Pessoa clone();
}
