package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.*;

public class AllNodesDistanceKinBinaryTree {
    List<Integer> list=new ArrayList<>();
    HashMap<TreeNode,TreeNode> parentMap=new HashMap<>();
    /*
     * The solution has 2 operations
     * 1. Find all nodes that are below the target and at a distance k
     * 2. Find all nodes that are above target or on the other branch of the tree. For this we create a parent Map
     *    which consist of all child-->parent mappings. We start iterating from the parent of target until root.
     *    For every node in the iteration we find nodes at a distance k-distance-1 in other subtree in which target
     *    is not there.
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        mapParent(root,null);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(target);
        int dist=0;
        //Find all nodes below target at a distance k
        while (!queue.isEmpty()){
            int size=queue.size();
            if(dist<=k){
                for (int i=0;i<size;i++){
                    TreeNode node=queue.poll();
                    if(dist==k)
                        list.add(node.val);
                    if(node.left!=null)
                        queue.offer(node.left);
                    if(node.right!=null)
                        queue.offer(node.right);
                }
                dist++;
            }
            else break;
        }

        dist=1;
        TreeNode parent=parentMap.get(target);
        TreeNode child=target;
        //Find all nodes that are above target at a distance k
        while (parent!=null && dist<=k){
            if(dist==k){
                list.add(parent.val);
                break;
            }
            if(parent.left!=child){
                specificDistance(parent.left,k-dist-1);
            }
            if(parent.right!=child){
                specificDistance(parent.right,k-dist-1);
            }
            child=parent;
            parent=parentMap.get(parent);
            dist++;
        }


        return list;
    }

    private void specificDistance(TreeNode node, int dist) {
        if(node==null || dist<0)
            return;
        if(dist==0){
            list.add(node.val);
            return;
        }
        specificDistance(node.left,--dist);
        specificDistance(node.right,dist);
    }

    private void mapParent(TreeNode root, TreeNode parent) {
        if(root!=null){
            parentMap.put(root,parent);
            mapParent(root.left,root);
            mapParent(root.right,root);
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{3,5,1,6,2,0,8,null,null,7,4,null,null,null,null,9,10,null,11,12};
        Integer[] arr1=new Integer[]{0,1,null,3,2};
        TreeNode root=new TreeNode().constructTree(arr1);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new AllNodesDistanceKinBinaryTree().distanceK(root,root.left,3));
        System.out.println(new AllNodesDistanceKinBinaryTree().distanceK(root,root.left.right,1));
    }
}
