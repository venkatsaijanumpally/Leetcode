package Trees.Easy;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {
    static List<Integer> list=new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        if(root==null) return list;
        inorderTraversalList(root);
        return list;
    }

    private static void inorderTraversalList(TreeNode node) {
        if(node==null)
            return;
        inorderTraversalList(node.left);
        list.add(node.val);
        inorderTraversalList(node.right);
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(4);
        System.out.println(inorderTraversal(root));
    }
}
