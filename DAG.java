import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class DAG<E extends Comparable<E>> implements Graph<E> {

    
    private HashMap<E,HashSet<E>> vertices;
    private HashMap<E,HashSet<E>> parents;
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
	this.parents = new HashMap<E, HashSet<E>>();
    }

    
    @Override
    public E lca(E val1, E val2){
	return val1;
    }

    @Override
    public void insert(E val){
	if(this.vertices.get(val) == null){
	    this.vertices.put(val, new HashSet<E>());
	    this.parents.put(val, new HashSet<E>());
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
	HashSet<E> wv = this.parents.get(w);
	if(!vw.contains(w)){
	    vw.add(w);
	    wv.add(v);
	}
	if(hasCycle()){
	    vw.remove(w);
	    wv.remove(v);
	}
	this.vertices.put(v, vw);
	this.parents.put(w, wv);
    }

    public boolean hasCycle(){
	DFS cycle = new DFS( new HashSet<E>(this.vertices.keySet()));
	return cycle.cycle;
    }

    public HashMap<E,Integer> ancestors(E node){
	/**
	 * Gets ancestors of a node and the shortest distance from the node
	 * to the ancestor
	 *
	 *
	 **/
	HashMap<E, Integer> anc = new HashMap<E, Integer>();
	for(E parent : this.parents.get(node)){
	    anc.put(parent,1);
	    this.ancestors(parent, 2, anc);
	}
	return anc;
	
    }
    
    private void ancestors(E start, int level, HashMap<E, Integer> anc){
	for(E child : this.parents.get(start)){
	    Integer curLev = anc.get(child);
	    if(curLev == null || level < (int)curLev ){
		anc.put(child, level);
	    }
	    this.ancestors(child, level + 1, anc);
	    
	}
    }

    public int getSize(){
	return this.size;
    }
}
