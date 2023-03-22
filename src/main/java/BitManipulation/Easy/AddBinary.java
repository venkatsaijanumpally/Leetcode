package BitManipulation.Easy;

public class AddBinary {
    public String addBinary(String a, String b) {
        if(a.equals("0"))
            return b;
        else if(b.equals("0"))
            return a;
        int aNum=0;
        int power=0;
        for(int i=a.length()-1;i>=0;i--){
            aNum+=Character.getNumericValue(a.charAt(i))*Math.pow(2,power);
            power++;
        }
        power=0;
        int bNum=0;
        for(int i=b.length()-1;i>=0;i--){
            bNum+=Character.getNumericValue(b.charAt(i))*Math.pow(2,power);
            power++;
        }

        int c=aNum+bNum;
        StringBuilder res= new StringBuilder();
        while (c>0){
            res.append(c & 1);
            c/=2;
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1010","1011"));
        System.out.println(new AddBinary().addBinary("11","1"));
        System.out.println(new AddBinary().addBinary("0","0"));
        System.out.println(new AddBinary().addBinary("11","0"));
    }
}
