package Dynamic.Utils;

import java.util.Comparator;

public class SortIntegersPair implements Comparable<SortIntegersPair> {
    public int x;
    public int steps;


    public SortIntegersPair(int x, int steps) {
        this.x = x;
        this.steps = steps;
    }

   /* @Override
    public int compare(SortIntegersPair o1, SortIntegersPair o2) {
        if(o1.steps==o2.steps)
            return o2.x-o1.x;
        return o2.steps-o1.steps;
    }*/

    @Override
    public int compareTo(SortIntegersPair o) {
        if(steps==o.steps)
            return o.x-x;
        return o.steps-steps;
    }
}
