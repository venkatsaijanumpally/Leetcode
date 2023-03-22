package Trees.SegmentTrees.Medium;

import Trees.Utils.BTPrinter;
import Trees.Utils.TreeNode;

public class RangeSumQueryMutable {
    TreeNode root;
    int size;
    int[] arr;
    public RangeSumQueryMutable(int[] nums) {
        size=nums.length-1;
        arr=new int[nums.length];
        arr=nums;
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

    public void update(int index, int val) {
        int diff=val-arr[index];
        arr[index]=val;
        update(root,index,0,size,diff);
    }

    public void update(TreeNode node, int index, int low, int high, int diff){
        if(low==high) {
            node.val+=diff;
            return;
        }
        int mid=low+(high-low)/2;
        node.val+=diff;
        if(index<=mid)
            update(node.left,index,low,mid,diff);
        else update(node.right,index,mid+1,high,diff);
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        RangeSumQueryMutable r=new RangeSumQueryMutable(arr);
        System.out.println();
        System.out.println(r.sumRange(0,1));
        System.out.println(r.sumRange(1,2));
        System.out.println(r.sumRange(1,1));
        System.out.println(r.sumRange(2,3));
        System.out.println(r.sumRange(2,4));
        System.out.println(r.sumRange(0,2));
        r.update(2,4);
        System.out.println(r.sumRange(0,2));
        r.update(1,4);
        System.out.println(r.sumRange(0,2));
    }
}
