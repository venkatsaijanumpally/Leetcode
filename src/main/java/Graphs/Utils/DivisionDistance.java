package Graphs.Utils;

public class DivisionDistance implements Comparable<DivisionDistance>{
    public String node;
    public int distance;
    public double div;
    public DivisionDistance(String node, int distance, double div){
        this.node=node;
        this.distance=distance;
        this.div=div;
    }

    @Override
    public int compareTo(DivisionDistance o) {
        if(distance > o.distance)
            return 1;
        if(distance < o.distance)
            return -1;
        return 0;
    }
}
