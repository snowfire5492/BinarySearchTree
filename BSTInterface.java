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

import java.io.*;				// used for IOException 

public interface BSTInterface {
	
	
	/**
	 * checks if entry exists, if so then replace entry with newEntry
	 * else adds a new entry to BST
	 * @param newEntry  an entry to add
	 * @return existing entry if replacement scenario, else 0 
	 */
	public int add(int newEntry) throws IOException;
	
	/**
	 * used for recursive call during Insertion operation
	 * @param rootNode current rootNode 
	 * @param newEntry an entry to add
	 * @return existing entry if replacement scenario, else 0
	 */
	public int addEntry(BinaryNode rootNode, int newEntry);
	
	
	/**
	 * checks if entry exists, if so then call remove(node,int)
	 * @param entry an entry to remove
	 */
	public void remove(int entry) throws IOException;
	
	/**
	 * recursive function called from above remove(int) function
	 * @param node 			
	 * @param entry
	 * @return BinaryNode 	last return will be null which will 
	 * 						cause unwrapping of recursive function
	 */
	public BinaryNode remove(BinaryNode node, int entry);
	
	/**
	 * used to find a specific entry within the bst
	 * @param entry	value to be found
	 * @return the node that contains this value or null if not found
	 */
	public BinaryNode getNode(int entry);
	
	/**
	 * used in conjunction with above, this is to recursively search tree
	 * @param entry
	 * @param node current node to start with 
	 * @return the node that contains value or null if not found 
	 */
	public BinaryNode getNode(int entry, BinaryNode node);
	
	/**
	 * finds right most node starting from a given node
	 * @param node to start looking from 
	 * @return int value within rightMost node
	 */
	public int rightMostNode(BinaryNode node);
	
	/**
	 * finds left most node starting from a given node
	 * @param node to start looking from
	 * @return int value within leftMost node
	 */
	public int leftMostNode(BinaryNode node);
	
	/**
	 * locates the inorder successor of any given entry
	 * @param entry to begin looking from
	 * @return the inorder successor value
	 */
	public int findSuccessor(int entry);
	
	/**
	 * locates the inorder predecessor of any given entry. 
	 * @param entry to begin looking from
	 * @return the inorder predecessor value
	 */
	public int findPredecessor(int entry);
	
	
	/**
	 * prints out BST in inorder Traversal
	 * @param node to pass left/right child into recursive call
	 */
	public void inorderTraverse(BinaryNode node);
	
	/**
	 * creates a String of inorderTraversal used to save data to output file
	 * @param node head of node, use getRoot()
	 * @return a string containing inorderTraversal of data
	 */
	public String inorderToString(BinaryNode node);
		
	
	/**
	 * returns main root node of bst
	 * @return binaryNode 
	 */
	public BinaryNode getRoot();
	
	/**
	 * prints BST inorder Traversal to file
	 */
	public void printTraversalsToFile() throws IOException;
	
	/**
	 * creates a String of preorderTraversal used to save data and print data
	 * @param node root node
	 * @return String containing preorderTraversal
	 */
	public String preorderToString(BinaryNode node);
	
	/**
	 * create a string of postorderTraversal used to save data and print data
	 * @param node the root node
	 * @return String containing postorder
	 */
	public String postorderToString(BinaryNode node);
	
	/**
	 * used to print Inorder Traversal to file
	 * @throws IOException
	 */
	public void printInorderToFile() throws IOException;
}
