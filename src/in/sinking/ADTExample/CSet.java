package in.sinking.ADTExample;

public abstract class CSet {
	
	public static CSet empty() {
		return new Empty();
	}
	
	public abstract CSet insert(int n);	
	public abstract boolean contains(int n);
	public abstract CSet union(CSet b);

	static private class Empty extends CSet {
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
	
	static private class Pair extends CSet {
		private int first;
		private CSet rest;
		
		public Pair(int first, CSet rest) {
			this.first = first;
			this.rest = rest;
		}
		
		@Override
		public CSet insert(int n) {
			if (!contains(n)) {
				return new Pair(n, this);
			}
			else {
				return this;
			}
		}
		
		@Override
		public boolean contains(int n) {
			if (first == n) {
				return true;
			}
			else {
				return rest.contains(n);
			}
		}
		
		@Override
		public CSet union(CSet b) {
			return rest.union(b).insert(first);
		}
	}
}
