package in.sinking.ADTExample;

public class Pair implements CSet {
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
