import java.util.Iterator;

public class MessageLoopIterator<E> implements Iterator<E>
{
	private DblListnode<E> currNode = new DblListnode<E>();
	private int curPos;  
	
	public MessageLoopIterator(DblListnode<E> dblListnode)
	{
		currNode = dblListnode;
		curPos = 0;
	}
	
	public boolean hasNext() 
	{
		return false;
	}

	public E next() 
	{
		if (!hasNext()) 
		{
            throw new NoSuchElementException();
        }
        Object result = list.get(curPos);
        curPos++;
        return result;
    }

	public void remove() 
	{
		throw new UnsupportedOperationException();
	}

}
