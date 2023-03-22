package LinkedList;

import LinkedList.Utils.ListNode;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null || k==0) return head;
        int count=0;
        ListNode front=head;
        //move the front pointer by k nodes
        while (front!=null && count<k){
            count++;
            front=front.next;
        }

        //If the front is null then k must have multiple unwanted cycles which can be reduced by modulus of size.
        //We need to move the front pointer again by k units since k is changed.
        if(front==null){
            k=k%count;
            if(k==0) return head;
            front=head;
            count=0;
            while (count<k){
                count++;
                front=front.next;
            }
        }

        ListNode rear=head;
        //We move rear and front till the end and connect one end to another.
        while (front.next!=null){
            front=front.next;
            rear=rear.next;
        }
        ListNode tempHead=head;
        head=rear.next;
        front.next=tempHead;
        rear.next=null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3};
        ListNode head=new ListNode().generateLinkedList(arr);
        ListNode ptr=new RotateList().rotateRight(head,6);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
