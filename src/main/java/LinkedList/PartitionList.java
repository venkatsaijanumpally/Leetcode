package LinkedList;

import LinkedList.Utils.ListNode;

public class PartitionList {

    /**
     * https://www.youtube.com/watch?v=KT1iUciJr4g
     * Here we make 2 seperate linked lists.
     * Left linked list - consists of nodes with values less than x.
     * Right linked list - consists of nodes with values greater or equal to x.
     * and connect left to right.
     * !Optimal Solution
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionOptimal2(ListNode head, int x) {
        if(head==null || head.next==null) return head;
        ListNode left=new ListNode(0),right=new ListNode(0);
        ListNode ltail=left,rtail=right;

        while (head!=null){
            if(head.val<x){
                ltail.next=head;
                ltail=head;
            }
            else {
                rtail.next=head;
                rtail=head;
            }
            head=head.next;
        }

        ltail.next=right.next;
        rtail.next=null;
        return left.next;
    }

    public ListNode partitionOptimal1(ListNode head, int x) {
        if(head==null || head.next==null) return head;
        ListNode head2=head;
        ListNode ptr;
        if(head2.val>=x){
            head2=head;
            ptr=null;
        }
        else {
            while (head2.next!=null && head2.next.val<x)
                head2=head2.next;
            ptr=head2;
            head2=head2.next;
        }

        if (head2==null || head2.next==null) return head;


        while (head2.next!=null){
            if(head2.next.val<x){
                ListNode temp=head2.next;
                head2.next=head2.next.next;
                if(ptr!=null){
                    temp.next=ptr.next;
                    ptr.next=temp;
                    ptr=temp;
                }
                else {
                    temp.next=head;
                    head=temp;
                    ptr=temp;
                }
                continue;
            }
            head2=head2.next;
        }
        return head;

    }

    public ListNode partition(ListNode head, int x) {
        if(head==null || head.next==null) return head;
        ListNode head2=head;
        /*while (head2.val!=x)
            head2=head2.next;*/

        while (head2!=null && head2.val<x)
            head2=head2.next;

        //if(head2.next==null) return head;

        ListNode ptr=head;
        if(ptr.val>=x)
            ptr=null;
        else {
            while (ptr.next!=null && ptr.next.val<x){
                ptr=ptr.next;
            }
        }
        while (head2!=null && head2.next!=null){
            if(head2.next.val<x){
                ListNode temp=head2.next;
                head2.next=head2.next.next;
                if(ptr!=null){
                    temp.next=ptr.next;
                    ptr.next=temp;
                    ptr=temp;
                }
                else {
                    temp.next=head;
                    head=temp;
                    ptr=temp;
                }
                continue;
            }
            head2=head2.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode six=new ListNode(2);
        ListNode fif=new ListNode(5,six);
        ListNode f=new ListNode(2,fif);
        ListNode third=new ListNode(3,f);
        ListNode second1=new ListNode(4,third);
        ListNode head1=new ListNode(1,second1);

        ListNode six2=new ListNode(2);
        ListNode fif2=new ListNode(5,six2);
        ListNode f2=new ListNode(0,fif2);
        ListNode third2=new ListNode(3,f2);
        ListNode second2=new ListNode(4,third2);
        ListNode head2=new ListNode(1,second2);

        ListNode third3=new ListNode(2);
        ListNode second3=new ListNode(1,third3);
        ListNode head3=new ListNode(3,second3);

        ListNode ptr= new PartitionList().partitionOptimal2(head1,3);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
