package Dynamic.MatrixChainMultiplication;

public class PalindromePartitioningIII {
    Integer[][] memoizePalindrome;
    Integer[][] memoize;
    public int palindromePartition(String s, int k) {
        memoizePalindrome=new Integer[s.length()][s.length()];
        memoize=new Integer[s.length()][k];
        return recursive(s,0,k-1);
    }

    /*
     * In this approach, for a given range start to length-1 calculate the count for each possible partition combination
     * and take the minimum.
     */
    private int recursive(String s, int start, int k) {
        if(k==0)
            return palindromeCount(s,start,s.length()-1);
        if(memoize[start][k]!=null) return memoize[start][k];

        int temp=Integer.MAX_VALUE;
        for(int i=start;i<s.length()-k;i++){
            temp=Math.min(temp,palindromeCount(s,start,i)+recursive(s,i+1,k-1));
        }
        return memoize[start][k]=temp;
    }

    /*
     * For a string (start,end) if it is not a palindrome then we can make it to a palindrome by changing the characters
     * where it is not matching.
     * Example      "xabad"
     *          To change this string to a palindrome we can either change 'x' to 'd' or conversely 'd' to 'x'. So the
     *          count to change this string into a palindrome is 1.
     */
    private int palindromeCount(String s, int start, int end) {
        if(start>=end) return 0;

        if(memoizePalindrome[start][end]!=null) return memoizePalindrome[start][end];

        if(s.charAt(start)==s.charAt(end))
            return memoizePalindrome[start][end]=palindromeCount(s,start+1,end-1);
        else
            return memoizePalindrome[start][end]=1+palindromeCount(s,start+1,end-1);
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningIII().palindromePartition("abc",2));
        System.out.println(new PalindromePartitioningIII().palindromePartition("abcdefg",3));
        System.out.println(new PalindromePartitioningIII().palindromePartition("pqabars",3));
        System.out.println(new PalindromePartitioningIII().palindromePartition("leetcode",8));
        System.out.println(new PalindromePartitioningIII().palindromePartition("aabbc",3));
    }
}
