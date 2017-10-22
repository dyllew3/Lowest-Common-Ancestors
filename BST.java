public class BST<E extends Comparable<E>> implements LCA<E> {
    
    private Node root;
    private int size;
    
    public class Node {
	public E value;
	public Node left;
	public Node right;

	public Node(E value){
	    this.value = value;
	} 
	    
    }

    public BST(){
	this.root = null;
	this.size = 0;
    }

    public Node getRoot(){
	return this.root;
    }

    public int getSize(){
	return this.size;
    }
    
    public void insert(E value){
	root = insert(root, value);
	size++;
    }

    private Node insert(Node x, E value){
	if(x == null) return new Node(value);
	int cmp = value.compareTo(x.value);
	if(cmp < 0){
	    x.left = insert(x.left, value);
	}
	if(cmp > 0){
	    x.right = insert(x.right, value);
	}
	return x;
    }

    @Override
    public E lca(E val1, E val2){
	/**
	 * Finds the lowest common ancestor of two values.
	 * Lowest common ancestor is defined as the lowest parent
	 * node the values share. 
	 **/
	return this.lca(root, val1, val2);
    }

    private E lca(Node x, E val1, E val2){
	if(x == null) return null;
	int cmp1 = val1.compareTo(x.value);
	int cmp2 = val2.compareTo(x.value);
	// Evaluates if the values diverge at the current node
	if(cmp1 < 0 && cmp2 < 0)
	    return this.lca(x.left, val1, val2);
	else if(cmp1 > 0 && cmp2 > 0)
	    return this.lca(x.right, val1, val2);
	else
	    return x.value;
    }
}
