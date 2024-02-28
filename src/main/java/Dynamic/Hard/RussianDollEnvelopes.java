package Dynamic.Hard;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]!= o2[0]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            }
        });
        System.out.println(Arrays.deepToString(envelopes));

        int maximum=1;
        int[] t = new int[envelopes.length];
        t[envelopes.length-1]=1;
        for (int i=envelopes.length-2;i>=0;i--){
            int max=0;
            for (int j=i+1;j<envelopes.length+Math.min(-max+1,0);j++){
                if(envelopes[i][0]<envelopes[j][0] && envelopes[i][1]<envelopes[j][1] && max<t[j]){
                    max = t[j];
                }
            }
            t[i]=max+1;
            maximum = Math.max(maximum,t[i]);
        }

        /*int maximum=1;
        int[] t = new int[envelopes.length];
        t[envelopes.length-1]=1;
        for (int i=envelopes.length-2;i>=0;i--){
            int max=0;
            for (int j=i+1;j<envelopes.length+Math.min(-max+1,0);j++){
                if(envelopes[i][0]<envelopes[j][0] && envelopes[i][1]<envelopes[j][1]){
                    max = t[j];
                    break;
                }
            }
            t[i]=max+1;
            maximum = Math.max(maximum,t[i]);
        }*/

        return maximum;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int[][] arr2 = {{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}};
        //System.out.println(new RussianDollEnvelopes().maxEnvelopes(arr1));
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(arr2));
    }
}
