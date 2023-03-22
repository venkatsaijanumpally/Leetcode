package Dynamic.MatrixChainMultiplication;

import java.util.HashMap;

public class ScrambleString {
    /*
     * https://www.youtube.com/watch?v=VyHEglhbm-A&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=41
     * recursiveString and recursiveBetter1 both are optimal. recursiveString makes use of strings to breakdown the task
     * whereas recursiveBetter1 uses indices of substring to breakdown.
     * First understand recursiveString then recursiveBetter1.
     */
    HashMap<String,Boolean> map=new HashMap<>();
    Boolean[][][] memoize;
    public boolean isScramble(String s1, String s2) {
        //return recursive(new StringBuilder(s1),new StringBuilder(s2),0,s1.length()-1,0,s1.length()-1);

        memoize=new Boolean[s1.length()][s1.length()][s1.length()+1];
        return recursiveBetter1(new StringBuilder(s1),new StringBuilder(s2),0,0,s1.length());


        //return recursiveString(s1,s2);
    }

    private boolean recursive(StringBuilder sb1, StringBuilder sb2, int start1, int end1, int start2, int end2) {
        if(start1==end1)
            return sb1.charAt(start1)==sb2.charAt(start2);
        if(sb1.substring(start1, end1).equals(sb2.substring(start2, end2)))
            return true;

        boolean temp=false;
        for(int k=0;k<end1-start1;k++){
            boolean left=recursive(sb1, sb2, start1, start1+k, start2, start2+k);
            boolean right=recursive(sb1, sb2, start1+k+1, end1, start2+k+1, end2);
            boolean leftcross=recursive(sb1, sb2, start1, start1+k, end2-k, end2);
            boolean rightcross=recursive(sb1, sb2, start1+k+1, end1, start2, end2-k-1);

            if(left && right || leftcross && rightcross) return true;
        }
        return false;
    }

    private boolean recursiveBetter(StringBuilder sb1, StringBuilder sb2, int start1, int start2, int len) {
        int end1=start1+len-1;
        int end2=start2+len-1;
        if(start1==end1)
            return sb1.charAt(start1)==sb2.charAt(start2);
        if(memoize[start1][start2][len]!=null) return memoize[start1][start2][len];
        if(sb1.substring(start1, end1+1).equals(sb2.substring(start2, end2+1)))
            return memoize[start1][start2][len]=true;

        boolean temp=false;
        for(int k=0;k<end1-start1;k++){
            boolean left=recursiveBetter(sb1, sb2, start1, start2, k+1);
            boolean right=recursiveBetter(sb1, sb2, start1+k+1, start2+k+1, len-k-1);
            boolean leftcross=recursiveBetter(sb1, sb2, start1, end2-k,k+1);
            boolean rightcross=recursiveBetter(sb1, sb2, start1+k+1, start2, len-k-1);

            if(left && right || leftcross && rightcross) return memoize[start1][start2][len]=true;
        }
        return memoize[start1][start2][len]=false;
    }

    /*
     * Example: string 1: "great", string 2: "rgeat", len=5
     * If start1=2, start2=0 then
     * end1=7, end2=5 the index 7 and 5 both are just outside the last index which are used for taking substring directly
     * since it has the concept of excluding last index.
     *
     * K iterates from 1 to 4 inclusive
     * case 1 calculates if the substring are calculated on the same side
     *      e.g (0 to k compared to 0 to k) and (k to length compared to k to length)
     * case 2 calculates if the substring are calculated on the opposite side
     *      e.g (0 to k compared to length-k to length) and (k to length compared to 0 to length-k)
     */
    private boolean recursiveBetter1(StringBuilder sb1, StringBuilder sb2, int start1, int start2, int len) {
        int end1=start1+len;
        int end2=start2+len;
        if(start1==end1-1)
            return sb1.charAt(start1)==sb2.charAt(start2);
        if(memoize[start1][start2][len]!=null) return memoize[start1][start2][len];
        if(sb1.substring(start1, end1).equals(sb2.substring(start2, end2)))
            return memoize[start1][start2][len]=true;

        for(int k=1;k<len;k++){
            boolean left=recursiveBetter1(sb1, sb2, start1, start2, k);
            boolean right=recursiveBetter1(sb1, sb2, start1+k, start2+k, len-k);
            boolean leftcross=recursiveBetter1(sb1, sb2, start1, end2-k,k);
            boolean rightcross=recursiveBetter1(sb1, sb2, start1+k, start2, len-k);

            if(left && right || leftcross && rightcross) return memoize[start1][start2][len]=true;
        }
        return memoize[start1][start2][len]=false;
    }




    /*
     * For every string s1,s2 if both are eual return true else divide into 2 cases:
     *
     * case 1 calculates if the substring are calculated on the same side
     *      e.g (0 to k compared to 0 to k) and (k to length compared to k to length)
     * case 2 calculates if the substring are calculated on the opposite side
     *      e.g (0 to k compared to length-k to length) and (k to length compared to 0 to length-k)
     */
    private boolean recursiveString(String s1, String s2) {
        if(s1.compareTo(s2)==0)
            return true;
        if(s1.length()<=1)
            return false;
        String key=s1+" "+s2;
        if(map.containsKey(key))
            return map.get(key);

        int n=s1.length();
        for(int k=1;k<n;k++){
            boolean case1=recursiveString(s1.substring(0,k),s2.substring(n-k,n))
                    && recursiveString(s1.substring(k,n),s2.substring(0,n-k));
            boolean case2=recursiveString(s1.substring(0,k),s2.substring(0,k))
                    && recursiveString(s1.substring(k,n),s2.substring(k,n));

            if(case1 || case2) {
                map.put(key,true);
                return true;
            }
        }

        map.put(key,false);
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new ScrambleString().isScramble("aa","ab"));
        System.out.println(new ScrambleString().isScramble("great","rgeat"));
        System.out.println(new ScrambleString().isScramble("great","rxeat"));
        System.out.println(new ScrambleString().isScramble("abcde","caebd"));
        System.out.println(new ScrambleString().isScramble("a","a"));
        System.out.println(new ScrambleString().isScramble("coder","ocder"));
        System.out.println(new ScrambleString().isScramble("coder","redoc"));
    }
}
