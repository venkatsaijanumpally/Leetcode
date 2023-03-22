package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementinaBST {
    List<Integer> list=new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        depthFirstTraversal(root,k);
        return list.get(k-1);
    }

    private void depthFirstTraversal(TreeNode root,int k) {
        if(root==null || k==0)
            return;
        depthFirstTraversal(root.left,k);
        list.add(root.val);
        k--;
        depthFirstTraversal(root.right,k);
    }

    public static void main(String[] args) {
        Integer[] arr={3,1,4,null,2};
        TreeNode root=new TreeNode().constructTree(arr);
        System.out.println(new KthSmallestElementinaBST().kthSmallest(root,1));
    }
}
