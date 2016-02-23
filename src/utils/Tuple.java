package utils;

/**
 * Represents a tuple of two elements.
 * @author G11
 */
public class Tuple<V,E> {
    /**
     * First element of the tuple.
     */
    private V v;
    
    /**
     * Second element of the tuple.
     */
    private E e;
    
    /**
     * Creates an instance of {@link Tuple} with the specified parameters.
     * @param v (V) The first element of the tuple.
     * @param e (E) The second element of the tuple.
     */
    public Tuple(V v,E e)
    {
        this.v=v;
        this.e=e;
    }
    
    /**
     * Sets the first element of this tuple.
     * @param v (V) The new first element.
     */
    public void setFirstElement(V v)
    {
        this.v=v;
    }
    
    /**
     * Sets the second element of this tuple.
     * @param e (E) The new second element.
     */
    public void setSecondElement(E e)
    {
        this.e=e;
    }
    
    /**
     * Returns the first element of this tuple.
     * @return (V) The first element.
     */
    public V getFirstElement()
    {
        return v;
    }
    
    /**
     * Returns the second element of this tuple.
     * @return (E) The second element.
     */
    public E getSecondElement()
    {
        return e;
    }

    @Override
    public boolean equals(Object other)
    {
        boolean result = other!=null && getClass().equals(other.getClass());
        if (result)
        {
            Tuple t = (Tuple) other;
            result = this==other || (t.getFirstElement().equals(v) && t.getSecondElement().equals(e));
        }
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("Tuple[%s, %s]", this.v.toString(), this.e.toString());
    }
    
    
}
