package Trees.Easy;

import Trees.Utils.QueueElement;
import Trees.Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthofBinaryTree {

    public int calculateHeight1(TreeNode root){
        if(root==null) return 0;

        if(root.left==null && root.right==null)
            return 1;

        if(root.left==null)
            return 1+calculateHeight1(root.right);
        if(root.right==null)
            return 1+calculateHeight1(root.left);

        int lh=1+calculateHeight1(root.left);
        int rh=1+calculateHeight1(root.right);
        /*if(root.left==null || root.right==null)
            return lh>rh?lh:rh;*/

        return lh<rh?lh:rh;
    }

    public int minDepth1(TreeNode root) {
        return calculateHeight1(root);
        //return min;
    }

    /**
     *! Optimal Solution
     * NK P159
     */
    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;

        Queue<QueueElement> queue = new LinkedList<>();
        QueueElement queueElement = new QueueElement();
        queueElement.node=root;
        queueElement.depth=1;
        queue.add(queueElement);

        while (!queue.isEmpty()){
            queueElement=queue.peek();
            queue.remove();
            TreeNode node= queueElement.node;
            int depth= queueElement.depth;
            if(node.left==null && node.right==null)
                return depth;

            if(node.left!=null){
                QueueElement leftqueueElement=new QueueElement();
                leftqueueElement.node=node.left;
                leftqueueElement.depth= depth+1;
                queue.add(leftqueueElement);
            }
            if(node.right!=null){
                QueueElement rightqueueElement=new QueueElement();
                rightqueueElement.node=node.right;
                rightqueueElement.depth= depth+1;
                queue.add(rightqueueElement);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        /*TreeNode root=new TreeNode(2);
        root.right=new TreeNode(3);
        root.right.right=new TreeNode(4);
        root.right.right.right=new TreeNode(5);
        System.out.print(new MinimumDepthofBinaryTree().minDepth(root));*/


        TreeNode root1=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.right.right=new TreeNode(4);
        root1.right.right.right=new TreeNode(5);
        root1.left=new TreeNode(6);
        System.out.print(new MinimumDepthofBinaryTree().minDepth(root1));
    }
}
