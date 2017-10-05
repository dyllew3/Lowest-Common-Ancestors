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

}
