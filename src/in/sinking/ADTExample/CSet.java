package in.sinking.ADTExample;

public abstract class CSet {
	
	public static CSet empty() {
		return new Empty();
	}
	
	public CSet insert(int n) {
		if (!contains(n)) {
			return new Pair(n, this);
		}
		else {
			return this;
		}
	}
		
	public boolean contains(int n) {
		if (this instanceof Empty) {
			return false;
		}
		else if (this instanceof Pair) {
			final Pair rep = (Pair) this;
			if (rep.first == n) {
				return true;
			}
			else {
				return rep.rest.contains(n);
			}
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}
	
	public CSet union(CSet b) {
		if (this instanceof Empty) {
			return b;
		}
		else if (this instanceof Pair) {
			final Pair rep = (Pair) this;
			return rep.rest.union(b).insert(rep.first);
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}

	static private class Empty extends CSet { }
	
	static private class Pair extends CSet {
		public int first;
		public CSet rest;
		
		public Pair(int first, CSet rest) {
			this.first = first;
			this.rest = rest;
		}
	}
}
