package BFS_DFS.Medium;

public class SurroundedRegions {
    int[][][] parent;
    int[][] rank;
    int m,n;


    //!Optimal
    public void solve2(char[][] board){
        m= board.length;
        n=board[0].length;
        //Traverse all four borders using 2 for loops
        for(int i=0;i<n;i++){
            dfs(i,0,board);
            dfs(i,m-1,board);
        }

        for(int j=1;j<m-1;j++){
            dfs(0,j,board);
            dfs(n-1,j,board);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='1')
                    board[i][j]='O';
                else if(board[i][j]=='O')
                    board[i][j]='X';
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    //Approach: Move in four directions and mark '1' for identification.
    private void dfs(int i, int j, char[][] board) {
        if(board[i][j]=='O'){
            board[i][j]='1';
            if(i-1>=0)
                dfs(i-1,j,board);
            if(i+1<m)
                dfs(i+1,j,board);
            if(j-1>=0)
                dfs(i,j-1,board);
            if(j+1<n)
                dfs(i,j+1,board);
        }
    }

    //Working
    public void solve(char[][] board) {
        m= board.length;
        n=board[0].length;
        parent=new int[m][n][2];
        rank=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    parent[i][j][0]=i;
                    parent[i][j][1]=j;
                }
            }
        }
        for(int i=m-1;i>0;i--){
            for(int j=0;j<n-1;j++){
                if(board[i][j]=='O'){
                    if(board[i-1][j]=='O')
                        union(i,j,i-1,j);
                    if(board[i][j+1]=='O')
                        union(i,j,i,j+1);
                }
            }
            if(board[i-1][n-1]=='O')
                union(i,n-1,i-1,n-1);
        }

        for(int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    int[] parents=getParent(i,j);
                    if(parents[0]==0 || parents[1]==0 || parents[0]==m-1 || parents[1]==n-1){
                        //Leave it as 'O'
                    }
                    else board[i][j]='X';
                }
                else
                    board[i][j]='X';
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    private void union(int i, int j, int i1, int j1) {
        int[] parent1=getParent(i,j);
        int[] parent2=getParent(i1,j1);

        if(parent1[0]==parent2[0] && parent1[1]==parent2[1])
            return;

        if(parent1[0]==0 || parent1[1]==0 || parent1[0]==m-1 || parent1[1]==n-1){
            parent[parent2[0]][parent2[1]]=parent[parent1[0]][parent1[1]];
        }
        else if(parent2[0]==0 || parent2[1]==0 || parent2[0]==m-1 || parent2[1]==n-1){
            parent[parent1[0]][parent1[1]]=parent[parent2[0]][parent2[1]];
        }

        else {
            int rank1=rank[parent1[0]][parent1[1]];
            int rank2=rank[parent2[0]][parent2[1]];

            if(rank1>rank2)
                parent[parent2[0]][parent2[1]]=parent[parent1[0]][parent1[1]];
            else if(rank2>rank1)
                parent[parent1[0]][parent1[1]]=parent[parent2[0]][parent2[1]];
            else {
                parent[parent2[0]][parent2[1]]=parent[parent1[0]][parent1[1]];
                rank[parent1[0]][parent1[1]]++;
            }
        }
    }

    private int[] getParent(int i, int j) {
         int[] parents=parent[i][j];
         if(parents[0]==i && parents[1]==j)
             return parents;
         return parent[i][j]=getParent(parents[0], parents[1]);
    }

    public static void main(String[] args) {
        char[][] arr={{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] arr3={{'O','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','O'}};
        char[][] arr4={{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        char[][] arr6={{'X','X','X'},{'X','O','O'},{'X','X','O'}};
        char[][] arr2={{'X'}};
        char[][] arr5={{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'}};
        new SurroundedRegions().solve2(arr);
        new SurroundedRegions().solve2(arr2);
        new SurroundedRegions().solve2(arr3);
        new SurroundedRegions().solve2(arr4);
        new SurroundedRegions().solve2(arr5);
        new SurroundedRegions().solve2(arr6);
    }
}
