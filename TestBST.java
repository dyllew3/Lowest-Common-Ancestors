import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class TestBST {

    public static int MIN_NUM_TESTS = 10000;

    @Rule
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
    public void testNodeClass(){
	/**
	 * This test evaluates if Node a can be initialised and then
	 * have its attributes altered.
	 **/
	BST<Integer>testBST = new BST<Integer>();
	for(int i = 0; i < MIN_NUM_TESTS; i++){
	    Random rn = new Random();
	    int testVal = rn.nextInt();
	    BST<Integer>.Node testNode = testBST.new Node(testVal);
	    // Convert Node value attribute back to int primitive type
	    // and compare to int value
	    // This is done as the int is stored as a class in the Node and
	    // comparing the Integer class to the int primitive type will cause
	    // the assertion to fail despite their values being equal
	    int nodeVal = testNode.value;
	    assertEquals("Node value and " + testVal, testVal, nodeVal);
	    // Reassign test value
	    testVal += rn.nextInt();
	    nodeVal = testNode.value;
	    // Check that test value and node value are no longer equal
	    assertFalse(testVal == nodeVal);
	    
	}
    }

    
}
