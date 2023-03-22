package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;
import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<TreeNode> linkedList=new LinkedList<>();
        linkedList.addFirst(root);
        int maxWidth=1;
        while (!linkedList.isEmpty()){
            while (linkedList.size()>0 && linkedList.peekFirst()==null)
                linkedList.removeFirst();
            while (linkedList.size()>0 && linkedList.peekLast()==null)
                linkedList.removeLast();
            int size=linkedList.size();
            if(size>maxWidth)
                maxWidth=size;
            for (int i=0;i<size;i++){
                TreeNode node=linkedList.pollFirst();
                if(node==null){
                    linkedList.addLast(null);
                    linkedList.addLast(null);
                    continue;
                }
                if(node.left==null&&node.right==null)
                    continue;
                linkedList.addLast(node.left);
                linkedList.addLast(node.right);
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,3,2,5,3,null,9};
        Integer[] arr1=new Integer[]{1,3,null,5,3};
        Integer[] arr2=new Integer[]{1,3,2,5,null,null,9,6,null,null,7};
        TreeNode root=new TreeNode().constructTree(arr2);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new MaximumWidthofBinaryTree().widthOfBinaryTree(root));
    }
}
