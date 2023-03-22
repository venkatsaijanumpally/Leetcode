package Dynamic.Medium;

import java.util.Arrays;

public class MinimumDeletionstoMakeStringBalanced {
    public int minimumDeletions(String s) {
        int start;
        int end;
        for(start=0;start<s.length() && s.charAt(start)=='a';start++){}

        for(end=s.length()-1;end>=0 && s.charAt(end)=='b';end--){}

        if(start>end)
            return 0;

        System.out.println(start+" "+end);

        int[] aOnRight=new int[end-start+3];
        int[] bOnLeft=new int[end-start+3];
        for(int i=start,j=1;i<=end;i++,j++){
            bOnLeft[j]+=s.charAt(i)=='b'?1:0;
            bOnLeft[j]+=bOnLeft[j-1];
        }
        for(int i=end,j=end-start+1;i>=start;i--,j--){
            aOnRight[j]+=s.charAt(i)=='a'?1:0;
            aOnRight[j]+=aOnRight[j+1];
        }

        int min=Integer.MAX_VALUE;
        for(int i=0;i<aOnRight.length-1;i++){
            min=Math.min(min,aOnRight[i+1]+bOnLeft[i]);
        }

        System.out.println(Arrays.toString(aOnRight));
        System.out.println(Arrays.toString(bOnLeft));
        return min;
    }

    public static void main(String[] args) {
        /*System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("aababbab"));
        System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("bbaaaaabb"));
        System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("aaabbb"));
        System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("aaabbb"));*/
        System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("bbbbbaaa"));
        System.out.println(new MinimumDeletionstoMakeStringBalanced().minimumDeletions("bbbbbbbaabbbbbaaabbbabbbbaabbbbbbaabbaaabaabbbaaaabaaababbbabbabbaaaabbbabbbbbaabbababbbaaaaaababaaababaabbabbbaaaabbbbbabbabaaaabbbaba"));
    }
}
