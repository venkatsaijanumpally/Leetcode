package LinkedList.Utils;

import LinkedList.IntersectionOfLinkedList;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public ListNode reverseList(ListNode head){
        ListNode curr=head,prev=null,next;
        while (curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    //TODO with a generic parameter for handling all type of data i.e list,array
    public ListNode generateLinkedList(int[] arr){
        int index=0;
        ListNode root=new ListNode(0);
        ListNode ptr=root;
        while (index< arr.length){
            ptr.next=new ListNode(arr[index]);
            ptr=ptr.next;
            index++;
        }
        return root.next;
    }
}
