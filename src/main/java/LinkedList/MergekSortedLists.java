package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.List;

public class MergekSortedLists {
    /**
     * Did not test this code locally but directly executed in leetcode.
     * https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        int last=lists.length-1;
        while (last!=0){
            int i=0,j=last;
            while (i<j){
                lists[i]=SortedMerge(lists[i],lists[j]);
                i++;
                j--;
                if(i>=j)
                    last=j;
            }
        }
        return lists[0];
    }

    private ListNode SortedMerge(ListNode a, ListNode b) {
        if(a==null)
            return b;
        else if(b==null)
            return a;
        ListNode node=null;
        if(a.val<b.val){
            node=a;
            a.next=SortedMerge(a.next,b);
        }
        else {
            node=b;
            b.next=SortedMerge(a,b.next);
        }
        return node;
    }

    public static void main(String[] args) {
        //ListNode ptr=new MergekSortedLists().mergeKLists();
    }
}
