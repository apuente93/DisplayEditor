import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p2
// Files:            DisplayEditor.java, DblListnode.java, 
//					 EmptyLoopException.java, MessageLoop.java,
//					 UnrecognizedCharacterException.java, 
//					 MessageLoopIterator.java
// Semester:         CS367 Spring 2014
//
// Author:           Alejandro Puente
// Email:            apuente@wisc.edu
// CS Login:         alejandr
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * The DisplayEditor class that runs the main method of the program.
 *
 * <p>Bugs: None
 *
 * @author Alejandro Puente
 */
public class DisplayEditor 
{
	/**
	 * The main method starts the entire program's execution. The user 
	 * can manipulate a circular doubly linked list through simple commands.
	 */
	public static void main (String[] args)
	{
		//The DotMatrix object that converts the data to their respective 
		//characters
		DotMatrix matrix = new DotMatrix();
		matrix.loadAlphabets("alphabets.txt");
		//This is the circular doubly linked list of data
		MessageLoop<List<String>> loop = new MessageLoop<List<String>>();
		//Keeps programming running until user types "q"
		boolean stop = false;
		//Scanner object that handles user input
		Scanner in = new Scanner(System.in);
		while (!stop)
		{
			//Variable that stores the user input
			String input = "";
			while(input.equals(""))
			{
				System.out.print("enter command (? for help)> ");
				input = in.nextLine();
			}
			//Variable that stores the user command option
			char option = input.charAt(0);
			//Variable that stores the remainder of the user input
			String remainder = "";
			//Variable that stores the remainder of the user input without
			//a space
			String remainder2 = "";
			//Variable that stores the remainder of the user input without
			//caps
			String remainder3 = "";
			if (input.length() > 1) 
			{
				remainder3 = input.substring(1).trim();
				remainder = input.substring(1);
				remainder = remainder.toUpperCase();
				remainder2 = remainder.substring(1);
			}
			//Array that holds the characters to be added to the message loop
			String [] wordsInLine = remainder2.split("(?!^)");
			switch (option) 
			{
			case '?':
				System.out.println("s (save)    l (load)       d (display)");
				System.out.println("n (next)    p (previous)   j (jump)");
				System.out.println("x (delete)  a (add after)  i "
						+ "(insert before)");
				System.out.println("c (context) r (replace)    q (quit)");
				break;
			case 's':
				if (remainder.equals(""))
				{
					System.out.println("invalid command");
				}
				else if (remainder.charAt(0) != ' ')
				{
					System.out.println("invalid command");
				}
				else if (loop.size() == 0)
				{
					System.out.println("no messages to save");
				}			
				else
				{
					//File that stores the message loop
					File savedFile = new File(remainder3);
					//Variable that holds the string message loop
					String outputText = "";
					//Variable that holds the DotMatrix representation of 
					//the current node in the loop
					List<String> curr = new ArrayList<String>();
					for (int i = 0; i < loop.size(); i++)
					{
						curr = loop.getCurrent();
						for (int j = 0; j < curr.size(); j++)
						{
							outputText += curr.get(j);
							outputText += '\n';
						}
						outputText += "##########";
						outputText += '\n';
						loop.forward();
					}
					if (savedFile.exists())
					{
						System.out.println("warning: file already exists, "
								+ "will be overwritten");
					}
					try 
					{
						//Variable used to write to file
						PrintWriter out = new PrintWriter(savedFile);
						out.print(outputText);
						out.close();
					} 
					catch (FileNotFoundException e) 
					{
						System.out.println("unable to save");
					}
				}
				break;
			case 'l':
				if (remainder.equals(""))
				{
					System.out.println("invalid command");
				}
				else if (remainder.charAt(0) != ' ')
				{
					System.out.println("invalid command");
				}
				else
				{
					//The new loop with the loaded data
					loop = new MessageLoop<List<String>>();
					//File that stores the message loop
					File loadedFile = new File(remainder3);
					try 
					{
						//Scanner object used to read the loaded file
						Scanner stdin = new Scanner(loadedFile);
						while (stdin.hasNext())
						{
							//The list of DotMatrix character sequence
							List<String> currNode = new ArrayList<String>();
							//Variable that reads one line at a time
							String line = stdin.nextLine();
							if (line.equals("##########"))
							{
								loop.addAfter(currNode);
								currNode = new ArrayList<String>();
							}
							else
							{
								currNode.add(line);
							}
						}
					} 
					catch (FileNotFoundException e) 
					{
						System.out.println("unable to load");
					}
				}
				break;
			case 'd':
				if (loop.size() == 0)
				{
					System.out.println("no messages");
				}
				else
				{
					//Variable that holds the DotMatrix representation of 
					//the current node in the loop
					List<String> curr1 = new ArrayList<String>();
					for (int i = 0; i < loop.size(); i++)
					{
						System.out.println();
						curr1 = loop.getCurrent();
						for (int j = 0; j < curr1.size(); j++)
						{
							System.out.println(curr1.get(j));
						}
						loop.forward();
					}
					System.out.println();
				}
				break;
			case 'n':
				if (loop.size() == 0)
				{
					System.out.println("no messages");
				}
				else
				{
					loop.forward();
					List<String> curr2 = new ArrayList<String>();
					curr2 = loop.getCurrent();
					if (loop.size() == 1)
					{
						//Print current context (Loop is 1 format)
						System.out.println("**********");
						System.out.println(loop.getCurrent());
						for (int i = 0; i < curr2.size(); i++)
						{
							System.out.println(curr2.get(i));
						}
						System.out.println("**********");
					}
					else if (loop.size() == 2)
					{
						loop.back();
						curr2 = loop.getCurrent();
						//Print current context (Loop is 2 format)
						System.out.println("**********");
						for (int i = 0; i < curr2.size(); i++)
						{
							System.out.println(curr2.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr2 = loop.getCurrent();
						for (int i = 0; i < curr2.size(); i++)
						{
							System.out.println(curr2.get(i));
						}
					}
					else
					{
						//Print current context (Loop is normal format)
						loop.back();
						curr2 = loop.getCurrent();
						for (int i = 0; i < curr2.size(); i++)
						{
							System.out.println(curr2.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr2 = loop.getCurrent();
						for (int i = 0; i < curr2.size(); i++)
						{
							System.out.println(curr2.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr2 = loop.getCurrent();
						for (int i = 0; i < curr2.size(); i++)
						{
							System.out.println(curr2.get(i));
						}
						loop.back();
					}
				}
				break;
			case 'p':
				if (loop.size() == 0)
				{
					System.out.println("no messages");
				}
				else
				{
					loop.back();
					List<String> curr3 = new ArrayList<String>();
					curr3 = loop.getCurrent();
					if (loop.size() == 1)
					{
						//Print current context (Loop is 1 format)
						System.out.println("**********");
						for (int i = 0; i < curr3.size(); i++)
						{
							System.out.println(curr3.get(i));
						}
						System.out.println("**********");
					}
					else if (loop.size() == 2)
					{
						loop.back();
						curr3 = loop.getCurrent();
						//Print current context (Loop is 2 format)
						System.out.println("**********");
						for (int i = 0; i < curr3.size(); i++)
						{
							System.out.println(curr3.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr3 = loop.getCurrent();
						for (int i = 0; i < curr3.size(); i++)
						{
							System.out.println(curr3.get(i));
						}
					}
					else
					{
						//Print current context (Loop is normal format)
						loop.back();
						curr3 = loop.getCurrent();
						for (int i = 0; i < curr3.size(); i++)
						{
							System.out.println(curr3.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr3 = loop.getCurrent();
						for (int i = 0; i < curr3.size(); i++)
						{
							System.out.println(curr3.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr3 = loop.getCurrent();
						for (int i = 0; i < curr3.size(); i++)
						{
							System.out.println(curr3.get(i));
						}
						loop.back();
					}
				}
				break;
			case 'j':
				boolean errorCheck = false;
				if (remainder.equals(""))
				{
					System.out.println("invalid command");
					errorCheck = true;
				}
				else if (remainder.charAt(0) != ' ')
				{
					System.out.println("invalid command");
					errorCheck = true;
				}
				//Variable that checks to see if remainder is able
				//to parse to an integer
				remainder = remainder.trim();
				boolean parsable = true;
				try
				{
					Integer.parseInt(remainder);
				}
				catch(NumberFormatException e)
				{
					parsable = false;
				}
				if (!parsable && !errorCheck)
				{
					System.out.println("invalid command");
				}
				if (parsable)
				{
					if (loop.size() == 0)
					{
						System.out.println("no messages");
					}
					else if (Integer.parseInt(remainder) > 0)
					{
						for (int i = 0; i < Integer.parseInt(remainder); i++)
						{
							loop.forward();
						}
						List<String> curr4 = new ArrayList<String>();
						curr4 = loop.getCurrent();
						if (loop.size() == 1)
						{
							//Print current context (Loop is 1 format)
							System.out.println("**********");
							for (int i = 0; i < curr4.size(); i++)
							{
								System.out.println(curr4.get(i));
							}
							System.out.println("**********");
						}
						else if (loop.size() == 2)
						{
							loop.back();
							curr4 = loop.getCurrent();
							//Print current context (Loop is 2 format)
							System.out.println("**********");
							for (int i = 0; i < curr4.size(); i++)
							{
								System.out.println(curr4.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr4 = loop.getCurrent();
							for (int i = 0; i < curr4.size(); i++)
							{
								System.out.println(curr4.get(i));
							}
						}
						else
						{
							//Print current context (Loop is normal format)
							loop.back();
							curr4 = loop.getCurrent();
							for (int i = 0; i < curr4.size(); i++)
							{
								System.out.println(curr4.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr4 = loop.getCurrent();
							for (int i = 0; i < curr4.size(); i++)
							{
								System.out.println(curr4.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr4 = loop.getCurrent();
							for (int i = 0; i < curr4.size(); i++)
							{
								System.out.println(curr4.get(i));
							}
							loop.back();
						}
					}
					else
					{
						for (int i = Integer.parseInt(remainder); i < 0; i++)
						{
							loop.back();
						}
						List<String> curr = new ArrayList<String>();
						curr = loop.getCurrent();
						if (loop.size() == 1)
						{
							//Print current context (Loop is 1 format)
							System.out.println("**********");
							for (int i = 0; i < curr.size(); i++)
							{
								System.out.println(curr.get(i));
							}
							System.out.println("**********");
						}
						else if (loop.size() == 2)
						{
							loop.back();
							curr = loop.getCurrent();
							//Print current context (Loop is 2 format)
							System.out.println("**********");
							for (int i = 0; i < curr.size(); i++)
							{
								System.out.println(curr.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr = loop.getCurrent();
							for (int i = 0; i < curr.size(); i++)
							{
								System.out.println(curr.get(i));
							}
						}
						else
						{
							//Print current context (Loop is normal format)
							loop.back();
							curr = loop.getCurrent();
							for (int i = 0; i < curr.size(); i++)
							{
								System.out.println(curr.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr = loop.getCurrent();
							for (int i = 0; i < curr.size(); i++)
							{
								System.out.println(curr.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr = loop.getCurrent();
							for (int i = 0; i < curr.size(); i++)
							{
								System.out.println(curr.get(i));
							}
							loop.back();
						}
					}
				}	
				break;
			case 'x':
				if (loop.size() == 0)
				{
					System.out.println("no messages");
				}
				
				else
				{
					loop.removeCurrent();
					List<String> curr5 = new ArrayList<String>();
					curr5 = loop.getCurrent();
					if (loop.size() == 0)
					{
						System.out.println("no messages");
					}									
					else if (loop.size() == 1)
					{
						//Print current context (Loop is 1 format)
						System.out.println("**********");
						for (int i = 0; i < curr5.size(); i++)
						{
							System.out.println(curr5.get(i));
						}
						System.out.println("**********");
					}
					else if (loop.size() == 2)
					{
						loop.back();
						curr5 = loop.getCurrent();
						//Print current context (Loop is 2 format)
						System.out.println("**********");
						for (int i = 0; i < curr5.size(); i++)
						{
							System.out.println(curr5.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr5 = loop.getCurrent();
						for (int i = 0; i < curr5.size(); i++)
						{
							System.out.println(curr5.get(i));
						}
					}
					else
					{
						//Print current context (Loop is normal format)
						loop.back();
						curr5 = loop.getCurrent();
						for (int i = 0; i < curr5.size(); i++)
						{
							System.out.println(curr5.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr5 = loop.getCurrent();
						for (int i = 0; i < curr5.size(); i++)
						{
							System.out.println(curr5.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr5 = loop.getCurrent();
						for (int i = 0; i < curr5.size(); i++)
						{
							System.out.println(curr5.get(i));
						}
						loop.back();
					}
				}
				break;
			case 'a':
				try
				{
					if (remainder.equals(""))
					{
						System.out.println("invalid command");
					}
					else if (remainder.charAt(0) != ' ')
					{
						System.out.println("invalid command");
					}
					else
					{
						for (int i = 0; i < wordsInLine.length; i++)
						{
							//Variable that converts each string to a char so 
							//that we can check the if it's in the correct 
							//range
							char ch = wordsInLine[i].charAt(0);
							if (!(ch >= 'A' && ch <= 'Z') && (ch != ' '))
							{
								throw new UnrecognizedCharacterException();
							}
							else
							{
								loop.addAfter(matrix.getDotMatrix(
								wordsInLine[i]));
							}
						}
						List<String> curr6 = new ArrayList<String>();
						curr6 = loop.getCurrent();
						if (loop.size() == 1)
						{
							//Print current context (Loop is 1 format)
							System.out.println("**********");
							for (int i = 0; i < curr6.size(); i++)
							{
								System.out.println(curr6.get(i));
							}
							System.out.println("**********");
						}
						else if (loop.size() == 2)
						{
							loop.back();
							curr6 = loop.getCurrent();
							//Print current context (Loop is 2 format)
							System.out.println("**********");
							for (int i = 0; i < curr6.size(); i++)
							{
								System.out.println(curr6.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr6 = loop.getCurrent();
							for (int i = 0; i < curr6.size(); i++)
							{
								System.out.println(curr6.get(i));
							}
						}
						else
						{
							//Print current context (Loop is normal format)
							loop.back();
							curr6 = loop.getCurrent();
							for (int i = 0; i < curr6.size(); i++)
							{
								System.out.println(curr6.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr6 = loop.getCurrent();
							for (int i = 0; i < curr6.size(); i++)
							{
								System.out.println(curr6.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr6 = loop.getCurrent();
							for (int i = 0; i < curr6.size(); i++)
							{
								System.out.println(curr6.get(i));
							}
							loop.back();
						}
					}
				}
				catch (UnrecognizedCharacterException e)
				{
					System.out.println("An unrecognized character "
							+ "has been entered.");
				}
				break;
			case 'i':
				try
				{
					if (remainder.equals(""))
					{
						System.out.println("invalid command");
					}
					else if (remainder.charAt(0) != ' ')
					{
						System.out.println("invalid command");
					}
					else
					{
						for (int i = 0; i < wordsInLine.length; i++)
						{
							//Variable that converts each string to a char so 
							//that we can check the if it's in the correct 
							//range
							char ch = wordsInLine[i].charAt(0);
							if (!(ch >= 'A' && ch <= 'Z') && (ch != ' '))
							{
								throw new UnrecognizedCharacterException();
							}
							else
							{
								loop.addBefore(matrix.getDotMatrix(
								wordsInLine[i]));
							}
						}
						List<String> curr7 = new ArrayList<String>();
						curr7 = loop.getCurrent();
						if (loop.size() == 0)
						{
							System.out.println("no messages");
						}
						else if (loop.size() == 1)
						{
							//Print current context (Loop is 1 format)
							System.out.println("**********");
							for (int i = 0; i < curr7.size(); i++)
							{
								System.out.println(curr7.get(i));
							}
							System.out.println("**********");
						}
						else if (loop.size() == 2)
						{
							loop.back();
							curr7 = loop.getCurrent();
							//Print current context (Loop is 2 format)
							System.out.println("**********");
							for (int i = 0; i < curr7.size(); i++)
							{
								System.out.println(curr7.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr7 = loop.getCurrent();
							for (int i = 0; i < curr7.size(); i++)
							{
								System.out.println(curr7.get(i));
							}
						}
						else
						{
							//Print current context (Loop is normal format)
							loop.back();
							curr7 = loop.getCurrent();
							for (int i = 0; i < curr7.size(); i++)
							{
								System.out.println(curr7.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr7 = loop.getCurrent();
							for (int i = 0; i < curr7.size(); i++)
							{
								System.out.println(curr7.get(i));
							}
							System.out.println("**********");
							loop.forward();
							curr7 = loop.getCurrent();
							for (int i = 0; i < curr7.size(); i++)
							{
								System.out.println(curr7.get(i));
							}
							loop.back();
						}
					}
				}
				catch (UnrecognizedCharacterException e)
				{
					System.out.println("An unrecognized character has"
							+ " been entered.");
				}
				break;
			case 'c':
				if (loop.size() == 0)
				{
					System.out.println("no messages");
				}
				else
				{
					List<String> curr8 = new ArrayList<String>();
					curr8 = loop.getCurrent();
					if (loop.size() == 1)
					{
						//Print current context (Loop is 1 format)
						System.out.println("**********");
						for (int i = 0; i < curr8.size(); i++)
						{
							System.out.println(curr8.get(i));
						}
						System.out.println("**********");
					}
					else if (loop.size() == 2)
					{
						loop.back();
						curr8 = loop.getCurrent();
						//Print current context (Loop is 2 format)
						System.out.println("**********");
						for (int i = 0; i < curr8.size(); i++)
						{
							System.out.println(curr8.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr8 = loop.getCurrent();
						for (int i = 0; i < curr8.size(); i++)
						{
							System.out.println(curr8.get(i));
						}
					}
					else
					{
						//Print current context (Loop is normal format)
						loop.back();
						curr8 = loop.getCurrent();
						for (int i = 0; i < curr8.size(); i++)
						{
							System.out.println(curr8.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr8 = loop.getCurrent();
						for (int i = 0; i < curr8.size(); i++)
						{
							System.out.println(curr8.get(i));
						}
						System.out.println("**********");
						loop.forward();
						curr8 = loop.getCurrent();
						for (int i = 0; i < curr8.size(); i++)
						{
							System.out.println(curr8.get(i));
						}
						loop.back();
					}
				}
				break;
			case 'r':
				try
				{
					if (remainder.equals(""))
					{
						System.out.println("invalid command");
					}
					else if (remainder.charAt(0) != ' ' ||
					remainder.length() != 2)
					{
						System.out.println("invalid command");
					}
					else
					{
						char ch = remainder.charAt(1);
						if (!(ch >= 'A' && ch <= 'Z') && (ch != ' '))
						{
							throw new UnrecognizedCharacterException();
						}
						else
						{
							remainder = remainder.trim();
							loop.removeCurrent();
							loop.addBefore(matrix.getDotMatrix(remainder));
							List<String> curr9 = new ArrayList<String>();
							curr9 = loop.getCurrent();
							if (loop.size() == 0)
							{
								System.out.println("no messages");
							}
							else if (loop.size() == 1)
							{
								//Print current context (Loop is 1 format)
								System.out.println("**********");
								for (int i = 0; i < curr9.size(); i++)
								{
									System.out.println(curr9.get(i));
								}
								System.out.println("**********");
							}
							else if (loop.size() == 2)
							{
								loop.back();
								curr9 = loop.getCurrent();
								//Print current context (Loop is 2 format)
								System.out.println("**********");
								for (int i = 0; i < curr9.size(); i++)
								{
									System.out.println(curr9.get(i));
								}
								System.out.println("**********");
								loop.forward();
								curr9 = loop.getCurrent();
								for (int i = 0; i < curr9.size(); i++)
								{
									System.out.println(curr9.get(i));
								}
							}
							else
							{
								//Print current context (Loop is normal format)
								loop.back();
								curr9 = loop.getCurrent();
								for (int i = 0; i < curr9.size(); i++)
								{
									System.out.println(curr9.get(i));
								}
								System.out.println("**********");
								loop.forward();
								curr9 = loop.getCurrent();
								for (int i = 0; i < curr9.size(); i++)
								{
									System.out.println(curr9.get(i));
								}
								System.out.println("**********");
								loop.forward();
								curr9 = loop.getCurrent();
								for (int i = 0; i < curr9.size(); i++)
								{
									System.out.println(curr9.get(i));
								}
								loop.back();
							}
						}
					}
				}
				catch (UnrecognizedCharacterException e)
				{
					System.out.println("An unrecognized character has been"
							+ " entered.");
				}
				break;
			case 'q':
				System.out.println("quit");
				stop = true;
				break;
			default:
				System.out.println("invalid command");
				break;
			}
		}
	}
}
