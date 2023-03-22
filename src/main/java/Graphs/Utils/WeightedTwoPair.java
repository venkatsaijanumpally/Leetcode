package Graphs.Utils;

public class WeightedTwoPair implements Comparable<WeightedTwoPair> {
    public int node;
    public int neighbour;
    public int dist;
    public WeightedTwoPair(int node, int neighbour, int distance){
        this.node = node;
        this.neighbour=neighbour;
        dist=distance;
    }

    @Override
    public int compareTo(WeightedTwoPair o) {
        if(dist > o.dist)
            return 1;
        if(dist < o.dist)
            return -1;
        return 0;
    }
}
