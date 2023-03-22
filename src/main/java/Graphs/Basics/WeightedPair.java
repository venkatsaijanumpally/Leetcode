package Graphs.Basics;

public class WeightedPair implements Comparable<WeightedPair> {
    public int node;
    public int dist;
    public WeightedPair(int node, int distance){
        this.node = node;
        dist=distance;
    }

    @Override
    public int compareTo(WeightedPair o) {
        if(dist > o.dist)
            return 1;
        if(dist < o.dist)
            return -1;
        return 0;
    }
}
