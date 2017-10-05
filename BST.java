
public class BST<E extends Comparable<E>> {

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

    public void insert(E value){
	root = insert(root, value);
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
}
