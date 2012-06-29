package in.sinking.ADTExample;

class Pair extends RepType {
    public int first;
    public RepType rest;

    public Pair(int first, RepType rest) {
        this.first = first;
        this.rest = rest;
    }
}
