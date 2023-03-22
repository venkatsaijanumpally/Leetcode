package Trees.Medium;

public class UniqueBinarySearchTrees {
    int[] arr;
    public int numTrees(int n) {
        arr=new int[n+1];
        arr[0]=1;
        return catalanNumber(n);
    }
    public int catalanNumber(int n){
        if(arr[n]!=0)
            return arr[n];
        arr[n]=0;
        for(int i=1;i<=n;i++){
            arr[n]+=catalanNumber(i-1)*catalanNumber(n-i);
        }
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTrees().numTrees(3));
    }
}
