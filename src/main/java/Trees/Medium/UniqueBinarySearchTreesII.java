package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
    //List<TreeNode> list=new ArrayList<>();
    public List<TreeNode> generateTrees(int n) {
        /*for(int i=1;i<=n;i++){
            TreeNode node=new TreeNode(i);
            addLeftTree(i-1,i-1,n,node,node);
        }*/
        return createTrees(1,n);
    }

    private List<TreeNode> createTrees(int start, int end) {
        List<TreeNode> list=new ArrayList<>();
        if(start>end){
            list.add(null);
            return list;
        }
        if(start==end){
            list.add(new TreeNode(start));
            return list;
        }
        List<TreeNode> left,right;
        for(int i=start;i<=end;i++){
            left=createTrees(start,i-1);
            right=createTrees(i+1,end);
            for (TreeNode leftNode:left){
                for (TreeNode rightNode:right){
                    TreeNode root=new TreeNode(i);
                    root.left=leftNode;
                    root.right=rightNode;
                    list.add(root);
                }
            }
        }
        return list;
    }

    private void addLeftTree(int currentIndex, int rootIndex, int n, TreeNode currNode, TreeNode rootNode) {
        /*if(currentIndex==0)
            addRightTree(rootIndex+1,n,rootNode);*/

    }

    public static void main(String[] args) {
        ArrayList<TreeNode> arr= (ArrayList<TreeNode>) new UniqueBinarySearchTreesII().generateTrees(3);
        System.out.println(arr);
        for(TreeNode node:arr){
            System.out.println(node.val);
        }
    }
}
