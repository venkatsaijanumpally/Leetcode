package Dynamic.Medium;

import java.util.ArrayList;
import java.util.List;

public class FindGoodDaystoRobtheBank {
    List<Integer> result=new ArrayList<>();
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        if(time==0){
            for(int i=0;i< security.length;i++)
                result.add(i);
        }
        else if(security.length<=time+time) return result;
        else {
            int left=1;
            int[] right=new int[security.length];
            right[security.length-1]=1;

            for(int i= security.length-2;i>=1;i--)
                right[i]=security[i]<=security[i+1]?right[i+1]+1:1;
            for(int i=1;i<time;i++)
                left=security[i-1]>=security[i]?left+1:1;


            for(int i=time;i< security.length-time;i++){
                if(left>=time && right[i]>=time+1 && security[i]<=security[i-1])
                    result.add(i);
                left=security[i-1]>=security[i]?left+1:1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr={5,3,3,3,5,6,2};
        int[] arr2={1,2,3,4,5,6};
        int[] arr3={5,3,1,3,5};
        System.out.println(new FindGoodDaystoRobtheBank().goodDaysToRobBank(arr,2));
        System.out.println(new FindGoodDaystoRobtheBank().goodDaysToRobBank(arr2,2));
        System.out.println(new FindGoodDaystoRobtheBank().goodDaysToRobBank(arr2,1));
        System.out.println(new FindGoodDaystoRobtheBank().goodDaysToRobBank(arr3,2));
    }
}
