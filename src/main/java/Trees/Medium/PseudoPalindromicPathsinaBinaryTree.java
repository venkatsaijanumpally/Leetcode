package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PseudoPalindromicPathsinaBinaryTree {
    public int pseudoPalindromicPaths (TreeNode root) {
        HashMap<Integer,Integer> frequencyCount=new HashMap<>();
        HashMap<Integer, HashSet<Integer>> indexMap=new HashMap<>();
        return helper1(root,frequencyCount,indexMap,0);
    }

    private int helper(TreeNode root, HashMap<Integer, Integer> frequencyCount, HashMap<Integer,HashSet<Integer>> indexMap, int depth) {
        if(root==null){
            /*if(depth%2==1){
                int count=0;
                int val=0;
                for(Integer i:frequencyCount.keySet()){
                    if(frequencyCount.get(i)%2==1){
                        count++;
                        val=i;
                    }
                    if(count>1)
                        break;
                }
                if(count>1) return 0;
                if(indexMap.get(val).contains(depth/2))
                    return 0;
                return 1;
            }*/
            int count=0;
            for(Integer i:frequencyCount.keySet()){
                if(frequencyCount.get(i)%2==1)
                    ++count;
                if(count>1)
                    return 0;
            }
            return 1;
        }

        depth++;
        frequencyCount.put(root.val,frequencyCount.getOrDefault(root.val,0)+1);
        if(indexMap.get(root.val)==null){
            indexMap.put(root.val,new HashSet<>());
            indexMap.get(root.val).add(depth);
        }
        else {
            indexMap.get(root.val).add(depth);
        }
        int left=helper(root.left,frequencyCount,indexMap,depth);
        int right=helper(root.right,frequencyCount,indexMap,depth);

        if(indexMap.get(root.val).size()>1){
            indexMap.get(root.val).remove(depth);
        }
        else
            indexMap.remove(root.val);
        frequencyCount.put(root.val,frequencyCount.get(root.val)-1);
        if(frequencyCount.get(root.val)==0)
            frequencyCount.remove(root.val);

        return left+right;
    }


    private int helper1(TreeNode root, HashMap<Integer, Integer> frequencyCount, HashMap<Integer,HashSet<Integer>> indexMap, int depth) {
        depth++;
        frequencyCount.put(root.val,frequencyCount.getOrDefault(root.val,0)+1);
        if(indexMap.get(root.val)==null){
            indexMap.put(root.val,new HashSet<>());
            indexMap.get(root.val).add(depth);
        }
        else {
            indexMap.get(root.val).add(depth);
        }


        int left=0;
        int right=0;
        if(root.left==null && root.right==null){
            int count=0;
            for(Integer i:frequencyCount.keySet()){
                if(frequencyCount.get(i)%2==1)
                    ++count;
                if(count>1)
                    break;
            }
            if(count<2)
                left=1;
        }
        else if(root.left==null) {
            right=helper1(root.right,frequencyCount,indexMap,depth);
        }
        else if(root.right==null){
            left=helper1(root.left,frequencyCount,indexMap,depth);
        }
        else {
            left=helper1(root.left,frequencyCount,indexMap,depth);
            right=helper1(root.right,frequencyCount,indexMap,depth);
        }

        if(indexMap.get(root.val).size()>1){
            indexMap.get(root.val).remove(depth);
        }
        else
            indexMap.remove(root.val);
        frequencyCount.put(root.val,frequencyCount.get(root.val)-1);
        if(frequencyCount.get(root.val)==0)
            frequencyCount.remove(root.val);

        return left+right;
    }


    public static void main(String[] args) {
        Integer[] arr=new Integer[]{2,3,1,3,1,null,1};
        TreeNode root=new TreeNode().constructTree(arr);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new PseudoPalindromicPathsinaBinaryTree().pseudoPalindromicPaths(root));
    }
}
