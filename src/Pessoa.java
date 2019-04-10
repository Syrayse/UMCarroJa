import java.util.Date;

public class Pessoa {

    private String mail;
    private String nome;
    private String password;
    private String morada;
    private Date dataNascimento;

    public Pessoa() {
        this.mail = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = new Date();
    }

    public Pessoa(String mail, String nome, String password, String morada, Date dataNascimento) {
        this.mail = mail;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(Pessoa pessoa) {
        this.mail = pessoa.getMail();
        this.nome = pessoa.getNome();
        this.password = pessoa.getPassword();
        this.morada = pessoa.getMorada();
        this.dataNascimento = pessoa.getDataNascimento();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pessoa{");
        sb.append("mail='").append(this.mail).append('\'');
        sb.append(", nome='").append(this.nome).append('\'');
        sb.append(", password='").append(this.password).append('\'');
        sb.append(", morada='").append(this.morada).append('\'');
        sb.append(", dataNascimento=").append(this.dataNascimento.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return this.mail.equals(pessoa.getMail()) &&
                this.nome.equals(pessoa.getNome()) &&
                this.password.equals(pessoa.getPassword()) &&
                this.morada.equals(pessoa.getMorada()) &&
                this.dataNascimento.equals(pessoa.getDataNascimento());
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    @Override
    public Pessoa clone() {
        return new Pessoa(this);
    }
}
