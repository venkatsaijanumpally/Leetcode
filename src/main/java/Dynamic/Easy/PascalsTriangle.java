package Dynamic.Easy;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    List<List<Integer>> result;
    public List<List<Integer>> generate(int numRows) {
        result=new ArrayList<>();
        result.add(new ArrayList<>());
        result.get(0).add(1);
        for(int i=1;i<numRows;i++)
        {
            List<Integer> prev=result.get(i-1);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(1);
            for(int j=1;j<=i-1;j++)
                list.add(prev.get(j-1)+prev.get(j));
            list.add(1);
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangle().generate(5));
    }
}
