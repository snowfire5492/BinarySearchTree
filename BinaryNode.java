
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
public class BinaryNode {
	
	private int data;				// to store node data
	private BinaryNode leftChild;	// left and right child nodes
	private BinaryNode rightChild;
	
	public BinaryNode(){
		this(0);					// calls next constructor
	}
	public BinaryNode(int entry){
		this(entry, null, null);	// calls next constructor
	}
	public BinaryNode(int entry, BinaryNode newLeft, BinaryNode newRight){
		data = entry;
		leftChild = newLeft;
		rightChild = newRight;
	}								// final constructor
	
	/**
	 * allows to retrieve data
	 * @return value of data in node (will be int)
	 */
	public int getData(){
		return data;
	}
	
	/**
	 * allows to set data
	 * @param entry to be entered as data into node
	 */
	public void setData(int entry){
		data = entry;
	}
	
	/**
	 * allows to retrieve leftChild
	 * @return Node that is LeftChild
	 */
	public BinaryNode getLeftChild(){
		return leftChild;
	}
	/**
	 * to retrieve rightChild
	 * @return Node that is rightChild
	 */
	public BinaryNode getRightChild(){
		return rightChild;
	}
	
	/**
	 * checks to see if leftChild exists
	 * @return true if exists
	 */
	public boolean hasLeftChild(){
		if(leftChild != null)
			return true;
		else
			return false;
	}
	
	/**
	 * checks if rightChild exists
	 * @return true if exists
	 */
	public boolean hasRightChild(){
		if(rightChild != null)
			return true;
		else 
			return false;
	}
	
	/**
	 * sets left child
	 * @param newLeft Node to be set as leftChild
	 */
	public void setLeftChild(BinaryNode newLeft){
		leftChild = newLeft;
	}
	
	/**
	 * sets right child node
	 * @param newRight Node to be set as rightChild
	 */
	public void setRightChild(BinaryNode newRight){
		rightChild = newRight;
	}
	
	
	
}
