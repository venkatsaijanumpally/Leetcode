package Trees.ConstructBinaryTree;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.HashMap;

public class PreorderandInorderTraversal {
    HashMap<Integer,Integer> hm=new HashMap<>();
    int preorderIndex=0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++)
            hm.put(inorder[i], i);
        return buildSubTreeHashmap(preorder,inorder,0,inorder.length-1);
        //return buildSubTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }

    /**
     *https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
     * !Optimal solution
     */
    private TreeNode buildSubTreeHashmap(int[] preorder, int[] inorder, int inStart, int inEnd) {
        if(inStart>inEnd)
            return null;

        TreeNode node=new TreeNode(preorder[preorderIndex]);
        int inorderRoot=hm.get(preorder[preorderIndex++]);

        node.left=buildSubTreeHashmap(preorder,inorder,inStart,inorderRoot-1);
        node.right=buildSubTreeHashmap(preorder,inorder,inorderRoot+1,inEnd);
        return node;
    }

    private TreeNode buildSubTree(int[] preorder, int[] inorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd) {
        if(preorderStart>preorderEnd)
            return null;

        TreeNode root=new TreeNode(preorder[preorderStart]);

        int inorderRootindex=inorderStart,newPreorderEnd=preorderStart;
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=inorderStart;i<=inorderEnd;i++){
            if(inorder[i]==preorder[preorderStart])
            {
                inorderRootindex=i;
                break;
            }
            hm.put(inorder[i],i);
        }
        if(inorderRootindex==inorderStart)
            root.left=null;
        else {
            /*while (preorder[newPreorderEnd]!=inorder[inorderRootindex-1])
                newPreorderEnd++;*/
            while (newPreorderEnd+1<=preorderEnd && hm.containsKey(preorder[newPreorderEnd+1]))
                newPreorderEnd++;

            root.left=buildSubTree(preorder,inorder,preorderStart+1,newPreorderEnd,inorderStart,inorderRootindex-1);
        }
        if(inorderRootindex==inorderEnd)
            root.right=null;
        else {
            /*while (preorder[newPreorderEnd]!=inorder[inorderRootindex-1])
                newPreorderEnd++;*/
            root.right=buildSubTree(preorder,inorder,newPreorderEnd+1,preorderEnd,inorderRootindex+1,inorderEnd);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};
        int[] preorder1={3,2,1,4};
        int[] inorder1={1,2,3,4};
        int[] preorder2={1,2};
        int[] inorder2={2,1};
        TreeNode root=new PreorderandInorderTraversal().buildTree(preorder,inorder);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        //System.out.println(root.left.val+" "+root.left.left.val);
    }
}
