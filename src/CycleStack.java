import java.lang.reflect.Array;

public class CycleStack<T>
{
    private final T[] container;
    private int count = 0;
    private int offset = 0;
    private final int size;

    public CycleStack(Class<T[]> c, int size)
    {
        this.container = c.cast(Array.newInstance(c.getComponentType(), size));
        this.size = size;
        // oh god java why you make me write that? --J
    }

    public void Push(T element)
    {
        if(count >= size)
        {
            container[offset] = element;
            offset = (offset + 1) % size;
        }
        else
        {
            container[(count+offset) % size] = element;
            count++;
        }
    }
    public T Pop()
    {
        if(count > 0)
        {
            var index = (count-1+offset)%size;
            var el = container[index];
            container[index] = null;
            count--;
            return el;
        }
        else return null;
    }
}

