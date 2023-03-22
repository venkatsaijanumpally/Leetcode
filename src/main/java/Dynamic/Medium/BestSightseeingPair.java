package Dynamic.Medium;

public class BestSightseeingPair {
    /*
     * In this solution using currMax we track the previous maximum element.
     * The profit is calculated by values[i]+values[j]+i-j
     * The profit can be understood as for every increment in the index a -1 is added to the sum. Which is why the currMax
     * is decremented by 1 for every increment in index.
     * At a particular index we update the result and we check if the current element is greater than currMax then we will
     * update the currMax.
     */
    public int maxScoreSightseeingPair(int[] values) {
        int currMax=values[0]-1,result=0;
        for(int i=1;i<values.length;i++){
            result=Math.max(result,currMax+values[i]);
            currMax=Math.max(currMax,values[i])-1;
        }
        return result;
    }
}
