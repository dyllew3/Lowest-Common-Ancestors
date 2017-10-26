import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class DAG<E extends Comparable<E>> implements Graph<E> {

    
    private HashMap<E,HashSet<E>> vertices;
    private int size;

    private class DFS{
	
	private HashSet<E> tempMark;
	private HashSet<E> permMark;
	public boolean cycle;
	private ArrayList<E> L;
	
	public DFS(HashSet<E> nodes){
	    tempMark = new HashSet<E>();
	    permMark = new HashSet<E>();
	    L = new ArrayList<E>();
	    this.cycle = false;
	    for(E node : nodes){
		dfs(node);
	    }
	}

	public void dfs(E node){
	    if(permMark.contains(node)){
		return;
	    }
	    if(tempMark.contains(node)){
		this.cycle = true;
		return;
	    }
	    tempMark.add(node);
	    for(E child : getEdges(node)){
		dfs(child);
	    }
	    permMark.add(node);
	    L.add(node);
	}
	
	
    } 

    
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
	    this.size++;
	}
    }

    public HashSet<E> getEdges(E val){
	return this.vertices.get(val);
    }
    

    public void connect(E v, E w){
	this.insert(v);
	this.insert(w);
	HashSet<E> vw = this.vertices.get(v);
	if(!vw.contains(w)){
	    vw.add(w);
	}
	if(hasCycle()){
	    vw.remove(w);
	}
	this.vertices.put(v, vw);
    }

    public boolean hasCycle(){
	DFS cycle = new DFS( new HashSet<E>(this.vertices.keySet()));
	return cycle.cycle;
    }

    public int getSize(){
	return this.size;
    }
}

