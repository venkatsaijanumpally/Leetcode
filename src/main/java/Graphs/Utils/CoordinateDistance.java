package Graphs.Utils;

import java.util.Comparator;

public class CoordinateDistance implements Comparable<CoordinateDistance> {
    public int x,y;
    public int dist;
    public CoordinateDistance(int x, int y, int dist){
        this.x=x;
        this.y=y;
        this.dist=dist;
    }


    @Override
    public int compareTo(CoordinateDistance o) {
        return dist-o.dist;
    }
}
