package Dynamic.Medium;

import java.util.Arrays;

public class CountNumberofTeams {
    public int numTeams(int[] rating) {
        int[] lesserOnRight=new int[rating.length];
        int[] lesserOnLeft=new int[rating.length];
        lesserOnRight[rating.length-1]=0;
        lesserOnLeft[0]=0;
        int count=0;

        for (int i=1;i<lesserOnLeft.length;i++){
            for (int j=0;j<i;j++){
                if(rating[j]<rating[i]){
                    lesserOnLeft[i]++;
                    count+=lesserOnLeft[j];
                }
            }
        }
        for (int i= lesserOnRight.length-2;i>=0;i--){
            for (int j=i+1;j<lesserOnRight.length;j++){
                if(rating[j]<rating[i]){
                    lesserOnRight[i]++;
                    count+=lesserOnRight[j];
                }
            }
        }
        System.out.println(Arrays.toString(lesserOnLeft));
        System.out.println(Arrays.toString(lesserOnRight));
        return count;
    }

    public static void main(String[] args) {
        int[] arr1={2,5,3,4,1};
        System.out.println(new CountNumberofTeams().numTeams(arr1));
    }
}
