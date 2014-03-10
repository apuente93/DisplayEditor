///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             MessageLoopIterator.java
// Semester:         CS302 Spring 2014
//
// Author:           Alejandro Puente
// Email:            apuente@wisc.edu
// CS Login:         alejandr
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The MessageLoopIterator class that is used to iterate through the message
 * loop.
 *
 * <p>Bugs: None
 *
 * @author Alejandro Puente
 */
public class MessageLoopIterator<E> implements Iterator<E>
{
	//DblListnode that represents the current node
	private DblListnode<E> currNode;
	//DblListnode that represents the last node
	private DblListnode<E> lastNode;
	//Variable that represents the current position
	private int curPos;  
	
	public MessageLoopIterator(DblListnode<E> dblListnode)
	{
		currNode = dblListnode;
		lastNode = dblListnode;
		curPos = 0;
	}
	
	public boolean hasNext() 
	{
		if (currNode.getData() == null)
		{
			return false;
		}
		else
		{
			currNode = currNode.getNext();
			if (currNode == lastNode)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}

	public E next() 
	{
		if (!hasNext()) 
		{
            throw new NoSuchElementException();
        }
		currNode = currNode.getNext();
        curPos++;
        return currNode.getData();
    }

	public void remove() 
	{
		throw new UnsupportedOperationException();
	}

}
