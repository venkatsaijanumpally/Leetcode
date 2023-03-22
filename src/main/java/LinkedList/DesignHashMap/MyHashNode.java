package LinkedList.DesignHashMap;

import java.util.Objects;

public class MyHashNode {
    int key;
    int value;
    int hashcode;
    MyHashNode next;
    public MyHashNode(int key,int value, int hashcode){
        this.key=key;
        this.value=value;
        this.hashcode=hashcode;
    }
}
