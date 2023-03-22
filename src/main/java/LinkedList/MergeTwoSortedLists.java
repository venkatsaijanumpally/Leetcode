package LinkedList;

import LinkedList.Utils.ListNode;

public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode ptr=head;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                ptr.next=l1;
                l1=l1.next;
            }
            else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr=ptr.next;
        }
        if (l1!=null){
            ptr.next=l1;
        }
        if (l2!=null){
            ptr.next = l2;
        }
        return head.next;
    }
    public static void main(String[] args){
        ListNode third=new ListNode(5);
        ListNode second1=new ListNode(3,third);
        ListNode head1=new ListNode(1,second1);


        ListNode third2=new ListNode(6);
        ListNode second2=new ListNode(4,third2);
        ListNode head2=new ListNode(2,second2);

        ListNode ptr=mergeTwoLists(head1,head2);;
        while(ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
