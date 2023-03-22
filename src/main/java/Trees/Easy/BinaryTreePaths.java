package Trees.Easy;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    ArrayList<String> list=new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root==null) return list;
        findAllPaths(root,String.valueOf(root.val));
        return list;
    }

    private void findAllPaths(TreeNode root,String path) {
        if(root.left!=null)
            findAllPaths(root.left,path+"->"+ root.left.val);
        if(root.right!=null)
            findAllPaths(root.right,path+"->"+root.right.val);
        if(root.left==null&&root.right==null)
            list.add(path);
    }

    public static void main(String[] args) {
        TreeNode root1=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.right.right=new TreeNode(4);
        root1.right.left=new TreeNode(10);
        root1.right.left.right=new TreeNode(11);
        root1.right.right.right=new TreeNode(5);
        root1.left=new TreeNode(6);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root1);
        System.out.println(new BinaryTreePaths().binaryTreePaths(root1));
    }
}
