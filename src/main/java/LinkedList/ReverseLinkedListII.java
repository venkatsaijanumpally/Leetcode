package LinkedList;

import LinkedList.Utils.ListNode;

public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left==right||head.next==null) return head;

        ListNode ptr=new ListNode(0);
        ptr.next=head;
        head=ptr;
        int count=1;

        ListNode previousNode=head;
        ListNode leftNode=head.next;
        while (count<left){
            previousNode=previousNode.next;
            leftNode=leftNode.next;
            count++;
        }

        ListNode rightNode=leftNode,prev=null,next;
        while (rightNode!=null && count<=right){
            next=rightNode.next;
            rightNode.next=prev;
            prev=rightNode;
            rightNode=next;
            count++;
        }

        previousNode.next=prev;
        leftNode.next=rightNode;

        return ptr.next;
    }

    public static void main(String[] args) {
        ListNode fif=new ListNode(5);
        ListNode f=new ListNode(4,fif);
        ListNode third=new ListNode(3,f);
        ListNode second1=new ListNode(2,third);
        ListNode head1=new ListNode(1,second1);

        ListNode ptr= new ReverseLinkedListII().reverseBetween(head1,4,5);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
