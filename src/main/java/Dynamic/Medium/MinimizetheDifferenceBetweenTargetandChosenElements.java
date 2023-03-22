package Dynamic.Medium;

public class MinimizetheDifferenceBetweenTargetandChosenElements {
    int[][] matrix;
    int n;
    int target;
    Integer[][] memoize;
    public int minimizeTheDifference(int[][] mat, int target) {
        matrix=mat;
        this.target=target;
        n=matrix[0].length;
        memoize=new Integer[mat.length][4901];
        return recursive(0,0);
    }

    private int recursive(int val, int index) {
        if(index== matrix.length){
            return Math.abs(target-val);
        }
        if(memoize[index][val]!=null) return memoize[index][val];

        int result=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            result=Math.min(result,recursive(val+matrix[index][i],index+1));
        }
        return memoize[index][val]=result;
    }

    public static void main(String[] args) {
        int[][] arr1={{1,2,3},{4,5,6},{7,8,9}};
        int[][] arr2={{1},{2},{3}};
        int[][] arr3={{1,2,9,8,7}};
        System.out.println(new MinimizetheDifferenceBetweenTargetandChosenElements().minimizeTheDifference(arr1,13));
        System.out.println(new MinimizetheDifferenceBetweenTargetandChosenElements().minimizeTheDifference(arr2,100));
        System.out.println(new MinimizetheDifferenceBetweenTargetandChosenElements().minimizeTheDifference(arr3,6));
    }
}
