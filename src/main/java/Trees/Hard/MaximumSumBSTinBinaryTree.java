package Trees.Hard;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class MaximumSumBSTinBinaryTree {
    int maximum=0;
    public int maxSumBST(TreeNode root) {
        //findMaxSum(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        findMaxSum2(root);
        return maximum;
    }

    private int[] findMaxSum2(TreeNode root) {
        if(root.left==null && root.right==null){
            if(maximum< root.val)
                maximum= root.val;
            return new int[]{1,root.val,root.val, root.val};
        }
        if(root.left==null){
            int[] right=findMaxSum2(root.right);
            if(right[0]==0 || right[2]<= root.val)
                return new int[4];
            int sum= root.val+right[1];
            if(sum>maximum)
                maximum=sum;
            return new int[]{1,sum, root.val, right[3]};
        }
        if(root.right==null){
            int[] left=findMaxSum2(root.left);
            if(left[0]==0 || left[3]>= root.val)
                return new int[4];
            int sum= root.val+left[1];
            if(sum>maximum)
                maximum=sum;
            return new int[]{1,sum, left[2], root.val};
        }

        int[] left=findMaxSum2(root.left);
        int[] right=findMaxSum2(root.right);

        if(left[0]==0 || right[0]==0)
            return new int[4];
        if(root.val<=left[3] || root.val>=right[2])
            return new int[4];

        int sum=left[1]+right[1]+root.val;
        if(sum>maximum)
            maximum=sum;
        return new int[]{1,sum,left[2],right[3]};
    }

    /*private int[] findMaxSum(TreeNode root, int min, int max) {
        if(root==null)
            return new int[]{1,0};
        int success=1;
        if(root.val<min || root.val>max)
            success=0;

        int[] left=findMaxSum(root.left,min,root.val-1);
        int[] right=findMaxSum(root.right, root.val+1,max);

        if(left[0]==0 || right[0]==0 || success==0)
            return new int[]{0,0};

        int sum=left[1]+right[1]+root.val;
        if(sum>maximum)
            maximum=sum;
        return new int[]{1,sum};
    }*/

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,4,3,2,4,2,5,null,null,null,null,null,null,4,6};
        Integer[] arr1=new Integer[]{4,3,null,1,2};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new MaximumSumBSTinBinaryTree().maxSumBST(root));
    }
}
