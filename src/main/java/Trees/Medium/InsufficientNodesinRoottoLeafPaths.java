package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class InsufficientNodesinRoottoLeafPaths {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean b=helper(root,limit,0);
        if(b)
            return root;
        return null;
    }

    boolean helper(TreeNode root, int limit, int sum){
        if(root.left==null && root.right==null)
            return !(sum+ root.val<limit);
        else if(root.left==null){
            if(!helper(root.right,limit,sum+root.val)){
                root.right=null;
                return false;
            }
            return true;
        }
        else if(root.right==null){
            if(!helper(root.left,limit,sum+root.val)){
                root.left=null;
                return false;
            }
            return true;
        }
        sum+=root.val;
        boolean left=helper(root.left,limit,sum);
        boolean right=helper(root.right,limit,sum);

        if(!left)
            root.left=null;
        if(!right)
            root.right=null;
        return left || right;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,-3,-5,null,4,null};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        b.print(System.out,new InsufficientNodesinRoottoLeafPaths().sufficientSubset(root,-1));
    }
}
