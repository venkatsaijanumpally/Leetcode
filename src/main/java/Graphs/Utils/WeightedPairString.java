package Graphs.Utils;

import Graphs.Basics.WeightedPair;

public class WeightedPairString implements Comparable<WeightedPairString> {
    public String node;
    public int dist;
    public WeightedPairString(String node, int distance){
        this.node = node;
        dist=distance;
    }

    @Override
    public int compareTo(WeightedPairString o) {
        return dist-o.dist;
    }
}
