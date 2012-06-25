package in.sinking.ADTExample;

public abstract class ASet {
	
	public static ASet empty() {
		return new Empty();
	}
	
	public static ASet insert(ASet s, int n) {
		if (!contains(s, n)) {
			return new Pair(n, s);
		}
		else {
			return s;
		}
	}
		
	public static boolean contains(ASet s, int n) {
		if (s instanceof Empty) {
			return false;
		}
		else if (s instanceof Pair) {
			final Pair rep = (Pair) s;
			if (rep.first == n) {
				return true;
			}
			else {
				return contains(rep.rest, n);
			}
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}
	
	public static ASet union(ASet a, ASet b) {
		if (a instanceof Empty) {
			return b;
		}
		else if (a instanceof Pair) {
			final Pair rep = (Pair) a;
			return insert(union(rep.rest, b), rep.first);
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}

	static private class Empty extends ASet { }
	
	static private class Pair extends ASet {
		public int first;
		public ASet rest;
		
		public Pair(int first, ASet rest) {
			this.first = first;
			this.rest = rest;
		}
	}
}