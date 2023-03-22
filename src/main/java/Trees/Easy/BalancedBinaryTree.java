package Trees.Easy;

import Trees.Utils.TreeNode;

public class BalancedBinaryTree {

    public int calculateHeight(TreeNode root){
        if(root==null) return 0;
        return 1+Math.max(calculateHeight(root.left),calculateHeight(root.right));
    }

    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        int lh,rh;
        lh=calculateHeight(root.left);
        rh=calculateHeight(root.right);
        if(Math.abs(lh-rh)<=1 && isBalanced(root.left) && isBalanced(root.right))
            return true;
        return false;
    }

    public static void main(String[] args) {

    }
}
