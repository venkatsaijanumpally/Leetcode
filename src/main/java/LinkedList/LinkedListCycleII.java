package LinkedList;

import LinkedList.Utils.ListNode;

public class LinkedListCycleII {
    /**
     * Finding first node of cycle - https://www.geeksforgeeks.org/find-first-node-of-loop-in-a-linked-list/
     * How slow and fast pointer work - https://www.geeksforgeeks.org/how-does-floyds-slow-and-fast-pointers-approach-work/
     *
     * Distance traveled by fast pointer = 2 * (Distance traveled by slow pointer)
     *
     * (m + n*x + k) = 2*(m + n*y + k)
     *
     * Note that before meeting the point shown above, fast was moving at twice speed.
     *
     * x -->  Number of complete cyclic rounds made by fast pointer before they meet first time
     *
     * y -->  Number of complete cyclic rounds made by slow pointer before they meet first time
     *
     *
     * From the above equation, we can conclude below
     * m + k = (x-2y)*n
     *
     * Which means m+k is a multiple of n.
     * So if we start moving both pointers again at the same speed such that one pointer (say slow) begins from the head
     * node of the linked list and other pointers (say fast) begins from the meeting point then when the slow pointer
     * completes m+k distance fast also completes (x-2y)*n distance and both end at the meeting point. Which means in the
     * last cycle of fast pointer after covering 'm' distance both will intersect at mth node(i.e the starting point of cycle)
     * and continue till the meeting point which is 'k' nodes away.
     *
     * If we start the slow pointer from beginning and fast pointer from meeting point by 1 step each as shown below
     * slow=slow.next;
     * fast=fast.next;
     * then by the time slow pointer reaches mth node fast have made 'k' less than (x-2y) cycles.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast=head,slow=head;

        while (fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                break;
            }
        }

        if(fast==null || fast.next==null)
            return null;

        slow=head;
        while (fast!=slow){
            slow=slow.next;
            fast=fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode f=new ListNode(5);
        ListNode third=new ListNode(0,f);
        ListNode second1=new ListNode(-3,third);
        ListNode head1=new ListNode(-10,second1);
        f.next=second1;
        ListNode ptr=new LinkedListCycleII().detectCycle(head1);
            System.out.print(ptr.val+"->");
    }
}
