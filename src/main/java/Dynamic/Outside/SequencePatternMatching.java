package Dynamic.Outside;

import Dynamic.LongestCommonSubsequence.LongestCommonSubsequence;

public class SequencePatternMatching {
    /*
     * https://www.youtube.com/watch?v=QVntmksK2es&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=30
     */
    public boolean isPatternSubsequence(String pattern, String str){
        int length=new LongestCommonSubsequence().longestCommonSubsequence(pattern,str);
        return length==pattern.length();
    }
}
