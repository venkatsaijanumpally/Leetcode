package Trees.SegmentTrees.Easy;

import Trees.Utils.BTPrinter;
import Trees.Utils.TreeNode;

public class RangeSumQueryImmutable {
    /*
     * The implementation is based on Segment trees
     * Steps:
     *      1. Construct the segment tree during initialization
     *          a. We move till leaf nodes and construct from bottom.
     *          b. Every nodes value is determined by sum of left and right nodes.
     *      2. Find range sum for all the queries
     *          a. If left>mid that means sum is in the right tree.
     *          b. If right<=mid that means sum is in the left tree.
     *          c. Else the some part of sum is in left tree and some in right tree in that case we find the left tree
     *             sum by sending range (left to mid) to left tree and (mid+1 to right) to right tree.
     *
     * This is a basic question refer to package-info for video.
     *
     * Note: The problem can be easily solved using prefix sum but used segment trees for learning.
     */
    TreeNode root;
    int size;
    public RangeSumQueryImmutable(int[] nums) {
        size=nums.length-1;
        root=constructSegmentTree(nums,0,nums.length-1);
        BTPrinter.print(root);
    }

    private TreeNode constructSegmentTree(int[] nums, int low, int high) {
        if(low==high)
            return new TreeNode(nums[low]);

        int mid=low+(high-low)/2;
        TreeNode node=new TreeNode();
        node.left=constructSegmentTree(nums,low,mid);
        node.right=constructSegmentTree(nums,mid+1,high);
        node.val=(node.left!=null?node.left.val:0) + (node.right!=null?node.right.val:0);
        return node;
    }

    public int sumRange(int left, int right) {
        return findRangeSum(root,left,right,0,size);
    }

    private int findRangeSum(TreeNode root, int left, int right, int low, int high) {
        if(left==low && right==high)
            return root.val;
        int mid=low+(high-low)/2;
        if(left>mid)
            return findRangeSum(root.right,left,right,mid+1,high);
        if(right<=mid)
            return findRangeSum(root.left,left,right,low,mid);

        int leftSum=findRangeSum(root.left,left,mid,low,mid);
        int rightSum=findRangeSum(root.right,mid+1,right,mid+1,high);
        return leftSum+rightSum;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        RangeSumQueryImmutable r=new RangeSumQueryImmutable(arr);
        System.out.println();
        System.out.println(r.sumRange(0,2));
        System.out.println(r.sumRange(0,1));
        System.out.println(r.sumRange(1,2));
        System.out.println(r.sumRange(1,1));
        System.out.println(r.sumRange(2,3));
        System.out.println(r.sumRange(2,4));
    }
}
