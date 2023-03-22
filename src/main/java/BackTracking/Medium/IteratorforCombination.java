package BackTracking.Medium;

import java.util.ArrayList;

public class IteratorforCombination {
    char[] chars;
    ArrayList<StringBuilder> list;
    int currIndex=0;
    public IteratorforCombination(String characters, int combinationLength) {
        chars=characters.toCharArray();
        list=new ArrayList<>();
        generateAllComb(0,list,chars,combinationLength, new StringBuilder());
        System.out.println();
    }

    private void generateAllComb(int index, ArrayList<StringBuilder> list, char[] chars,int combinationLength, StringBuilder st) {
        if(st.length()==combinationLength){
            list.add(new StringBuilder(st));
            return;
        }
        if(index== chars.length) return;
        st.append(chars[index]);
        generateAllComb(index+1,list,chars,combinationLength,st);
        st.deleteCharAt(st.length()-1);
        generateAllComb(index+1,list,chars,combinationLength,st);
    }

    public String next() {
        return list.get(currIndex++).toString();
    }

    public boolean hasNext() {
        return !(currIndex ==list.size());
    }


    public static void main(String[] args) {
        IteratorforCombination it=new IteratorforCombination("abc",2);
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
    }
}
