import java.time.LocalDateTime;

public class Pessoa {

    private long id;
    private String mail;
    private String nome;
    private String password;
    private String morada;
    private LocalDateTime dataNascimento;
    private HistoricoAluguer historicoAluguer;

    public Pessoa() {
        id = -1;
        mail = "";
        nome = "";
        password = "";
        morada = "";
        dataNascimento = LocalDateTime.now();
        historicoAluguer = new HistoricoAluguer();
    }

    public Pessoa(long id, String mail, String nome, String password, String morada, LocalDateTime dataNascimento) {
        this.id = id;
        this.mail = mail;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.historicoAluguer = new HistoricoAluguer();
    }

    public Pessoa(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.mail = pessoa.getMail();
        this.nome = pessoa.getNome();
        this.password = pessoa.getPassword();
        this.morada = pessoa.getMorada();
        this.dataNascimento = pessoa.getDataNascimento();
        this.historicoAluguer = pessoa.getHistoricoAluguer();
    }

    public long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getMorada() {
        return morada;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public HistoricoAluguer getHistoricoAluguer() {
        return historicoAluguer.clone();
    }

    public void addAluguer(Aluguer aluguer) {
        this.historicoAluguer.addAluguer(aluguer);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return this.mail.equals(pessoa.getMail()) &&
                this.nome.equals(pessoa.getNome()) &&
                this.password.equals(pessoa.getPassword()) &&
                this.morada.equals(pessoa.getMorada()) &&
                this.dataNascimento.equals(pessoa.getDataNascimento()) &&
                this.historicoAluguer.equals(pessoa.getHistoricoAluguer());
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Pessoa{");
        sb.append("id='").append(id).append('\'');
        sb.append("mail='").append(mail).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", morada='").append(morada).append('\'');
        sb.append(", dataNascimento=").append(dataNascimento.toString());
        sb.append(", historicoAluguer=").append(historicoAluguer.toString());
        sb.append('}');
        return sb.toString();
    }

    public Pessoa clone() {
        return new Pessoa(this);
    }
}
