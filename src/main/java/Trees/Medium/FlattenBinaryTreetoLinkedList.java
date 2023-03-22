package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.Stack;

public class FlattenBinaryTreetoLinkedList {
    /**
     *! Both methods flatten and flattenMethod2 are Optimal
     */
    /*
     * Method 1
     * Here We push each element into a stack in the order right then left
     * and maintain a current pointer(curr) to set next popped element.
     */
    public void flatten(TreeNode root) {
        if(root==null) return;

        /*TreeNode temp=root.right;
        root.right=root.left;
        flatten(root.right);*/
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        //Temporary node
        TreeNode curr=new TreeNode(0);
        curr.right=root;
        while (!stack.isEmpty()){
            TreeNode node= stack.pop();
            curr.right=node;
            if(node.right!=null)
                stack.push(node.right);
            if(node.left!=null)
                stack.push(node.left);
            node.left=null;
            curr=curr.right;
        }
    }

    /*
     * Method 2:
     * At a particular root if we see left is not null then we first flatten left tree.
     * right tree is saved in temp and the flattened left tree is set to root.right
     * we traverse till the right end of right tree till we find null then we assign it
     * to temp.
     */
    public void flattenMethod2(TreeNode root){
        if(root==null) return;

        if(root.left!=null)
            flattenMethod2(root.left);

        TreeNode temp=root.right;
        root.right=root.left;
        root.left=null;
        while (root.right!=null)
            root=root.right;
        root.right=temp;
        flattenMethod2(temp);
    }

    public static void main(String[] args) {
        Integer[] arr={1,2,5,3,4,null,6};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        new FlattenBinaryTreetoLinkedList().flatten(root);
        b.print(System.out,root);
    }
}
