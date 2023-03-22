package BitManipulation.Easy;

public class Numberof1Bits {
    //https://leetcode.com/problems/number-of-1-bits/discuss/55099/Simple-Java-Solution-Bit-Shifting
    /*
     * Here we tackle even unsigned int.
     * see link.
     */
    public int hammingWeight(int n) {
        int res=0;
        while(n!=0){
            res+=(n&1);
            n=n>>>1;
        }
        return res;
    }
}
