package Trees.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left=null,right=null;

    public TreeNode(){
    }

    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left=left;
        this.right=right;
    }

    public TreeNode constructTree(Integer[] arr){
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(arr[0]);
        queue.offer(root);
        int size=1;
        int index=1;
        while (!queue.isEmpty()&&index<arr.length){
            for(int i=0;i<size&&index<arr.length;i++){
                TreeNode node=queue.poll();

                if(arr[index]!=null){
                    node.left=new TreeNode(arr[index]);
                    queue.offer(node.left);
                }
                index++;
                if(index< arr.length&&arr[index]!=null){
                    node.right=new TreeNode(arr[index]);
                    queue.offer(node.right);
                }
                index++;
            }
            size*=2;
        }
        return root;
    }
}
