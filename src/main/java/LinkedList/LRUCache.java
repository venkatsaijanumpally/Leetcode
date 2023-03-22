package LinkedList;

import LinkedList.Utils.DoubleLinkedList;

import java.util.HashMap;

public class LRUCache {

    int size;
    int capacity;
    HashMap<Integer, DoubleLinkedList> hm=new HashMap<>();
    DoubleLinkedList head,tail;

    public LRUCache(int capacity) {
        this.capacity=capacity;
        size=0;
        head=new DoubleLinkedList(-1,-1);
        tail=new DoubleLinkedList(-1,-1);
        head.prev=null;
        head.next=tail;

        tail.prev=head;
        tail.next=null;
    }

    public int get(int key) {
        DoubleLinkedList node=hm.get(key);

        if(node!=null){
            removeNode(node);
            addNode(node);
            return node.value;
        }
        return -1;
    }

    private void addNode(DoubleLinkedList node) {
        node.prev=head;
        node.next=head.next;

        head.next.prev=node;
        head.next=node;
    }

    public void removeNode(DoubleLinkedList node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public int popTail(){
        DoubleLinkedList node=tail.prev;
        removeNode(node);
        return node.key;
    }

    public void put(int key, int value) {
        DoubleLinkedList node=hm.get(key);

        if(node!=null){
            node.value=value;
            moveToHead(node);
        }
        else{
            node=new DoubleLinkedList(key,value);
            hm.put(key,node);
            addNode(node);
            size++;

            if(size>capacity) {
                hm.remove(popTail());
                size--;
            }
        }
    }

    private void moveToHead(DoubleLinkedList node) {
        removeNode(node);
        addNode(node);
    }
}
