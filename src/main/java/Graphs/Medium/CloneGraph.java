package Graphs.Medium;

import Graphs.Utils.Node;

import java.util.HashMap;
import java.util.HashSet;

public class CloneGraph {
    HashMap<Integer,Node> hm=new HashMap<>();
    HashSet<Integer> visited=new HashSet<>();
    public Node cloneGraph(Node node) {
        if(node==null) return null;
        hm.put(node.val, new Node(node.val));
        dfs(node, hm.get(node.val));
        return hm.get(1);
    }

    private void dfs(Node mainNode, Node node) {
        visited.add(node.val);
        for(Node neighbour: mainNode.neighbors){
            if(hm.containsKey(neighbour.val)){
                node.neighbors.add(hm.get(neighbour.val));
            }
            else {
                Node newNode=new Node(neighbour.val);
                hm.put(neighbour.val, newNode);
                node.neighbors.add(newNode);
            }
            if(!visited.contains(neighbour.val))
                dfs(neighbour, hm.get(neighbour.val));
        }
    }

    public static void main(String[] args) {

    }
}
