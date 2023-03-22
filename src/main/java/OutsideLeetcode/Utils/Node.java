package OutsideLeetcode.Utils;

public class Node {
    public int name;
    public Node neighbours[];
    public Node(int name, Node[] neighbours){
        this.name=name;
        this.neighbours=neighbours;
    }
}
