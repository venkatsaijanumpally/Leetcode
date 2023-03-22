package BFS_DFS.Medium;

import BFS_DFS.Utils.Pair;

import java.util.ArrayList;
import java.util.HashSet;

public class WaterandJugProblem {
    HashSet<ArrayList<Integer>> states=new HashSet<>();
    HashSet<Pair> statesPair=new HashSet<>();
    int jug1Capacity;
    int jug2Capacity;
    int targetCapacity;
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        //Edge Cases
        //fill both jugs and check if it equals target
        if(jug1Capacity+jug2Capacity==targetCapacity) return true;
        // if target is greater than both jugs capacity combined then it cant be achieved
        if(jug1Capacity+jug2Capacity<targetCapacity) return false;
        this.jug1Capacity=jug1Capacity;
        this.jug2Capacity=jug2Capacity;
        this.targetCapacity=targetCapacity;
        //return dfs(0, 0);
        return dfsSet(0, 0);
    }

    private boolean dfs(int jug1, int jug2) {
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(jug1);
        arr.add(jug2);
        if(states.contains(arr))
            return false;
        if(jug1+jug2==targetCapacity)
            return true;
        states.add(arr);

        boolean empty1=dfs(0,jug2);
        if(empty1) return true;
        boolean empty2=dfs(jug1,0);
        if(empty2) return true;
        boolean fill1=dfs(jug1Capacity,jug2);
        if(fill1) return true;
        boolean fill2=dfs(jug1,jug2Capacity);
        if(fill2) return true;

        //Transfer 1 to 2
        boolean tranfer1= dfs(Math.max(0,jug1-(jug2Capacity-jug2)),Math.min(jug2Capacity,jug1+jug2));
        if(tranfer1) return true;
        boolean tranfer2= dfs(Math.min(jug1Capacity,jug2+jug1),Math.max(0,jug2-(jug1Capacity-jug1)));
        if(tranfer2) return true;

        return false;
    }

    //!Optimal
    private boolean dfsSet(int jug1, int jug2) {
        if(jug1+jug2==targetCapacity)
            return true;
        Pair pair=new Pair(jug1,jug2);
        if(statesPair.contains(pair))
            return false;
        statesPair.add(pair);

        if(dfsSet(0,jug2)) return true; // Empty jug1
        if(dfsSet(jug1,0)) return true; // Empty jug2
        if(dfsSet(jug1Capacity,jug2)) return true; //Fill Jug1
        if(dfsSet(jug1,jug2Capacity)) return true; // Fill Jug2
        //Transfer 1 to 2
        if(dfsSet(Math.max(0,jug1-(jug2Capacity-jug2)),Math.min(jug2Capacity,jug1+jug2))) return true;
        //Transfer 2 to 1
        if(dfsSet(Math.min(jug1Capacity,jug2+jug1),Math.max(0,jug2-(jug1Capacity-jug1)))) return true;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WaterandJugProblem().canMeasureWater(3,5,4));
        System.out.println(new WaterandJugProblem().canMeasureWater(2,6,5));
        System.out.println(new WaterandJugProblem().canMeasureWater(1,2,3));
    }
}
