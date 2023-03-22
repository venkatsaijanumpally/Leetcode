package Trees.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public Node constructNode(Integer[] arr){
        Queue<Node> queue=new LinkedList<>();
        Node root=new Node(arr[0]);
        queue.offer(root);
        int size=1;
        int index=1;
        while (!queue.isEmpty()&&index<arr.length){
            for(int i=0;i<size&&index<arr.length;i++){
                Node node=queue.poll();

                if(arr[index]!=null){
                    node.left=new Node(arr[index]);
                    queue.offer(node.left);
                }
                index++;
                if(arr[index]!=null){
                    node.right=new Node(arr[index]);
                    queue.offer(node.right);
                }
                index++;
            }
            size*=2;
        }
        return root;
    }
}
