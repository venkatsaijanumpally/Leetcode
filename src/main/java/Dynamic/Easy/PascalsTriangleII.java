package Dynamic.Easy;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    /*
     * https://www.shmoop.com/polynomial-equations/Pascal-and-Binomial-Theorem.html%20
     * https://www.geeksforgeeks.org/find-the-nth-row-in-pascals-triangle/
     * Pascal Triangle is the expansion of binomial expression (a+b)^n
     * The nth row is nothing but NC0, NC1, NC2, ....  NCN
     * We use the NCR formula to calculate elements in a row
     * Formula NCR/NC(R-1) = (N-R+1)/R
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result=new ArrayList<>();

        int prev=1;
        result.add(1);
        for(int i=1;i<=rowIndex;i++){
            long curr=((long)prev*(rowIndex-i+1))/i;
            result.add((int)curr);
            prev=(int)curr;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangleII().getRow(30));
    }
}
