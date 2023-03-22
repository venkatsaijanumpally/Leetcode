package OutsideLeetcode;

public class ADT_Assignment_Q3 {

    int[][] vectors;
    int px,py;
    public int findRegion(int[][] vectors, int px, int py){
        this.vectors=vectors;
        this.px=px;
        this.py=py;
        //Search b/w last,1
        if(pointCrossProduct(vectors.length, true)>0 && pointCrossProduct(1,false)>0)
            return 1;
        return searchnew(1,vectors.length);
    }

    private int searchold(int low, int high) {
        if(low>high) return -1;
        else if(low+1==high)
            return high;

        int mid=(low+high)/2;
        int z=crossProduct(mid,low);
        int lowCross=pointCrossProduct(low,true);
        int midCross=pointCrossProduct(mid,false);
        if(midCross==0)
            return mid;
        else if(z>=0){
               if(lowCross>0 && midCross>0)
                   return searchold(low,mid);
               else return searchold(mid,high);
        }
        else {
            if(lowCross>0 || midCross>0)
                return searchold(low,mid);
            else return searchold(mid+1,high);
        }
    }

    private int searchnew(int low, int high) {
        if(low+1==high){
            if(pointCrossProduct(low,true)==0 && dotProduct(low))
                return low;
            return high;
        }

        int mid=(low+high)/2;
        int z=crossProduct(mid,low);
        int lowCross=pointCrossProduct(low,true);
        int midCross=pointCrossProduct(mid,false);
        if(midCross==0 && dotProduct(mid))
            return mid;
        else if(z>=0){
            if(lowCross>=0 && midCross>=0)
                return searchnew(low,mid);
            else return searchnew(mid,high);
        }
        else {
            if(lowCross>=0 || midCross>=0)
                return searchnew(low,mid);
            else return searchnew(mid,high);
        }
    }

    private boolean dotProduct(int mid) {
        --mid;
        return px*vectors[mid][0]+py*vectors[mid][1]>0;
    }

    private int crossProduct(int x, int y){
        --x;--y;
        return vectors[x][0]*vectors[y][1]-vectors[x][1]*vectors[y][0];
    }

    private int pointCrossProduct(int v, boolean first){
        --v;
        if(first)
            return px*vectors[v][1]-py*vectors[v][0];
        return vectors[v][0]*py-vectors[v][1]*px;
    }

    public static void main(String[] args) {
        int[][] arr={{2,2},{2,-2},{-2,-2},{-2,2}};
        System.out.println(new ADT_Assignment_Q3().findRegion(arr,0,-7));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr,-12,0));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr,-3,-3));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr,-8,8));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr,4,4));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr,-3,-1));


        int[][] arr2={{0,2},{2,0},{0,-2},{-1,-8},{-5,-7},{-8,0},{-3,1},{-4,4}};
        System.out.println(new ADT_Assignment_Q3().findRegion(arr2,-8,1));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr2,-3,2));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr2,-4,4));
        System.out.println(new ADT_Assignment_Q3().findRegion(arr2,1,-1));
    }
}
