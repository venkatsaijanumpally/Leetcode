package Trees.Medium.Simple;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class AddOneRowtoTree {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode newRoot=new TreeNode(val);
            newRoot.left=root;
            root=newRoot;
        }
        else {
            Queue<TreeNode> queue=new LinkedList<>();
            queue.offer(root);
            int size;
            for(int i=1;i<depth-1;i++){
                size = queue.size();
                TreeNode node;
                for (int j = 0; j < size; j++) {
                    node = queue.poll();
                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
            }
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                TreeNode tempLeft=node.left;
                TreeNode tempRight=node.right;
                node.left=new TreeNode(val);
                node.left.left=tempLeft;
                node.right=new TreeNode(val);
                node.right.right=tempRight;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{4,2,6,3,1,5};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,new AddOneRowtoTree().addOneRow(root,1,2));

    }
}
