package LinkedList;

import LinkedList.Utils.ListNode;

public class SwapNodesinPairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode prev=null,curr=head,currnext=head.next;
        head=head.next;
        ListNode temp;
        while (currnext!=null){
            curr.next=currnext.next;
            currnext.next=curr;
            if(prev!=null)
                prev.next=currnext;

            if(curr.next==null)
                break;

            temp=curr;
            curr=currnext;
            currnext=temp;
            prev=currnext;
            curr=curr.next.next;
            currnext=currnext.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode fif=new ListNode(7);
        ListNode f=new ListNode(6,fif);
        ListNode third=new ListNode(5,f);
        ListNode second1=new ListNode(3,third);
        ListNode head1=new ListNode(1,second1);
        ListNode ptr= new SwapNodesinPairs().swapPairs(head1);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
