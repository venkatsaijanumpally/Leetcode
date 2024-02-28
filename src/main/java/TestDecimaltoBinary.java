public class TestDecimaltoBinary {

    public int recur(String s, int i, int j){
        if(isPalindrome(s,i,j))
            return 0;

        int min=Integer.MAX_VALUE;
        for (int k=i;k<j;k++){
            min = Math.min(1+recur(s,i,k)+recur(s,k+1,j),min);
        }
        return min;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i<j){
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;j--;
        }
        return true;
    }

    public static void main(String[] args) {
        int decimal = 10;

        StringBuilder stringBuilder=new StringBuilder();
        while (decimal>0){
            if((decimal&1)>0)
                stringBuilder.insert(0,"1");
            else stringBuilder.insert(0,"0");
            decimal=decimal>>1;
        }
        System.out.println(stringBuilder);



        System.out.println(new TestDecimaltoBinary().recur("aab",0,2));
        System.out.println(new TestDecimaltoBinary().recur("bababcbadcede",0,12));

    }
}
