package Trees.Medium.Simple;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(root==null) return list;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int size;
        while (!queue.isEmpty()){
            int max=Integer.MIN_VALUE;
            size= queue.size();
            for(int i=0;i<size;i++){
                TreeNode node= queue.poll();
                if(node.val>max)
                    max=node.val;
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
            }
            list.add(max);
        }
        return list;
    }
}
