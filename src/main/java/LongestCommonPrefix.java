public class LongestCommonPrefix {
    public String commonPrefix(String s1, String s2) {
        StringBuilder result =  new StringBuilder();
        int i=0,j=0;
        for(i=0,j=0;i<s1.length()&&j<s2.length();i++,j++){
            if(s1.charAt(i)!=s2.charAt(j))
                break;
            result.append(s1.charAt(i));
        }
        return result.toString();
    }
    public String longestcommonPrefix(int start, int end, String[] arr){
        if(start==end)
            return arr[start];
        else if(end>start){
            int mid= start+(end-start)/2;
            String s1=longestcommonPrefix(start,mid,arr);
            String s2=longestcommonPrefix(mid+1,end,arr);

            return commonPrefix(s1,s2);
        }
        return "";
    }
    public static void main (String[] args) {

    }
}
