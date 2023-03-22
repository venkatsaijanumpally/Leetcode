package LinkedList.Easy;

import LinkedList.Utils.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head==null) return null;
        ListNode prev=null,curr=head,next=head;
        while (curr!=null){
            next=next.next;
            curr.next=prev;
            prev=curr;
            curr=next;

        }

        return prev;
    }

    public static void main(String[] args) {
        int[] list={1,2,3,4,5};
        System.out.println(new ReverseLinkedList().reverseList(new ListNode().generateLinkedList(list)).val);
    }
}
