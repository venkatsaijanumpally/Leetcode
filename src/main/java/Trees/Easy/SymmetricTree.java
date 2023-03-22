package Trees.Easy;

import Trees.Utils.TreeNode;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return isSymmetricChilds(root.left,root.right);
    }

    private boolean isSymmetricChilds(TreeNode left, TreeNode right) {
        if(left==null && right==null)
            return true;
        else if(left!=null && right!=null){
            if(left.val==right.val)
                return isSymmetricChilds(left.right,right.left) && isSymmetricChilds(left.left,right.right);
            else return false;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(2);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(4);
        root.right.left=new TreeNode(4);
        root.right.right=new TreeNode(5);
        System.out.println(new SymmetricTree().isSymmetric(root));
    }
}
