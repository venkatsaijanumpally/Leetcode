package Graphs.Utils;

public class MinEffortPair implements Comparable {
    public int x2,y2;
    public int pathDiff;

    public MinEffortPair(int x2,int y2,int pathDiff){
        this.x2=x2;
        this.y2=y2;
        this.pathDiff=pathDiff;
    }

    @Override
    public int compareTo(Object o) {
        MinEffortPair b= (MinEffortPair) o;
        return pathDiff - b.pathDiff;
    }
}
