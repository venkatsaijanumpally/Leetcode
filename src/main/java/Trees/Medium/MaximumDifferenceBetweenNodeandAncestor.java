package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class MaximumDifferenceBetweenNodeandAncestor {
    int maxDiff=0;
    public int maxAncestorDiff(TreeNode root) {
        maximumAncestorDiff(root.left,root.val, root.val);
        maximumAncestorDiff(root.right,root.val,root.val);
        return maxDiff;
    }

    private void maximumAncestorDiff(TreeNode root, int max, int min) {
        if(root==null) return;

        if(root.val>max){
            int diff=root.val-min;
            if(maxDiff<diff)
                maxDiff=diff;
            max= root.val;
        }
        else if(root.val<min){
            int diff=max- root.val;
            if(diff>maxDiff)
                maxDiff=diff;
            min= root.val;
        }
        maximumAncestorDiff(root.left,max,min);
        maximumAncestorDiff(root.right,max,min);
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{8,3,10,1,6,null,14,null,null,4,7,13};
        Integer[] arr1=new Integer[]{1,null,2,null,0,3};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new MaximumDifferenceBetweenNodeandAncestor().maxAncestorDiff(root));
    }
}
