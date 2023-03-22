package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class DeleteNodeinaBST {
    TreeNode previousNode;
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;
        TreeNode deletionNode;
        if(root.val==key){
            deletionNode=root;
            /*if(root.left==null){
                return root.right;
            }
            else
                root=root.left;
            connectNodes(root,deletionNode.right);*/
            return connectNodes(root.left,deletionNode.right);
        }
        else {
            deletionNode = DFS(root,key);
            if(deletionNode==null){}
            else if(previousNode.left==deletionNode){
                previousNode.left=deletionNode.left;
                previousNode.left=connectNodes(previousNode.left,deletionNode.right);
            }
            else {
                previousNode.right=deletionNode.left;
                previousNode.right=connectNodes(previousNode.right,deletionNode.right);
            }
        }
        return root;
    }

    private TreeNode DFS(TreeNode root, int key){
        if(root==null)
            return null;
        if(key== root.val)
            return root;
        previousNode=root;
        if(key>root.val)
            return DFS(root.right,key);
        return DFS(root.left,key);
    }

    private TreeNode connectNodes(TreeNode root, TreeNode rightEndNode) {
        if(root==null) return rightEndNode;
        if(rightEndNode==null) return root;

        TreeNode ptr=root;
        while (ptr.right!=null)
            ptr=ptr.right;
        ptr.right=rightEndNode;
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{5,3,6,2,4,null,7};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,new DeleteNodeinaBST().deleteNode(root,3));
    }
}
