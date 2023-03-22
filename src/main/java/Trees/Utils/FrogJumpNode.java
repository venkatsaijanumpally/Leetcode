package Trees.Utils;

public class FrogJumpNode {
    public int node;
    public double probability;

    public int parent;


    public FrogJumpNode(int parent, int node, double probability) {
        this.node = node;
        this.parent=parent;
        this.probability = probability;
    }
}
