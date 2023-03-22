package Trees.Medium;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.HashMap;

public class PathSumIII {
    int count=0;

    /**
     * https://leetcode.com/problems/path-sum-iii/discuss/91915/my-detailed-explanation-to-the-hashmap-method
     * !Optimal Solution pathSumHashmap()
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null)return count;
        //findNumberOfPaths(root,targetSum,0,new ArrayList<>(),0,0);
        //findNumberOfPathsBrute(root,targetSum,0);
        //findNumberOfPathsBrute1(root,targetSum);
        //return count;
        HashMap<Integer,Integer> hm=new HashMap<>();
        hm.put(0,1);
        pathSumHashmap(root,targetSum,0,hm);
        return count;
    }

    private void pathSumHashmap(TreeNode root, int targetSum, int sum, HashMap<Integer, Integer> hm) {
        if(root==null) return;

        sum+=root.val;
        count+=hm.getOrDefault(sum-targetSum,0);
        hm.put(sum,hm.getOrDefault(sum,0)+1);

        pathSumHashmap(root.left,targetSum,sum,hm);
        pathSumHashmap(root.right,targetSum,sum,hm);
        hm.put(sum,hm.get(sum)-1);
    }

    //Slow
    private void findNumberOfPathsBrute1(TreeNode root, int targetSum) {
        if(root==null) return;

        recursivePathSum(root,targetSum,0);

        findNumberOfPathsBrute1(root.left,targetSum);
        findNumberOfPathsBrute1(root.right,targetSum);
    }

    private void recursivePathSum(TreeNode root, int targetSum, int sum) {
        if(root==null) return;
        sum+=root.val;
        if(sum==targetSum)
            count++;

        recursivePathSum(root.left,targetSum,sum);
        recursivePathSum(root.right,targetSum,sum);
    }

    public static void main(String[] args) {
        Integer[] i={10,5,-3,3,2,null,11,3,-2,null,1};
        Integer[] i1={5,4,8,11,null,13,4,7,2,null,null,5,1};
        Integer[] i2={-2,null,-3};
        Integer[] i3={0,1,1};
        Integer[] i4={1,2};
        Integer[] i5={1,null,2,null,3,null,4,null,5};
        TreeNode root4=new TreeNode(1);
        root4.left=new TreeNode(2);
        TreeNode root=new TreeNode().constructTree(i);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new PathSumIII().pathSum(root,8));
    }
}
