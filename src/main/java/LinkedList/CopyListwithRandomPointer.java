package LinkedList;

import LinkedList.Utils.Node;

import java.util.HashMap;

public class CopyListwithRandomPointer {
    /**
     * https://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
     * Method 1 - We make use of a hashmap to store corresponding nodes of original linked list to that of new linked
     * list.
     * @param head
     * @return
     */
    public Node copyRandomListMethod1(Node head) {
        if(head==null) return null;

        HashMap<Node,Node> hm=new HashMap<>();
        Node originalPtr=head;
        Node newHead = new Node(0);
        Node ptr=newHead;
        //we traverse through the original linked list and create the corresponding nodes with values and assign the next
        //pointer. We do not disturb the random pointer here.
        while (originalPtr!=null){
            Node node=new Node(originalPtr.val);
            ptr.next=node;
            ptr=node;
            hm.put(originalPtr,node);
            originalPtr=originalPtr.next;
        }

        originalPtr=head;
        ptr=newHead.next;
        //In this loop we connect the random pointer of new linked list by checking the corresponding node in hashmap.
        while (originalPtr!=null){
            ptr.random=hm.get(originalPtr.random);
            originalPtr=originalPtr.next;
            ptr=ptr.next;
        }
        return newHead.next;
    }

    /**
     * Method 2 - Create the copy of node 1 and insert it between node 1 & node 2 in the original Linked List, create a
     * copy of 2 and insert it between 2 & 3. Continue in this fashion, add the copy of N after the Nth node
     * Now copy the random link in this way -   original->next->random= original->random->next;
     * original->next is nothing but a copy of the original and Original->random->next is nothing but a copy of the random.
     * Advantage with this solution is O(1) Space Complexity.
     * !Optimal Solution
     */
    public Node copyRandomListMethod2(Node head){
        if(head==null) return null;

        Node ptr=head;
        while (ptr!=null){
            Node node=new Node(ptr.val);
            node.next=ptr.next;
            ptr.next=node;
            ptr=node.next;
        }

        ptr=head;
        Node ptr1=null;
        while (ptr!=null){
            /*ptr1.random=ptr.random!=null?ptr.random.next:null;
            ptr=ptr1.next;
            ptr1=ptr.next;*/
            ptr1=ptr.next;
            ptr1.random=ptr.random!=null?ptr.random.next:null;
            ptr=ptr1.next;
        }

        ptr=head;
        Node newHead=head.next;
        ptr1=null;
        while (ptr!=null){
            ptr1=ptr.next;
            ptr.next=ptr1.next;
            ptr1.next=ptr1.next!=null?ptr1.next.next:null;
            ptr=ptr.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node third=new Node(3);
        Node second=new Node(3);
        Node head=new Node(3,second,null);
        second.next=third;
        second.random=head;

        Node ptr=new CopyListwithRandomPointer().copyRandomListMethod2(head);
        while (ptr!=null){
            System.out.print(ptr.val);
            if(ptr.random!=null)
                System.out.print(" "+ptr.random.val+"--->");
            else
                System.out.print("--->");
            ptr=ptr.next;
        }
    }
}
