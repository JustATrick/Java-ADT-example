package in.sinking.ADTExample;

public abstract class CSet {
	
	public static CSet empty() {
		return new Empty();
	}
	
	public abstract CSet insert(int n);	
	public abstract boolean contains(int n);
	public abstract CSet union(CSet b);
}
