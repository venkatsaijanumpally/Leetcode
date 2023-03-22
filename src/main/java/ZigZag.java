public class ZigZag {

    public static String zigZag(String s, int numRows){
        if(numRows == 1)
            return s;
        int cycle=2*numRows-2;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<numRows;i++){
            for(int j=i;j<s.length();j+=cycle){
                stringBuilder.append(s.charAt(j));
                int k=j+cycle-2*i;
                if(i!=0&&i!=numRows-1&&k<s.length())
                    stringBuilder.append(s.charAt(k));
            }
        }
        return String.valueOf(stringBuilder);
    }

    public static void main(String args[]) {
        System.out.println(zigZag("PAYPALISHIRING",4));
    }

}