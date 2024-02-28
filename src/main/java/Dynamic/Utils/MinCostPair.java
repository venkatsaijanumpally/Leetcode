package Dynamic.Utils;

import java.util.Comparator;

public class MinCostPair implements Comparator<MinCostPair> {
    public int value;
    public int minNode;


    public MinCostPair(int value, int minNode) {
        this.value = value;
        this.minNode = minNode;
    }

    @Override
    public int compare(MinCostPair o1, MinCostPair o2) {
        return o1.value-o2.value;
    }
}
