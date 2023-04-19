
public class TreeLocator<T> implements Locator<T> {
	private LocNode<T> root,current;
	
	public TreeLocator() {
		super();
		root=current=null;
	}

	@Override
	public int add(T e, Location loc) {
		if(loc==null) return 0;
		Pair<Boolean, Integer> pair=find(loc);
		if(pair.first) {
			current.data.insert(e);
			return pair.second;
		}
		LocNode<T> newNode=new LocNode<T>(loc, e);
		if(empty()) {
			root=current=newNode;
			return pair.second;
		}
		else {
			if (current.key.x>loc.x && current.key.y>= loc.y)
				current.c1=newNode;
			else if (current.key.x>=loc.x && current.key.y< loc.y)
				current.c2=newNode;
			else if (current.key.x<loc.x && current.key.y<= loc.y)
				current.c3=newNode;
			else if (current.key.x<=loc.x && current.key.y> loc.y)
				current.c4=newNode;	
			
			current=newNode;
			return pair.second;
		}
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		if(loc==null) return new Pair<List<T>, Integer>(new LinkedList<T>(), 0);
		Pair<Boolean, Integer> pair=find(loc);
		if(pair.first)                        
			return new Pair<List<T>, Integer>(current.data, pair.second);
		else
			return new Pair<List<T>, Integer>(new LinkedList<T>(), pair.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		if(loc==null || e==null) return new Pair<Boolean, Integer>(false,0);
		Pair<Boolean, Integer> pair=find(loc);
		if (pair.first)
			if(pair.first=removeAllData(e))
				return pair;
	
		return pair;
	
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> listget = new LinkedList<Pair<Location, List<T>>>();
		inList(listget, root);
	
		return listget;
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		List<Pair<Location, List<T>>> list = new LinkedList<Pair<Location, List<T>>>();
		Pair<List<Pair<Location, List<T>>>, Integer> pair = new Pair<List<Pair<Location, List<T>>>, Integer>(list, 0);
		if(lowerLeft!=null && upperRight!=null) 
			inRange(pair, root, lowerLeft, upperRight);

		return pair;
	}

	
	// private method>>> ||
	//               >>> \/
	
	private void inList(List<Pair<Location, List<T>>> inc, LocNode<T> r) {
		if (r==null) return;
		
		inc.insert(new Pair<Location, List<T>>(r.key, r.data));
		if(r.c1!=null)
			inList(inc,r.c1);
		if(r.c2!=null)
			inList(inc,r.c2);
		if(r.c3!=null)
			inList(inc,r.c3);
		if(r.c4!=null)
			inList(inc,r.c4);
	}
	private void inRange(Pair<List<Pair<Location, List<T>>>, Integer> pair, LocNode<T> r, Location ll, Location ur) {
		if(r==null) return;
		
		pair.second++;
		if( (r.key.x>= ll.x && r.key.x<=ur.x) && (r.key.y>= ll.y && r.key.y<=ur.y) ) {
			pair.first.insert(new Pair<Location, List<T>>(r.key, r.data));
			
		}
		Location lr = new Location(ll.x, ur.y );
		Location ul = new Location(ur.x, ll.y );
		
		if(r.c1!=null && ( (r.key.x>ur.x && r.key.y >=ur.y) || (r.key.x>ll.x && r.key.y >=ll.y) ) )
			inRange(pair, r.c1, ll, ur);
		if(r.c2!=null && ( (r.key.x>=lr.x && r.key.y <lr.y) || (r.key.x>=ul.x && r.key.y <ul.y) ))
			inRange(pair, r.c2, ll, ur);
		if(r.c3!=null && ( (r.key.x<ll.x && r.key.y <=ll.y) || (r.key.x<ur.x && r.key.y <=ur.y) ))
			inRange(pair, r.c3, ll, ur);
		if(r.c4!=null && ( (r.key.x<=ul.x && r.key.y >ul.y) || (r.key.x<=lr.x && r.key.y >lr.y) ))
			inRange(pair, r.c4, ll, ur);
		
			
	}

	
	private boolean removeAllData(T e) {
		if(current.data.empty()) return false;
		boolean found=false;
		current.data.findFirst();
		
		while(!current.data.last()) {
			if(current.data.retrieve().equals(e)) {
				current.data.remove();
				found=true;
			}else
				current.data.findNext();
		}
		if(current.data.retrieve().equals(e)) {
			current.data.remove();
			found=true;
		}
		return found;
		
	}
	
	private boolean empty() {
		return root==null; 
	}
	private Pair<Boolean, Integer> find(Location loc){
		
		LocNode<T> p=root, child=root;
		int count =0;
		if (empty() || loc == null) 
			return new Pair<Boolean, Integer>(false, count);
		
		while(child != null) {
			p=child;
			count++;
			if (child.key.x==loc.x && child.key.y== loc.y) {
				current=child; 
				return new Pair<Boolean, Integer>(true, count);
			}
			else if (child.key.x>loc.x && child.key.y>= loc.y)
				child=child.c1;
			else if (child.key.x>=loc.x && child.key.y< loc.y)
				child=child.c2;
			else if (child.key.x<loc.x && child.key.y<= loc.y)
				child=child.c3;
			else if (child.key.x<=loc.x && child.key.y> loc.y)
				child=child.c4;				
		}
		current=p;
		return new Pair<Boolean, Integer>(false, count);
		
	}


}

