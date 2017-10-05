import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class TestBST {

    @Rule
    // No exception should be thrown during these tests
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testInit(){
	/**
	 * Evaluates if BST<E extends Comparable<E>> can be initialised
	 * Tests a variety of classes that have the compareTo function
	 *
	 **/
	BST<Integer> testOneBST = new BST<Integer>();
	BST<Double> testTwoBST = new BST<Double>();
	BST<String> testThreeBST = new BST<String>();
	
    }

    @Test
    public void testNodeInitTrue(){
	/**
	 * This test evaluates if Node a can be initialised with a value
	 *.
	 **/
	// A series of values to instantiate Node class with for testing
	int[] testVals = {2,3,100, -9, -10, -11, 203, 10000};
	BST<Integer>testBST = new BST<Integer>();
	for(int testVal : testVals ){
	    BST<Integer>.Node testNode = testBST.new Node(testVal);
	    // Convert Node value attribute back to int primitive type
	    // and compare to int value
	    // This is done as the int is stored as a class in the Node and
	    // comparing the Integer class to the int primitive type will cause
	    // the assertion to fail despite their values being equal
	    int nodeVal = testNode.value;
	    assertEquals("Node value and " + testVal, testVal, nodeVal);
	}
    }
    

    @Test
    public void testSetRoot(){
	/**
	 * Tests that the root is set after the first insert
	 * and that it contains the first value inserted
	 *
	 **/
	// Range of values used for  
	int[] rootVals = {Integer.MIN_VALUE,0,-1,43,5,-46,4,54,5,4,
			  Integer.MAX_VALUE};
	for(int val : rootVals){
	    BST<Integer> testBST = new BST<Integer>();
	    assertNull(testBST.getRoot());
	    testBST.insert(val);
	    // Check that root is now not null
	    assertNotNull(testBST.getRoot());
	    int rootVal = testBST.getRoot().value;
	    // Evaluate whether the value stored in root is the value inserted
	    assertEquals("Root value is: " + rootVal + " and inserted val is:"
			 + val, rootVal, val);
	}

    }
    
}
