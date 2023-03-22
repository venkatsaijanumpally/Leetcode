package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class InsertintoaBinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode ptr=root;
        TreeNode prev=null;
        while (ptr!=null){
            prev=ptr;
            if(ptr.val<val)
                ptr=ptr.right;
            else ptr=ptr.left;
        }
        if(prev==null){
            return new TreeNode(val);
            /*if(prev.val<val)
                prev.right=new TreeNode(val);
            else prev.left=new TreeNode(val);
            return root;*/
        }
        else if(prev.val<val){
            prev.right=new TreeNode(val);
            return root;
        }
        else {
            prev.left=new TreeNode(val);
            return root;
        }

    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{4,2,7,1,3};
        Integer[] arr1=new Integer[]{40,20,60,10,30,50,70};
        Integer[] arr3=new Integer[]{4,2,7,1,3,null,null,null,null,null,null};
        TreeNode root=new TreeNode().constructTree(arr3);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        b.print(System.out,new InsertintoaBinarySearchTree().insertIntoBST(root,5));
    }
}
