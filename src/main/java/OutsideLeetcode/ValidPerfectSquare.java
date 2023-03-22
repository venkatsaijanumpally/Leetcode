package OutsideLeetcode;

public class ValidPerfectSquare {
    /**
     * https://medium.com/swlh/valid-perfect-square-an-application-of-binary-search-algorithm-810f8c56b6cb
     * https://leetcode.com/discuss/interview-experience/1561125/Amazon-or-SDE-1-or-Offer
     * @param args
     */
    public static void main(String[] args){
        ValidPerfectSquare v=new ValidPerfectSquare();
        System.out.println(v.validPerfectSquare(64));
    }
    public boolean validPerfectSquare(int x){
        if(x==1) return true;

        int low=2,high=x/2;
        while (low<=high){
            int mid=low+(high-low)/2;
            if(mid*mid==x) return true;
            else if(mid*mid>x)high=mid-1;
            else low=mid+1;
        }
        return false;
    }
}
