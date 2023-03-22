package OutsideLeetcode;

import java.util.*;

public class BadVertices {
    ArrayList<ArrayList<Integer>> adj;
    public void colouring(int n, int[][] edges){
        adj=new ArrayList<>();
        int[][] neighbourColour=new int[n+1][2];
        int[] colour=new int[n+1];

        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());

        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        //Start
        Random random=new Random();
        for(int i=1;i<=n;i++){
            //colour[i]= random.nextInt(2);
            colour[i]= 1;
        }
        for(int i=1;i<=n;i++){
            for(int j:adj.get(i)){
                neighbourColour[i][colour[j]]++;
            }
        }

        System.out.println(Arrays.toString(colour));
        Queue<Integer> queue=new LinkedList<>();
        System.out.print("Bad Vertices: ");
        for(int i=1;i<=n;i++){
            if(checkBadVertex(i,colour,neighbourColour)){
                queue.offer(i);
                System.out.print(i+" ");
            }
        }

        System.out.println();
        int k=0;
        while (!queue.isEmpty()){
            int node=queue.poll();
            if(!checkBadVertex(node,colour,neighbourColour)){
                continue;
            }
            for(int neighbour: adj.get(node)){
                neighbourColour[neighbour][colour[node]]--;
                neighbourColour[neighbour][(colour[node]+1)%2]++;
                if(checkBadVertex(neighbour,colour,neighbourColour))
                    queue.add(neighbour);
            }
            colour[node]=(colour[node]+1)%2;
            k++;
        }
        System.out.println("Final Colours: "+Arrays.toString(colour));
        System.out.println("K: "+(k==edges.length));
        System.out.println();
    }

    private boolean checkBadVertex(int node, int[] colour, int[][] neighbourColour) {
        return neighbourColour[node][colour[node]]>neighbourColour[node][(colour[node]+1)%2];
    }


    public static void main(String[] args) {
        int[][] edges1={{1,2},{1,3},{1,4},{3,5},{3,6},{3,7}};
        int[][] edges2={{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}};
        new BadVertices().colouring(7,edges1);
        //new BadVertices().colouring(4,edges2);
    }
}
