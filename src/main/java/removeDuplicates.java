public class removeDuplicates {
    public static int removeduplicates(int[] arr, int value){
        int length=1,track=1;
        /*for(int i=0,j=arr.length-1;i<=j;i++,j--){
            if(arr[i]==arr[i-1])
                continue;
            else {
                arr[length]=arr[i];
                length++;
            }
        }*/
        int i=0,j=arr.length-1;
        while(i<j){
            if(arr[i]==value && arr[j]==value)
                j--;
            else if(arr[i]==value) {
                arr[i++]=arr[j--];
            }
            else{
                i++;
            }
        }
        if(i==j && arr[i]==value)
            length=i-1;
        else if(j<i) length=j;
        else length=i;
        for(int k=0;k<arr.length;k++){
         System.out.print(arr[k]+" ");
        }
        return length+1;
    }
    public static void main(String args[]){
        int[] arr={0,1,2,2,3,0,4,2};
        System.out.println(removeduplicates(arr,2));
    }
}
