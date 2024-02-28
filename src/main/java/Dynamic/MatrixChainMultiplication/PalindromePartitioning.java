package Dynamic.MatrixChainMultiplication;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    //Refer PalindromePartitioningII first
    Boolean[][] memoizePalindrome;
    List<List<String>>[][] memoize;

    public List<List<String>> partition(String s) {
        memoize = new ArrayList[s.length()][s.length()];
        memoizePalindrome = new Boolean[s.length()][s.length()];
        return recursive(s, 0, s.length() - 1);
    }

    private List<List<String>> recursive(String s, int start, int end) {
        List<List<String>> lists = new ArrayList<>();
        if (start >= end) {
            List<String> list = new ArrayList<>();
            list.add(s.substring(start, start + 1));
            lists.add(list);
            return lists;
        }
        if (memoize[start][end] != null) return memoize[start][end];
        if (isPalindrome(s, start, end)) {
            List<String> list = new ArrayList<>();
            list.add(s.substring(start, end + 1));
            lists.add(list);
        }

        for (int i = start; i < end; i++) {
            if (isPalindrome(s, start, i)) {
                for (List child : recursive(s, i + 1, end)) {
                    List<String> list = new ArrayList<>();
                    list.add(s.substring(start, i + 1));
                    list.addAll(child);
                    lists.add(list);
                }
            }
        }
        return memoize[start][end] = lists;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start >= end) return true;
        if (memoizePalindrome[start][end] != null) return memoizePalindrome[start][end];

        return memoizePalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome(s, start + 1, end - 1);
    }

/*    public List<List<String>> partition2(String s) {
        Boolean[][] memoize = new Boolean[s.length() + 1][s.length() + 1];

        int n=s.length();
        for (int i=1;i<=n;i++) memoize[i][i]=true;

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i<=n-len+1;i++){
                int j=i+len-1;

                if(s.charAt(i-1)==s.charAt(j-1) && ( len==2 || memoize[i+1][j-1] )){
                    memoize[i][j]=true;
                }
            }
        }
    }*/

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().partition("aab"));
        System.out.println(new PalindromePartitioning().partition("a"));
        System.out.println(new PalindromePartitioning().partition("aaba"));
        System.out.println(new PalindromePartitioning().partition("bb"));
    }
}
