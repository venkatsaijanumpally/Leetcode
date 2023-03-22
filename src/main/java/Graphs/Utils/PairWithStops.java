package Graphs.Utils;

public class PairWithStops implements Comparable<PairWithStops> {
    public int node;
    public int dist;
    public int stops;
    public PairWithStops(int node, int distance, int stops){
        this.node = node;
        dist=distance;
        this.stops=stops;
    }

    @Override
    public int compareTo(PairWithStops o) {
        if(dist > o.dist)
            return 1;
        if(dist < o.dist)
            return -1;
        return 0;
    }
}
