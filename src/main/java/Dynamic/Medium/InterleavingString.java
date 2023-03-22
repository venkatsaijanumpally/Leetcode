package Dynamic.Medium;

import java.util.HashMap;

public class InterleavingString {
    public static void main(String args[]) {
        System.out.println(isInterleaveDP("aabcc", "dbbca", "aadbbbaccc"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int i=0,j=0,k=0;
        int f2=0;
        /*while(k<s3.length()){
            if(i<s1.length() && f2!=1 && s1.charAt(i)==s3.charAt(k)){
                i++;
                k++;
                f2=0;
            }
            else if(j<s2.length() && s2.charAt(j)==s3.charAt(k)){
                j++;
                k++;
                f2=1;
            }
            else if(f2==1)
                f2=0;
            else
                break;
        }*/
        while(k<s3.length()){
            if(j<s2.length() && s2.charAt(j)==s3.charAt(k)){
                j++;
                k++;
            }
            else if(i<s1.length() && s1.charAt(i)==s3.charAt(k)){
                i++;
                k++;
            }
            else
                break;
        }
        return i == s1.length()  && j == s2.length()  && k == s3.length();
    }

    static HashMap<String,Boolean> hm=new HashMap<>();
    public static boolean isInterleaveDP(String s1, String s2, String s3){
        int len1=s1.length();
        int len2=s2.length();
        int len3=s3.length();

        if(len1+len2!=len3)
            return false;
        /*else return checkInterleave(s1,s2,s3,len1,len2,len3,0,0,0);*/
        else{
            int[][] bool_arr=new int[len1+1][len2+1];
            return checkInterleave1(s1,s2,s3,len1,len2,len3,0,0,0,bool_arr)==1;
        }

    }

    private static boolean checkInterleave(String s1, String s2, String s3, int len1, int len2, int len3, int i, int j, int k) {
        if(k==len3)
            return (i==len1 && j==len2)?true:false;

        String s= i + "*" + j + "*" + k;
        if(hm.containsKey(s))
            return hm.get(s);

        if(i==len1){
            hm.put(s, (s2.charAt(j)==s3.charAt(k))?checkInterleave(s1,s2,s3,len1,len2,len3,i,j+1,k+1):false);
            return hm.get(s);
        }
        if(j==len2){
            hm.put(s,(s1.charAt(i)==s3.charAt(k))?checkInterleave(s1,s2,s3,len1,len2,len3,i+1,j,k+1):false);
            return hm.get(s);
        }

        boolean r1=false,r2=false;
        if(s1.charAt(i)==s3.charAt(k))
            r1= checkInterleave(s1,s2,s3,len1,len2,len3,i+1,j,k+1);
        if(s2.charAt(j)==s3.charAt(k))
            r2= checkInterleave(s1,s2,s3,len1,len2,len3,i,j+1,k+1);
        hm.put(s,r1 || r2);
        return r1 || r2;
    }
    private static int checkInterleave1(String s1, String s2, String s3, int len1, int len2, int len3, int i, int j, int k, int[][] bool) {
        if(k==len3)
            return i == len1 && j == len2?1:-1;

        if(bool[i][j]!=0)
            return bool[i][j];

        if(i==len1){
            bool[i][j] = (s2.charAt(j)==s3.charAt(k))?checkInterleave1(s1,s2,s3,len1,len2,len3,i,j+1,k+1,bool):-1;
            return bool[i][j];
        }
        if(j==len2){
            bool[i][j] =(s1.charAt(i)==s3.charAt(k))?checkInterleave1(s1,s2,s3,len1,len2,len3,i+1,j,k+1,bool):-1;
            return bool[i][j];
        }

        int r1=-1,r2=-1;
        if(s1.charAt(i)==s3.charAt(k))
            r1= checkInterleave1(s1,s2,s3,len1,len2,len3,i+1,j,k+1,bool);
        if(s2.charAt(j)==s3.charAt(k))
            r2= checkInterleave1(s1,s2,s3,len1,len2,len3,i,j+1,k+1,bool);
        bool[i][j]=Math.max(r1,r2);
        return bool[i][j];
    }
}
