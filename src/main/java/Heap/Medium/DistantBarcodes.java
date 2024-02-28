package Heap.Medium;

import Heap.Utils.Barcode;

import java.util.*;

public class DistantBarcodes {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] result = new int[barcodes.length];
        int index=0;

        HashMap<Integer,Integer> hm = new HashMap();
        PriorityQueue<Barcode> queue = new PriorityQueue<>();
        for (int barcode: barcodes){
            hm.put(barcode,hm.getOrDefault(barcode,0)+1);
        }
        for (Map.Entry<Integer,Integer> entry: hm.entrySet()){
            queue.add(new Barcode(entry.getKey(),entry.getValue()));
        }

        Barcode prev=queue.poll();
        result[index++]=prev.getBarcode();
        prev.decrementCount();
        while (!queue.isEmpty()){
            Barcode next = queue.poll();
            result[index++] = next.getBarcode();
            next.decrementCount();
            if(prev!=null && prev.getCount()>0){
                queue.add(prev);
            }

            if(next.getCount()!=0){
                prev = next;
            }
            else prev = null;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] barcodes1={1,1,1,2,2,2,2};
        System.out.println(Arrays.toString(new DistantBarcodes().rearrangeBarcodes(barcodes1)));
    }
}
