package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class FlowerPlantingWithNoAdjacent {

    HashMap<Integer, ArrayList<Integer>> adj=new HashMap<>();
    public int[] gardenNoAdj(int n, int[][] paths) {
        for(int[] i : paths){
            if(!adj.containsKey(i[0]))
                adj.put(i[0],new ArrayList<>());
            if(!adj.containsKey(i[1]))
                adj.put(i[1],new ArrayList<>());
            adj.get(i[0]).add(i[1]);
            adj.get(i[1]).add(i[0]);
        }

        int[] nodeColour=new int[n];
        for(int key=1;key<=n;key++){
            if(!adj.containsKey(key)){
                nodeColour[key-1]=1;
            }
            else if(nodeColour[key-1]==0){
                Stack<Integer> stack=new Stack<>();
                stack.push(key);
                while (!stack.isEmpty()){
                    int[] colour=new int[5];
                    int node=stack.pop();
                    for(int neighbour: adj.get(node)){
                        colour[nodeColour[neighbour-1]]=1;
                        if(nodeColour[neighbour-1]==0)
                            stack.push(neighbour);
                    }
                    for(int i=1;i<5;i++){
                        if(colour[i]==0){
                            nodeColour[node-1]=i;
                            break;
                        }
                    }
                }
            }
        }
        return nodeColour;
    }

    public static void main(String[] args) {
        int[][] path=new int[][]{{1,2},{2,3},{3,1}};
        int[][] path2=new int[][]{{3,4},{4,5}};
        int[][] path3=new int[][]{{1,2},{3,4}};
        System.out.println(Arrays.toString(new FlowerPlantingWithNoAdjacent().gardenNoAdj(3, path)));
        System.out.println(Arrays.toString(new FlowerPlantingWithNoAdjacent().gardenNoAdj(5, path2)));
        System.out.println(Arrays.toString(new FlowerPlantingWithNoAdjacent().gardenNoAdj(4, path3)));
    }
}
