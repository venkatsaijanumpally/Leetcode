package LinkedList.DesignHashMap;

import java.util.ArrayList;
import java.util.Objects;

public class MyHashMap {
    /**
     * https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
     */
    ArrayList<MyHashNode> bucketArray;
    int size;
    int numBuckets;
    public MyHashMap() {
        size=0;
        numBuckets=10;
        bucketArray=new ArrayList<>();

        for(int i=0;i<10;i++)
            bucketArray.add(null);
    }

    private static final int hashCode(int key) {
        return Objects.hashCode(key);
    }

    public int getBucketIndex(int key){
        int hashcode=hashCode(key);
        int index=hashcode%numBuckets;
        return index<0?-index:index;
    }

    public void put(int key, int value) {
        int hashcode=hashCode(key);
        int bucketIndex=getBucketIndex(key);

        MyHashNode head=bucketArray.get(bucketIndex);
        while (head!=null){
            if(head.key==key && head.hashcode==hashcode){
                head.value=value;
                return;
            }
            head=head.next;
        }

        size++;
        head=bucketArray.get(bucketIndex);
        MyHashNode node=new MyHashNode(key,value,hashcode);
        node.next=head;
        bucketArray.set(bucketIndex,node);

        if((1.0*size)/numBuckets>=0.7){
            ArrayList<MyHashNode> temp=bucketArray;
            bucketArray=new ArrayList<>();
            numBuckets=2*numBuckets;
            size=0;
            for(int i=0;i<numBuckets;i++)
                bucketArray.add(null);

            for (MyHashNode headnode:temp) {
                while (headnode!=null){
                    put(headnode.key,headnode.value);
                    headnode=headnode.next;
                }
            }
        }
    }

    public int get(int key) {
        int hashcode=hashCode(key);
        int bucketIndex=getBucketIndex(key);

        MyHashNode head=bucketArray.get(bucketIndex);
        while (head!=null){
            if(head.key==key && head.hashcode == hashcode)
                return head.value;
            head=head.next;
        }
        return -1;
    }

    public void remove(int key) {
        int hashcode=hashCode(key);
        int bucketIndex=getBucketIndex(key);

        MyHashNode prev=null;
        MyHashNode head=bucketArray.get(bucketIndex);
        while (head!=null){
            if(head.key==key && head.hashcode == hashcode){
                size--;
                if(prev!=null)
                    prev.next = head.next;
                else
                    bucketArray.set(bucketIndex,head.next);
                return;
            }
            prev=head;
            head=head.next;
        }
    }
    public static void main(String[] args){
        System.out.println(hashCode(12));
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(2, 2);
        map.put(4, 4);
        map.put(5, 5);
        map.put(11, 1);
        map.put(21, 1);
        map.put(55, 1);
        map.remove(21);
        map.remove(11);
        map.remove(1);
    }
}
