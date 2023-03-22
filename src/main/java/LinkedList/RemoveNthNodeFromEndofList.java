package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.List;

public class RemoveNthNodeFromEndofList {
    public static void main(String[] args) {
        ListNode fif=new ListNode(7);
        ListNode f=new ListNode(6,fif);
        ListNode third=new ListNode(5,f);
        ListNode second1=new ListNode(3,third);
        ListNode head1=new ListNode(1,second1);
        ListNode ptr= new RemoveNthNodeFromEndofList().removeNthFromEnd(head1,5);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return null;

        ListNode ptr1=head;
        ListNode ptrn=head;
        for(int i=0;i<n;i++)
            ptrn=ptrn.next;

        if(ptrn==null)
            return head.next;

        while (ptrn.next!=null){
            ptr1=ptr1.next;
            ptrn=ptrn.next;
        }
        System.out.println(ptr1.val);
        ptr1.next=ptr1.next.next;

        ListNode ptr=head;
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
        return head;
    }
}
