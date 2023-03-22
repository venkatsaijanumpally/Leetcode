package Trees.Hard;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class BinaryTreeMaximumPathSum {
    int maximum=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        findMax(root);
        return maximum;
    }

    /**
     * We start from leaf node. We have 2 operations at a particular node.
     * 1. Set the maximum if it is less than the maximum we find at a node.
     * 2. Return max value to parent node.
     *
     * 1. Set maximum: We find the maximum out of the combinations left+right+root, left+root, right+root.
     *    If none of them is greater than maximum them maximum wont be set.
     *    If none of them is greater than ZERO we return ZERO. Since not adding a negative sum path is better than
     *    adding it.
     *
     * 2. Return max value to parent
     *    We can only pass maximum out of left+root, root+right, root
     *    If none of them is greater than ZERO we return ZERO. Since not adding a negative sum path is better than
     *    adding it.
     *    Note: We cannot consider left+root+right since we can select only one path. We cannot move from left subtree
     *    to root and then to right subtree and then back to root and then to parent of root which is a wrong path.
     */
    private int findMax(TreeNode root) {
        if(root==null)
            return 0;

        int left=findMax(root.left);
        int right=findMax(root.right);
        int max= root.val;
        if(left>0 && right>0){
            if(maximum<max+left+right)
                maximum=max+left+right;
            return left>right? Math.max(left + max, 0) : Math.max(right+max, 0);
        }
        else if(left<0 && right<0){
            if(max>0 && max>maximum){
                maximum=max;
                return max;
            }
            return 0;
        }
        else if(left>0){
            if(maximum<max+left){
                maximum=max+left;
            }
            return max+left>0?max+left:0;
        }
        else {
            if(maximum<max+right){
                maximum=max+right;
            }
            return max+right>0?max+right:0;
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{-10,9,20,null,null,15,7};
        Integer[] arr1=new Integer[]{1,2,3};
        Integer[] arr2=new Integer[]{1,2,10,-1,-4,-5,-6,null,null,3,null,7,4,null,null,null,-2};
        TreeNode root=new TreeNode().constructTree(arr2);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(root));
    }
}
