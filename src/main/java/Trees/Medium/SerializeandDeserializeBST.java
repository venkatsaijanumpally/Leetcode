package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.Arrays;

public class SerializeandDeserializeBST {

    int index=0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        //return inorderTraversal(root,"");
        //return BFT(root);
        StringBuilder sb=new StringBuilder();
        Preorder(root,sb);
        return sb.substring(1);
    }

    private void Preorder(TreeNode root,StringBuilder s) {
        if(root==null)
            return;
        s.append("*").append(root.val);
        Preorder(root.left,s);
        Preorder(root.right,s);
    }

    public TreeNode deserialize(String data) {
        if(data.length()==0) return null;
        String[] arr=data.split("\\*");

        return constructBST(Integer.MIN_VALUE,Integer.MAX_VALUE, arr);
    }

    /*
     * To construct the BST we start from 0 index move till last one by one.
     * At a particular index "i" the elements that are after 'i' and less than arr[i] belong to left subtree
     * and elements after 'i' that are greater than arr[i] belong to right tree.
     * example: 4,2,3,7,6,9
     *          4 - Min:Integer.min , Max:Integer.Max
     *          2 - Min:Integer.min, Max:4   This element is after 4 and less than 4 so belongs to left tree.
     *          3 - Min:2, Max:4 This element is after 2 and greater than 2 so belongs to right tree.
     */
    private TreeNode constructBST(int low, int high, String[] arr) {
        if(index== arr.length)
            return null;

        int val=Integer.parseInt(arr[index]);
        if(val<low||val>high) return null;
        index++;
        TreeNode root=new TreeNode(val);

        root.left=constructBST(low, val, arr);
        root.right=constructBST(val, high,arr);

        return root;
    }


    // Decodes your encoded data to tree.
    /*public TreeNode deserialize(String data) {
        if(data.length()==0) return null;
        String[] strings=data.split("\\*");
        int[] arr=new int[strings.length];
        for (int i=0;i< arr.length;i++)
            arr[i]=Integer.parseInt(strings[i]);

        return constructBST(Integer.MIN_VALUE,Integer.MAX_VALUE, arr);
    }

    private TreeNode constructBST(int low, int high, int[] arr) {
        if(index== arr.length||arr[index]<low||arr[index]>high)
            return null;

        TreeNode root=new TreeNode(arr[index++]);

        root.left=constructBST(low, root.val, arr);
        root.right=constructBST(root.val, high,arr);

        return root;
    }*/


    public static void main(String[] args) {
        TreeNode node=new TreeNode().constructTree(new Integer[]{4,2,7,null,3,6,9});
        /*TreeNode node=new TreeNode().constructTree(new Integer[]{});*/
        BinaryTreePrinter b=new BinaryTreePrinter();
        /*b.print(System.out,node);
        System.out.println();*/
        String s=new SerializeandDeserializeBST().serialize(node);
        System.out.println("String:"+s);
        System.out.println(Arrays.toString(s.split("\\*")));
        b.print(System.out,new SerializeandDeserializeBST().deserialize(s));
    }
}
