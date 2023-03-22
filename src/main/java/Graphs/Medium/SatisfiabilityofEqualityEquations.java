package Graphs.Medium;

public class SatisfiabilityofEqualityEquations {
    int[] parent=new int[27];
    int[] rank=new int[27];

    public boolean equationsPossible(String[] equations) {
        for(int i=0;i<27;i++)
            parent[i]=i;
        boolean[] visited=new boolean[27];

        for(String eqn: equations){
            if(eqn.startsWith("!=", 1))
                continue;
            int a=eqn.charAt(0)-96;
            int b=eqn.charAt(3)-96;
            union(a,b);
        }
        for(String eqn: equations){
            if(eqn.startsWith("==", 1))
                continue;
            int a=eqn.charAt(0)-96;
            int b=eqn.charAt(3)-96;
            int parentA=getParent(a);
            int parentB=getParent(b);
            if(parentA==parentB)
                return false;
        }

        return true;
    }

    private void union(int a, int b) {
        int parentA=getParent(a);
        int parentB=getParent(b);

        if(parentA == parentB)
            return;
        else if(rank[parentA]==rank[parentB]){
            rank[parentA]++;
            parent[parentB]=parentA;
        }
        else if(rank[parentA]<rank[parentB])
            parent[parentA]=parentB;
        else parent[parentB]=parentA;
    }

    private int getParent(int x) {
        if(x==parent[x])
            return x;
        return parent[x]=getParent(parent[x]);
    }

    public static void main(String[] args) {
        String[] arr={"a==b","b!=a"};
        String[] arr2={"a==b","b==a"};
        String[] arr3={"c==c","f!=a","f==b","b==c"};
        String[] arr4={"a==b","b!=c","c==a"};
        System.out.println(new SatisfiabilityofEqualityEquations().equationsPossible(arr));
        System.out.println(new SatisfiabilityofEqualityEquations().equationsPossible(arr2));
        System.out.println(new SatisfiabilityofEqualityEquations().equationsPossible(arr3));
        System.out.println(new SatisfiabilityofEqualityEquations().equationsPossible(arr4));
    }
}
