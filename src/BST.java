

public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	private BSTNode<K,T> root, current;
	

	public BST() {
		super();
		root=current=null;
	}

	@Override
	public boolean empty() {
		return root==null;
	}
	
	@Override
	public boolean full() {
		return false;
	}
	
	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data=e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K, T> c= current;
		Pair<Boolean, Integer> p= findKey(key);
		if(key==null) return p;
		if(!p.first) // if p.first is false return current on its place before call method find(K key).
			current=c;
		
		return p;
	}
	
	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<K, T> newNode ,c= current;
		Pair<Boolean, Integer> p= findKey(key);
		if(key==null/*|| data == null*/) return p;
		if(p.first) {
			current=c;
			p.first = !p.first;
			return p ;
		}
		// key does not exist
		p.first = !p.first;
		newNode =new BSTNode<K, T>(key, data);
		if (empty()) {
			root= current= newNode;
			return p;
		}
		else {
			// insert key on correct place and made new current 
			if(current.key.compareTo(key)>0)
				current.left=newNode;
			else
				current.right=newNode;
			
			current=newNode;
			return p;
		}		
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		Pair<Boolean, Integer> pair = new Pair<Boolean, Integer>(false,0);
		if(key==null) return pair;
		BSTNode<K, T> q;
		q = remove(key, root, pair);
		current=root=q;
		return pair;
	}

	
	@Override
	public List<K> getAll() {
		List<K> getlist = new LinkedList<K>();
		inList(getlist, root);
		 
		return getlist;
	}
	
	// private method>>> ||
	//               >>> \/
	
	private void inList(List<K> inc, BSTNode<K, T> r) {
		if (r==null) return;
		
		if(r.left!=null)
			inList(inc,r.left);
		inc.insert(r.key);
		if(r.right!=null)
			inList(inc,r.right);
	}
	
	private BSTNode<K, T> remove(K key, BSTNode<K, T> r, Pair<Boolean, Integer> pair){
		if(r==null) 
			return null; 
		pair.second++;
		BSTNode<K, T> min, child=null;
		if(r.key.compareTo(key)>0)
			r.left=remove(key, r.left,pair);
		else if(r.key.compareTo(key)<0)
			r.right=remove(key, r.right,pair);
		else {
			pair.first=true;
			if (r.left!=null && r.right!=null) {
				min=findmin(r.right);
				r.key=min.key;
				r.data=min.data;
				int temp=pair.second;
				r.right=remove(min.key, r.right,pair);
				pair.second=temp;
			}else {
				if(r.right==null)
					child=r.left;
				else if(r.left==null)
					child=r.right;
				return child;
			}
			
		}
		return r;
	}
	private BSTNode<K, T> findmin(BSTNode<K, T> r){
		if (r==null)// this case is impossible in this program because we called after check if(r.left!=null && r.right!=null)
			return null;
		while(r.left!=null)
			r=r.left;
		
		return r;
	}
	
	private Pair<Boolean, Integer> findKey(K key) {
		BSTNode<K, T> q=root,p=root;
		int count=0;
		if(empty() || key==null)
			return new Pair<Boolean,Integer>(false,count);
		
		while(p != null) {
			q=p;
			count++;
			if(p.key.compareTo(key)==0) {
				current=p;
				return new Pair<Boolean,Integer>(true,count);				
			}
			else if(p.key.compareTo(key)>0)
				p=p.left;
			else
				p=p.right;
		}
		current=q;
		return new Pair<Boolean,Integer>(false,count); 
		
	}

}
