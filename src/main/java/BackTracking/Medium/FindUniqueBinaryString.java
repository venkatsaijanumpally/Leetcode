package BackTracking.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(nums));
        return findDifferentBinaryString(hashSet,0, new StringBuilder()).toString();
    }

    public StringBuilder findDifferentBinaryString(HashSet<String> nums, int index, StringBuilder sb){
        if(index==nums.size()){
            if(nums.contains(sb.toString()))
                return new StringBuilder();
            return sb;
        }
        index++;
        StringBuilder s1=findDifferentBinaryString(nums,index,sb.append('0'));
        if(s1.length()!=0)
            return s1;
        sb.deleteCharAt(sb.length()-1);
        StringBuilder s2=findDifferentBinaryString(nums,index,sb.append('1'));
        if(s2.length()!=0)
            return s2;
        sb.deleteCharAt(sb.length()-1);
        return s2;
    }

    public static void main(String[] args) {
        String[] s={"010","001","000"};
        System.out.println(new FindUniqueBinaryString().findDifferentBinaryString(s));
    }
}
