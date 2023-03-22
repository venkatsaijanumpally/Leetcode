import java.util.HashMap;
import java.util.Map;

public class RomantoInt {
    public static int romantoInt(String s){
        //char c=s.charAt(0);
        int result=0;
        Map<Character,Integer> map=new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        //map.put('IV',4);
        map.put('X',10);
        //map.put('IX',9);
        map.put('L',50);
        //map.put('XL',40);
        map.put('C',100);
        //map.put('XC',90);
        map.put('D',500);
        //map.put('CD',40);
        map.put('M',1000);
        int priority=0;
        for (int i=s.length()-1; i>-1;i--){
                if(map.get(s.charAt(i))>=priority){
                    result+=map.get(s.charAt(i));
                    priority=map.get(s.charAt(i));
                }
                else {
                    result-=map.get(s.charAt(i));
                }
        }
        /*for (int i=0; i<s.length();i++){
            switch (s.charAt(i)){
                case 'M' : {
                    result+=1000;
                    break;
                }
                case 'D' : {
                    result+=500;
                    break;
                }
                case 'C' : {
                    result+=100;
                    break;
                }
                case 'L' : {
                    result+=50;
                    break;
                }
                case 'X' : {
                    result+=10;
                    break;
                }
                case 'V' : {
                    result+=5;
                    break;
                }
                case 'I' : {
                    result+=1;
                    break;
                }
            }
        }*/
        return result;
    }
    public static void main(String args[]){
        System.out.println(romantoInt("IV"));
    }
}
