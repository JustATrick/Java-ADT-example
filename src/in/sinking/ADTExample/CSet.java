package in.sinking.ADTExample;

import in.sinking.ADTExample.representation.Empty;
import in.sinking.ADTExample.representation.Pair;
import in.sinking.ADTExample.representation.RepType;

public class CSet {
	private RepType rep_;
	
	private CSet(final RepType rep) {
		rep_ = rep;
	}
	
	private static CSet make_empty() {
		return new CSet(new Empty());
	}
	
	private static CSet make_pair(int first, RepType rest) {
		return new CSet(new Pair(first, rest));
	}
	
	private static CSet up(RepType rep) {
		return new CSet(rep);
	}
	
	public static CSet empty() {
		return make_empty();
	}
	
	public CSet insert(int n) {
		if (!contains(n)) {
			return make_pair(n, rep_);
		}
		else {
			return this;
		}
	}
		
	public boolean contains(int n) {
		if (rep_ instanceof Empty) {
			return false;
		}
		else if (rep_ instanceof Pair) {
			final Pair rep = (Pair) rep_;
			if (rep.first == n) {
				return true;
			}
			else {
				return up(rep.rest).contains(n);
			}
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}
	
	public CSet union(CSet b) {
		if (rep_ instanceof Empty) {
			return b;
		}
		else if (rep_ instanceof Pair) {
			final Pair rep = (Pair) rep_;
			return up(rep.rest).union(b).insert(rep.first);
		}
		else {
			throw new IllegalStateException("Someone changed my rep without letting me know!");
		}
	}
}
