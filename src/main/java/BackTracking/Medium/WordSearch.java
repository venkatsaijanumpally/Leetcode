package BackTracking.Medium;

public class WordSearch {
    /*
     * Move in 4 directions compare characters, if chars match then move further else backtrack.
     * Both recursion and recursion2 are same.
     */
    boolean visited[][];
    public boolean exist(char[][] board, String word) {
        if(word.length()==0 && board.length==1 && board[0].length==1) return board[0][0]==word.charAt(0);
        visited=new boolean[board.length][board[0].length];
        for(int i=0;i< board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(recursion(board,word,i,j,0))
                        return true;
            }
        }
        return false;
    }

    private boolean recursion(char[][] board, String word, int i, int j, int index) {
        if(index==word.length()){
            return true;
        }

        if(word.charAt(index)==board[i][j] && !visited[i][j]){
            visited[i][j]=true;
            if(j<board[0].length-1 && recursion(board,word,i,j+1,index+1)){
                return true;
            }
            if(j>0 && recursion(board,word,i,j-1,index+1)){
                return true;
            }
            if(i<board.length-1 && recursion(board,word,i+1,j,index+1)){
                return true;
            }
            if(i>0 && recursion(board,word,i-1,j,index+1)){
                return true;
            }

            visited[i][j]=false;
        }
        else return false;
        return false;
    }

    private boolean recursion2(char[][] board, String word, int i, int j, int index){
        if(index==word.length()){
            return true;
        }

        if(i<0 || j<0 || i>=board.length || j>=board[0].length || word.charAt(index)!=board[i][j] || visited[i][j])
            return false;
        else {
            visited[i][j]=true;
            boolean result=recursion(board,word,i+1,j,index+1) || recursion(board,word,i,j+1,index+1) ||
                    recursion(board,word,i,j-1,index+1) || recursion(board,word,i-1,j,index+1);
            visited[i][j]=false;
            return result;
        }
    }

    public static void main(String[] args) {
        char[][] board1={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board2={{'C','C'},{'C','C'}};
        System.out.println(new WordSearch().exist(board1,"ABCCED"));
        System.out.println(new WordSearch().exist(board2,"CCCCC"));
    }
}
