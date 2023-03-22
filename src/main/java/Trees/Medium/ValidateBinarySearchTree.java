package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidSubTree(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    boolean minflag=false,maxflag=false;
    private boolean isValidSubTree(TreeNode root, int minValue, int maxValue) {
        if(root==null) return true;

        if(root.val>=maxValue || root.val<=minValue){
            //Handle the scenario where root val is either Integer MAX or MIN value.
            if(root.val==Integer.MIN_VALUE && minValue==Integer.MIN_VALUE){
                if(minflag)
                    return false;
                else
                    minflag=true;
            }
            else if(root.val==Integer.MAX_VALUE && maxValue==Integer.MAX_VALUE){
                if(maxflag)
                    return false;
                else
                    maxflag=true;
            }
            else
                return false;
        }

        if(isValidSubTree(root.left,minValue, root.val) && isValidSubTree(root.right, root.val, maxValue))
            return true;
        return false;
    }

    public static void main(String[] args) {
        TreeNode root1=new TreeNode(2);
        root1.right=new TreeNode(8);
        root1.right.right=new TreeNode(12);
        root1.right.left=new TreeNode(4);
        root1.right.left.right=new TreeNode(5);
        root1.right.right.right=new TreeNode(13);
        root1.left=new TreeNode(2);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root1);
        System.out.println(new ValidateBinarySearchTree().isValidBST(root1));

        TreeNode root=new TreeNode(2147483647);
        root.left=new TreeNode(-2147483648);
        root.right=new TreeNode(2147483647);
        //root.left.left=new TreeNode(-2147483648);
        System.out.println(new ValidateBinarySearchTree().isValidBST(root));
    }
}
