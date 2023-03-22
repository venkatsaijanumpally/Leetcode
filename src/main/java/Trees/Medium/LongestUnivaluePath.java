package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class LongestUnivaluePath {
    int maxCount=0;
    public int longestUnivaluePath(TreeNode root) {
        helper(root,root.val,-1);
        return maxCount;
    }

    public int helper(TreeNode root, int val, int count){
        if(root==null){
            return count;
        }
        else if(root.val!=val){
            int temp=count;
            count=0;
            int nextLeft=helper(root.left,root.val,count);
            int nextRight=helper(root.right,root.val,count);
            if(maxCount<nextLeft+nextRight)
                maxCount=nextLeft+nextRight;
            return temp;
        }
        else{
            ++count;
            int nextLeft=helper(root.left,root.val,0);
            int nextRight=helper(root.right,root.val,0);
            if(maxCount<nextLeft+nextRight)
                maxCount=nextLeft+nextRight;
            if (nextLeft>nextRight)
                return count+nextLeft;
            return count+nextRight;
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,4,5,4,4,5};
        Integer[] arr1=new Integer[]{5,4,5,1,1,5};
        Integer[] arr2=new Integer[]{1,null,1,1,1,1,1,1};
        Integer[] arr3=new Integer[]{1,null,1,1,1};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new LongestUnivaluePath().longestUnivaluePath(root));
    }
}
