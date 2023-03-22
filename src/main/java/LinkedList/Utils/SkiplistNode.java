package LinkedList.Utils;

public class SkiplistNode {
    public int val;
    public SkiplistNode[] next;
    public SkiplistNode() {
        next=new SkiplistNode[8];
    }
    public SkiplistNode(int val) {
        next=new SkiplistNode[8];
        this.val = val;
    }
}
