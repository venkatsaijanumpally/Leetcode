package LinkedList;

import LinkedList.Utils.ListNode;

import java.util.Stack;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val){
        this.val=val;
        this.prev=null;
        this.next=null;
        this.child=null;
    }
};
public class FlattenaMultilevelDoublyLinkedList {

    /**
     * https://www.geeksforgeeks.org/flatten-a-multi-level-linked-list-set-2-depth-wise/
     * Method 1: Recursion
     * In this method, if the current node has a child or next node then we call flatten(child) and flatten(next) respectively.
     */

    Node last;
    public Node flattenRecursion(Node head){
        if(head==null)
            return null;

        //When we flatten a child node then the last node will be pointing to last node of child list.
        // else if a node does not have a child then last and head will be the same.
        last=head;
        Node next=head.next;

        if(head.child!=null){
            //head.next=flattenRecursion(head.child);
            Node child=flattenRecursion(head.child);
            head.next=child;
            if(child!=null)
                child.prev=head;
            head.child=null;
        }

        if(head.next!=null){
            //last.next=flattenRecursion(next);
            Node lastNode=last;
            Node nextNode=flattenRecursion(next);
            lastNode.next=nextNode;
            if(nextNode!=null)
                nextNode.prev=lastNode;
        }

        return head;
    }

    /**
     * Method 2:
     * In this method if we find a child node or next node present for head then we push it into the stack.
     * Step 1: Pop the element from stack.
     * Step 2: If next or child node is present push it into stack.
     * Step 3: Connect the current node(temp) to prev node.
     * Step 4: Set every node child to null and move prev to temp.
     * !Optimal Solution
     */
    public Node flatten(Node head) {
        if(head==null) return null;
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        Node prev=null;
        while (!stack.empty()){
            Node temp=stack.peek();
            stack.pop();

            if(temp.next!=null)
                stack.push(temp.next);
            if(temp.child!=null)
                stack.push(temp.child);
            if(prev!=null){
                prev.next=temp;
                temp.prev=prev;
            }

            temp.child=null;
            prev=temp;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head=new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.child = new Node(7);
        head.next.child.child = new Node(9);
        head.next.child.child.child = new Node(14);
        head.next.child.child.child.child= new Node(15);
        head.next.child.child.child.child.next= new Node(23);
        head.next.child.child.child.child.next.child = new Node(24);
        head.next.child.next = new Node(8);
        head.next.child.next.child = new Node(16);
        head.next.child.next.child.child= new Node(17);
        head.next.child.next.child.child.next= new Node(18);
        head.next.child.next.child.child.next.next= new Node(19);
        head.next.child.next.child.child.next.next.next
                = new Node(20);
        head.next.child.next.child.child.next.next.next.child
                = new Node(21);
        head.next.child.next.next = new Node(10);
        head.next.child.next.next.child = new Node(11);
        head.next.child.next.next.next = new Node(12);


        Node ptr=new FlattenaMultilevelDoublyLinkedList().flatten(head);
        while (ptr!=null){
            if(ptr.prev!=null)
            System.out.print(ptr.val+"->");
            else System.out.print("N"+"->");
            ptr=ptr.next;
        }
    }
}
