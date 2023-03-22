package Trees.Medium.Simple;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FindDuplicateSubtrees {
    HashMap<String,Integer> hm=new HashMap<>();
    List<TreeNode> list=new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        addDuplicates(root);
        return list;
    }

    /*
     * For every string sb generated at every node it is pushed into hashmap and if any duplicates come it is
     * added into the list.
     * Why POSTORDER is taken?
     * If we take inorder an example
     *      0                       0
     *     / \                     / \
     *  null  0                   0   null
     *         \                 /
     *          null           null
     *
     *  N0N0N                   N0N0N       -- Inorder
     *  0N0NN                   00NNN       -- Postorder
     */
    private String addDuplicates(TreeNode root) {
        if(root==null)
            return "N";
        String left=addDuplicates(root.left);
        String right=addDuplicates(root.right);
        String sb=root.val+" "+left+" "+right;
        hm.put(sb,hm.getOrDefault(sb,0)+1);
        if(hm.get(sb)==2)
            list.add(root);
        return sb;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,3,4,null,2,4,null,null,4};
        Integer[] arr2=new Integer[]{0,0,0,0,null,null,0,null,null,null,0};
        TreeNode root=new TreeNode().constructTree(arr2);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        List<TreeNode> list= new FindDuplicateSubtrees().findDuplicateSubtrees(root);
        for(TreeNode node: list){
            System.out.print(node.val+" ");
        }
        System.out.println(Arrays.toString(new FindDuplicateSubtrees().findDuplicateSubtrees(root).toArray()));
    }
}
