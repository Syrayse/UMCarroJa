public class Localizacao {

    private float x;
    private float y;



    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Localizacao(){
        this.x=0;
        this.y=0;
    }

    public Localizacao(float x , float y){
        this.x=x;
        this.y=y;
    }

    public Localizacao(Localizacao outraLoc){
        this.x=outraLoc.getX();
        this.y=outraLoc.getY();
    }

    public boolean equals(Object o){
        if (o==this) return true;
        if(o.getClass()!=this.getClass() || o==null) return false;
        Localizacao k = (Localizacao) o;
        return (k.getX()==this.x && k.getY()==this.y);
    }

    public String toString(){
        return ("X = " + this.getX() + " Y = " + this.getY() +"\n" );
    }

    public Localizacao clone(){
        Localizacao k = new Localizacao(this);
        return k;
    }






}
