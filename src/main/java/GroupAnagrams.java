import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] str={"eat","tea","tan","ate","nat","bat"};
        System.out.println(new GroupAnagrams().groupAnagrams(str));
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list=new ArrayList<>();
        int index=0;
        HashMap<String,Integer> hm=new HashMap<>();
        for (int i = 0; i < strs.length ; i++) {
            char[] ch=strs[i].toCharArray();
            Arrays.sort(ch);
            String s=new String(ch);
            if(hm.containsKey(s)){
                list.get(hm.get(s)).add(strs[i]);
            }
            else{
                hm.put(s,index);
                list.add(new ArrayList<>());
                list.get(index).add(strs[i]);
                index++;
            }
        }
        return list;
    }
}
