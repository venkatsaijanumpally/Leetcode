package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorofaBinaryTree {

    /*
     * Method 1: Using paths of both nodes
     * 1. Find a path from the root to p and store it in a list.
     * 2. Find a path from the root to q and store it in another list.
     * 3. Traverse both paths till the values in lists are the same. Return the common element just before the mismatch.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listP=new ArrayList<>();
        List<TreeNode> listQ=new ArrayList<>();
        findPath(root,p,listP);
        findPath(root,q,listQ);
        System.out.println(listP+" "+listQ);
        int size=listP.size()<listQ.size()? listP.size() : listQ.size();
        int i;
        for(i=0;i<size;i++){
            if(listP.get(i)!=listQ.get(i))
                break;
        }
        return listP.get(i-1);
    }

    private boolean findPath(TreeNode root, TreeNode node, List<TreeNode> list) {
        if(root==null)
            return false;
        list.add(root);
        if(root==node)
            return true;

        if(root.left!=null){
            boolean left=findPath(root.left,node,list);
            if(left) return true;
            list.remove(list.size()-1);
        }
        if(root.right!=null){
            boolean right=findPath(root.right,node,list);
            if(right) return true;
            list.remove(list.size()-1);
        }

        return false;
    }

    /**
     * Method 2: Find both the elements in a single traverse
     * At a particular node
     *      - if it is 'p' or 'q' then return the node.
     *      - if not then search in left tree and right tree
     *      - if the left tree is null and right is null which means none of the node
     *        is present below the root
     *      - if left and right are not null then root is the LCA.
     *      - is one of them is not null then below the below the root either it may
     *        have p or q or both.
     *
     *! Optimal Solution
     */
    public TreeNode lowestCommonAncestorMethod2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        if(root==p || root==q)
            return root;

        TreeNode left=lowestCommonAncestorMethod2(root.left,p,q);
        TreeNode right=lowestCommonAncestorMethod2(root.right,p,q);
        if(left!=null && right!=null)
            return root;
        if(left!=null)
            return left;
        return right;
    }



    public static void main(String[] args) {
        Integer[] arr={3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root=new TreeNode().constructTree(arr);
        System.out.println(new LowestCommonAncestorofaBinaryTree().
                lowestCommonAncestorMethod2(root,root.left,root.left.right.right).val);
    }
}
