package Dynamic.Medium;

import Trees.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTrees {
    public List<TreeNode> allPossibleFBT(int n) {
        if(n%2==0) return new ArrayList<>();

        ArrayList<ArrayList<TreeNode>> result=new ArrayList<>();
        result.add(null);
        result.add(1,new ArrayList<>());
        result.get(1).add(new TreeNode(0));
        /*result.add(3,new ArrayList<>());*/
        for(int i=3;i<=n;i+=2){
            result.add(null);
            result.add(i,new ArrayList<>());
            for(int j=1;j<i;j+=2){
                ArrayList<TreeNode> left=result.get(j);
                ArrayList<TreeNode> right=result.get(i-j-1);
                for(TreeNode nodeLeft: left){
                    for (TreeNode nodeRight: right){
                        TreeNode head=new TreeNode(0);
                        head.left=nodeLeft;
                        head.right=nodeRight;
                        result.get(i).add(head);
                    }
                }

            }
        }
        return result.get(n);
    }

    public static void main(String[] args) {

    }
}
