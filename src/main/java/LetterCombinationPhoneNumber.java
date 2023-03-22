import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationPhoneNumber {
    static HashMap<Integer, ArrayList<Character>> hm=new HashMap<>();
    static List<String> result=new ArrayList<>();
    public static List<String> letterCombinations(String digits) {
        hm.put(2, new ArrayList<>(Arrays.asList('a','b','c')));
        hm.put(3, new ArrayList<>(Arrays.asList('d','e','f')));
        hm.put(4, new ArrayList<>(Arrays.asList('g','h','i')));
        hm.put(5, new ArrayList<>(Arrays.asList('j','k','l')));
        hm.put(6, new ArrayList<>(Arrays.asList('m','n','o')));
        hm.put(7, new ArrayList<>(Arrays.asList('p','q','r','s')));
        hm.put(8, new ArrayList<>(Arrays.asList('t','u','v')));
        hm.put(9, new ArrayList<>(Arrays.asList('w','x','y','z')));
        
        //ArrayList<Integer> ar=new ArrayList<>();
        //ArrayList<String> l=new ArrayList<>();
        System.out.println(hm);
        /*StringBuilder s=new StringBuilder();
        StringBuilder s1=new StringBuilder();
        s.append('a');
        s1.append(s);
        s1.append('b');
        System.out.println(s1);*/
        findAllPermutations(digits,0, new StringBuilder());
        return result;
    }

    private static void findAllPermutations(String digits, int pos, StringBuilder s) {
        if(pos==digits.length())
        {
            if(s.length()>0)
                result.add(String.valueOf(s));
            return;
        }
        int posdigit = Character.getNumericValue(digits.charAt(pos));

        /*if((posdigit == 7 || posdigit == 9) && alphaIndex ==4)
            return;

        if(alphaIndex == 3)
            return;*/

        StringBuilder s1 =new StringBuilder();
        s1.append(s);
        s1.append(hm.get(posdigit).get(0));
        findAllPermutations(digits,pos+1,s1);

        StringBuilder s2 =new StringBuilder();
        s2.append(s);
        s2.append(hm.get(posdigit).get(1));
        findAllPermutations(digits,pos+1,s2);

        StringBuilder s3 =new StringBuilder();
        s3.append(s);
        s3.append(hm.get(posdigit).get(2));
        findAllPermutations(digits,pos+1,s3);

        if(posdigit == 7 || posdigit == 9){
            StringBuilder s4 =new StringBuilder();
            s4.append(s);
            s4.append(hm.get(posdigit).get(3));
            findAllPermutations(digits,pos+1,s4);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(letterCombinations("24"));
    }
}
