package Trees.Medium;

import Trees.Utils.TreeNode;

public class FindBottomLeftTreeValue {
    int h=-1,value=0;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return value;
    }

    private void dfs(TreeNode node, int height) {
        if(node==null) return;
        if(h<height){
            h=height;
            value= node.val;
        }
        dfs(node.left,height+1);
        dfs(node.right,height+1);
    }

    public static void main(String[] args) {

    }
}
