package LinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class LFUCache {
    /**
     * https://medium.com/algorithm-and-datastructure/lfu-cache-in-o-1-in-java-4bac0892bdb3
     * @param capacity
     */

    int capacity;
    int size;
    int min=-1;
    HashMap<Integer, Integer> vals;
    HashMap<Integer,Integer> count;
    HashMap<Integer, LinkedHashSet<Integer>> frequency;

    public LFUCache(int capacity) {
        vals=new HashMap<>();
        count=new HashMap<>();
        frequency=new HashMap<>();
        size=0;
        this.capacity=capacity;
        frequency.put(0,new LinkedHashSet<>());
    }

    public int get(int key) {
        if(!vals.containsKey(key))
            return -1;
        int counts=count.get(key);
        if(min==counts && frequency.get(counts).size()==1)
            min++;
        frequency.get(counts).remove(key);
        count.put(key,counts+1);
        if(!frequency.containsKey(counts+1))
            frequency.put(counts+1,new LinkedHashSet<>());
        frequency.get(counts+1).add(key);
        return vals.get(key);
    }

    public void put(int key, int value) {
        if(capacity<=0)
            return;
        /*if(vals.containsKey(key)){
            if(min==count.get(key) && frequency.get(count.get(key)).size()==1)
                min++;
            vals.put(key,value);
            frequency.get(count.get(key)).remove(key);
            count.put(key,count.get(key)+1);
            //frequency.put(count.get(key),frequency.get(count.get(key)).add(key));
            if(!frequency.containsKey(count.get(key)))
                frequency.put(count.get(key),new LinkedHashSet<>());
            frequency.get(count.get(key)).add(key);
            return;
        }*/
        if(vals.containsKey(key)){
            vals.put(key,value);
            get(key);
            return;
        }
        if(size>=capacity)
            evict();
        min=0;
        vals.put(key,value);
        count.put(key,0);
        frequency.get(0).add(key);
        ++size;
    }

    private void evict() {
        int evictKey=frequency.get(min).iterator().next();
        vals.remove(evictKey);
        count.remove(evictKey);
        frequency.get(min).remove(evictKey);
    }

    public static void main(String[] args){
        LFUCache lfu=new LFUCache(2);
        /*lfu.put(1,1);
        lfu.put(2,2);
        lfu.put(3,3);
        lfu.put(3,4);
        lfu.put(1,5);
        System.out.println(lfu.get(2));*/

        lfu.put(1,1);
        lfu.put(2,2);
        System.out.println(lfu.get(1));
        lfu.put(3,3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4,4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));
    }
}
