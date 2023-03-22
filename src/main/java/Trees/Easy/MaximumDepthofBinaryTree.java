package Trees.Easy;

import Trees.Utils.TreeNode;

public class MaximumDepthofBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return maxDepthCount(root,0);
    }

    private int maxDepthCount(TreeNode root, int height) {
        if(root==null)
            return height;
        else {
            int lh=maxDepthCount(root.left,height+1);
            int rh=maxDepthCount(root.right,height+1);
            return lh>rh?lh:rh;
        }
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(2);
        //root.left.left=new TreeNode(5);
        //root.left.right=new TreeNode(4);
        root.right.left=new TreeNode(4);
        root.right.right=new TreeNode(5);
        root.right.right.right=new TreeNode(5);
        System.out.println(new MaximumDepthofBinaryTree().maxDepth(root));
    }
}
