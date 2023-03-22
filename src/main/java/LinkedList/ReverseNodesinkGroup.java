package LinkedList;

import LinkedList.Utils.ListNode;

public class ReverseNodesinkGroup {

    /**
     * !Working Optimal Code
     * https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupOptimal(ListNode head, int k) {
        if(head==null) return null;
        int count=0;
        ListNode current=head,prev=null,next=null,ptr=head;

        while (ptr!=null && count<k){
            ptr=ptr.next;
            count++;
        }

        if(count<k)
            return head;

        count=0;
        while (count<k && current!=null) {
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
            count++;
        }

        if(next!=null)
            head.next=reverseKGroupOptimal(next,k);

        return prev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head.next==null || k==1) return head;
        int size=0;
        ListNode current=head,groupHead=head,prevGroupLastNode=null;
        while (current!=null){
            ++size;
            if(size%k==0){
                ListNode nextGroupHead=current.next;
                ListNode newHead=reverseList(groupHead, k);
                groupHead.next=nextGroupHead;
                if(prevGroupLastNode!=null)
                    prevGroupLastNode.next=newHead;
                prevGroupLastNode=groupHead;
                if(groupHead==head) head=newHead;
                groupHead=nextGroupHead;
                current=nextGroupHead;
                continue;
            }
            current=current.next;
        }
        return head;
    }

    public ListNode reverseList(ListNode head, int k){
        ListNode prev=null,curr=head,next;
        while (curr!=null && k>0){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
            k--;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode fif=new ListNode(7);
        ListNode f=new ListNode(6,fif);
        ListNode third=new ListNode(5,f);
        ListNode second1=new ListNode(3,third);
        ListNode head1=new ListNode(1,second1);
        ListNode ptr= new ReverseNodesinkGroup().reverseKGroupOptimal(head1,3);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
