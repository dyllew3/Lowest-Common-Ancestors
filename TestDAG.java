import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.ArrayList;

public class TestDAG {
    
    @Test
    public void testInit(){
	/**
	 * Evaluates if DAG<E extends Comparable<E>> implements Graph<E>
	 * can be initialised Tests a variety of classes 
	 * 
	 **/
	DAG<Integer> testOne = new DAG<Integer>();
	DAG<Double> testTwo = new DAG<Double>();
	DAG<String> testThree = new DAG<String>();
	
    }

    public ArrayList<HashMap<Integer, Integer>> ancestors(){
	ArrayList<HashMap<Integer, Integer>> result;
	result = new ArrayList<HashMap<Integer, Integer>>();
	// List of all ancestors and their distance from the node
	// In the form {Node1, dist1, Node2, dist2}
	int [][] ancNode = {
	    {1, 1, 3, 1},
	    {0, 2, 2, 2, 3, 1, 1, 1},
	    {1, 6, 2, 5, 3, 4, 4, 3, 5, 2, 6, 1},
	    {},
	};
	for(int [] ancs : ancNode){
	    HashMap<Integer, Integer> anc = new HashMap<Integer,Integer>();
	    for(int i = 0; i < ancs.length - 1; i += 2){
		anc.put(ancs[i], ancs[i + 1]);
	    }
	    result.add(anc);
	}
	return result;
    } 
    
    @Test
    public void testInsertTrue(){
	/**
	 * This test evaluates if vertex is being inserted.
	 *.
	 **/
	// A series of values to be inserted into DAG class
	int[][] testVals = {
	    // Worst case
	    {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
	    // Best Case
	    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
	    // Next test case is randomised
	    {1, 4, 25, 3, 5, 64, 6, 7},
	    // Contains duplicates
	    {10, 10, 9, 7, 5, 3, 1, 10, 1}
	};
	for (int[] tests :testVals){	
	    DAG<Integer> dag = new DAG<Integer>();
	    for(int val : tests){
		dag.insert(val);
		assertNotNull(dag.getEdges(val));
	    }
	}
    }
    

    @Test
    public void testInsertFalse(){
	/**
	 * This test evaluates if vertex is being inserted.
	 *.
	 **/
	// A series of values to be inserted into DAG class
	int[][] testVals = {
	    {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
	    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
	    {1, 4, 25, 3, 5, 64, 6, 7},
	};
	int prev = 0;
	for (int[] tests :testVals){	
	    DAG<Integer> dag = new DAG<Integer>();
	    for(int  i = 0; i < tests.length - 1 ; i++){
		assertNull(dag.getEdges(tests[i + 1]));
		dag.insert(tests[i]);
		assertNotNull(dag.getEdges(tests[i]));
	    }
	}

    }

    @Test
    public void testGetSize(){
	int[][] testVals = {
	    {},
	    {0},
	    {1,2},
	    {1, 2, 3, 4},
	    {10, 9, 7, 6, 5, 4, 3, 2, 1},
	    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
	    {1, 4, 25, 3, 5, 64, 6, 7},
	    //Duplicates
	    {1,1,1,2,2,3,5,3}
	};
	int index = 0;
	int [] sizes = {0,1,2,4,9,10,8,4};
	for (int[] tests : testVals){	
	    DAG<Integer> dag = new DAG<Integer>();
	    for(int  i = 0; i < tests.length; i++){
		dag.insert(tests[i]);
	    }
	    assertEquals(sizes[index++], dag.getSize());
	}
	
    }
    
    @Test
    public void testConnectNoCycles(){
	int[][] testVals = {
	    {10, 9, 7, 6, 5, 4, 3, 2, 1},
	    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
	    {1, 4, 25, 3, 5, 64, 6, 7},
	};
	for (int[] tests :testVals){	
	    for(int  i = 0; i < tests.length - 1 ; i++){
		DAG<Integer> dag = new DAG<Integer>();
		dag.insert(tests[i + 1]);
		assertFalse(dag.getEdges(tests[i + 1]).contains(tests[i]));
		dag.connect(tests[i + 1], tests[i]);
		assertTrue(dag.getEdges(tests[i + 1]).contains(tests[i]));
	    }
	}
    }

    @Test
    public void testConnectCycles(){
	int[][] testVals = {
	    {1,2,3,1},
	    {1,2,2,4,3,4,1},
	    {1,2,3,4,5,6,2,9},
	    // Multiple cycles
	    {1,2,4,1,3,5,6,7,5,3}
	};
	
	for (int[] tests :testVals){
	    DAG<Integer> dag = new DAG<Integer>();
	    for(int  i = 0; i < tests.length - 1 ; i++){
		dag.connect(tests[i + 1], tests[i]);
	    }
	    assertFalse(dag.hasCycle());

	}

    }

    @Test
    public void testAncestors(){
	int[][] testVals = {
	    {1,2,3,2},
	    // Duplicate ancestors at 
	    {0,2,2,3,3,4,0,1,1,6,1,4},
	    // Straight line
	    {1,2,2,3,3,4,4,5,5,6,6,7},
	    // No ancestors
	    {1,2,3,2},
	};
	ArrayList<HashMap<Integer, Integer>> ancestors = ancestors();
	int[] ancCheck = {2, 4, 7, 3};
	for (int i = 0; i < testVals.length ; i++){
	    DAG<Integer> dag = new DAG<Integer>();
	    int [] tests = testVals[i];
	    for(int  j = 0; j < tests.length - 1 ; j += 2){
		dag.connect(tests[j], tests[j + 1]);
	    }
	    assertTrue(dag.ancestors(ancCheck[i]).equals(ancestors.get(i)));
	}
	

    }

    
}
