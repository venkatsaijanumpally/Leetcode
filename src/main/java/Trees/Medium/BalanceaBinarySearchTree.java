package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalanceaBinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        inorder(root,list);
        return formBalancedBST(list,0,list.size()-1);
    }

    private TreeNode formBalancedBST(List<Integer> list, int low, int high) {
        if(low>high)
            return null;

        int mid=low+(high-low)/2;
        TreeNode root=new TreeNode(list.get(mid));
        root.left=formBalancedBST(list,low,mid-1);
        root.right=formBalancedBST(list,mid+1,high);
        return root;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if(root==null) return;

        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,null,2,null,3,null,4,null,null};
        Integer[] arr1=new Integer[]{2,1,3};
        Integer[] arr2=new Integer[]{1,null,15,14,17,7,null,null,null,2,12,null,3,9,null,null,null,null,11};
        Integer[] arr3=new Integer[]{1,5,2,null,null,null,3,null,4,null,5};
        TreeNode root=new TreeNode().constructTree(arr2);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        b.print(System.out,new BalanceaBinarySearchTree().balanceBST(root));
    }
}
