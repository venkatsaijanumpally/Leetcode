package Trees.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortItemsbyGroupsRespectingDependencies {
    ArrayList<ArrayList<Integer>> ItemOrdering=new ArrayList<>();
    ArrayList<Integer> groupOrdering=new ArrayList<>();
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        for (int i=0;i<n;i++){
            if(group[i]==-1)
                group[i]=m++;
        }
        for(int i=0;i<m;i++){
            ItemOrdering.add(new ArrayList<>());
            groupOrdering.add(i);
        }
        for(int i=0;i<n;i++){
            ItemOrdering.get(group[i]).add(i);
        }
        for(int i=0;i<n;i++){
            for(int beforeItem:beforeItems.get(i)){
                int beforeGroup=group[beforeItem];
                if(group[i]==beforeGroup)
                    arrangeItemsInAGroup(beforeGroup,i,beforeItem);
                else arrangeGroups(beforeGroup,group[i]);
            }
        }
        int index=0;
        int[] result=new int[n];
        for (int groupNum: groupOrdering){
            for(int groupItem:ItemOrdering.get(groupNum)){
                result[index++]=groupItem;
            }
        }
        return result;
    }

    private void arrangeGroups(int beforeGroup, int afterGroup) {
        int indexBefore=groupOrdering.indexOf(beforeGroup);
        int indexAfter=groupOrdering.indexOf(afterGroup);
        if(indexBefore<indexAfter) return;

        groupOrdering.remove(indexBefore);
        groupOrdering.add(indexAfter,beforeGroup);
    }

    private void arrangeItemsInAGroup(int group, int afterItem, int beforeItem) {
        int indexAfter=ItemOrdering.get(group).indexOf(afterItem);
        int indexBefore=ItemOrdering.get(group).indexOf(beforeItem);
        if(indexBefore<indexAfter) return;

        ItemOrdering.get(group).remove(indexBefore);
        ItemOrdering.get(group).add(indexAfter,beforeItem);
    }

    public static void main(String[] args) {
        int[] group1={-1,-1,1,0,0,1,0,-1};
        List<List<Integer>> beforeItems=new ArrayList<>();
        for(int i=0;i<8;i++)beforeItems.add(new ArrayList<>());
        beforeItems.get(1).add(6);
        beforeItems.get(2).add(5);
        beforeItems.get(3).add(6);
        beforeItems.get(4).add(3);
        beforeItems.get(4).add(6);
        System.out.println(Arrays.toString(new SortItemsbyGroupsRespectingDependencies().sortItems(8, 2, group1, beforeItems)));
    }
}
