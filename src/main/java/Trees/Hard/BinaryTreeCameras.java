package Trees.Hard;

import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;

public class BinaryTreeCameras {
    int result=0;
    public int minCameraCover(TreeNode root) {
        int rootresult=findMinCameras(root);
        if(rootresult==-1) ++result;
        return result;
    }

    private int findMinCameras(TreeNode root) {
        if(root==null)
            return 0;

        int left=findMinCameras(root.left);
        int right=findMinCameras(root.right);

        if(left==-1 || right==-1){
            result++;
            return 1;
        }
        if(left==1 || right==1)
            return 0;
        return -1;
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{0,0,null,0,0};
        Integer[] arr1=new Integer[]{0,0,null,0,null,0,null,null,0};
        Integer[] arr2=new Integer[]{0};
        TreeNode root=new TreeNode().constructTree(arr2);
        BinaryTreePrinter b=new BinaryTreePrinter();
        b.print(System.out,root);
        System.out.println();
        System.out.println(new BinaryTreeCameras().minCameraCover(root));
    }
}
