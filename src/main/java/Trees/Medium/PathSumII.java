package Trees.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    List<List<Integer>> list=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root==null) return list;
        //List<Integer> pathList=new ArrayList<>();
        addAllPaths(root,targetSum, 0, new ArrayList<>());
        return list;
    }

    private void addAllPaths(TreeNode root, int targetSum, int sum, List<Integer> pathList) {
        //if(root==null) return;
        sum+=root.val;
        pathList.add(root.val);
        if(root.left==null && root.right==null){
            if(sum==targetSum)
                list.add(new ArrayList<>(pathList));
            pathList.remove(pathList.size()-1);
            return;
        }
        if(root.left==null){
            addAllPaths(root.right,targetSum,sum,pathList);
            pathList.remove(pathList.size()-1);
            return;
        }
        if(root.right==null){
            addAllPaths(root.left,targetSum,sum,pathList);
            pathList.remove(pathList.size()-1);
            return;
        }
        addAllPaths(root.left,targetSum,sum,pathList);
        addAllPaths(root.right,targetSum,sum,pathList);
        pathList.remove(pathList.size()-1);
        return;
    }

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(2);
        List<Integer> list1=new ArrayList<>(list);
        list1.add(3);
        list.add(4);
        list.remove(0);
        System.out.println(list +" "+ list1);
        TreeNode root=new TreeNode(5) ;
        root.left=new TreeNode(4);
        root.left.left=new TreeNode(11);
        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(2);
        root.right=new TreeNode(8);
        root.right.left=new TreeNode(13);
        root.right.right=new TreeNode(4);
        root.right.right.left=new TreeNode(5);
        root.right.right.right=new TreeNode(1);
        List<List<Integer>> result=new PathSumII().pathSum(root,22);
        System.out.println(result);
    }
}
