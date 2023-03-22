package LinkedList;

public class LinkedListTest {
    public static class Node{
        int Data;
        Node next;
        Node(int data){
            this.Data=data;
            next=null;
        }
    }
    public static void main(String[] args){
        Node head=new Node(1);
        Node second=new Node(2);
        Node third=new Node(3);
        head.next=second;
        second.next=third;

        Node ptr=head;
        while(ptr!=null){
            System.out.print(ptr.Data+"->");
            ptr=ptr.next;
        }

        LRUCache lruCache=new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.get(2);
    }
}
