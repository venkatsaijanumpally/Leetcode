package Trees.Easy;

import Trees.Utils.TreeNode;

public class SumofLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return sum(root,false);
    }

    private int sum(TreeNode root, boolean i) {
        if(i && root.left==null && root.right==null)
            return root.val;
        int c=0;
        if(root.left!=null)
            c+=sum(root.left, true);
        if(root.right!=null)
            c+=sum(root.right,false);
        return c;
    }
}
