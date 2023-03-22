package BFS_DFS.Utils;

import java.util.Objects;

public class Pair{
    //https://stackoverflow.com/questions/53438370/comparing-objects-in-hashset
    public int jug1;
    public int jug2;

    public  Pair(int jug1,int jug2){
        this.jug1=jug1;
        this.jug2=jug2;
    }

    @Override
    public boolean equals(Object o) {
        // typecast o to Complex so that we can compare data members
        Pair c = (Pair) o;

        // Compare the data members and return accordingly
        return jug1==c.jug1 && jug2==c.jug2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jug1, jug2);
    }
}
