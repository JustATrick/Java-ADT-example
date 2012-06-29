package in.sinking.ADTExample;


public class Empty extends CSet {
	@Override
	public CSet insert(int n) {
		return new Pair(n, this);
	}
	
	@Override
	public boolean contains(int n) {
		return false;
	}
	
	@Override
	public CSet union(CSet b) {
		return b;
	}
}