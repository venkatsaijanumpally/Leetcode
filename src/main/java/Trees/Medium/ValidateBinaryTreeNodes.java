package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ValidateBinaryTreeNodes {
    public TreeNode root=null;
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild){
        HashMap<TreeNode,TreeNode> parentMap=new HashMap<>();
        HashMap<Integer,Integer> parentMapInt=new HashMap<>();
        root=new TreeNode(0);
        parentMap.put(root,null);
        parentMapInt.put(0,null);
        //boolean success=constructTree(root,leftChild,rightChild,parentMap);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        boolean success=true;
        for (int i=0;i<n;i++){
            TreeNode node=queue.poll();
            if(node==null){
                //root=new TreeNode()
            }
            if(leftChild[i]!=-1){
                if(parentMapInt.containsKey(leftChild[i])){
                    success=false;
                    break;
                }
                node.left=new TreeNode(leftChild[i]);
                parentMap.put(node.left,node);
                parentMapInt.put(node.left.val,node.val);
            }
            if(rightChild[i]!=-1){
                if(parentMapInt.containsKey(rightChild[i])){
                    success=false;
                    break;
                }
                node.right=new TreeNode(rightChild[i]);
                parentMap.put(node.right,node);
                parentMapInt.put(node.right.val,node.val);
            }
            if(node.left!=null)
                queue.offer(node.left);
            if(node.right!=null)
                queue.offer(node.right);
        }
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        //System.out.println(root.left.right==root.right.left);
        return success;
    }
    int index=0;
    private boolean constructTree(TreeNode node, int[] leftChild, int[] rightChild, HashMap<TreeNode,TreeNode> parentMap){
        if(index>=leftChild.length)
            return true;

        if(leftChild[index]!=-1){
            node.left=new TreeNode(leftChild[index]);
            parentMap.put(node.left,node);
        }
        if(rightChild[index]!=-1){
            node.right=new TreeNode(rightChild[index]);
            parentMap.put(node.right,node);
        }
        index++;
        boolean left=true,right=true;
        if(node.left!=null)
            left=constructTree(node.left,leftChild,rightChild,parentMap);
        if(node.right!=null)
            right=constructTree(node.right,leftChild,rightChild,parentMap);
        return left && right;
    }

    public static void main(String[] args) {
        int[] leftChild=new int[]{1,-1,3,-1};
        int[] rightChild=new int[]{2,3,-1,-1};
        int[] leftChild1=new int[]{1,0};
        int[] rightChild1=new int[]{-1,-1};
        int[] leftChild2=new int[]{3,-1,1,-1};
        int[] rightChild2=new int[]{-1,-1,0,-1};
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodes(4,leftChild2,rightChild2));
    }
}
