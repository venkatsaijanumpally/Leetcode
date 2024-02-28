package Heap.Utils;

public class Building implements Comparable<Building>{
    public int building;
    public int bricks;
    public int ladders;

    public Building(int building, int bricks, int ladders) {
        this.building = building;
        this.bricks = bricks;
        this.ladders = ladders;
    }

    @Override
    public int compareTo(Building o) {
        /*return  o.building - building;*/
        if (this.building != o.building) {
            return o.building - this.building;
        } else {
            // If buildings are the same, order by the number of bricks first
            int bricksDiff = o.bricks - this.bricks;
            if (bricksDiff != 0) {
                return bricksDiff;
            } else {
                // If number of bricks are the same, order by the number of ladders
                return o.ladders - this.ladders;
            }
        }
    }
}
