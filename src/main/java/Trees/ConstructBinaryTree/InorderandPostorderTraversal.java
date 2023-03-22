package Trees.ConstructBinaryTree;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.HashMap;

public class InorderandPostorderTraversal {
    HashMap<Integer,Integer> hm=new HashMap<>();
    int postindex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i=0;i<postorder.length;i++)
            hm.put(inorder[i],i);
        postindex=postorder.length-1;
        return buildSubTree(postorder,inorder,0,postorder.length-1);
    }

    private TreeNode buildSubTree(int[] postorder, int[] inorder, int inStart, int inEnd) {
        if(inStart>inEnd)
            return null;

        TreeNode node=new TreeNode(postorder[postindex]);
        int inRoot=hm.get(postorder[postindex--]);

        node.right=buildSubTree(postorder,inorder,inRoot+1,inEnd);
        node.left=buildSubTree(postorder,inorder,inStart,inRoot-1);

        return node;
    }

    public static void main(String[] args) {
        int[] postorder={9,15,7,20,3};
        int[] inorder={9,3,15,20,7};
        TreeNode root=new InorderandPostorderTraversal().buildTree(inorder,postorder);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
    }
}
