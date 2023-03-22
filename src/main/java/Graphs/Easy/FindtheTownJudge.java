package Graphs.Easy;

public class FindtheTownJudge {
    public int findJudge(int n, int[][] trust) {
        /*HashSet<Integer> hs=new HashSet<>();
        for(int i=1;i<=n;i++)
            hs.add(i);
        for(int i=0;i< trust.length;i++){
            hs.remove(trust[i][0]);
        }
        return hs.size()==1?hs.iterator().next():-1;*/

        /*int expectedJudge=trust[0][1];
        for(int i=1;i< trust.length;i++){
            if(expectedJudge!=trust[i][1]){
                expectedJudge=-1;
                break;
            }
        }
        return expectedJudge;*/

        /*HashSet<Integer> hs=new HashSet<>();
        for(int i=1;i<=n;i++)
            hs.add(i);
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();

        for(int i=0;i<=n;i++)
            list.add(new ArrayList<>());
        for(int i=0;i<trust.length;i++){
            list.get(trust[i][0]).add(trust[i][1]);
            hs.remove(trust[i][0]);
        }

        if(hs.size()==0) return -1;
        int judge=hs.iterator().next();
        for(int i=1;i<=n;i++){
            if(i==judge) continue;
            else {
                if(!list.get(i).contains(judge)) return -1;
            }
        }
        return judge;*/


        //!Optimal
        int[] trusts=new int[n+1];
        int[] trustedby=new int[n+1];

        for(int i=0;i<trust.length;i++){
            trusts[trust[i][0]]=trusts[trust[i][0]]+1;
            trustedby[trust[i][1]]=trustedby[trust[i][1]]+1;
        }

        for(int i=1;i<=n;i++){
            if(trusts[i]==0 && trustedby[i]==n-1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] arr={{1,3},{2,3},{3,1}};
        int[][] arr1={{1,3},{2,3}};
        int[][] arr2={{1,2},{2,3}};
        System.out.println(new FindtheTownJudge().findJudge(3,arr));
        System.out.println(new FindtheTownJudge().findJudge(3,arr1));
        System.out.println(new FindtheTownJudge().findJudge(3,arr2));
    }
}
