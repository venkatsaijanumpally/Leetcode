package Dynamic.MatrixChainMultiplication;

public class PalindromePartitioningII {
    /*
     * recursiveOptimize is optimal than recursive. recursiveOptimize is a simple optimization on recursive method.
     * First understand recursive then understand recursiveOptimize.
     */
    Integer[][] memoize;
    Boolean[][] memoizePalindrome;
    public int minCut(String s) {
        memoize=new Integer[s.length()][s.length()];
        //return recursive(s,0,s.length()-1);

        memoizePalindrome=new Boolean[s.length()][s.length()];
        return recursiveOptimize(s,0,s.length()-1);
    }

    private int recursive(String s, int start, int end) {
        if(start>=end) return 0;
        if(memoize[start][end]!=null) return memoize[start][end];
        if(isPalindrome(s,start,end)) return memoize[start][end]=0;

        int res=Integer.MAX_VALUE;
        for(int i=start;i<end;i++){
            int temp=recursive(s,start,i)+recursive(s,i+1,end)+1;

            if(res>temp)
                res=temp;
        }
        return memoize[start][end]=res;
    }

    //!Optimal
    private int recursiveOptimize(String s, int start, int end) {
        if(start>=end) return 0;
        if(memoize[start][end]!=null) return memoize[start][end];
        if(isPalindromeOptimized(s,start,end)) return memoize[start][end]=0;

        int res=Integer.MAX_VALUE;
        for(int i=start;i<end;i++){
            /*
            * An Optimization: We will make the partition only if the string till the partition
		    * (till Kth position) is a valid palindrome. Because the question states that all
		    * partition must be a valid palindrome. If we dont check this, we will have to
		    * perform recursion on the left subproblem too (solve(str, i, k)) and	we will waste
		    * a lot of time on subproblems that is not required. Without this the code will give
		    * correct answer but TLE on big test cases.
		    * Basic Idea here is if there is a palindrome on the leftside then there is a meaning to
		    * partition it.
		    */
            if(isPalindromeOptimized(s,start,i)){
                res=Math.min(res,1+recursiveOptimize(s,i+1,end));
            }
        }
        return memoize[start][end]=res;
    }

    private boolean isPalindrome(String s, int start, int end) {
        /*int mid=start+(end-start)/2;
        StringBuilder sb=new StringBuilder(s.substring(mid+1,end+1)).reverse();
        if((end-start)%2==0)
            return s.substring(start,mid).equals(sb.toString());
        else
            return s.substring(start,mid+1).equals(sb.toString());*/

        while (start<end){
            if(s.charAt(start)!=s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    /*
     * Memoization to check isPalindrome
     */
    private boolean isPalindromeOptimized(String s, int start, int end) {
        if(start>=end) return true;
        if(memoizePalindrome[start][end]!=null) return memoizePalindrome[start][end];

        return memoizePalindrome[start][end]= s.charAt(start) == s.charAt(end) && isPalindromeOptimized(s, start + 1, end - 1);
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningII().minCut("nitia"));
        System.out.println(new PalindromePartitioningII().minCut("aab"));
        System.out.println(new PalindromePartitioningII().minCut("a"));
        System.out.println(new PalindromePartitioningII().minCut("ab"));
        System.out.println(new PalindromePartitioningII().minCut("abcdef"));
    }
}
