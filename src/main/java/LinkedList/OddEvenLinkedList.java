package LinkedList;

import LinkedList.Utils.ListNode;

public class OddEvenLinkedList {
    /*
    //Not faster so ignoring the solution
    public ListNode oddEvenListOptimal(ListNode head){
        if(head==null || head.next==null) return head;
        ListNode odd=head,even=head.next;
        ListNode evenhead=even;
        while (even.next!=null){
            odd.next=even.next;
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }
        odd.next=evenhead;
        return head;
    }*/

    //Create 2 lists even and odd add an element to odd when count is even and add element to even when count is odd.
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode odd=head,even=head.next;
        head=head.next.next;
        ListNode oddptr=odd,evenptr=even;
        int count=0;
        while (head!=null){
            if(count%2==0){
                oddptr.next=head;
                oddptr=head;
            }
            else {
                evenptr.next=head;
                evenptr=head;
            }
            head=head.next;
            count++;
        }
        oddptr.next=even;
        if(count%2==1)
            evenptr.next=null;
        return odd;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        ListNode head= new ListNode().generateLinkedList(arr);
        ListNode ptr=new OddEvenLinkedList().oddEvenList(head);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
