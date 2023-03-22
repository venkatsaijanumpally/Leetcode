package Trees.ConstructBinaryTree;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class ConstructBinarySearchTreefromPreorderTraversal {
    int index=0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return constructTreeFromPreorder(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private TreeNode constructTreeFromPreorder(int[] preorder, int min, int max) {
        if(index>preorder.length-1)
            return null;
        if(preorder[index]<min || preorder[index]>max)
            return null;
        TreeNode node=new TreeNode(preorder[index++]);
        node.left=constructTreeFromPreorder(preorder,min,node.val);
        node.right=constructTreeFromPreorder(preorder,node.val,max);
        return node;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{8,5,1,7,10,12};
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,new ConstructBinarySearchTreefromPreorderTraversal().bstFromPreorder(arr));
        System.out.println();
    }
}
