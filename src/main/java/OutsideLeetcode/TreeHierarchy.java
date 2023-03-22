package OutsideLeetcode;

import OutsideLeetcode.Utils.TreeNode;
import OutsideLeetcode.Utils.WeightedTreeNode;

import java.util.*;

public class TreeHierarchy {

    HashMap<Integer, int[]> t;
    HashMap<Integer,ArrayList<WeightedTreeNode>> adj;
    public int minTime(TreeNode root, int[][] edges){
        Stack<TreeNode> stack=new Stack<>();
        adj=new HashMap<>();
        for(int[] edge: edges){
            if(!adj.containsKey(edge[0]))
                adj.put(edge[0],new ArrayList<>());
            adj.get(edge[0]).add(new WeightedTreeNode(edge[1],edge[2]));
        }

        stack.add(root);
        t=new HashMap<>();

        return minTimeRecursive(new WeightedTreeNode(root.name,0));
    }

    private int minTimeRecursive(WeightedTreeNode root) {
        if(root==null || adj.get(root.node)==null) return 0;

        int[][] time=new int[adj.get(root.node).size()][3];
        int k=0;
        int min=0;
        for(WeightedTreeNode child: adj.get(root.node)){
            time[k][0]=minTimeRecursive(child);
            time[k][1]=child.node;
            time[k][2]=child.distance;
            min+=child.distance;
            k++;
        }
        sort(time);
        min+=calculateMax(time,root.node);
        int[] order=new int[time.length];
        for(int i=0;i< order.length;i++)
            order[i]=time[i][1];
        t.put(root.node,order);
        return min;
    }

    private int minTime(int[][] edges, int n){
        adj=new HashMap<>();
        t=new HashMap<>();
        int[] degree=new int[n+1];
        int[] parent=new int[n+1];
        for(int[] edge: edges){
            if(!adj.containsKey(edge[0])){
                adj.put(edge[0],new ArrayList<>());
            }
            adj.get(edge[0]).add(new WeightedTreeNode(edge[1],edge[2]));
            degree[edge[0]]++;
            parent[edge[1]]=edge[0];
        }

        Queue<Integer> queue=new LinkedList<>();
        int[] minVal=new int[n+1];
        for(int i=1;i<=n;i++){
            if(degree[i]==0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int node= queue.poll();
            int min=0;
            if(adj.get(node)==null){}
            else {
                int[][] time=new int[adj.get(node).size()][3];
                int k=0;
                for(WeightedTreeNode child: adj.get(node)){
                    time[k][0]=minVal[child.node];
                    time[k][1]=child.node;
                    time[k][2]=child.distance;
                    min+=child.distance;
                    k++;
                }
                sort(time);
                min+=calculateMax(time,node);
                int[] order=new int[time.length];
                for(int i=0;i< order.length;i++)
                    order[i]=time[i][1];
                t.put(node,order);
            }
            minVal[node]=min;
            --degree[parent[node]];
            if(degree[parent[node]]==0)
                queue.offer(parent[node]);
        }
        return minVal[1];
    }

    private int calculateMax(int[][] time, int root) {
        //if(time.length==0) return 0;
        int x=time[0][0];
        for(int i=1;i<time.length;i++){
            int y=time[i][0];
            x=Math.max(0,x-time[i][2]);
            x=Math.max(x,y);
        }
        return x;
    }

    private void sort(int[][] time) {
        if(time.length==1)
            return;
        else if(time.length==2){
            if(time[1][0]>time[0][0])
                swap(time,1,0);
        }
        else {
            if(time[0][0]<time[1][0]) swap(time,0,1);
            if(time[1][0]<time[2][0]) swap(time,1,2);
            if(time[0][0]<time[1][0]) swap(time,0,1);
        }
    }

    private void swap(int[][] time, int i, int j) {
        int[] temp=time[i];
        time[i]=time[j];
        time[j]=temp;
    }

    private int minTime2(int[][] edges, int n){
        adj=new HashMap<>();
        t=new HashMap<>();
        //HashMap<Integer,Integer> degree=new HashMap<>();

        int[] degree=new int[n+1];
        int[] parent=new int[n+1];
        for(int[] edge: edges){
            if(!adj.containsKey(edge[0])){
                adj.put(edge[0],new ArrayList<>());
            }
            adj.get(edge[0]).add(new WeightedTreeNode(edge[1],edge[2]));
            //degree.put(edge[0],degree.getOrDefault(edge[0],0)+1);
            //degree.put(edge[1],degree.getOrDefault(edge[1],0));
            degree[edge[0]]++;
            parent[edge[1]]=edge[0];
        }



        /*PriorityQueue<TreeHierarchyPairs> queue=new PriorityQueue<>();
        queue.offer(new TreeHierarchyPairs())*/
        Queue<Integer> queue=new LinkedList<>();
        int[] minVal=new int[n+1];
        /*for(Map.Entry<Integer,Integer> entry: degree.entrySet()){
            if(entry.getValue()==0)
        }*/
        for(int i=1;i<=n;i++){
            if(degree[i]==0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int node= queue.poll();
            int min=0;
            if(adj.get(node)==null){}
            else {
                int[][] time=new int[adj.get(node).size()][3];
                int k=0;
                for(WeightedTreeNode child: adj.get(node)){
                    time[k][0]=minVal[child.node];
                    time[k][1]=child.node;
                    time[k][2]=child.distance;
                    min+=child.distance;
                    k++;
                }
                sort(time);
                min+=calculateMax(time,node);
                int[] order=new int[time.length];
                for(int i=0;i< order.length;i++)
                    order[i]=time[i][1];
                t.put(node,order);
            }
            minVal[node]=min;
            --degree[parent[node]];
            if(degree[parent[node]]==0)
                queue.offer(parent[node]);
        }
        return minVal[1];
    }

    public static void main(String[] args) {
        TreeNode node7=new TreeNode(7,new TreeNode[0]);
        TreeNode node6=new TreeNode(6,new TreeNode[0]);
        TreeNode node5=new TreeNode(5,new TreeNode[0]);
        TreeNode node4=new TreeNode(4,new TreeNode[]{node7});
        TreeNode node3=new TreeNode(3,new TreeNode[]{node5,node6});
        TreeNode node2=new TreeNode(2,new TreeNode[0]);
        TreeNode node1=new TreeNode(1,new TreeNode[]{node2,node3,node4});
        int[][] edges1={{1,2,3},{1,3,1},{1,4,4},{3,5,1},{3,6,2},{4,7,1}};
        int[][] edges2={{1,2,4},{2,5,2},{2,6,3},{6,9,5},{6,10,4},{6,11,3},{1,3,3},{1,4,4},{4,7,1},{4,8,4},{4,12,9},{7,13,6},{13,16,3},{8,14,3},{8,15,2}};
        System.out.println(new TreeHierarchy().minTime(node1,edges1));
        System.out.println(new TreeHierarchy().minTime(edges1,7));
        System.out.println(new TreeHierarchy().minTime(edges2,16));
    }
}
