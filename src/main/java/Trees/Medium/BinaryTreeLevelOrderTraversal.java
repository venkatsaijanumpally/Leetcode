package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    List<List<Integer>> arrayLists=new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return arrayLists;
        Queue<TreeNode> queue1=new LinkedList<>();
        Queue<TreeNode> queue2=new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty()){
            ArrayList<Integer> arr=new ArrayList<>();
            while (!queue1.isEmpty()){
                TreeNode node=queue1.peek();
                queue1.remove();
                arr.add(node.val);
                if(node.left!=null)
                    queue2.add(node.left);
                if(node.right!=null)
                    queue2.add(node.right);
            }
            arrayLists.add(arr);
            ArrayList<Integer> arr1=new ArrayList<>();
            boolean flag=queue2.isEmpty();
            while (!queue2.isEmpty()){
                TreeNode node=queue2.peek();
                queue2.remove();
                arr1.add(node.val);
                if(node.left!=null)
                    queue1.add(node.left);
                if(node.right!=null)
                    queue1.add(node.right);
            }
            if(!flag)
                arrayLists.add(arr1);
        }
        return arrayLists;
    }

    /**
     * While entering the loop calculate size of queue so that we can find the number of elements in that particular
     * level and loop till that size and add next level items into queue.
     * !Optimal Solution
     */
    public List<List<Integer>> levelOrderOptimal(TreeNode root){
        if(root==null) return arrayLists;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                list.add(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            arrayLists.add(list);
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
        System.out.println(new BinaryTreeLevelOrderTraversal().levelOrderOptimal(root));
    }
}
