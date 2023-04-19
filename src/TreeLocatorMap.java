
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	private Map<K, Location> bst;
	private Locator<K> TreeLoc;
	
	public TreeLocatorMap() {
		super();
		this.bst =new BST<K, Location>() ;
		this.TreeLoc  =new TreeLocator<K>();
	}

	@Override
	public Map<K, Location> getMap() {
		return bst;
	}

	@Override
	public Locator<K> getLocator() {
		return TreeLoc;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer> pair = bst.insert(k, loc);//here don't insert if K exist
		if(k==null|| loc == null) return pair;
		if(pair.first) //if inserted K and loc in bst will insert in treeLoc
			TreeLoc.add(k, loc);
			
		return pair;
		
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		if(bst.empty()) return new Pair<Boolean, Integer>(false, 0);//this don't important for logic but became code fast.
		
		Pair<Boolean, Integer> pair = bst.find(k);
		if(k==null|| loc == null) return pair;
		if(pair.first) {//if find return true go in ifStatement 
			Location ofloc = bst.retrieve();
			bst.update(loc);
			if(loc.x!=ofloc.x && loc.y!=ofloc.y) {
				TreeLoc.remove(k, ofloc);
				TreeLoc.add(k, loc);
			}
			
		}
		return pair;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean, Integer> pair = bst.find(k);
		if(pair.first) 
			return new Pair<Location, Integer>(bst.retrieve(),pair.second);
		else
			return new Pair<Location, Integer>(null, pair.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		if(bst.empty()) return new Pair<Boolean, Integer>(false,0);
		
		Pair<Boolean, Integer> pair = bst.find(k);
		if(k==null) return pair;
		
		if(pair.first) {
			Location ofloc = bst.retrieve();
			bst.remove(k);
			TreeLoc.remove(k, ofloc);			
		}
		
		return pair;
	}

	@Override
	public List<K> getAll() {
		return bst.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location, List<K>>>, Integer> pair = TreeLoc.inRange(lowerLeft, upperRight);
		List<K> listRange =new LinkedList<K>();
		if(lowerLeft!=null && upperRight!=null)
			getListKey(pair.first, listRange);
		return new Pair<List<K>, Integer>(listRange, pair.second);
	}
	
	// private method>>> ||
	//               >>> \/
	
	private void getListKey(List<Pair<Location, List<K>>> listpair, List<K> listRange) {
		if(listpair.empty())return;
		
		listpair.findFirst();
		while (!listpair.last()) {
			getKey(listpair.retrieve().second, listRange);
			listpair.findNext();
		}
		getKey(listpair.retrieve().second, listRange);
	}
	private void getKey(List<K> listkey, List<K> listRange) {
		if(listkey.empty()) return;
		
		listkey.findFirst();
		while(!listkey.last()) {
			listRange.insert(listkey.retrieve());
			listkey.findNext();
		}
		listRange.insert(listkey.retrieve());
	}


}

