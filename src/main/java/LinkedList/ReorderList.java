package LinkedList;

import LinkedList.Utils.ListNode;

public class ReorderList {
    /**
     * https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head.next==null) return;
        ListNode head1=head,head2=head.next;
        while (head2!=null&&head2.next!=null){
            head1=head1.next;
            head2=head2.next.next;
        }
        head2=reverseList(head1.next);
        head1.next=null;
        ListNode ptr=head;
        ListNode ptr1=head2;
        while (ptr1!=null){
            System.out.print(ptr1.val+"->");
            ptr1=ptr1.next;
        }
        System.out.println();
        while (head2!=null){
            /*if(ptr==null){
                ptr.next=head2;
                break;
            }
            else if(head2==null)
                break;*/
            ListNode temp=head2;
            head2=head2.next;
            temp.next=ptr.next;
            ptr.next=temp;
            ptr=ptr.next.next;
            /*if(head2.val<ptr.val){
                ListNode temp=head2;
                head2=head2.next;
                temp.next=ptr.next;
                ptr.next=temp;
                ptr=ptr.next.next;
            }
            else {
                ListNode temp=ptr;
                ptr=ptr.next;
                temp.next=head2.next;
                head2.next=temp;
                head2=head2.next.next;
            }*/

        }
        if(ptr!=null)
            ptr.next=null;
        ptr=head;
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }

    public ListNode reverseList(ListNode head){
        ListNode curr=head,prev=null,next;
        while (curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode fif=new ListNode(7);
        ListNode f=new ListNode(6,fif);
        ListNode third=new ListNode(5,f);
        ListNode second1=new ListNode(3,third);
        ListNode head1=new ListNode(1,second1);
        new ReorderList().reorderList(head1);
    }
}
