package UMCarroJa.PublicInfo;

public abstract class InfoPublica
{
    private long id;
    
    public InfoPublica() {
        id = -1;
    }
    
    public InfoPublica(long id) {
        this.id = id;
    }
    
    public InfoPublica(InfoPublica ip) {
        id = ip.getId();
    }
    
    public long getId() {
        return id;
    }
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        InfoPublica ip = (InfoPublica)o;
        return this.id == ip.getId();
    }
    
    public abstract String toString();
}
