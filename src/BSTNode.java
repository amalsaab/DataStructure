
public class BSTNode<K extends Comparable<K>,T> {
	public K key;
	public T data;
	public BSTNode<K,T> left,right;
	
	public BSTNode(K k, T d) {
		this.key=k;
		this.data=d;
		left=right=null;
	}
	public BSTNode(K k, T d,BSTNode<K,T> l,BSTNode<K,T> r) {
		this.key=k;
		this.data=d;
		this.left=l;
		this.right=r;
	}
		
}
