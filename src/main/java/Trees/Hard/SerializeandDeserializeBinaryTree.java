package Trees.Hard;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SerializeandDeserializeBinaryTree {
    /*
     * Both methods 1,2 are good. Only difference is one uses queues and other uses recursion. Even though recursion
     * solution output is faster but in real-time it fails if input is too larges by STACKOVERFLOW at that time we
     * need to use stacks and queues.
     */
    // Encodes a tree to a single string.
    //Method 1: Serialize and deserialize using BFS
    public String serialize(TreeNode root) {
        if(root==null) return "";
        StringBuilder sb=new StringBuilder();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i=0;i<size;i++){
                TreeNode node=queue.poll();
                if(node==null){
                    sb.append("#");
                    continue;
                }
                sb.append("#").append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.substring(1);
    }

    //Method 1
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length()==0)
            return null;

        String[] s=data.split("#");
        int index=1;
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.parseInt(s[0]));
        queue.offer(root);
        while (!queue.isEmpty() && index<s.length){
            int size=queue.size();
            for (int i=0;i<size&&index<s.length;i++){
                TreeNode node=queue.poll();
                if(s[index].length()>0){
                    node.left=new TreeNode(Integer.parseInt(s[index]));
                    queue.offer(node.left);
                }

                ++index;
                if(index<s.length && s[index].length()!=0){
                    node.right=new TreeNode(Integer.parseInt(s[index]));
                    queue.offer(node.right);
                }
                index++;
            }
        }


        return root;
    }




    //Method 2: serialize and deserialize using DFS
    public String serializeinDFS(TreeNode root) {
        if(root==null) return "";
        StringBuilder sb=new StringBuilder();
        /*Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.pop();
            if(node==null){
                sb.append("#");
                continue;
            }
            sb.append("#").append(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }*/
        DFS(sb,root);
        return sb.substring(1);
        //return preorderTraversal(root,new StringBuilder()).toString();
    }

    private void DFS(StringBuilder sb, TreeNode root) {
        if(root==null){
            sb.append("#");
            return;
        }
        sb.append("#").append(root.val);
        DFS(sb,root.left);
        DFS(sb,root.right);
    }

    //Method 2
    public TreeNode deserializeDFS(String data) {
        String[] s=data.split("#");
        Queue<String> queue= new LinkedList<>(Arrays.asList(s));
        TreeNode root=constructTree(queue);
        return root;
    }

    public TreeNode constructTree(Queue<String> queue){
        String s=queue.poll();
        if(s==null || s.length()==0)
            return null;

        TreeNode node=new TreeNode(Integer.parseInt(s));
        node.left=constructTree(queue);
        node.right=constructTree(queue);
        return node;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,3,null,null,4,5};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        String s=new SerializeandDeserializeBinaryTree().serializeinDFS(root);
        System.out.println(s);
        b.print(System.out,new SerializeandDeserializeBinaryTree().deserializeDFS(s));
    }
}
