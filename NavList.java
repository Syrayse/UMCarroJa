import java.util.ArrayList;
import java.util.Collection;

public class NavList<E>
{
    private static final int DEF_PAGE_SIZE = 10;
    
    private int curr;
    private int end;
    private ArrayList<E> coll;
    
    public NavList() {
        curr = 0;
        end = 0;
        coll = new ArrayList<>();
    }
    
    public NavList(Collection<E> c) {
        curr = 0;
        end = DEF_PAGE_SIZE > c.size() ? c.size() : DEF_PAGE_SIZE;
        coll = new ArrayList<>(c);
    }
    
    public String previousPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        
        return sb.toString();
    }
    
    public String nextPage() {
        curr = (curr + DEF_PAGE_SIZE) % coll.size();
        
        StringBuilder sb = new StringBuilder();
        sb.append("");
        
        
        return sb.toString();    
    }

  
}
