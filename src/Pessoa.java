import java.util.Date;

public class Pessoa {

    private String mail;
    private String nome;
    private String password;
    private String morada;
    private Date dataNascimento;

    public String getMail(){ return this.mail; }
    public String getNome(){ return this.nome; }
    public String getPassword(){ return this.password; }
    public String getMorada(){ return this.morada; }
    public Date getDataNascimento(){return this.dataNascimento;}

    public void setMail(String x){ this.mail=x;}
    public void setNome(String x){ this.nome=x;}
    public void setPassword(String x){ this.password=x;}
    public void setMorada(String x){ this.morada; }
    public void setDataNascimento(Date x){
        Date k = new Date(x);
        this.dataNascimento=k;
    }



}
