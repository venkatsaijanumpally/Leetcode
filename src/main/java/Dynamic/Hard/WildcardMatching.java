package Dynamic.Hard;

public class WildcardMatching {
    /*
     * recursiveBetterTopDown and recursiveBetter and TopDown all are optimal approaches and are same logic.
     * recursiveBetterTopDown is same as recursiveBetter but starts from top.
     * First understand the recursive approach from recursiveBetterTopDown and go to TopDown.
     */
    Boolean[][][] memoize;
    Boolean[][] memoizeBetter;
    public boolean isMatch(String s, String p) {
        memoize=new Boolean[s.length()][p.length()][2];
        //return recursive(s,p,0,0,false);


        /*memoizeBetter=new Boolean[s.length()][p.length()];
        return recursiveBetter(s,p,0,0);*/

        /*memoizeBetter=new Boolean[s.length()][p.length()];
        return recursiveBetterTopDown(s,p,s.length()-1,p.length()-1);*/

        //TopDown
        //!Optimal
        boolean[][] memoizeBetter=new boolean[s.length()+1][p.length()+1];

        memoizeBetter[0][0]=true;
        for(int i=1;i<=s.length();i++)
            memoizeBetter[i][0]=false;

        for(int j=1;j<=p.length();j++)
            if(p.charAt(j-1)=='*')
                memoizeBetter[0][j]=memoizeBetter[0][j-1];

        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)=='*')
                    memoizeBetter[i][j]=memoizeBetter[i-1][j] || memoizeBetter[i][j-1];
                else if(p.charAt(j-1)=='?' || s.charAt(i-1)==p.charAt(j-1))
                    memoizeBetter[i][j]=memoizeBetter[i-1][j-1];
                else memoizeBetter[i][j]=false;
            }
        }
        return memoizeBetter[s.length()][p.length()];
    }

    //!Optimal
    private boolean recursiveBetterTopDown(String s, String p, int sIndex, int pIndex) {
        if(pIndex==-1){
            if(sIndex==-1)  return true;
            return false;
        }
        if(sIndex==-1){
            while (pIndex!=-1 && p.charAt(pIndex)=='*')
                --pIndex;
            if(pIndex==-1) return true;
            return false;
        }
        if(memoizeBetter[sIndex][pIndex]!=null) return memoizeBetter[sIndex][pIndex];

        if(p.charAt(pIndex)=='*')
            return memoizeBetter[sIndex][pIndex]=
                    recursiveBetterTopDown(s,p,sIndex,pIndex-1) || recursiveBetterTopDown(s,p,sIndex-1,pIndex);
        else if(p.charAt(pIndex)=='?')
            return memoizeBetter[sIndex][pIndex]=recursiveBetterTopDown(s,p,sIndex-1,pIndex-1);
        else {
            if(s.charAt(sIndex)==p.charAt(pIndex))
                return memoizeBetter[sIndex][pIndex]=recursiveBetterTopDown(s,p,sIndex-1,pIndex-1);
            return memoizeBetter[sIndex][pIndex]=false;
        }
    }

    //!Optimal
    private boolean recursiveBetter(String s, String p, int sIndex, int pIndex) {
        if(pIndex==p.length()){
            if(sIndex==s.length())  return true;
            return false;
        }
        if(sIndex==s.length()){
            while (pIndex!=p.length() && p.charAt(pIndex)=='*')
                ++pIndex;
            if(pIndex==p.length()) return true;
            return false;
        }
        if(memoizeBetter[sIndex][pIndex]!=null) return memoizeBetter[sIndex][pIndex];

        if(p.charAt(pIndex)=='*')
            return memoizeBetter[sIndex][pIndex]=
                    recursiveBetter(s,p,sIndex,pIndex+1) || recursiveBetter(s,p,sIndex+1,pIndex);
        else if(p.charAt(pIndex)=='?')
            return memoizeBetter[sIndex][pIndex]=recursiveBetter(s,p,sIndex+1,pIndex+1);
        else {
            if(s.charAt(sIndex)==p.charAt(pIndex))
                return memoizeBetter[sIndex][pIndex]=recursiveBetter(s,p,sIndex+1,pIndex+1);
            return memoizeBetter[sIndex][pIndex]=false;
        }
    }

    private boolean recursive(String s, String p, int stringIndex, int patternIndex, boolean star) {
        if(stringIndex==s.length() || patternIndex==p.length()){
            if(patternIndex==p.length()){
                if(stringIndex==s.length() || star) return true;
                return false;
            }
            if(patternIndex!=p.length() && p.charAt(patternIndex)=='*')
                return recursive(s,p,stringIndex,patternIndex+1,true);
            return false;
        }
        int starint=star?1:0;
        if(memoize[stringIndex][patternIndex][starint]!=null) return memoize[stringIndex][patternIndex][starint];

        switch (p.charAt(patternIndex)){
            case '*':{
                return memoize[stringIndex][patternIndex][starint]=recursive(s,p,stringIndex,patternIndex+1,true);
            }
            case '?':{
                return memoize[stringIndex][patternIndex][starint]=recursive(s,p,stringIndex+1,patternIndex+1,star);
            }
            default:{
                if(star){
                    if(s.charAt(stringIndex)==p.charAt(patternIndex))
                        return memoize[stringIndex][patternIndex][starint]=recursive(s,p,stringIndex+1,patternIndex+1,false) ||
                                recursive(s,p,stringIndex+1,patternIndex,true);
                    return memoize[stringIndex][patternIndex][starint]=recursive(s,p,stringIndex+1,patternIndex,true);
                }
                else if(s.charAt(stringIndex)==p.charAt(patternIndex))
                    return memoize[stringIndex][patternIndex][starint]=recursive(s,p,stringIndex+1,patternIndex+1,false);
                return memoize[stringIndex][patternIndex][starint]=false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().isMatch("cdefghi","*"));
        System.out.println(new WildcardMatching().isMatch("cd","?d"));
        System.out.println(new WildcardMatching().isMatch("abpqrscdcd","ab*cd*"));
        System.out.println(new WildcardMatching().isMatch("aa","*"));
        System.out.println(new WildcardMatching().isMatch("aa","a"));
        System.out.println(new WildcardMatching().isMatch("","*******"));
        System.out.println(new WildcardMatching().isMatch("acdcb","a*c?b"));
    }
}
