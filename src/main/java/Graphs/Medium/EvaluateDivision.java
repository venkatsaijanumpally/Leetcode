package Graphs.Medium;

import Graphs.Utils.DivisionDistance;
import Graphs.Utils.WeightedDoublePair;

import java.util.*;

public class EvaluateDivision {
    HashMap<String,ArrayList<WeightedDoublePair>> adj=new HashMap<>();
    HashMap<String,String> parent=new HashMap<>();
    HashMap<String,Integer> rank=new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result=new double[queries.size()];
        for(int i=0;i< equations.size();i++){
            String numerator=equations.get(i).get(0);
            String deno=equations.get(i).get(1);
            if(!adj.containsKey(numerator)){
                adj.put(numerator,new ArrayList<>());
                parent.put(numerator,numerator);
                rank.put(numerator,1);
            }
            if(!adj.containsKey(deno)){
                adj.put(deno,new ArrayList<>());
                parent.put(deno,deno);
                rank.put(deno,1);
            }
            union(numerator,deno);
            adj.get(numerator).add(new WeightedDoublePair(deno,values[i]));
            adj.get(deno).add(new WeightedDoublePair(numerator,1/values[i]));
        }

        int k=0;
        for(List<String> query:queries){
            if(!adj.containsKey(query.get(0)) || !adj.containsKey(query.get(1))
                    || !getParent(query.get(0)).equals(getParent(query.get(1)))){
                result[k++]=-1;
                continue;
            }
            HashMap<String,Double> distMap=new HashMap<>();
            distMap.put(query.get(0),1d);
            Queue<DivisionDistance> queue=new PriorityQueue<>();
            queue.offer(new DivisionDistance(query.get(0),0, 1d));
            while (!queue.isEmpty() && !distMap.containsKey(query.get(1))){
                DivisionDistance pair= queue.poll();
                for(WeightedDoublePair next:adj.get(pair.node)){
                    if(distMap.containsKey(next.node))
                        continue;
                    double div=pair.div * next.div;
                    int distance= pair.distance+1;
                    distMap.put(next.node,div);
                    queue.add(new DivisionDistance(next.node, distance, div));
                }
            }
            if(!distMap.containsKey(query.get(1)))
                result[k++]=-1;
            else result[k++]=distMap.get(query.get(1));
        }
        return result;
    }

    private boolean union(String numerator, String deno) {
        String p1=getParent(numerator);
        String p2=getParent(deno);

        if(p1.equals(p2))
            return false;
        else if(rank.get(p1)>rank.get(p2)){
            parent.put(p2,p1);
        }
        else if(rank.get(p1)<rank.get(p2))
            parent.put(p1,p2);
        else {
            parent.put(p2,p1);
            rank.put(p1,rank.get(p1)+1);
        }
        return true;
    }

    private String getParent(String node) {
        if(parent.get(node).equals(node))
            return node;
        parent.put(node,getParent(parent.get(node)));
        return parent.get(node);
    }

    public static void main(String[] args) {
        /*List<List<String>> eqn1=new ArrayList<>();
        eqn1.add(new ArrayList<>());
        eqn1.add(new ArrayList<>());
        eqn1.get(0).add("a");
        eqn1.get(0).add("b");
        eqn1.get(1).add("b");
        eqn1.get(1).add("c");
        double[] val1=new double[]{2.0,3.0};
        List<List<String>> queries1=new ArrayList<>();
        queries1.add(new ArrayList<>());
        queries1.add(new ArrayList<>());
        queries1.add(new ArrayList<>());
        queries1.add(new ArrayList<>());
        queries1.add(new ArrayList<>());
        queries1.get(0).add("a");
        queries1.get(0).add("c");
        queries1.get(1).add("b");
        queries1.get(1).add("a");
        queries1.get(2).add("a");
        queries1.get(2).add("e");
        queries1.get(3).add("a");
        queries1.get(3).add("a");
        queries1.get(4).add("x");
        queries1.get(4).add("x");*/


        List<List<String>> eqn2=new ArrayList<>();
        eqn2.add(new ArrayList<>());
        eqn2.add(new ArrayList<>());
        eqn2.add(new ArrayList<>());
        eqn2.get(0).add("a");
        eqn2.get(0).add("b");
        eqn2.get(1).add("e");
        eqn2.get(1).add("f");
        eqn2.get(2).add("b");
        eqn2.get(2).add("e");
        double[] val2=new double[]{3.4,1.4,2.3};
        List<List<String>> queries2=new ArrayList<>();
        queries2.add(new ArrayList<>());
        queries2.get(0).add("a");
        queries2.get(0).add("f");
        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(eqn2, val2, queries2)));
    }
}
