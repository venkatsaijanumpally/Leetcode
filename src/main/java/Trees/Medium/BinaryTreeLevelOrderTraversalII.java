package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ll=new LinkedList<>();
        if(root==null) return ll;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
                list.add(node.val);
            }
            ll.addFirst(list);
        }
        return ll;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(4);
        root.right.right=new TreeNode(10);
        System.out.println(new BinaryTreeLevelOrderTraversalII().levelOrderBottom(root));
    }
}
