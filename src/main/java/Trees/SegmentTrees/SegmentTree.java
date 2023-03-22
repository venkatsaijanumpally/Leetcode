package Trees.SegmentTrees;

import Trees.SegmentTrees.Medium.RangeSumQueryMutable;
import Trees.Utils.BTPrinter;
import Trees.Utils.TreeNode;

import java.util.Arrays;

public class SegmentTree {

    TreeNode root;
    int size;
    int[] arr;
    public SegmentTree(int[] nums) {
        size=nums.length-1;
        //Method 1
        /*arr=new int[nums.length];
        arr=nums;
        root=constructSegmentTreeMethod1(nums,0,nums.length-1);
        BTPrinter.print(root);*/

        //Method2
        arr=new int[4*nums.length];
        constructSegmentTreeArray(nums,0,size,0);
        System.out.println(Arrays.toString(arr));
    }

    /*
     * Method 1:
     * This method constructs the segment tree in the form of a tree and for every rannge sum or update query traverses
     * through the tree recursively to find the result.
     */
    private TreeNode constructSegmentTreeMethod1(int[] nums, int low, int high) {
        if(low==high)
            return new TreeNode(nums[low]);

        int mid=low+(high-low)/2;
        TreeNode node=new TreeNode();
        node.left=constructSegmentTreeMethod1(nums,low,mid);
        node.right=constructSegmentTreeMethod1(nums,mid+1,high);
        node.val=(node.left!=null?node.left.val:0) + (node.right!=null?node.right.val:0);
        return node;
    }

    public int sumRangeMethod1(int left, int right) {
        return findRangeSumMethod1(root,left,right,0,size);
    }

    private int findRangeSumMethod1(TreeNode root, int left, int right, int low, int high) {
        if(left<=low && right>=high)
            return root.val;
        if(right<low || left>high)
            return 0;
        /*if(left==low && right==high)
            return root.val;*/
        int mid=low+(high-low)/2;
        /*if(left>mid)
            return findRangeSumMethod1(root.right,left,right,mid+1,high);
        if(right<=mid)
            return findRangeSumMethod1(root.left,left,right,low,mid);*/

        int leftSum=findRangeSumMethod1(root.left,left,right,low,mid);
        int rightSum=findRangeSumMethod1(root.right,left,right,mid+1,high);
        return leftSum+rightSum;
    }

    public void updateMethod1(int index, int val) {
        int diff=val-arr[index];
        arr[index]=val;
        updateMethod1(root,index,0,size,diff);
    }

    public void updateMethod1(TreeNode node, int index, int low, int high, int diff){
        if(low==high) {
            node.val+=diff;
            return;
        }
        int mid=low+(high-low)/2;
        node.val+=diff;
        if(index>mid)
            updateMethod1(node.right,index,mid+1,high,diff);
        else updateMethod1(node.left,index,low,mid,diff);
    }




    /**
     * Method 2: Construct a segment tree array
     * In this method instead of constructing the tree we construct a array. Using which we run range sum and update
     * query.
     * !Optimal Solution
     **/

    private int constructSegmentTreeArray(int[] nums, int low, int high, int np) {
        if(low==high){
            arr[np]=nums[low];
            return nums[low];
        }

        int mid=low+(high-low)/2;
        arr[np]=constructSegmentTreeArray(nums,low,mid,2*np+1)+
                constructSegmentTreeArray(nums, mid+1, high, 2*np+2);
        return arr[np];
    }

    public int sumRangeMethod2(int left, int right){
        return sumRangeMethod2(left,right,0,size,0);
    }

    private int sumRangeMethod2(int left, int right, int low, int high, int np){
        if(left<=low && right>=high)
            return arr[np];
        if(right<low || left>high)
            return 0;

        int mid=low+(high-low)/2;
        int leftSum=sumRangeMethod2(left,right,low,mid,2*np+1);
        int rightSum=sumRangeMethod2(left,right,mid+1,high,2*np+2);
        return leftSum+rightSum;
    }

    public void updateMethod2(int index, int val){
        updateMethod2(index,0,size,val,0);
    }

    public void updateMethod2(int index, int low, int high, int val, int np){
        if(low==high) {
            arr[np]=val;
            return;
        }
        int mid=low+(high-low)/2;
        if(index<=mid)
            updateMethod2(index,low,mid,val,np*2+1);
        else updateMethod2(index,mid+1,high,val,np*2+2);
        arr[np]=arr[np*2+1]+arr[np*2+2];
    }
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        int[] arr1=new int[]{1,3,6,0,10,7};
        int[] arr2=new int[]{1,3,5,7,9,11};
        int[] arr3=new int[]{1,3,5};
        SegmentTree r=new SegmentTree(arr1);

        //Method 1
        /*System.out.println();
        System.out.println(r.sumRangeMethod1(0,1));
        System.out.println(r.sumRangeMethod1(1,2));
        System.out.println(r.sumRangeMethod1(1,1));
        System.out.println(r.sumRangeMethod1(2,3));
        System.out.println(r.sumRangeMethod1(2,4));
        System.out.println(r.sumRangeMethod1(0,2));
        r.updateMethod1(2,4);
        System.out.println(r.sumRangeMethod1(0,2));
        r.updateMethod1(1,4);
        System.out.println(r.sumRangeMethod1(0,2));*/


        System.out.println(r.sumRangeMethod2(0,2));
        r.updateMethod2(1,4);
        System.out.println(r.sumRangeMethod2(1,3));
    }
}
