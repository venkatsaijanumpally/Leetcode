package LinkedList;

import LinkedList.Utils.ListNode;

public class RemoveDuplicatesfromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) return head;

        ListNode ptr1=head.next;
        ListNode ptr2=head;
        while(ptr1!=null){
            if(ptr1.val== ptr2.val){
                ptr1=ptr1.next;
                ptr2.next=ptr1;
            }
            else{
                ptr1=ptr1.next;
                ptr2=ptr2.next;
            }
        }
        return head;
    }

    public static void main(String[] args){
        ListNode fif=new ListNode(6);
        ListNode f=new ListNode(6,fif);
        ListNode third=new ListNode(5,f);
        ListNode second1=new ListNode(1,third);
        ListNode head1=new ListNode(1,second1);

        ListNode ptr=deleteDuplicates(head1);;
        while(ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
