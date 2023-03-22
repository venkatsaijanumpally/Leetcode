package Heap.Utils;

import java.util.Comparator;

public class KWeakPair implements Comparable<KWeakPair> {
    public int ones;
    public int index;
    public KWeakPair(int ones,int index){
        this.ones=ones;
        this.index=index;
    }

    @Override
    public int compareTo(KWeakPair o) {
        if(ones==o.ones)
            return index-o.index;
        return ones-o.ones;
    }


    /*@Override
    public int compare(Object o1, Object o2) {
        KWeakPair p1= (KWeakPair) o1;
        KWeakPair p2= (KWeakPair) o2;
        if(p1.ones==p2.ones)
            return p1.ones-p2.ones;
        else return p1.index-p2.index;

    }*/
}
