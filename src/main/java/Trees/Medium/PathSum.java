package Trees.Medium;

import Trees.Utils.TreeNode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        return findPath1(root,targetSum,0);
    }

    /*private boolean findPath(TreeNode root, int targetSum, int sum) {
        if(sum>targetSum)
            return false;
        if(root==null)
            return targetSum == sum;

        *//*if(findPath(root.left,targetSum,sum+root.val) || findPath(root.right,targetSum,sum+ root.val))
            return true;

        return false;*//*
        return findPath(root.left,targetSum,sum+root.val) || findPath(root.right,targetSum,sum+ root.val);
    }*/

    //!Optimal Solution
    private boolean findPath1(TreeNode root, int targetSum, int sum) {
        sum+= root.val;

        if(root.right==null && root.left==null)
            return targetSum==sum;

        if(root.left==null)
            return findPath1(root.right,targetSum,sum);

        if(root.right==null)
            return findPath1(root.left,targetSum,sum);

        return findPath1(root.left,targetSum,sum) || findPath1(root.right,targetSum,sum);
    }

    public static void main(String[] args) {
        TreeNode root1=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.left=new TreeNode(8);
        root1.right.right=new TreeNode(4);
        root1.right.right.right=new TreeNode(5);
        root1.right.right.left=new TreeNode(6);
        System.out.print(new PathSum().hasPathSum(root1,15));
    }
}
