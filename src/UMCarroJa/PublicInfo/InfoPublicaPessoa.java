package UMCarroJa.PublicInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class InfoPublicaPessoa extends InfoPublica
{
    private String email;
    private String nome;
    private LocalDate dataNascimento;
    
    public InfoPublicaPessoa() {
        super();
        email = "";
        nome = "";
        dataNascimento = LocalDate.now();
    }

    public InfoPublicaPessoa(long id, String email, String nome, LocalDate dataNascimento) {
        super(id);
        this.email = email;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        InfoPublicaPessoa ip = (InfoPublicaPessoa)o;
        return super.equals(ip) &&
                this.email.equals(ip.getEmail()) &&
                this.nome.equals(ip.getNome()) &&
                this.dataNascimento.equals(ip.getDataNascimento());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("email = ").append(this.email);
        sb.append(",nome = ").append(this.nome);
        sb.append(",data_nascimento = ")
            .append(this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return sb.toString();
    }
    
}
