package Trees.Hard;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

import java.util.Stack;

public class RecoveraTreeFromPreorderTraversal {
    /*
     * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/discuss/274621/JavaC%2B%2BPython-Iterative-Stack-Solution
     */
    public TreeNode recoverFromPreorder(String traversal) {
        Stack<TreeNode> stack=new Stack<>();
        for(int i=0;i<traversal.length();){
            int level;
            //Find level by traversing the '-'
            for (level=0;traversal.charAt(i)=='-';i++)
                level++;

            int val=0;
            //Find the value
            for (val=0;i<traversal.length() && traversal.charAt(i)!='-';i++)
                val=val*10+(traversal.charAt(i)-'0');

            //remove nodes which are less than the level
            while (stack.size()>level)
                stack.pop();

            TreeNode node=new TreeNode(val);
            if(!stack.isEmpty()){
                if(stack.peek().left==null)
                    stack.peek().left=node;
                else stack.peek().right=node;
            }
            stack.add(node);
        }

        while (stack.size()>1)
            stack.pop();
        return stack.pop();
    }


    public static void main(String[] args) {
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,new RecoveraTreeFromPreorderTraversal().recoverFromPreorder("1-2--3--4-5--6--7"));
    }
}
