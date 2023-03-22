package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    List<List<Integer>> arrayLists=new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null) return arrayLists;
        Stack<TreeNode> stack1=new Stack<>();
        Stack<TreeNode> stack2=new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            ArrayList<Integer> arr=new ArrayList<>();
            while (!stack1.isEmpty()){
                TreeNode node=stack1.pop();
                arr.add(node.val);
                if(node.left!=null)
                    stack2.push(node.left);
                if(node.right!=null)
                    stack2.push(node.right);
            }
            arrayLists.add(arr);
            ArrayList<Integer> arr1=new ArrayList<>();
            boolean flag=stack2.isEmpty();
            while (!stack2.isEmpty()){
                TreeNode node=stack2.pop();
                arr1.add(node.val);
                if(node.right!=null)
                    stack1.add(node.right);
                if(node.left!=null)
                    stack1.add(node.left);
            }
            if(!flag)
                arrayLists.add(arr1);
        }
        return arrayLists;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(4);
        root.right.right=new TreeNode(10);
        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root));
    }
}
