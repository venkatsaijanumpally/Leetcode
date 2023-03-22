package BackTracking.Medium;

import java.util.ArrayList;

public class BeautifulArrangement {
    /*
     * Both recursion methods are optimal
     */
    int result=0;
    boolean[] visited;
    int n;
    public int countArrangement(int n) {
        this.n=n;
        visited=new boolean[n+1];
        //recursion(1,0);
        ArrayList<Integer> nums=new ArrayList<>();
        for(int i=1;i<=n;i++)
            nums.add(i);
        recursion(1,0,nums);
        return result;
    }

    private void recursion(int index, int size) {
        if(size==n){
            result++;
            return;
        }

        for(int i=1;i<=n;i++){
            if(!visited[i] && (i%index==0 || index%i==0)){
                visited[i]=true;
                recursion(index+1,size+1);
                visited[i]=false;
            }
        }
    }

    private void recursion(int index, int size, ArrayList<Integer> nums) {
        if(size==n){
            result++;
            return;
        }

        for(int i=0;i<nums.size();i++){
            if(nums.get(i)%index==0 || index%nums.get(i)==0){
                int rem= nums.get(i);
                nums.remove(i);
                recursion(index+1,size+1,nums);
                nums.add(i,rem);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new BeautifulArrangement().countArrangement(2));
        System.out.println(new BeautifulArrangement().countArrangement(1));
        System.out.println(new BeautifulArrangement().countArrangement(3));
    }
}
