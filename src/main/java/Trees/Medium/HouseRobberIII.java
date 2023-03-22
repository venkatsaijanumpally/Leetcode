package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class HouseRobberIII {
    /*
     * https://medium.com/@mollishrees/how-to-solve-house-robber-iii-leetcode-337-46cd8b73f695
     * At a particular root we run recursive function for left subtree and right subtree and
     * to calculate result
     * result[0] -> we include root and get grandchild
     * result[1] -> we don't include root and get best of child and grandchild.
     */
    public int rob(TreeNode root) {
        int[] arr=robHouses(root);
        return Math.max(arr[0], arr[1]);
    }

    private int[] robHouses(TreeNode root) {
        if(root==null)
            return new int[]{0,0};

        int[] result=new int[2];
        int[] left=robHouses(root.left);
        int[] right=robHouses(root.right);

        result[0]=root.val+left[1]+right[1];
        result[1]=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return result;
    }

    public static void main(String[] args) {
        TreeNode node=new TreeNode().constructTree(new Integer[]{3,2,3,null,3,null,1});
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,node);
        System.out.println();
        System.out.println(new HouseRobberIII().rob(node));
    }
}
