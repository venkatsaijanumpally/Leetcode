package OutsideLeetcode;

public class TreeHierarchyPairs implements Comparable<TreeHierarchyPairs> {
    public int node;
    public int degree;

    public TreeHierarchyPairs(int node, int degree){
        this.node=node;
        this.degree=degree;
    }


    @Override
    public int compareTo(TreeHierarchyPairs o) {
        if(degree>o.degree)
            return 1;
        if(degree<o.degree)
            return -1;
        return 0;
    }
}
