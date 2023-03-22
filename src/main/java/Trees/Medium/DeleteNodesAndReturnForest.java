package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.*;

public class DeleteNodesAndReturnForest {
    /**
     * !Optimal Solution
     * Start from leaf nodes and go till root.
     * If the node value is in to_delete array then return null else return root.
     */
    public List<TreeNode> delNodesUsingMapOptimal(TreeNode root, int[] to_delete){
        List<TreeNode> rootList=new ArrayList<>();
        if(root==null) return rootList;

        HashSet<Integer> toDeletevals=new HashSet<>();
        for(int i=0;i<to_delete.length;i++)
            toDeletevals.add(to_delete[i]);
        deleteNodes(root,toDeletevals,rootList);

        if(!toDeletevals.contains(root.val)){
            rootList.add(root);
        }
        return rootList;
    }

    private TreeNode deleteNodes(TreeNode root, HashSet<Integer> to_delete, List<TreeNode> rootList) {
        if(root==null)
            return null;

        root.left=deleteNodes(root.left,to_delete,rootList);
        root.right=deleteNodes(root.right,to_delete,rootList);

        if(to_delete.contains(root.val)){
            if(root.left!=null) rootList.add(root.left);
            if(root.right!=null) rootList.add(root.right);
            return null;
        }

        return root;
    }


    public List<TreeNode> delNodesUsingMap(TreeNode root, int[] to_delete) {
        List<TreeNode> rootList=new ArrayList<>();
        if(root==null) return rootList;
        rootList.add(root);

        HashMap<TreeNode,TreeNode> parentMap=new HashMap();
        HashSet<Integer> toDeletevals=new HashSet<>();
        for(int i=0;i<to_delete.length;i++)
            toDeletevals.add(to_delete[i]);
        mapDelNodeandParent(root,null,toDeletevals,parentMap);
        boolean delRoot=false;
        if(parentMap.containsKey(root)){
            parentMap.remove(root);
            rootList.remove(root);
            if(root.left!=null)
                rootList.add(root.left);
            if(root.right!=null)
                rootList.add(root.right);
        }

        for(Map.Entry entry:parentMap.entrySet()){
            TreeNode node= (TreeNode) entry.getKey();
            TreeNode parent= (TreeNode) entry.getValue();
            if(rootList.contains(node)){
                rootList.remove(node);
                if(node.left!=null)
                    rootList.add(node.left);
                if(node.right!=null)
                    rootList.add(node.right);
            }
            else {
                if(parent.left==node)
                    parent.left=null;
                else parent.right=null;
                if(node.left!=null)
                    rootList.add(node.left);
                if(node.right!=null)
                    rootList.add(node.right);
            }
        }

        return rootList;
    }

    private void mapDelNodeandParent(TreeNode root, TreeNode parent, HashSet<Integer> toDeletevals, HashMap<TreeNode, TreeNode> parentMap) {
        if(root==null || toDeletevals.isEmpty())
            return;
        if(toDeletevals.contains(root.val)){
            parentMap.put(root,parent);
            toDeletevals.remove(root.val);
        }

        if(root.left!=null)
            mapDelNodeandParent(root.left,root,toDeletevals,parentMap);
        if(root.right!=null)
            mapDelNodeandParent(root.right,root,toDeletevals,parentMap);
    }


    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> rootList=new ArrayList<>();
        rootList.add(root);
        for(int val:to_delete){
            deleteNode(rootList,val);
        }
        return rootList;
    }

    private void deleteNode(List<TreeNode> rootList, int val) {
        boolean success=false;
        for (int i=0;i<rootList.size() && !success;i++){
            TreeNode root= rootList.get(i);
            if(root.val==val){
                rootList.remove(root);
                if(root.left!=null)
                    rootList.add(root.left);
                if(root.right!=null)
                rootList.add(root.right);
                success=true;
            }
            else {
                boolean left=deleteNodeFromRoot(root.left,root,val,rootList);
                boolean right=deleteNodeFromRoot(root.right,root,val,rootList);
                if(left||right)
                    success=true;
            }
        }
    }

    private boolean deleteNodeFromRoot(TreeNode root, TreeNode parent, int val, List<TreeNode> rootList) {
        if(root==null) return false;

        if(root.val==val){
            TreeNode deletionNode=null;
            if(parent.left==root){
                deletionNode=parent.left;
                parent.left=null;
            }
            else {
                deletionNode=parent.right;
                parent.right=null;
            }
            if(deletionNode.left!=null)
                rootList.add(deletionNode.left);
            if(deletionNode.right!=null)
                rootList.add(deletionNode.right);
            return true;
        }
        return deleteNodeFromRoot(root.left,root,val,rootList) || deleteNodeFromRoot(root.right,root,val,rootList);
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,3,4,5,6,7};
        int[] del=new int[]{3,5};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        List<TreeNode> list=new DeleteNodesAndReturnForest().delNodesUsingMapOptimal(root,del);
        for(TreeNode node:list){
            b.print(System.out,node);
            System.out.println();
        }
    }
}
