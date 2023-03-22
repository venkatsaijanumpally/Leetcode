public class LongestSubstringwithAtLeastKRepeatingCharacters {
    /**
     * https://www.youtube.com/watch?v=5QpMpO2CAb0
     * https://leetcode.com/discuss/interview-experience/1560441/SAP-Labs-or-Software-developer-or-Accepted-or-August-2021
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new LongestSubstringwithAtLeastKRepeatingCharacters().longestSubstring("aaabb",3));
    }
    public int longestSubstring(String s, int k) {
        return longestSubSubstring(s.toCharArray(),0,s.length(),k);
    }

    private int longestSubSubstring(char[] array, int start, int end, int k) {
        if(end-start<k) return 0;

        int[] count=new int[26];
        for(int i=start;i<end;i++)
            count[array[i]-'a']++;

        for (int i=start;i<end;i++){
            if(count[array[i]-'a']<k){
                int j=i+1;
                while (j<end && count[array[j]-'a']<k)
                    j++;
                return Math.max(longestSubSubstring(array,start,i,k),longestSubSubstring(array,j,end,k));
            }
        }
        return end-start;
    }
}
