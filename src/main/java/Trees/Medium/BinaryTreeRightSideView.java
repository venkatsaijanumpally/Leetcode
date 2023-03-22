package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {
    //Both solutions are working
    List<Integer> result=new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        //dfs(root,0);
        dfsOptimal(root,0);
        return result;
    }

    /*
     * Levels here represent number of levels down the node not to be included after which the nodes will be included.
     * right variable gives the number of nodes down the right nodes.
     * left variable gives number of nodes down the left node.
     *
     */
    private int dfs(TreeNode node, int levels) {
        if(node==null) return 0;
        if(levels==0)
            result.add(node.val);

        //When adding nodes on the right tree we consider the "levels"
        int right=dfs(node.right, Math.max(0,levels-1));
        //When adding nodes on the left node we need to consider nodes that are more the "levels" and "right"
        int left=dfs(node.left, Math.max(levels-1,right));

        return Math.max(right,left)+1;
    }

    /*
     * In this approach result list size represents the number of levels it has covered. If the currlevel goes beyond
     * the result list size then add the node.
     */
    //!Optimal
    private void dfsOptimal(TreeNode node, int currLevel) {
        if(node==null) return;
        if(result.size()==currLevel)
            result.add(node.val);

        dfsOptimal(node.right, currLevel+1);
        dfsOptimal(node.left, currLevel+1);
    }

    public static void main(String[] args) {
        Integer[] arr1={1,2,3,null,5,null,4};
        Integer[] arr2={1,2,3,4,5,null,null,null,6,7,null,8,null,null,null,null};
        TreeNode head1=new TreeNode().constructTree(arr2);
        System.out.println(new BinaryTreeRightSideView().rightSideView(head1));
    }
}
