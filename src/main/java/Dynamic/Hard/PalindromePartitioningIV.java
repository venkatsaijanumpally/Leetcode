package Dynamic.Hard;

public class PalindromePartitioningIV {
    /*
     * Both TopDown and recursive are good. recursive is generic method whereas topdown uses simple trick.
     * TopDown and TopDown2 are same approach.
     */
    Boolean[][] memoizePalindrome;
    Boolean[][][] memoize;
    public boolean checkPartitioning(String s) {
        memoize=new Boolean[s.length()][s.length()][3];
        memoizePalindrome=new Boolean[s.length()][s.length()];
        //return recursive(s,0,0,2);

        //return TopDown(s);

        return TopDown2(s);
    }

    /*
     * Approach: Find 2 indices
     * The question states to partition the string into 3 parts which is nothing but 2 cuts, which means if we can find
     * 2 indices in the string i,j where 0<=i<j<length then if all 3 partitions (0 to i), (i+1 to j), (j+1,length) are
     * all palindromes then we have 3 palindromes.
     */
    private boolean TopDown(String s) {
        boolean[][] memoize=new boolean[s.length()][s.length()];

        for(int i=0;i<s.length()-2;i++){
            for(int j=i+1;j<s.length()-1;j++){
                if(isPalindrome(s,0,i) && isPalindrome(s,i+1,j) && isPalindrome(s,j+1,s.length()-1))
                    return true;
            }
        }
        return false;
    }

    /*
     * This approach is same as TopDown but here we created a memoized array to check palindrome.
     */
    private boolean TopDown2(String s) {
        boolean[][] memoizePalindrome=new boolean[s.length()][s.length()];

        memoizePalindrome[0][0]=true;
        for(int i=s.length()-1;i>=0;i--){
            for(int j=i;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j))
                    memoizePalindrome[i][j]=(i+1)<(j-1)?memoizePalindrome[i+1][j-1]:true;
                else memoizePalindrome[i][j]=false;
            }
        }

        for(int i=0;i<s.length()-2;i++){
            for(int j=i+1;j<s.length()-1;j++){
                if(memoizePalindrome[0][i] && memoizePalindrome[i+1][j] && memoizePalindrome[j+1][s.length()-1])
                    return true;
            }
        }
        return false;
    }


    /*
     * The recursive method is also optimal but for the current scenario we can use the TopDown trick. The recursive
     * method is more generic method which works for any number of partitions.
     */
    private boolean recursive(String s, int start, int curr, int count) {
        if(count==0){
            return isPalindrome(s,start,s.length()-1);
        }
        if(curr==s.length()-1) return false;
        if(memoize[start][curr][count]!=null)
            return memoize[start][curr][count];

        if(isPalindrome(s,start,curr))
            return memoize[start][curr][count]=recursive(s,curr+1,curr+1,count-1) || recursive(s,start,curr+1,count);
        else return memoize[start][curr][count]=recursive(s,start,curr+1,count);
    }

    private boolean isPalindrome(String s, int start, int end) {
        if(start>=end) return true;
        if(memoizePalindrome[start][end]!=null)
            return memoizePalindrome[start][end];

        return memoizePalindrome[start][end]= s.charAt(start) == s.charAt(end) && isPalindrome(s, start + 1, end - 1);
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningIV().checkPartitioning("abcbdd"));
        System.out.println(new PalindromePartitioningIV().checkPartitioning("bcbddxy"));
        System.out.println(new PalindromePartitioningIV().checkPartitioning("juchzcedhfesefhdeczhcujzzvbmoeombv"));
    }
}
