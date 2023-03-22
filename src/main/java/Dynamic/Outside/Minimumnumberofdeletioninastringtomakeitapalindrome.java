package Dynamic.Outside;

import Dynamic.LongestPalindromicSubsequence.LongestPalindromicSubsequence;

public class Minimumnumberofdeletioninastringtomakeitapalindrome {
    /*
     * The problem is same as LongestPalindromicSubsequence
     * If we can find a longest length palindromic subsequence which is nothing but the palindrome which requires minimum
     * number of deletions.
     */
    public int minimumLength(String s){
        int maxLength=new LongestPalindromicSubsequence().longestPalindromeSubseq(s);
        return s.length()-maxLength;
    }
}
