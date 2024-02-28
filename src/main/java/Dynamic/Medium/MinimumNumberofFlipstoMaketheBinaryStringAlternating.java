package Dynamic.Medium;

public class MinimumNumberofFlipstoMaketheBinaryStringAlternating {
    public int minFlips(String s) {
        if(s.length()==1)
            return 0;
        else if (!s.contains("0") || !s.contains("1")) {
            return s.length()/2;
        }

        int[] num=new int[s.length()];
        int n= num.length;
        for (int i=0;i< num.length;i++)
            num[i]=s.charAt(i)=='1'?1:0;

        int[] oddone=new int[n+1];
        int[] evenone=new int[n+1];

        for(int i=0;i<n;i++){
            if(i%2==0){
                evenone[i+1] = evenone[i] + (num[i]==1?0:1);
                oddone[i+1] = oddone[i] + (num[i]==1?1:0);
            }
            else {
                evenone[i+1] = evenone[i] + (num[i]==1?1:0);
                oddone[i+1] = oddone[i] + (num[i]==1?0:1);
            }
        }

        int minimum = Math.min(evenone[n],oddone[n]);

        if(n%2!=0){
            for (int i=1;i<n;i++){
                minimum = Math.min(minimum, oddone[n]-oddone[i]+evenone[i]);
                minimum = Math.min(minimum, evenone[n]-evenone[i]+oddone[i]);
            }
        }

        return minimum;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberofFlipstoMaketheBinaryStringAlternating().minFlips("111000"));
        System.out.println(new MinimumNumberofFlipstoMaketheBinaryStringAlternating().minFlips("1111"));
        System.out.println(new MinimumNumberofFlipstoMaketheBinaryStringAlternating().minFlips("000"));
        System.out.println(new MinimumNumberofFlipstoMaketheBinaryStringAlternating().minFlips("1110"));
        System.out.println(new MinimumNumberofFlipstoMaketheBinaryStringAlternating().minFlips("010"));
    }
}
