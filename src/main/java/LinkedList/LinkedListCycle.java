package LinkedList;

import LinkedList.Utils.ListNode;

public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        if(head==null || head.next==null) return false;
        ListNode ptr1=head;
        ListNode ptr2=head.next;
        while(ptr2!=null&&ptr2.next!=null){
            if(ptr1==ptr2)
                return true;
            ptr1=ptr1.next;
            ptr2=ptr2.next.next;
        }
        return false;
    }

    public static void main(String[] args){
        ListNode fif=new ListNode(6);
        ListNode f=new ListNode(6,fif);
        ListNode third=new ListNode(5,f);
        ListNode second1=new ListNode(1,third);
        ListNode head1=new ListNode(1,second1);

        fif.next=null;
        System.out.println(hasCycle(head1));
    }
}
