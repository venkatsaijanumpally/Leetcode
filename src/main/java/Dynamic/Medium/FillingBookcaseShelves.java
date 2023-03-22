package Dynamic.Medium;

import java.util.HashMap;

public class FillingBookcaseShelves {

    //HashMap<String,Integer> memoize;
    Integer[][] memoize;
    public int minHeightShelves(int[][] books, int shelfWidth) {
        /*int[][][] t=new int[books.length][2][2];

        t[0][0]=new int[]{shelfWidth-books[0][0],books[0][1]};
        t[0][1]=new int[]{shelfWidth-books[0][0],books[0][1]};

        for(int i=1;i< books.length;i++){
            if(books[i][0]<=t[i-1][0][0]){
                t[i][0][0]=t[i-1][0][0]-books[i][0];
                t[i][0][1]=Math.max(t[i-1][0][1],books[i][1]);

                t[i][1][0]=shelfWidth-books[i][0];
                t[i][1][1]=t[i-1][]
            }
            else {

            }
        }*/

        //return recursive(books,1,shelfWidth-books[0][0],books[0][1],books[0][1],shelfWidth);

        //memoize=new HashMap<>();
        memoize=new Integer[books.length][1001];
        return recur(books,1,shelfWidth-books[0][0],books[0][1],shelfWidth);
    }

    public int recursive(int[][] books, int index, int remainingWidth, int height, int currHeight, int shelfWidth){
        if(index==books.length)
            return height;

        if(remainingWidth>=books[index][0]){
            int updatedMax=Math.max(currHeight,books[index][1]);
            return Math.min(recursive(books,index+1,remainingWidth-books[index][0],height+Math.max(0,updatedMax-currHeight),updatedMax, shelfWidth),
                    recursive(books,index+1, shelfWidth-books[index][0],height+books[index][1],books[index][1],shelfWidth)
                    );
        }
        else return recursive(books,index+1, shelfWidth-books[index][0],height+books[index][1],books[index][1],shelfWidth);
    }

    public int recur(int[][] books, int index, int remainingWidth, int currHeight, int shelfWidth){
        if(index==books.length)
            return currHeight;

        /*String s = index + "*" + remainingWidth + "*" + currHeight;
        if(memoize.containsKey(s))
            return memoize.get(s);*/
        if(memoize[index][remainingWidth]!=null)
            return memoize[index][remainingWidth];

        if(remainingWidth>=books[index][0]){
            int updatedMax=Math.max(currHeight,books[index][1]);
            int result = Math.min(recur(books,index+1,remainingWidth-books[index][0], updatedMax, shelfWidth),
                    currHeight+recur(books,index+1, shelfWidth-books[index][0], books[index][1], shelfWidth)
            );
            //memoize.put(s,result);
            memoize[index][remainingWidth]=result;
            return result;
        }
        else {
            /*memoize.put(s,currHeight+recur(books,index+1, shelfWidth-books[index][0], books[index][1],shelfWidth));
            return memoize.get(s);*/
            return memoize[index][remainingWidth]=currHeight+recur(books,index+1, shelfWidth-books[index][0], books[index][1],shelfWidth);
        }
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        int[][] arr2=new int[][]{{1,3},{2,4},{3,2}};
        System.out.println(new FillingBookcaseShelves().minHeightShelves(arr,4));
        System.out.println(new FillingBookcaseShelves().minHeightShelves(arr2,6));
    }
}
