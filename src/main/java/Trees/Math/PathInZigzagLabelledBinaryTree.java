package Trees.Math;

import java.util.ArrayList;
import java.util.List;

public class PathInZigzagLabelledBinaryTree {
    /**
     *!Optimal Solution
     * https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/discuss/323312/Simple-solution-in-java-(Using-properties-of-complete-binary-tree)-(O-log-N)
     * https://daniel-leskosky.medium.com/path-in-zigzag-labelled-binary-tree-4a38f5f04740
     * If suppose the tree is not in zigzag then we find the parent of a label by dividing it with 2 and so on till root.
     * We apply the same logic here but before we divide the element we find the mirror element on the other side and
     * divide it with 2 to get the parent.
     * Example
     *                  1
     *                 / \
     *                /   \
     *               /     \
     *              3         2
     *             / \       / \
     *            /   \     /   \
     *           4     5   6     7
     *          /\    /\   /\    /\
     *        15 14 13 12 11 10 9 8
     *  For the element 14 the mirror is 9. We try to find out 9 and 9/2=4 4 is the parent of 14.
     */
    public List<Integer> pathInZigZagTreeMethod2(int label){
        List<Integer> list=new ArrayList<>();
        list.add(label);
        int parent=label;

        while (parent!=1){
            int depth=(int)(Math.log(parent)/Math.log(2));
            int offset= (int) (Math.pow(2,depth+1)-1-parent);
            parent= (int) ((Math.pow(2,depth)+offset)/2); //Finding mirror and divide by 2
            list.add(0,parent);
        }
        return list;
    }


    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> list=new ArrayList<>();
        /*int max=1,min=1,level=0;
        while (label>max){
            level++;
            max*=2;
        }
        min=max/2;*/
        int totalLevels=0;
        while (label!=0){
            totalLevels++;
            list.add(0,label);
            label=label/2;
        }
        System.out.println(list);
        boolean evenLevel=totalLevels%2==0;
        int level=0;
        int max=1,min;
        for(int i=0;i<list.size();i++){
            min=max;
            max*=2;
            if(evenLevel && level%2==0){
                int element;
                int item=list.get(i);
                if(item>=(min+max)/2)
                    element=min+max-1-item;
                else
                    element=max-1-(item-min);
                list.set(i,element);
            }
            else if(!evenLevel && level%2!=0){
                int element;
                int item=list.get(i);
                if(item>=(min+max)/2)
                    element=min-(item-max+1);
                else
                    element=max-1-(item-min);
                list.set(i,element);
            }
            level++;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PathInZigzagLabelledBinaryTree().pathInZigZagTreeMethod2(12));
    }
}
