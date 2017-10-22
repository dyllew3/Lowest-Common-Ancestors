import java.util.HashMap;

public class DAG<E extends Comparable<E>> implements LCA<E> {

    public class Node{
	E value;
	
    };

    
    private HashMap<E,Node[]> vertices;
    private int size;


    public DAG(){
	this.size = 0;
    }

    @Override
    public E lca(E val1, E val2){
	return val1;
    }
    
}
