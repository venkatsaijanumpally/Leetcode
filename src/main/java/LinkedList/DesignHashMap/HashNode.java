package LinkedList.DesignHashMap;

import java.util.Objects;

public class HashNode<K,V> {
    K key;
    V value;
    int hashcode;
    HashNode<K,V> next;
    public HashNode(K key,V value, int hashcode){
        this.key=key;
        this.value=value;
        this.hashcode=hashcode;
    }
    private static int hashCode (int key) {
        return Objects.hashCode(key);
    }
    public static void main(String[] args){
        System.out.println(hashCode(12));
    }
}
