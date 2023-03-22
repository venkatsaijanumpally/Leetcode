package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoveZeroSumConsecutiveNodesfromLinkedList {
    /**
     * Method 2:
     *!OptimalSolution
     */
    public ListNode removeZeroSumSublistsMethod2(ListNode head){
        ListNode tempHead=new ListNode(0,head);
        ListNode ptr=tempHead;
        HashMap<Integer,ListNode> prefix=new HashMap<>();
        int sum=0;
        //We add all pairs of (sum,ListNode) to hashmap
        //Dont worry about duplicates, when we get a sum which is already there then it gets overidden which is
        //useful in next loop to directly connect the beginning appearance of that sum node to end appearance of that node.
        //e.g:  prefix Sum:  1  4  6  3  2  6  9  7  6
        // In the above example first 6 will be connected to 3rd 6 in the next loop.
        while (ptr!=null){
            sum+=ptr.val;
            prefix.put(sum,ptr);
            ptr=ptr.next;
        }

        sum=0;
        ptr=tempHead;
        while (ptr!=null){
            sum+= ptr.val;
            ptr.next=prefix.get(sum).next;
            ptr=ptr.next;
        }
        return tempHead.next;
    }
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode tempHead=new ListNode(0);
        tempHead.next=head;
        ListNode ptr=head;
        HashMap<Integer,ListNode> prefix=new HashMap<>();
        int sum=0;
        while (ptr!=null){
            sum+= ptr.val;
            if(sum==0){
                tempHead.next= ptr.next;
                prefix.clear();
            }
            else if(prefix.containsKey(sum)){
                int SUM=sum;
                ListNode removeNode=prefix.get(sum).next;
                while (removeNode!=ptr){
                    SUM+=removeNode.val;
                    prefix.remove(SUM);
                    removeNode=removeNode.next;
                }
                prefix.get(sum).next=ptr.next;
            }
            else prefix.put(sum,ptr);
            ptr=ptr.next;
        }
        return tempHead.next;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,3,2,-3,-2,5,5,-5,1};
        int[] arr2=new int[]{2,2,-2,1,-1,-1};
        int[] arr3=new int[]{1,0,0,-1,2,-1,0};
        ListNode head=new ListNode().generateLinkedList(arr2);
        ListNode ptr=new RemoveZeroSumConsecutiveNodesfromLinkedList().removeZeroSumSublistsMethod2(head);
        while (ptr!=null){
            System.out.print(ptr.val+"->");
            ptr=ptr.next;
        }
    }
}
