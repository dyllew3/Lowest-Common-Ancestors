import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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

    
    public ArrayList<E> lca(E val1, E val2){
	ArrayList<E> result = new ArrayList<E>();
	HashMap<E, Integer> anc1 = ancestors(val1);
	HashMap<E, Integer> anc2 = ancestors(val2);
	if(anc1.get(val2) != null){
	    result.add(val2);
	    return result;
	}
	if(anc2.get(val1) != null){
	    result.add(val1);
	    return result;
	}
	HashSet<E> common = new HashSet<E>(anc1.keySet());
	common.retainAll(anc2.keySet());
	int min1 = Integer.MAX_VALUE;
	int min2 = Integer.MAX_VALUE;
	for(E key : common){
	    Integer dist1 = anc1.get(key);
	    Integer dist2 = anc2.get(key);
	    if(dist1 < min1 && dist2 < min2){
		min1 = dist1;
		min2 = dist2;
		Iterator<E> i = result.iterator();
		while(i.hasNext()){
		    E newKey = i.next();
		    if(anc1.get(newKey) > min1 && anc2.get(newKey) > min2)
			i.remove();
		}
		result.add(key);
	    }
	    else if(dist1 < min1 && dist2 >= min2 ||
		    dist1 >= min1 && dist2 < min2 ||
		    dist1 == min1 && dist2 == min2){
		result.add(key);
	    }
	}
	return result;
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
