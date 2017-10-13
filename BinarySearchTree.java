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

import java.io.*;							// used for file output function


public class BinarySearchTree extends BinaryNode implements BSTInterface{
	
	

	String preorderTemp = "Pre-order: ";			// used for preorderToString()
	String inorderTemp = "In-order: ";				// used for inorderToString() function
	String postorderTemp = "Post-order: ";			// used for postorderToString()
	
	String fileName = "C://Users//Eric//"	// file path for output.txt
			+ "Desktop//output.txt";
	
	/*
	String fileName = System.getProperty("user.dir") 
			+ File.separator + "output.txt";
	*/
	private BinaryNode head = null;
	
	
	@Override
	public BinaryNode getNode(int entry){			// to search for a node via entry
		BinaryNode result = getNode(entry, head);	// begin at head of bst
		return result;
	}
	
	@Override
	public BinaryNode getNode(int entry, BinaryNode node){	
		BinaryNode result = node;					
		
		if(node.getData() == entry){				// if node data matches entry data then finished
			result = node;
		} else if(node.getData() > entry){			// towards leftChild
			if (node.hasLeftChild()){
				result = getNode(entry, node.getLeftChild());	//recursive call to start at leftchild
			}else{
				result = null;
				System.out.print(entry + " doesn't exist!");
			}	
		} else if(node.getData() < entry) { 		// towards rightChild
			if(node.hasRightChild()){
				result = getNode(entry, node.getRightChild());	//recursive call to start at rightchild
			}else{
				result = null;
				System.out.print(entry + " doesn't exist!");
			}
		}
		return result;
	}
	
	
	@Override
	public int add( int newEntry ){
		int result = 0;
		if (head == null){ // Tree is empty
			BinaryNode temp = new BinaryNode(newEntry);
			head = temp;
			
		}else{
			result = addEntry( head, newEntry);		// calls method to add entry
		}
		return result;
	}
	@Override
	public int addEntry(BinaryNode root, int newEntry){
		int result = 0;
		
		if(root.getData() == newEntry) {			//data is same in current node
			System.out.println(newEntry + " already exists, ignore.");
			result = root.getData();
		}
		else if(root.getData() > newEntry){ 		// towards leftChild
			if(root.hasLeftChild())					// left child exists then re-call addEntry
				result = addEntry(root.getLeftChild(), newEntry);
			else										// left child is empty so set new node
				root.setLeftChild(new BinaryNode(newEntry));	// exits function
		}
		else if(root.getData() < newEntry){			// towards rightChild
			if(root.hasRightChild())				// right child exists then re-call addEntry
				result = addEntry(root.getRightChild(), newEntry);
			else										// right child is empty so set new node
				root.setRightChild(new BinaryNode(newEntry));	// exits function
		}
			
		return result;
	}
	
	@Override
	public void remove(int entry) throws IOException {			// initially called 
		if(getNode(entry) != null){			// checks if entry exists, prompt handled by getNode()
			 head = remove( head , entry);			// calls other remove() function using node and entry
			
			System.out.print("In-order: ");			
			inorderTraverse(head);					// inorder traverse to console
			printInorderToFile(); 					// saving to file after deletion
			
		}									// if entry doesnt exist then exits from remove() function
	}
	
	@Override
	public BinaryNode remove(BinaryNode root, int entry){
		
		if (root == null)					// BASE CASE
			return root;
		
		if (root.getData() > entry)			// towards leftChild
			root.setLeftChild(remove(root.getLeftChild(), entry));
		else if (root.getData() < entry)	// towards rightChild
			root.setRightChild(remove(root.getRightChild(), entry));
		else{								// root.getData() == entry
			if(!root.hasLeftChild())		// root node doesnt have leftChild
				return root.getRightChild();
			else if(!root.hasRightChild())	// root node doesnt have rightChild
				return root.getLeftChild();
											// perform data swap
			root.setData(leftMostNode(root.getRightChild()));
											// deletes the inorder successor 
			root.setRightChild(remove(root.getRightChild(), root.getData()));
		}
		
		return root;
	}
	
	@Override
	public int rightMostNode(BinaryNode node){	// finds right most node
		int result = 0;
		 
		if(node.hasRightChild()){				// if a right child exists
			result = rightMostNode(node.getRightChild());	//recursive call 
		}else
			result = node.getData();			// if no right child, finish 
		
		return result;
	}
	
