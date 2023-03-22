package Dynamic.Medium;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaystoAddParentheses {
    ArrayList[][] memoize;
    public List<Integer> diffWaysToCompute(String expression) {
        String[] arr=expression.split("[+]|[-]|[*]|[/]");
        expression=expression.replaceAll("\\d","");
        //String[] arr1=expression.split("\\d");
        char[] characters=new char[expression.length()];
        for(int i=0;i<expression.length();i++)
            characters[i]=expression.charAt(i);
        //char[] chars={'*','-','*'};

        int[] arrint=new int[arr.length];
        for (int i=0;i<arr.length;i++)
            arrint[i]=Integer.parseInt(arr[i]);
        memoize=new ArrayList[characters.length+1][characters.length+1];
        return recursive(arrint,characters,0,characters.length);
    }

    private ArrayList<Integer> recursive(int[] arr, char[] chars, int start,int end) {
        if(start==end){
            ArrayList<Integer> endlist=new ArrayList<>();
            if(start==chars.length)
                endlist.add(arr[chars.length]);
            else endlist.add(arr[start]);
            return endlist;
        }
        if(memoize[start][end]!=null) return memoize[start][end];

        ArrayList<Integer> list=new ArrayList<>();
        for(int i=start;i<end;i++){
            ArrayList<Integer> left=recursive(arr,chars,start,i);
            ArrayList<Integer> right=recursive(arr,chars,i+1,end);

            for(int j=0;j< left.size();j++){
                for(int k=0;k<right.size();k++){
                    switch (chars[i]){
                        case '+':{list.add(left.get(j)+right.get(k));break;}
                        case '-':{list.add(left.get(j)-right.get(k));break;}
                        case '*':{list.add(left.get(j)*right.get(k));break;}
                        default:{list.add(left.get(j)/right.get(k));break;}
                    }
                }
            }
        }
        return memoize[start][end]=list;
    }

    public static void main(String[] args) {
        System.out.println(new DifferentWaystoAddParentheses().diffWaysToCompute("2*3-4*5"));
        System.out.println(new DifferentWaystoAddParentheses().diffWaysToCompute("2-1-1"));
    }
}
