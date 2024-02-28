package Dynamic.Medium;

public class EditDistance {
    Integer[][] memoize;

    public int minDistance(String word1, String word2) {
        /*memoize = new Integer[word1.length()+1][word2.length()+1];
        return recursive(word1, word2, 0, 0);*/

        int[][] t = new int[word1.length() + 1][word2.length() + 1];
        t[word1.length()][word2.length()] = 0;
        for (int j = word2.length() - 1; j >= 0; j--) {
            t[word1.length()][j] = word2.length() - j;
        }
        for (int i = word1.length() - 1; i >= 0; i--) {
            t[i][word2.length()] = word1.length() - i;
        }

        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if(word1.charAt(i)!=word2.charAt(j)){
                    int replace = 1 + t[i+1][j+1];
                    int insert = 1 + t[i][j+1];
                    int ignore = 1 + t[i+1][j];
                    t[i][j] = Math.min(replace, Math.min(insert,ignore));
                }
                else {
                    int move = t[i+1][j+1];
                    int ignore = 1 + t[i+1][j];
                    t[i][j] = Math.min(move,ignore);
                }
            }
        }
        return t[0][0];
    }

    private int recursive(String word1, String word2, int index1, int index2) {
        if (memoize[index1][index2] != null) return memoize[index1][index2];

        int remianing1 = word1.length() - index1;
        int remianing2 = word2.length() - index2;
        if (remianing2 == 0) return memoize[index1][index2] = remianing1;
        else if (remianing1 == 0) {
            return memoize[index1][index2] = remianing2;
        }

        if (word1.charAt(index1) == word2.charAt(index2)) {
            //Ignore, move
            int move = recursive(word1, word2, index1 + 1, index2 + 1);
            int ignore = 1 + recursive(word1, word2, index1 + 1, index2);
            return memoize[index1][index2] = Math.min(move, ignore);
        }
        else {
            //Ignore,replace, insert
            int replace = 1 + recursive(word1, word2, index1 + 1, index2 + 1);
            int ignore = 1 + recursive(word1, word2, index1 + 1, index2);
            int insert = 1 + recursive(word1, word2, index1, index2 + 1);
            return memoize[index1][index2] = Math.min(replace, Math.min(ignore, insert));
        }
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("horse", "ros"));
        System.out.println(new EditDistance().minDistance("intention", "execution"));
        System.out.println(new EditDistance().minDistance("sea", "eat"));
        System.out.println(new EditDistance().minDistance("park", "spake"));
    }
}
