package Graphs.Medium;

public class NodeWithHighestEdgeScore {
    public int edgeScore(int[] edges) {
        long[] result=new long[edges.length];

        for(int i=0;i<edges.length;i++){
            result[edges[i]]+=i;
        }

        long max=Long.MIN_VALUE;
        int index=-1;
        for(int i=0;i<result.length;i++){
            if(max<result[i]){
                max=result[i];
                index=i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] edges1={1,0,0,0,0,7,7,5};
        System.out.println(new NodeWithHighestEdgeScore().edgeScore(edges1));
    }
}
