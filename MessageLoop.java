import java.util.Iterator;


public class MessageLoop<E> implements LoopADT
{
	//A reference to the first node in the list
	private DblListnode<E> firstNode;
	//A reference to the last node in the list
	private DblListnode<E> lastNode;
	//A reference to the current node in the list
	private DblListnode<E> currNode;
	//The number of items in the list
	private int numItems;
	
	/**
	* The constructor of the message loop class. It creates
	* an empty message loop
	*/
	public MessageLoop()
	{
		firstNode = new DblListnode<E>(lastNode, null, lastNode);
		currNode = firstNode;
		lastNode = new DblListnode<E>(firstNode, null, firstNode);
	    numItems = 0;
	}

	/**
    * Adds the given item immediately before the current 
    * item.  After the new item has been added, the new item becomes the 
    * current item.
    * 
    * @param item - the item to add
    */
	public void addBefore(Object item) 
	{
		
	}

	/**
    * Adds the given item immediately after the current 
    * item.  After the new item has been added, the new item becomes the 
    * current item.
    * 
    * @param item - the item to add
    */
	public void addAfter(Object item) 
	{
		
	}

	/**
    * Returns the current item.  If the Loop is empty, an 
    * EmptyLoopException is thrown.
    * 
    * @return the current item
    * @throws EmptyLoopException if the Loop is empty
    */
	public Object getCurrent() 
	{
		return null;
	}

	/**
    * Removes and returns the current item.  The item immediately 
    * after the removed item then becomes the  current item.  
    * If the Loop is empty initially, an EmptyLoopException 
    * is thrown.
    * 
    * @return the removed item
    * @throws EmptyLoopException if the Loop is empty
    */
	public Object removeCurrent() 
	{
		return null;
	}

	/**
    * Advances the current item forward one item resulting in the item 
    * that is immediately after the current item becoming the  
    * current item.
    */
	public void forward() 
	{
		
	}

	/**
    * Moves the current item backwards one item resulting in the item 
    * that is immediately before the current item becoming the  
    * current item.
    */
	public void back() 
	{
		
	}

	/**
    * Returns the number of items in this Loop.
    * @return the number of items in this Loop
    */
	public int size() 
	{
		return numItems;
	}

	/**
    * Returns an iterator for this Loop.
    * Rather than using the Iterable interface we'll combine it with the ADT.
    * @return an iterator for this Loop
    */
	public Iterator iterator() 
	{
		return null;
	}

}
