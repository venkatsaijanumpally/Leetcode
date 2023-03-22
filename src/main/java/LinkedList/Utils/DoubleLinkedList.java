package LinkedList.Utils;

public class DoubleLinkedList {
    public int key;
    public int value;
    public DoubleLinkedList prev;
    public DoubleLinkedList next;
    public DoubleLinkedList(int key,int value){
        this.key=key;
        this.value=value;
    }
    public DoubleLinkedList(int key,int value, DoubleLinkedList previous, DoubleLinkedList next){
        this.key=key;
        this.value=value;
        this.prev=previous;
        this.next=next;
    }
}
