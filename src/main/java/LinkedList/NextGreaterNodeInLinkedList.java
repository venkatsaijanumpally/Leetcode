package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.ArrayList;
import java.util.Stack;

public class NextGreaterNodeInLinkedList {
    /**
     * Method2 - Monotonic Stack
     * https://medium.com/@vishnuvardhan623/monotonic-stack-e9dcc4fa8c3e
     * !Optimal Solution
     * @param head
     * @return
     */
    public int[] nextLargerNodesMethod2(ListNode head){
        ListNode stack=null;
        ListNode curr=head,prev=null,next;
        int size=0;
        //Reverse the linked list.
        while (curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
            size++;
        }
        int[] arr=new int[size];
        int i=size-1;
        //Start traversing from prev node till end
        while (prev!=null){
            //Current element is for tracking purpose
            ListNode current=prev;
            prev=prev.next;
            //Pop all elements in stack which are lesser than current
            while(stack!=null && current.val>=stack.val)
                stack=stack.next;
            //if stack is not null that means there is a element that is greater than current element.
            if(stack!=null)
                arr[i]=stack.val;
            //Connect the current element to stack
            current.next=stack;
            stack=current;
            i--;
        }
        return arr;
    }

    //Below is also a similar implementation of monotonic stack.
    public int[] nextLargerNodes(ListNode head){
        Stack<Integer> stack=new Stack<>();
        ListNode ptr=head;
        int size=0;
        while (ptr!=null){
            size++;
            ptr=ptr.next;
        }

        int[] arr=new int[size];
        ptr=head;
        int count=0;
        while (ptr!=null){
            arr[count++]= ptr.val;
            ptr=ptr.next;
        }

        stack.push(arr[size-1]);
        arr[size-1]=0;
        int high;
        for(int i=size-2;i>=0;i--){
            if(stack.peek()<=arr[i]){
                while (!stack.empty() && stack.peek()<=arr[i])
                    stack.pop();
                if(stack.empty()){
                    stack.push(arr[i]);
                    arr[i]=0;
                }
                else {
                    high=stack.peek();
                    stack.push(arr[i]);
                    arr[i]=high;
                }
            }
            else {
                high=stack.peek();
                stack.push(arr[i]);
                arr[i]=high;

            }
        }
        return arr;
    }

    public static void main(String[] args) {
        //int[] arr=new int[]{1,7,5,1,9,2,5,1};
        int[] arr=new int[]{2,7,4,3,5};
        ListNode head=new ListNode().generateLinkedList(arr);
        int[] arr1=new NextGreaterNodeInLinkedList().nextLargerNodesMethod2(head);
        for(int i=0;i< arr1.length;i++){
            System.out.print(arr1[i]+" ");
        }
    }
}
