
package beadando1;

import java.util.Objects;


public class Huffmann implements Comparable<Huffmann> {
    public int amount;
    public char letter;
    public Huffmann parent;
    public Huffmann left;
    public Huffmann right;
    public int leftEdge;
    public int rightEdge;
    
    public Huffmann(int a, char l)
    {
        amount=a;
        letter=l;
        parent=null;
        left=null;
        right=null;
        leftEdge=-1;
        rightEdge=-1;
    }
       
    
    public int compareTo(Huffmann h)
    {
        return this.amount-h.amount;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof Huffmann))
        {
            return false;
        }
        Huffmann h2 = (Huffmann) o;
        return Objects.equals(this.amount,h2.amount) && Objects.equals(this.letter,h2.letter);
    }
    
}
