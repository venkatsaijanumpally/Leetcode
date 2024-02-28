package BackTracking.Utils;

public class WeightedPair implements Comparable<WeightedPair>{
    public int node;
    public int time;
    public WeightedPair(int node, int distance){
        this.node = node;
        time =distance;
    }

    @Override
    public int compareTo(WeightedPair o) {
        if(time > o.time)
            return 1;
        if(time < o.time)
            return -1;
        return 0;
    }
}
