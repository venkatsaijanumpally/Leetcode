package Trees.ConstructBinaryTree;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.HashMap;

public class PreorderandPostorderTraversal {

    int preindex=0;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildSubTree(preorder,postorder,0,postorder.length-1);
    }
    private TreeNode buildSubTree(int[] preorder, int[] postorder, int postStart, int postEnd) {
        if(postStart>postEnd)
            return null;

        TreeNode node=new TreeNode(preorder[preindex++]);

        if(postStart==postEnd) return node;
        int i;
        //Here we find the next element position in postorder array.
        for(i=postStart;i<=postEnd;i++){
            if(preorder[preindex]==postorder[i])
                break;
        }


        node.left=buildSubTree(preorder,postorder,postStart,i);
        node.right=buildSubTree(preorder,postorder,i+1,postEnd-1);

        return node;
    }

    public static void main(String[] args) {
        int[] postorder={4,5,2,6,7,3,1};
        int[] preorder={1,2,4,5,3,6,7};
        int[] preorder2={5,11,4,13,10,7,9,1,3,6,8,2,12,20,26};
        int[] postorder2={4,10,9,7,1,13,11,6,12,20,26,2,8,3,5};
        int[] postorder1={1};
        int[] preorder1={1};
        TreeNode root=new PreorderandPostorderTraversal().constructFromPrePost(preorder2,postorder2);
        //TreeNode root=new PreorderandPostorderTraversal().constructFromPrePost(preorder,postorder);
        //TreeNode root=new PreorderandPostorderTraversal().constructFromPrePost(new int[]{12,11,7,9,1},new int[]{12,11,9,7,1});
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
    }
}
