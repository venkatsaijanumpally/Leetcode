package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.Stack;

public class PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode f=new ListNode(1,null);
        ListNode third=new ListNode(2,f);
        ListNode second=new ListNode(2,third);
        ListNode head=new ListNode(1,second);
        System.out.println(new PalindromeLinkedList().isPalindromeOptimalPointers(head));
    }
    public boolean isPalindrome(ListNode head) {
        if(head.next==null) return true;
        Stack<Integer> stack=new Stack<>();
        ListNode ptr=head;
        int count=0;
        while (ptr!=null){
            stack.push(ptr.val);
            ptr=ptr.next;
            count++;
        }
        ptr=head;
        for (int i=0;i<count/2;i++){
            if(ptr.val!=stack.pop())
                return false;
            ptr=ptr.next;
        }
        return true;
    }
    public boolean isPalindromeOptimalPointers(ListNode head) {
        if(head.next==null) return true;
        ListNode current=head;
        ListNode prev=null;
        ListNode next=null;
        int size=0;
        while (current!=null){
            current=current.next;
            size++;
        }

        current=head;
        int index=0;
        while (index<size/2){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
            ++index;
        }
        if(size%2==1)
            current=current.next;

        while (current!=null){
            if(current.val!=prev.val)
                return false;
            current=current.next;
            prev=prev.next;
        }
        return true;
    }
}
