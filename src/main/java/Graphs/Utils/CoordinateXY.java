package Graphs.Utils;

import java.util.Objects;

public class CoordinateXY {
    public int x,y;
    public CoordinateXY(int x, int y){
        this.x=x;
        this.y=y;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (!(obj instanceof CoordinateXY))
            return false;

        CoordinateXY that = (CoordinateXY) obj;
        return this.x == that.x && this.y == that.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }
}
