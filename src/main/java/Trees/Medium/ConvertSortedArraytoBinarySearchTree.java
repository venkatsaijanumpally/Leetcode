package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0) return null;
        TreeNode root=new TreeNode(nums[(nums.length-1)/2]);
        int mid=(nums.length-1)/2;
        generateBST(nums, 0, mid-1,root, true);
        generateBST(nums, mid+1, nums.length-1,root, false);
        return root;
    }

    private void generateBST(int[] nums, int low, int high, TreeNode node, boolean side) {
        if(low<=high){
            int mid=low+(high-low)/2;
            if(side) {
                node.left=new TreeNode(nums[mid]);
                generateBST(nums, low, mid-1,node.left, true);
                generateBST(nums, mid+1, high,node.left, false);
            }
            else {
                node.right=new TreeNode(nums[mid]);
                generateBST(nums, low, mid-1,node.right, true);
                generateBST(nums, mid+1, high,node.right, false);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums={-10,-3,0,5,9};
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,new ConvertSortedArraytoBinarySearchTree().sortedArrayToBST(nums));
        System.out.println(new ConvertSortedArraytoBinarySearchTree().sortedArrayToBST(nums).right.right.val);
    }
}
