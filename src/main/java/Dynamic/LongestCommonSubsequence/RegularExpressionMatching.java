package Dynamic.LongestCommonSubsequence;

public class RegularExpressionMatching {
    Boolean[][] memoize;
    public boolean isMatch(String s, String p) {
        memoize=new Boolean[s.length()][p.length()];
        return recursive(s,p,s.length()-1,p.length()-1);
    }

    private boolean recursive(String s, String p, int sIndex, int pIndex) {
        if(pIndex==-1) {
            if(sIndex==-1) return true;
            return false;
        }
        if(sIndex==-1){
            while (pIndex>-1 && p.charAt(pIndex)=='*')
                pIndex=pIndex-2;
            return pIndex==-1;
        }
        if(memoize[sIndex][pIndex]!=null) return memoize[sIndex][pIndex];

        if(p.charAt(pIndex)=='.')
            return memoize[sIndex][pIndex]=recursive(s,p,sIndex-1,pIndex-1);
        else if(p.charAt(pIndex)=='*'){
            boolean add=false,skip=false;
            if(p.charAt(pIndex-1)!='.'){
                if(p.charAt(pIndex-1)==s.charAt(sIndex)){
                    add=recursive(s,p,sIndex-1,pIndex);
                    skip=recursive(s,p,sIndex,pIndex-2);
                }
                else
                    add=recursive(s,p,sIndex,pIndex-2);
            }
            else {
                add=recursive(s,p,sIndex-1,pIndex);
                skip=recursive(s,p,sIndex,pIndex-2);
            }
            return memoize[sIndex][pIndex]=add || skip;
        }
        else return memoize[sIndex][pIndex]=s.charAt(sIndex)==p.charAt(pIndex) && recursive(s,p,sIndex-1,pIndex-1);
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching().isMatch("aa","a"));
        System.out.println(new RegularExpressionMatching().isMatch("aa","a*"));
        System.out.println(new RegularExpressionMatching().isMatch("ab",".*"));
        System.out.println(new RegularExpressionMatching().isMatch("aabbcc","a*"));
        System.out.println(new RegularExpressionMatching().isMatch("aabbcc",".*"));
        System.out.println(new RegularExpressionMatching().isMatch("aabbcc",".*bc*"));
        System.out.println(new RegularExpressionMatching().isMatch("aab","c*a*b"));
    }
}
