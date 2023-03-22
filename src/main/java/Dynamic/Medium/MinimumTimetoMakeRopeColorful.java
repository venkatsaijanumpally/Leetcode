package Dynamic.Medium;

public class MinimumTimetoMakeRopeColorful {
    public int minCost(String colors, int[] neededTime) {
        int curr=0;
        int sum=0;
        for(int i=1;i<colors.length();i++){
            if(colors.charAt(i)==colors.charAt(curr)){
                sum+=neededTime[curr]<neededTime[i]?neededTime[curr]:neededTime[i];
                curr=neededTime[curr]<neededTime[i]?i:curr;
            }
            else {
                curr=i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] neededTime={1,2,3,4,5};
        int[] neededTime2={1,2,3};
        System.out.println(new MinimumTimetoMakeRopeColorful().minCost("abaac",neededTime));
        System.out.println(new MinimumTimetoMakeRopeColorful().minCost("abc",neededTime2));
    }
}
