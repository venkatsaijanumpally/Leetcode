package Trees.Medium;

import LinkedList.Utils.ListNode;
import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class LinkedListinBinaryTree {
    public int count=0;
    public boolean isSubPath(ListNode head, TreeNode root) {
        boolean b=DFS(head,root);
        System.out.println(count);
        return b;
    }

    private boolean DFS(ListNode head, TreeNode root) {
        if(root==null)
            return false;

        boolean success=false;
        if(root.val==head.val){
            count++;
            success=searchLL(head,root);
        }
        if(success)
            return success;
        return DFS(head,root.left) || DFS(head,root.right);
    }

    private boolean searchLL(ListNode head, TreeNode root) {
        if(head==null)
            return true;
        if(root==null || head.val!=root.val)
            return false;

        return searchLL(head.next,root.left) || searchLL(head.next,root.right);
    }

    public static void main(String[] args) {
        int[] list=new int[]{1,4,2,6};
        ListNode head=new ListNode().generateLinkedList(list);
        Integer[] arr=new Integer[]{1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new LinkedListinBinaryTree().isSubPath(head,root));
    }
}
