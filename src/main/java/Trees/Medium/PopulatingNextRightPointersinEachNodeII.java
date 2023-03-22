package Trees.Medium;

import Trees.Utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNodeII {
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
     * The recursive function will traverse though only the left items.
     * e.g: root, root.left, root.left.left, root.left.left.left and so on
     * At a particular root we create a temporary node nextLevel and nextLevelCurr
     * to track the trailing element.
     * We run the "for loop" for the entire level from left to right and connect them
     * using the nextLevelCurr.
     * and then again call the function to connect next level.
     */
    public Node connect1(Node root){
        if(root==null)
            return root;

        Node nextLevel=new Node();
        Node nextLevelCurr=nextLevel;
        for(Node node=root;node!=null;node=node.next){
            if(node.left!=null){
                nextLevelCurr.next=node.left;
                nextLevelCurr=nextLevelCurr.next;
            }
            if(node.right!=null){
                nextLevelCurr.next=node.right;
                nextLevelCurr=nextLevelCurr.next;
            }
        }

        connect1(nextLevel.next);
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr={1,2,3,4,5,null,7};
        Node root=new Node().constructNode(arr);
        root=new PopulatingNextRightPointersinEachNodeII().connect1(root);
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
