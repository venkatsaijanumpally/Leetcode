package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.LargeArrayOfInteger;
import Trees.Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class MaximumProductofSplittedBinaryTree {
    long totalSum,maximum;
    public int maxProduct(TreeNode root) {
        HashMap<TreeNode,Integer> sumOfSubtree=new HashMap<>();
        //totalSum=mapSumTillNode(root,sumOfSubtree);
        totalSum=calculateSum(root);
        maximum=0l;

        /*for (Map.Entry entry:sumOfSubtree.entrySet()){
            int val= (int) entry.getValue();
            maximum=Math.max(maximum, (totalSum-val)*val);
        }*/
        //int max= (int) (findMaxProduct(root,sumOfSubtree)%(Math.pow(10,9)+7));

        findMaxProductRecur(root);

        return (int) (maximum%(1e9+7));
    }

    private long findMaxProductRecur(TreeNode root) {
        if(root==null) return 0l;

        long left=findMaxProductRecur(root.left);
        long right=findMaxProductRecur(root.right);
        long sum=left+right+root.val;
        long currProd=(totalSum-sum)*sum;
        maximum=Math.max(maximum,currProd);
        return sum;
    }

    private long calculateSum(TreeNode root) {
        if(root==null) return 0;

        long left=calculateSum(root.left);
        long right=calculateSum(root.right);
        return left+right+root.val;
    }

    private long findMaxProduct(TreeNode root, HashMap<TreeNode, Long> sumOfSubtree) {
        if(root.left==null && root.right==null)
            return root.val*(totalSum-root.val);
        if(root.left==null){
            long right=findMaxProduct(root.right,sumOfSubtree);
            long rightCut= (totalSum-sumOfSubtree.get(root.right))*sumOfSubtree.get(root.right);
            return Math.max(right,rightCut);
        }
        if(root.right==null){
            long left=findMaxProduct(root.left,sumOfSubtree);
            long leftCut=((totalSum-sumOfSubtree.get(root.left))*sumOfSubtree.get(root.left));
            return Math.max(left,leftCut);
        }

        long leftMax=findMaxProduct(root.left,sumOfSubtree);
        long rightMax=findMaxProduct(root.right,sumOfSubtree);
        long leftCut=((totalSum-sumOfSubtree.get(root.left))*sumOfSubtree.get(root.left));
        long rightCut=(totalSum-sumOfSubtree.get(root.right))*sumOfSubtree.get(root.right);
        return Math.max(Math.max(leftCut,rightCut),Math.max(leftMax,rightMax));
    }

    private int mapSumTillNode(TreeNode root, HashMap<TreeNode, Integer> sumOfSubtree) {
        if(root==null)
            return 0;

        int sumLeft=mapSumTillNode(root.left,sumOfSubtree);
        int sumRight=mapSumTillNode(root.right,sumOfSubtree);
        sumLeft+=sumRight+root.val;
        sumOfSubtree.put(root,sumLeft);
        return sumLeft;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,3,4,5,6};
        Integer[] arr1=new Integer[]{1,null,2,3,4,null,null,5,6};
        Integer[] arr3=LargeArrayOfInteger.getArray();
        TreeNode root=new TreeNode().constructTree(arr3);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new MaximumProductofSplittedBinaryTree().maxProduct(root));
    }
}
