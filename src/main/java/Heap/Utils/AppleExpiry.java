package Heap.Utils;

public class AppleExpiry implements Comparable<AppleExpiry> {
    public int day;
    public int count;

    public AppleExpiry(int day, int count) {
        this.day = day;
        this.count = count;
    }


    @Override
    public int compareTo(AppleExpiry o) {
        return day - o.day;
    }
}
