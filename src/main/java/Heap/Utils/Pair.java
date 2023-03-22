package Heap.Utils;

import java.util.Comparator;

public class Pair implements Comparable<Pair> {

    public int index;
    public int score;

    public Pair(int index, int score){
        this.index=index;
        this.score=score;
    }

    @Override
    public int compareTo(Pair o) {
        return o.score-score;
    }
}
