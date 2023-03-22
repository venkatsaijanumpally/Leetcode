package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Stack;

public class MaximumBinaryTree {
    /*
     * Method 1: Using Monotonic Stack and Stack
     * We push the elements fron the array to monotonic stack one by one
     *      - If the element to be pushed is lesser than peek element then the element is pushed.
     *      - If the element to be pushed is greater than peek element then the elements in monotonic stack is popped
     *        until the peek element is greater than the element to be pushed or empty. All the popped elements are
     *        saved in another stack 2.
     *        peek element in stack 2 is connected to the to be pushed element left, remaining elements in stack 2 are
     *        connected in the node.right,right,right and so on.
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> monotonic=new Stack<>();
        for(int i=0;i<nums.length;i++){
            TreeNode top=null;
            if(!monotonic.isEmpty())
                top=monotonic.peek();
            if(monotonic.isEmpty() || nums[i]<top.val){
                monotonic.push(new TreeNode(nums[i]));
            }
            else if(nums[i]>top.val){
                Stack<TreeNode> stack=new Stack<>();
                while (!monotonic.isEmpty() && monotonic.peek().val<nums[i]){
                    stack.push(monotonic.pop());
                }
                TreeNode node=new TreeNode(nums[i]);
                monotonic.push(node);
                node.left=stack.pop();
                node=node.left;
                while (!stack.isEmpty()){
                    node.right=stack.pop();
                    node=node.right;
                }
            }
        }
        TreeNode downNode=monotonic.pop();
        while (!monotonic.isEmpty()){
            TreeNode upNode=monotonic.pop();
            upNode.right=downNode;
            downNode=upNode;
        }
        return downNode;
    }

    /**
     * Method 2: Divide and Conquer
     * For every recursive call the helper method finds out the maximum element between low and high using a for loop
     * and creates a node and calls further for left node and right node.
     *
     * !Optimal Method
     */
    public TreeNode constructMaximumBinaryTreeDivideandConquer(int[] nums){
        return helper(nums,0,nums.length-1);
    }

    private TreeNode helper(int[] nums, int low, int high) {
        if(low>high)
            return null;

        int maxindex=low;
        for(int i=low;i<=high;i++){
            if(nums[i]>nums[maxindex])
                maxindex=i;
        }
        TreeNode root=new TreeNode(nums[maxindex]);
        root.left=helper(nums,low,maxindex-1);
        root.right=helper(nums,maxindex+1,high);
        return root;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{3,2,1,6,0,5};
        int[] arr1=new int[]{3,2,1};
        TreeNode root=new MaximumBinaryTree().constructMaximumBinaryTreeDivideandConquer(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
    }
}
