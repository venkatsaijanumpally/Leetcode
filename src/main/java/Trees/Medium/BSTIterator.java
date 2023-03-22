package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
    List<Integer> list=new ArrayList<>();
    int index=0;
    public BSTIterator(TreeNode root) {
        inorderTraversalList(root);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index<list.size();
    }

    private void inorderTraversalList(TreeNode node) {
        if(node==null)
            return;
        inorderTraversalList(node.left);
        list.add(node.val);
        inorderTraversalList(node.right);
    }
}
