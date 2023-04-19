
public class LocNode<T> {
	public Location key;
	public List<T> data;
	public LocNode<T> c1,c2,c3,c4;
	
	public LocNode(Location key, T data) {
		super();
		this.key = key;
		this.data = new LinkedList<T>();
		this.data.insert(data);
		this.c1=this.c2=this.c3=this.c4=null;
	}
	public LocNode(Location key, T data, LocNode<T> c1, LocNode<T> c2, LocNode<T> c3, LocNode<T> c4) {
		super();
		this.key = key;
		this.data = new LinkedList<T>();
		this.data.insert(data);
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
	}

	

}
