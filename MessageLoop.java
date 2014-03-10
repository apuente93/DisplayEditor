///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             MessageLoop.java
// Semester:         CS302 Spring 2014
//
// Author:           Alejandro Puente
// Email:            apuente@wisc.edu
// CS Login:         alejandr
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;

/**
 * The MessageLoop class that represents a circular doubly linked chain of
 * data.
 *
 * <p>Bugs: None
 *
 * @author Alejandro Puente
 */
public class MessageLoop<E> implements LoopADT<E>
{
	//A reference to the current node in the list
	private DblListnode<E> currNode;
	//A reference to the first node in the list
	private DblListnode<E> firstNode;
	//The number of items in the list
	private int numItems;
	
	/**
	* The constructor of the message loop class. It creates
	* an empty message loop
	*/
	public MessageLoop()
	{
		currNode = new DblListnode<E>();
		firstNode = new DblListnode<E>();
	    numItems = 0;
	}

	/**
    * Adds the given item immediately before the current 
    * item.  After the new item has been added, the new item becomes the
    * current item.
    * 
    * @param item - the item to add
    */
	public void addBefore(E item) 
	{
		if (currNode.getData() == null)
		{
			currNode.setData(item);
			currNode.setPrev(currNode);
			currNode.setNext(currNode);
			numItems++;
		}
		else
		{
			//The new DblListnode that holds the given item that 
			//will come before the current node
			DblListnode<E> newCurr = new DblListnode<E>(
			currNode.getPrev(), item, currNode);
			currNode.getPrev().setNext(newCurr);
			currNode.setPrev(newCurr);
			currNode = newCurr;
			numItems++;
		}	
	}

	/**
    * Adds the given item immediately after the current 
    * item.  After the new item has been added, the new item becomes the
    * current item.
    * 
    * @param item - the item to add
    */
	public void addAfter(E item) 
	{
		if (currNode.getData() == null)
		{
			currNode.setData(item);
			currNode.setPrev(currNode);
			currNode.setNext(currNode);
			firstNode = currNode;
			numItems++;
		}
		
		else
		{
			//The new DblListnode that holds the given item that 
			//will come after the current node
			DblListnode<E> newCurr = new DblListnode<E>(
			currNode, item, currNode.getNext());
			currNode.setNext(newCurr);
			firstNode.setPrev(newCurr);
			currNode = newCurr;
			numItems++;
		}		
	}

	/**
    * Returns the current item.  If the Loop is empty, an 
    * EmptyLoopException is thrown.
    * 
    * @return the current item
    * @throws EmptyLoopException if the Loop is empty
    */
	public E getCurrent() 
	{
		if (currNode.getData() == null)
		{
			throw new EmptyLoopException();
		}
		return currNode.getData();
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
	public E removeCurrent() 
	{
		if (currNode.getData() == null)
		{
			throw new EmptyLoopException();
		}
		
		else
		{
			currNode.getPrev().setNext(currNode.getNext());
			currNode.getNext().setPrev(currNode.getPrev());
			currNode = currNode.getNext();
			numItems--;
			return currNode.getData();
		}
	}

	/**
    * Advances the current item forward one item resulting in the item 
    * that is immediately after the current item becoming the  
    * current item.
    */
	public void forward() 
	{
		if (currNode.getData() == null)
		{
			return;
		}
		
		else
		{
			currNode = currNode.getNext();
		}
	}

	/**
    * Moves the current item backwards one item resulting in the item 
    * that is immediately before the current item becoming the  
    * current item.
    */
	public void back() 
	{
		if (currNode.getData() == null)
		{
			return;
		}
		
		else
		{
			currNode = currNode.getPrev();
		}
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
		MessageLoopIterator<E> iterator = new MessageLoopIterator<E>(currNode);
		return iterator;
	}

}
