package Dynamic.Medium;

public class PushDominoes {
    /*
     * Steps:
     *      - Construct previousRight array and nextLeft array. At a particular index previousRight shows the index of
     *        of previous right seen similarly nextLeft shows the index of next Left element from current index.
     *      - Rules for constructing previousRight
     *        a. If the current element is 'R' previousRight[i]=-2 and save index in prevRight
     *        b. If the current element is '.' previousRight[i] is set to prevRight which tracks the last seen right char.
     *        c. If the current element is 'L' previousRight[i] is set as -1 and last seen right(prevRight) set to -1
     *           which means not available.
     *        Similarly we construct nextLeft.
     *
     *      - To find the result. Traverse index through 0 to length
     *        a. If nextLeft[i] and previousRight[i] are -1 which means there is no domino that will effect this char.
     *        b. If previousRight[i] or nextLeft[i] one of them is -1 then there is a one sided force.
     *        c. If previousRight[i] or nextLeft[i] one of them is -2 then the current domino itself is directed in a
     *           particular direction i.e if charAt[3]='L' then nextLeft[3] will be set as -2 which is how we created nextLeft.
     *        d. If both have some values then we have 2 possibilities. If the number of elements between L domino and
     *           'R' domino is even then there wont be middle element and all elements between L domino and R domino will
     *            move in the direction of nearest domino. Else if number of elements is ODD then we will have a 'mid'
     *            element which does not get effected by either forces.
     */
    public String pushDominoes(String dominoes) {
        char[] dominoesChar=dominoes.toCharArray();
        int[] left=new int[dominoes.length()];
        int[] right=new int[dominoes.length()];

        int nextLeft=-1;
        for(int i=dominoes.length()-1;i>=0;i--){
            if(dominoes.charAt(i)=='R'){
                left[i]=-1;
                nextLeft=-1;
            }
            else if(dominoes.charAt(i)=='.')
                left[i]=nextLeft;
            else {
                left[i]=-2;
                nextLeft=i;
            }
        }

        int prevRight=-1;
        for(int i=0;i<dominoes.length();i++){
            if(dominoes.charAt(i)=='L'){
                right[i]=-1;
                prevRight=-1;
            }
            else if(dominoes.charAt(i)=='.')
                right[i]=prevRight;
            else {
                right[i]=-2;
                prevRight=i;
            }
        }

        int i=0;
        for(;i<dominoes.length();i++){
            if(left[i]==-1 && right[i]==-1) continue;
            else if(right[i]==-1 || left[i]==-2) dominoesChar[i]='L';
            else if(left[i]==-1 || right[i]==-2) dominoesChar[i]='R';
            else{
                if((left[i]-right[i])%2!=0){
                    if(i<=right[i]+(left[i]-right[i])/2) dominoesChar[i]='R';
                    else dominoesChar[i]='L';
                }
                else {
                    int mid=right[i]+(left[i]-right[i])/2;
                    if(i==mid) continue;
                    else if(i<mid) dominoesChar[i]='R';
                    else dominoesChar[i]='L';
                }
            }
        }

        return String.valueOf(dominoesChar);
    }

    public static void main(String[] args) {
        System.out.println(new PushDominoes().pushDominoes(".L.R...LR..L.."));
        System.out.println(new PushDominoes().pushDominoes("RR.L"));
        System.out.println(new PushDominoes().pushDominoes("R...LLLLLR.RL"));
    }
}
