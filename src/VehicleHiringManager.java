public class VehicleHiringManager {
	private LocatorMap<String> locMap;
	
	public VehicleHiringManager() {
		locMap= new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return locMap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		this.locMap= locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		if(id==null|| loc == null) return false;
		Pair<Boolean, Integer> pair =locMap.add(id, loc);
		return pair.first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		if(id==null|| loc == null) return false;
		Pair<Boolean, Integer> pair =locMap.move(id, loc);
		return pair.first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		if(id==null) return false;
		Pair<Boolean, Integer> pair =locMap.remove(id);
		return pair.first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		Pair<Location, Integer> pair = locMap.getLoc(id);
		return pair.first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		Pair<List<String>, Integer> pair;
		if( loc == null) return new LinkedList<String>();
		pair= locMap.getInRange(new Location(loc.x-r, loc.y-r), new Location(loc.x+r, loc.y+r));
		return pair.first;
	}


}


