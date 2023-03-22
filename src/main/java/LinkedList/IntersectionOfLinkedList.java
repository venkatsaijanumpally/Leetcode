package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.HashSet;

public class IntersectionOfLinkedList {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> hm=new HashSet<>();

        while(headA!=null){
            hm.add(headA);
            headA=headA.next;
        }

        while(headB!=null){
            if(hm.contains(headB))
                return headB;
            hm.add(headB);
            headB=headB.next;
        }
        return null;
    }

    public static ListNode getIntersectionNodeUsingLength(ListNode headA, ListNode headB) {
        /**
         * This approach depends on length.
         * In this a single pointer will loop through linked list headA and headB.
         * For ptr1 length to traverse entire headA and then from headB till the interection
         * point is same as ptr2 traversing entire headB and then from headA till intersection point.
         */
        ListNode ptr1=headA;
        ListNode ptr2=headB;
        while(ptr1!=ptr2){
            if(ptr1==null)
                ptr1=headB;
            else
                ptr1=ptr1.next;
            if(ptr2==null)
                ptr2=headA;
            else
                ptr2=ptr2.next;
        }
        return ptr1;
    }

    public static void main(String[] args){
        ListNode fif=new ListNode(5);
        ListNode f=new ListNode(4,fif);
        ListNode third=new ListNode(8,f);
        ListNode second1=new ListNode(1,third);
        ListNode head1=new ListNode(4,second1);

        ListNode third2=new ListNode(3,f);
        ListNode second2=new ListNode(6,second1);
        ListNode head2=new ListNode(5,second2);

        ListNode ptr=getIntersectionNodeUsingLength(head1,head2);;
        if(ptr!=null){
            System.out.print(ptr.val+"->");
        }
    }
}
