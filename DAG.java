import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class DAG<E extends Comparable<E>> implements Graph<E> {

    
    public HashMap<E,HashSet<E>> vertices;
    private int size;


    public DAG(){
	this.size = 0;
	this.vertices = new HashMap<E, HashSet<E>>();
    }

    
    @Override
    public E lca(E val1, E val2){
	return val1;
    }

    @Override
    public void insert(E val){
	if(this.vertices.get(val) == null){
	    this.vertices.put(val, new HashSet<E>());
	}
    }

    

}
