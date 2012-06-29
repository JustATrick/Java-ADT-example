package in.sinking.ADTExample;

public interface CSet {
	public CSet insert(int n);	
	public boolean contains(int n);
	public CSet union(CSet b);
}
