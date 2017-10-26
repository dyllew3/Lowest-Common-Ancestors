import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

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
	    {1,1,1,2,2,3,5,3,}
	};
	int index = 0;
	int [] sizes = {0,1,2,4,9,10,8,4};
	for (int[] tests :testVals){	
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

}
