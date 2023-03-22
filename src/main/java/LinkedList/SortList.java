package LinkedList;

import LinkedList.Utils.ListNode;

public class SortList {
    /**
     * Method: Merge Sort
     * https://www.geeksforgeeks.org/merge-sort-for-linked-list/
     * !Optimal Solution
     */
    private ListNode mergeSort(ListNode head) {
        if(head.next==null) return head;

        ListNode mid=findMid(head);
        ListNode head2=mid.next;
        mid.next=null;
        ListNode newHead1=mergeSort(head);
        ListNode newHead2=mergeSort(head2);
        ListNode mergeHead=merge(newHead1,newHead2);
        return mergeHead;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode mergeHead=new ListNode(0);
        ListNode mergeptr=mergeHead;
        while (head1!=null && head2!=null){
            if(head1.val<head2.val){
                mergeptr.next=head1;
                head1=head1.next;
            }
            else {
                mergeptr.next=head2;
                head2=head2.next;
            }
            mergeptr=mergeptr.next;
        }
        if (head1!=null)
            mergeptr.next=head1;
        if (head2!=null)
            mergeptr.next=head2;
        return mergeHead.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow=head,fast=head.next;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;

        ListNode start=head,end=head.next;
        if(start.val>end.val){
            head.next=head.next.next;
            end.next=head;
            head=end;
        }
        start=head;
        end=head.next;
        ListNode extra=new ListNode(0);
        extra.next=head;
        head=extra;
        start=head;
        while (end.next!=null){
            if(end.next.val>end.val){
                end=end.next;
            }
            else {
                ListNode temp=end.next;
                while (start.next.val<temp.val)
                    start=start.next;
                end.next=temp.next;
                temp.next=start.next;
                start.next=temp;
                start=head;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{-1,5,3,4,0};
        ListNode head=new ListNode().generateLinkedList(arr);
        ListNode ptr=new SortList().mergeSort(head);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
