package Heap.Utils;

public class Barcode implements Comparable<Barcode>{
    private int barcode;
    private int count;

    public Barcode(int barcode, int count) {
        this.barcode = barcode;
        this.count = count;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public int getCount() {
        return count;
    }

    public void decrementCount() {
        this.count--;
    }

    @Override
    public int compareTo(Barcode o) {
        return o.count - count;
    }
}
