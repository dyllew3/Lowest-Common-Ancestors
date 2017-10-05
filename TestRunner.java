import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

// This class runs all  tests in the TestBST class 
public class TestRunner {
    
    public static void main(String[] args) {
	Result result = JUnitCore.runClasses(TestBST.class);
	for (Failure failure : result.getFailures()) {
	    System.out.println(failure.toString());
	}
	if(result.wasSuccessful())
	    System.out.println("All tests pass!");
    }
}
