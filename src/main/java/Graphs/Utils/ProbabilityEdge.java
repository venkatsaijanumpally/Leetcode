package Graphs.Utils;

import Graphs.Basics.WeightedPair;

import java.util.Comparator;

public class ProbabilityEdge implements Comparable<ProbabilityEdge> {
    public Double probability;
    public int neighbour;
    public ProbabilityEdge(int neighbour, Double probability){
        this.neighbour=neighbour;
        this.probability=probability;
    }

    /*
     * MAX PRIORITY QUEUE IMPLEMENTATION
     */
    @Override
    public int compareTo(ProbabilityEdge o) {
        if(probability > o.probability)
            return -1;
        if(probability < o.probability)
            return 1;
        return 0;
    }
}
