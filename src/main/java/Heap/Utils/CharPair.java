package Heap.Utils;

import java.util.Comparator;

public class CharPair implements Comparable<CharPair>{
    public char c;
    public int count;
    public CharPair(char c, int value){
        this.c=c;
        count=value;
    }


    @Override
    public int compareTo(CharPair o) {
        return o.count-count;
    }
}
