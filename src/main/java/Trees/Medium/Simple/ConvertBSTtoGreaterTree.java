package Trees.Medium.Simple;

import Trees.Utils.TreeNode;

public class ConvertBSTtoGreaterTree {
    /*
     * Duplicate - https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
     * Start from right bottom node and update values.
     */
    int sum=0;
    public TreeNode convertBST(TreeNode root) {
        modifyValues(root);
        return root;
    }

    private void modifyValues(TreeNode root) {
        if(root==null) return;
        modifyValues(root.right);
        sum+= root.val;
        root.val=sum;
        modifyValues(root.left);
    }
}
