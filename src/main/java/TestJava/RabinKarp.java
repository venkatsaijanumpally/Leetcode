package TestJava;

public class RabinKarp {
    public RabinKarp(){}

    public long hashCode(String string){
        long hashcode=0;
        int power=string.length()-1;
        for(int i=0;i<string.length();i++){
            hashcode+=(string.charAt(i)-96) * Math.pow(10,power--);
        }
        return hashcode;
    }

    public long rollingHash(String string, int start, int end, long currentHash, int patternLength){
        if(end==string.length()-1) return 0;
        long rollingHash=currentHash;
        int deletingPower=Math.abs(start-end);
        rollingHash-=(string.charAt(start)-96)*Math.pow(10,deletingPower);
        rollingHash*=Math.pow(10,1);
        //System.out.println(start+" "+end);
        rollingHash+=(string.charAt(end+1)-96);
        return rollingHash;
    }

    public boolean search(String string, String pattern){
        string=string.toLowerCase();
        pattern=pattern.toLowerCase();
        long patternHash=hashCode(pattern);
        long currentHash=hashCode(string.substring(0,pattern.length()));
        for(int i=0;i<string.length()-pattern.length()+1;i++) {
            if (patternHash == currentHash){
                if (string.substring(i, i + pattern.length()).equals(pattern))
                    return true;
            }
            currentHash=rollingHash(string,i,i+pattern.length()-1,currentHash, pattern.length());
        }
        return false;
    }
}
