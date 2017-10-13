/**
 * CS 241
 * Professor: Dr. Wei Sophie
 *
 * Project #1
 *
 * <create a BinarySearchTree that can be filled with user input
 * and certain operations performed including pre, post and inorder 
 * traversals>
 *
 * @author Eric Schenck
 * last modified: 10/13/17
 */

import java.io.*;				// used for data output to file 
import java.util.Scanner;		// used for data input from keyboard

public class Project1 {
	public static void main(String[] args) throws IOException{
		
		BinarySearchTree bst = new BinarySearchTree();	// creating bst object
		
														// prompt to enter data
		System.out.println("Please enter the initial sequence of values:");
		
		Scanner keyboard = new Scanner(System.in);	
		String sLine;							// string to hold input line
	
		sLine = keyboard.nextLine();			// input from keyboard
		
		String[] splitString = sLine.split("\\s+");	// split into an array of strings
		int[] correctInput = new int[splitString.length];	// int array to hold parsed input	
		
		for(int i = 0; i < splitString.length ; ++i){	// parses and saves to correctInput
			
			try{						// tries parsing to int[] if fail gives prompt
				correctInput[i] = Integer.parseInt(splitString[i]);
			} catch( Exception e ){
				System.out.println("incorrect input " + splitString[i]);
			}
		}
		
		for (int i = 0; i< correctInput.length ; ++i) // adding values into BST
		{
			if( correctInput[i] != 0)
				bst.add(correctInput[i]);			// values added to BST
		}
		
		bst.printTraversalsToFile();				// print Traversals to file
		
		System.out.println(bst.preorderToString(bst.getRoot()));	// print Traversals to console
		System.out.println(bst.inorderToString(bst.getRoot()));
		bst.inorderTemp = ("In-order : ");							// clears out inorderTemp
		System.out.print(bst.postorderToString(bst.getRoot()));
		
		E: while(true){								// only exits when command E is entered
			
			int inputValue = 0;						// used to store value entered
			char tempChar = 'a'; 					// used to store command character
			
			System.out.print("\nCommand? ");
			
			String userSel = keyboard.nextLine();	// user input
			String[] splitUserSel = userSel.split("\\s+"); // break up to check contents
			
			try{
				tempChar = userSel.toLowerCase().charAt(0);	// gathers command character
			
			} catch( Exception e ){						// incase of wrong command entry
				System.out.println("Please enter a character next time.");
			}
			if ( tempChar != 'e' && tempChar != 'h' ){
				try{
					inputValue = Integer.parseInt(splitUserSel[1]);	// saves value as int
				} catch( Exception e ){						// incase of wrong value entry
				
					System.out.println("Please enter correct value next time.");
				}
			}
			switch(tempChar) {
				case 'i': 	// used for value insertion
					int test = bst.add(inputValue);
					
					
					if(test == 0){
						System.out.print("In-order: ");
						bst.inorderTraverse(bst.getRoot()); 				// inorder traverse to console
						bst.printInorderToFile(); 					// saving to file after insertion
					}
						break;
					
				case 'd':	// used for value deletion
					bst.remove(inputValue);	
					break;
					
				case 'p': 	// used to find predecessor
					int predecessor = bst.findPredecessor(inputValue);
					if (predecessor != 0 && predecessor < inputValue)
						System.out.print("Predecessor is " + predecessor);
					break;
					
				case 's': 	// used to find successor
					int successor = bst.findSuccessor(inputValue);
					if (successor != 0 && successor > inputValue)
						System.out.print("Successor is " + successor);
					break;
					
				case 'e':	// used to exit program
					System.out.print("Thank you fo using my program!");
					break E;
					
				case 'h': 	// display commands 
					System.out.println("I Insert a value");
					System.out.println("D Delete a value");
					System.out.println("P Find predecessor");
					System.out.println("S Find successor");
					System.out.println("E Exit the program");
					System.out.print("H Display this message");
					break;
					
				default:
					System.out.print("Incorrect Entry, try again.");
					break;
			}
		}
		
		keyboard.close();   			// closing keyboard input
	}
}
