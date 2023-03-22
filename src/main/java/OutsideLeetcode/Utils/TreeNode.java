package OutsideLeetcode.Utils;

public class TreeNode {
    public int name;
    public TreeNode[] neighbours;
    public TreeNode(int name, TreeNode[] neighbours){
        this.name=name;
        this.neighbours=neighbours;
    }
    public TreeNode(int name){
        this.name=name;
        neighbours=new TreeNode[0];
    }
}
