package Trees.Medium;

import Trees.Utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNode {
    /**
     *! Both Solutions connect and connect1 are optimal
     * Method 1:
     * Traverse in BFS manner by using a Queue and connect each elements next by popping.
     */
    public Node connect(Node root) {
        if(root==null) return root;
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size= queue.size();
            Node curr= queue.poll();
            if(curr.left!=null)
                queue.offer(curr.left);
            if (curr.right!=null)
                queue.offer(curr.right);

            for(int i=0;i< size-1;i++){
                curr.next=queue.poll();
                curr=curr.next;
                if(curr.left!=null)
                    queue.offer(curr.left);
                if (curr.right!=null)
                    queue.offer(curr.right);
            }
        }
        return root;
    }

    /*
     * Method 2:
     * At a particular root connect its left.next to right
     * If root.next is not null which means there are parallel subtrees which we need to connect by
     * assigning this subtree right to the next subtree left.
     */
    public Node connect1(Node root){
        if(root==null)
            return root;
        if(root.left!=null && root.right!=null)
            root.left.next=root.right;
        if(root.next!=null && root.right!=null)
            root.right.next=root.next.left;

        connect1(root.left);
        connect1(root.right);
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr={1,2,3,4,5,6,7};
        Node root=new Node().constructNode(arr);
        root=new PopulatingNextRightPointersinEachNode().connect(root);
        while (root!=null){
            Node node=root;
            while (node!=null){
                System.out.print(node.val+"-->");
                node=node.next;
            }
            System.out.println();
            root=root.left;
        }
    }
}
