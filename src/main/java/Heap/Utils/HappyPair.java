package Heap.Utils;

import java.util.Comparator;

public class HappyPair implements Comparable<HappyPair> {
    public char character;
    public int count;

    public HappyPair(char character, int count) {
        this.character = character;
        this.count = count;
    }

    /*@Override
    public int compare(HappyPair o1, HappyPair o2) {
        return o1.count - o2.count;
    }*/

    @Override
    public int compareTo(HappyPair o) {
        return o.count - count;
    }
}
