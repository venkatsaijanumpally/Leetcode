package Graphs.Medium;

public class CheapestPair implements Comparable<CheapestPair> {
    public int neighbour;
    public int dist;
    public int k;
    public CheapestPair(int neighbour, int distance, int k){
        this.neighbour=neighbour;
        dist=distance;
        this.k=k;
    }

    @Override
    public int compareTo(CheapestPair o) {
        if(dist > o.dist)
            return 1;
        if(dist < o.dist)
            return -1;
        return 0;
    }
}
