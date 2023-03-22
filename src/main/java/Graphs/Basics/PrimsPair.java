package Graphs.Basics;

public class PrimsPair implements Comparable<PrimsPair> {
    int nodeA;
    int nodeB;
    int distance;

    public PrimsPair(int nodeA,int nodeB, int distance){
        this.nodeA=nodeA;
        this.nodeB=nodeB;
        this.distance=distance;
    }

    @Override
    public int compareTo(PrimsPair o) {
        if(distance > o.distance)
            return 1;
        if(distance < o.distance)
            return -1;
        return 0;
    }
}
