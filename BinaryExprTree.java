/**
   A simple binary expression tree implementation.
   <BR> 
   Each node in the tree holds a String that is either a binary operator
   (restricted to "+", "-", "*", "/") or can be treated as an integer value.
   
   @author Jim Teresco
   @version Fall 2019
*/
public class BinaryExprTree {

    /** some constants to define the operators */
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String TIMES = "*";
    public static final String DIVIDE = "/";

    /** the value at the root of this tree, which will always
	be either a String (internal nodes representing operators),
	or an Integer (leaf nodes representing numbers) */
    private Object value;
    /** left child */
    private BinaryExprTree left;
    /** right child */
    private BinaryExprTree right;

    /**
       construct a new BinaryExprTree object for a leaf node, which
       must contain a number

       @param value a number to store in this tree leaf
    */
    public BinaryExprTree(int value) {

	this.value = new Integer(value);
    }
    
    /**
       construct a new BinaryExprTree object for an internal node,
       which must contain an operator, and which must have two 
       children

       @param op the operation at this node
       @param left the left subtree
       @param right the right subtree
    */
    public BinaryExprTree(String op, BinaryExprTree left, BinaryExprTree right) {

	// To do: add error checking to make sure op is a valid operator
	// and that both left and right are not null
	this.value = op;
	this.left = left;
	this.right = right;
    }
    
    /**
       method to check if this is an operator (internal) node.

       @return true if the value is a valid operator, false otherwise
     */
    public boolean isOperator() {
	return value instanceof String;
    }

    /**
       method to evaluate an expression stored in a BinaryTree

       @return an int representing the computed value of this tree
     */
    public int evaluate() {

        if (isOperator()) {
            int leftVal = left.evaluate();
            int rightVal = right.evaluate();
            if (value.equals(PLUS)) return leftVal+rightVal;
            if (value.equals(MINUS)) return leftVal-rightVal;
            if (value.equals(TIMES)) return leftVal*rightVal;
            if (value.equals(DIVIDE)) return leftVal/rightVal;
        }

	return (Integer)value;
    }

    /**
       return a string representation of the expression represented
       by this tree

       @return a string representation of the expression represented by this tree
    */
    public String toString() {

	if (isOperator()) {
	    return "(" + left + " " + value + " " + right + ")";
	}
	return value.toString();
    }
    
    /** main method to set up an answer */
    public static void main(String[] args) {

        /* build and evaluate the binary tree for ((4+3)*(10-5))/2 */
        /* we will build it from the bottom up */
        BinaryExprTree four = new BinaryExprTree(4);
        BinaryExprTree three = new BinaryExprTree(3);
        BinaryExprTree ten = new BinaryExprTree(10);
        BinaryExprTree five = new BinaryExprTree(5);
        BinaryExprTree two = new BinaryExprTree(2);

        /* build the actual tree structure */
        BinaryExprTree plus = new BinaryExprTree(PLUS, four, three);
        BinaryExprTree minus = new BinaryExprTree(MINUS, ten, five);
        BinaryExprTree times = new BinaryExprTree(TIMES, plus, minus);
        BinaryExprTree divide = new BinaryExprTree(DIVIDE, times, two);

        /* what does it look like? */
        System.out.println(divide);

        /* what's the answer */
        System.out.println(divide.evaluate());
    }
}