	@Override
	public int leftMostNode(BinaryNode node){ 	// finds left most node
		int result = 0;
		
		if(node.hasLeftChild()){				// if a left child exists
			result = leftMostNode(node.getLeftChild());		//recursive call
		}else
			result = node.getData();
		
		return result;
	}
	
	@Override
	public int findSuccessor(int entry){
		int result = 0;
		BinaryNode tempNode = getNode(entry);	// go to node in question
		if(tempNode == null){
			System.out.print("Sorry this entry doesnt exist");
		}else{									
			if(tempNode.hasRightChild()){		// check for right child
				result = leftMostNode(tempNode.getRightChild());
			}else{
				
				String[] tempString = inorderToString(head).split("\\s+");
					
				loop : for( int i = 1; i < tempString.length / 2; ++i){
					if(Integer.parseInt(tempString[i]) == entry
							&& (i+1) != 0){
						result = Integer.parseInt(tempString[i+1]);
						break loop;
					}
				}
			System.out.print("entry has no Successor"); // prints if at last entry
			}
		}
		return result;
		
	}
	
	@Override
	public int findPredecessor(int entry){ 
		int result = 0;
		BinaryNode tempNode = getNode(entry);	// go to node in question
		if(tempNode == null){					// if node doesnt exist 
			System.out.print("Sorry this entry doesnt exist");
		}else{									
			if(tempNode.hasLeftChild()){		// check for left child
				result = rightMostNode(tempNode.getLeftChild());
			}else{
				String[] tempString = inorderToString(head).split("\\s+");
			
				loop : for( int i = 1; i < tempString.length / 2; ++i){
					if(Integer.parseInt(tempString[i]) == entry
							&& (i-1) != 0){
						result = Integer.parseInt(tempString[i-1]);
						break loop;
					}
				}
			}
		}
		if(result == 0 || result > entry)
			System.out.print("entry has no Predecessor");
		
		return result;
	}
	
	@Override
	public String preorderToString(BinaryNode node) {
		if(node != null){				// if node exists 
			preorderTemp += node.getData() + " "; // root
			preorderToString(node.getLeftChild());	// left 
			preorderToString(node.getRightChild()); // right
		}
		
		return preorderTemp;
	}
	
	@Override
	public void inorderTraverse(BinaryNode node) {
		
		if( node != null){						// if node exists
			inorderTraverse(node.getLeftChild());	// left
			System.out.print(node.getData() + " ");	// root ( prints to console )
			inorderTraverse(node.getRightChild());	// right
		}
	}
	
	@Override
	public String inorderToString(BinaryNode node) {
		//String inorderTemp = "";
		if( node != null){						// if node exists
			inorderToString(node.getLeftChild());	// left
			inorderTemp += node.getData() + " ";	// root ( saves to string )
			inorderToString(node.getRightChild());	// right
		}
		//result = inorderTemp;
		//inorderTemp = "In-order: ";					// resetting inorderTemp to clear string
		return inorderTemp;		 						// return string
	}

	
	@Override
	public String postorderToString(BinaryNode node) {
		if(node != null){
			postorderToString(node.getLeftChild());	// left
			postorderToString(node.getRightChild());// right
			postorderTemp += node.getData() + " ";	// root ( prints to console )
		}
		return postorderTemp;
	}
	
	
	@Override
	public BinaryNode getRoot(){
		return head;							// returns root node of BST
	}
	
	@Override
	public void printTraversalsToFile() throws IOException{

		File file = new File(fileName);			// using FileWriter to write to output.txt
		FileWriter outFile = new FileWriter(file , false); // overwrite data in file initially
												// prints inorder Traversal to output.txt file
		outFile.write(preorderToString(head) + "\n");
		preorderTemp = "pre-order: ";			// clearing out preorder String 
		outFile.write(inorderToString(head) + "\n");
		inorderTemp = "In-order: ";				// clearing out inorder String
		outFile.write(postorderToString(head) + "\n");
		postorderTemp = "Post-order: ";
	
		outFile.close();				// closing output file
	}
	
	@Override
	public void printInorderToFile() throws IOException{
		
		File file = new File(fileName);
		FileWriter outFile = new FileWriter(file, true); // append data to file
		
		outFile.write(inorderToString(head) + "\n");	// write inorderTraversal to output
		
		inorderTemp = "In-order: ";				// clears out string for next input
		
		outFile.close();				// closing output file
	}

}
