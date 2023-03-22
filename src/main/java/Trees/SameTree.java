package Trees;

import Trees.Utils.TreeNode;
import Trees.Utils.TreeNode;

public class SameTree {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;

        if(p!=null && q!=null){
            if(p.val==q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            else return false;
        }
        return false;
    }
    private static boolean inorderTraversalList(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;

        if(p!=null && q!=null){
            return inorderTraversalList(p.left, q.left) && inorderTraversalList(p.right, q.right);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(4);

        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.left.left=new TreeNode(5);
        root1.left.right=new TreeNode(4);
        System.out.println(isSameTree(root,root1));
    }
}
