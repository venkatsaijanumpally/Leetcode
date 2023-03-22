package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class NumberofGoodLeafNodesPairs {
    int result=0;
    public int countPairs(TreeNode root, int distance) {
        helper(root,distance);
        return result;
    }

    private int[] helper(TreeNode root, int distance) {
        if(root==null)
            return new int[distance+1];
        if(root.left==null && root.right==null){
            int[] arr=new int[distance+1];
            arr[1]++;
            return arr;
        }

        int[] left=helper(root.left,distance);
        int[] right=helper(root.right,distance);

        for (int i=1;i<distance;i++){
            for (int j=1;j<distance;j++){
                if(i+j>distance)
                    break;
                result+=left[i]*right[j];
            }
        }

        int[] res=new int[distance];
        for (int i=2;i<distance;i++){
            res[i]=left[i-1]+right[i-1];
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,3,4,5,6,7};
        Integer[] arr1=new Integer[]{7,1,4,6,null,5,3,null,null,null,null,null,2};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new NumberofGoodLeafNodesPairs().countPairs(root,3));
    }
}
