package LinkedList;

import LinkedList.Utils.ListNode;
import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class ConvertSortedListtoBinarySearchTree {
    /**
     * Using the function sortedListToBST(ListNode head, int i, int j)
     * we generate a BST by starting from the leaf nodes to root.
     * The idea is to insert nodes in BST in the same order as they appear
     * in Linked List.
     * Similar method - https://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
     */
    ListNode head1=null;
    public TreeNode sortedListToBST(ListNode head, int i, int j) {
        if(i > j) return null;

        int mid = i + (j-i)/2;
        TreeNode left = sortedListToBST(head, i, mid-1);
        TreeNode root = new TreeNode(head1.val);
        root.left = left;
        head1 = head1.next;
        root.right = sortedListToBST(head, mid+1, j);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return new TreeNode(head.val);
        int count=0;
        ListNode ptr=head;
        head1=head;
        while (ptr!=null){
            count++;
            ptr=ptr.next;
        }
        return sortedListToBST(head,0,count-1);
        /*ptr=head;
        int[] nums=new int[count];
        int i=0;
        while (ptr!=null){
            nums[i++]= ptr.val;
            ptr=ptr.next;
        }
        TreeNode root=new TreeNode(nums[(nums.length-1)/2]);
        int mid=(nums.length-1)/2;
        generateBST(nums, 0, mid-1,root, true);
        generateBST(nums, mid+1, nums.length-1,root, false);
        return root;*/
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
        ListNode fif=new ListNode(9);
        ListNode f=new ListNode(5,fif);
        ListNode third=new ListNode(0,f);
        ListNode second1=new ListNode(-3,third);
        ListNode head1=new ListNode(-10,second1);
        int[] arr=new int[]{1,2,3,4,5,6,7,8,9};
        ListNode head=new ListNode().generateLinkedList(arr);
        TreeNode ptr= new ConvertSortedListtoBinarySearchTree().sortedListToBST(head);
        /*while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }*/
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,ptr);
    }
}
