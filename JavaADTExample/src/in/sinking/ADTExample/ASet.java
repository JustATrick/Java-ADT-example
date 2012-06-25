package in.sinking.ADTExample;

import in.sinking.ADTExample.representation.Empty;
import in.sinking.ADTExample.representation.Pair;
import in.sinking.ADTExample.representation.RepType;

public class ASet {
	private RepType rep_;
	
	private ASet(final RepType rep) {
		rep_ = rep;
	}
	
	private static ASet make_empty() {
		return new ASet(new Empty());
	}
	
	private static ASet make_pair(int first, RepType rest) {
		return new ASet(new Pair(first, rest));
	}
	
	private static ASet up(RepType rep) {
		return new ASet(rep);
	}
	
	public static ASet empty() {
		return make_empty();
	}
	
	public static ASet insert(ASet s, int n) {
		if (!contains(s, n)) {
			return make_pair(n, s.rep_);
		}
		else {
			return s;
		}
	}
		
	public static boolean contains(ASet s, int n) {
		if (s.rep_ instanceof Empty) {
			return false;
		}
		else if (s.rep_ instanceof Pair) {
			final Pair rep = (Pair) s.rep_;
			if (rep.first == n) {
				return true;
			}
			else {
				return contains(up(rep.rest), n);
			}
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}
	
	public static ASet union(ASet a, ASet b) {
		if (a.rep_ instanceof Empty) {
			return b;
		}
		else if (a.rep_ instanceof Pair) {
			final Pair rep = (Pair) a.rep_;
			return insert(union(up(rep.rest), b), rep.first);
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}
}
