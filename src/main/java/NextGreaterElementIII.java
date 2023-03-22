public class NextGreaterElementIII {
    public static void main(String[] args) {
        System.out.println(new NextGreaterElementIII().nextGreaterElement(2147483486));
    }
    public int nextGreaterElement(int n) {
        char[] num=String.valueOf(n).toCharArray();
        int length=num.length;
        int i=length-1;
        for(;i>0;i--){
            if(num[i]>num[i-1])
                break;
        }
        if(i==0) return -1;
        for(int j=length-1;j>=i;j--){
            if(num[i-1]<num[j]){
                char temp=num[i-1];
                num[i-1]=num[j];
                num[j]=temp;
                break;
            }
        }
        for(int j=i;j<=i+(length-1-i)/2;j++){
            char tempswap = num[j];
            num[j] = num[length-1 - (j-i)];
            num[length-1 - (j -i)] = tempswap;
        }
        long val = Long.parseLong(new String(num));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
        //return Integer.parseInt(String.valueOf(num));
    }
}
