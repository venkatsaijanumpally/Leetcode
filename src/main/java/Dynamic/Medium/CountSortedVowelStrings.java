package Dynamic.Medium;

public class CountSortedVowelStrings {
    public int countVowelStrings(int n) {
        int[][] t=new int[n+1][5];
        t[1][0]=5;
        t[1][1]=4;
        t[1][2]=3;
        t[1][3]=2;
        t[1][4]=1;

        for(int i=2;i<=n;i++){
            t[i][4]=1;
            for(int j=3;j>=0;j--){
                t[i][j]=t[i-1][j]+t[i][j+1];
            }
        }
        return t[n][0];
    }

    public static void main(String[] args) {
        System.out.println(new CountSortedVowelStrings().countVowelStrings(1));
        System.out.println(new CountSortedVowelStrings().countVowelStrings(2));
        System.out.println(new CountSortedVowelStrings().countVowelStrings(3));
    }
}
