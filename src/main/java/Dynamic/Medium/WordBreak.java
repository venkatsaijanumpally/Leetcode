package Dynamic.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak {
    Boolean[][] memoize;
    public boolean wordBreak(String s, List<String> wordDict) {
        memoize=new Boolean[s.length()+1][s.length()+1];
        return recursiveMemoize(s,wordDict,1,1);
    }

    private boolean recursiveMemoize(String s, List<String> wordDict, int prevIndex, int currIndex) {
        if(currIndex>s.length()){
            if(prevIndex==currIndex) return true;
            return false;
        }
        if(memoize[prevIndex][currIndex]!=null) return memoize[prevIndex][currIndex];

        if(wordDict.contains(s.substring(prevIndex-1,currIndex)))
            return memoize[prevIndex][currIndex]=recursiveMemoize(s,wordDict,currIndex+1,currIndex+1) ||
                    recursiveMemoize(s,wordDict,prevIndex,currIndex+1);
        return memoize[prevIndex][currIndex]=recursiveMemoize(s,wordDict,prevIndex,currIndex+1);
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak().wordBreak("leetcode",new ArrayList<String>(Arrays.asList("leet","code"))));
        System.out.println(new WordBreak().wordBreak("applepenapple",new ArrayList<String>(Arrays.asList("apple","pen"))));
    }
}
