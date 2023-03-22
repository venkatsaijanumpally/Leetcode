package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class TrimaBinarySearchTree {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null) return null;

        if(root.val < low) return trimBST(root.right,low,high);
        if(root.val > high) return trimBST(root.left,low,high);

        root.left=trimBST(root.left,low,high);
        root.right=trimBST(root.right,low,high);

        return root;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{4,2,6,1,3,5};
        Integer[] arr1=new Integer[]{3,0,4,null,2,null,null,1};
        TreeNode root=new TreeNode().constructTree(arr1);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,new TrimaBinarySearchTree().trimBST(root,1,3));
        System.out.println();
    }
}
