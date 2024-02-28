package Graphs.Medium;

import java.util.HashMap;
import java.util.Map;

public class PairMultiplier {
    public static void main(String[] args) {
        // Example HashMap representing pairs of integers
        HashMap<Integer, Integer> pairs = new HashMap<>();
        pairs.put(1, 2);
        pairs.put(3, 4);
        pairs.put(5, 3);

        // Calculate the sum of products of all pair combinations
        long result = calculateSumOfProducts(pairs);

        System.out.println("Sum of products: " + result);
    }

    public static long calculateSumOfProducts(HashMap<Integer, Integer> pairs) {
        long result = 0;

        // Iterate through all pairs
        for (Map.Entry<Integer, Integer> entry1 : pairs.entrySet()) {
            int value1 = entry1.getValue();

            for (Map.Entry<Integer, Integer> entry2 : pairs.entrySet()) {
                if(entry1==entry2) continue;
                int value2 = entry2.getValue();

                // Multiply values and add to the result
                result += (long) value1 * value2;
            }
        }

        return result;
    }
}

