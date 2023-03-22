package BackTracking.Medium;

import java.util.*;

public class ClosestDessertCost {
    int closestVal;
    //https://leetcode.com/problems/closest-dessert-cost/discuss/1085792/Java-Backtracking
    //!Optimal
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        closestVal=baseCosts[0];
        closestCost(baseCosts,0,target,toppingCosts);
        return closestVal;
    }

    public boolean closestCost(int[] baseCosts, int index, int target, int[] toppingCosts){
        if(index==baseCosts.length)
            return false;

        for(int i=0;i<baseCosts.length;i++){
            closestToppingCost(baseCosts[i],0,target,toppingCosts);
        }
        return false;
    }

    private void closestToppingCost(int totalCost, int index, int target, int[] toppingCosts) {
        if(Math.abs(target-totalCost)<Math.abs(target-closestVal) ||
                ( Math.abs(target-totalCost)==Math.abs(target-closestVal) && totalCost<closestVal))
            closestVal=totalCost;

        if(index==toppingCosts.length || totalCost>closestVal) return;

        closestToppingCost(totalCost,index+1,target,toppingCosts);
        closestToppingCost(totalCost+toppingCosts[index],index+1,target,toppingCosts);
        closestToppingCost(totalCost+2*toppingCosts[index],index+1,target,toppingCosts);
        /*if(index==toppingCosts.length){
            if(totalCost==target){
                closestVal=target;
                return true;
            }
            if(Math.abs(totalCost-target)<Math.abs(target-closestVal))
                closestVal=totalCost;
            return false;
        }

        if(totalCost==target){
            closestVal=target;
            return true;
        }
        else if(Math.abs(totalCost-target)==Math.abs(target-closestVal))
            if(totalCost<closestVal)
                closestVal=totalCost;
        else if(Math.abs(totalCost-target)<Math.abs(target-closestVal))
            closestVal=totalCost;
        else if(totalCost>target)
            return false;

        for(int i=0;i<=2;i++){
            boolean b=closestToppingCost(totalCost,index+1,target,toppingCosts);
            if(b) return true;
            totalCost+=toppingCosts[index];
        }
        return false;*/
    }




    //Not Working
    public int closestCostDP(int[] baseCosts, int[] toppingCosts, int target){
        SortedSet<Integer> s=new TreeSet<>();
        findAllToppingsSum(toppingCosts,0,s,0);
        int[] toppingSums=new int[s.size()];
        int index=0;
        for(Integer i: s)
            toppingSums[index++]=i;
        searchClosest(baseCosts,toppingSums,target);
        return closestVal;
    }

    private void searchClosest(int[] baseCosts,int[] toppingSums, int target) {
        closestVal=baseCosts[0];
        for(int i=0;i< baseCosts.length;i++){
            if(baseCosts[i]<target){
                int[] diff=binarySearchClosest(toppingSums,target-baseCosts[i],0,toppingSums.length-1);
                if(Math.abs(baseCosts[i]+diff[0]-target)==Math.abs(closestVal-target) && closestVal>baseCosts[i]+diff[0])
                    closestVal=baseCosts[i]+diff[0];
                else if(Math.abs(baseCosts[i]+diff[0]-target)<Math.abs(closestVal-target))
                    closestVal=baseCosts[i]+diff[0];
                if(Math.abs(baseCosts[i]+diff[1]-target)<Math.abs(closestVal-target))
                    closestVal=baseCosts[i]+diff[1];
            }
            else {
                if(Math.abs(baseCosts[i]-target)<Math.abs(closestVal-target))
                    closestVal=baseCosts[i];
            }
        }
    }

    public int[] binarySearchClosest(int[] toppingSums, int diff, int low, int high) {
        int[] ans=new int[2];
        int tempLow=low;
        int tempHigh=high;
        while (low<=high){
            int mid=low+(high-low)/2;

            if(diff>=toppingSums[mid]){
                ans[0]=toppingSums[mid];
                low=mid+1;
            }
            else high=mid-1;
        }
        low=tempLow;
        high=tempHigh;
        while (low<=high){
            int mid=low+(high-low)/2;

            if(diff<=toppingSums[mid]){
                ans[1]=toppingSums[mid];
                high=mid-1;
            }
            else low=mid+1;
        }
        return ans;
    }

    private void findAllToppingsSum(int[] toppingCosts, int index, SortedSet<Integer> set, int sum) {
        if(index==toppingCosts.length){
            set.add(sum);
            return;
        }

        for(int i=0;i<=2;i++){
            findAllToppingsSum(toppingCosts,index+1,set,sum);
            sum+=toppingCosts[index];
        }
    }

    public static void main(String[] args) {
        int[] baseCosts={1,7};
        int[] toppingCosts={3,4};
        int[] baseCosts2={2,3};
        int[] toppingCosts2={4,5,100};
        int[] baseCosts3={3,10};
        int[] toppingCosts3={2,5};
        int[] baseCosts4={1};
        int[] toppingCosts4={8,10};
        int[] baseCosts5={4};
        int[] toppingCosts5={9};
        int[] baseCosts6={8,4,4,5,8};
        int[] toppingCosts6={3,10,9,10,8,10,10,9,3};
        System.out.println(new ClosestDessertCost().closestCost(baseCosts,toppingCosts,10));
        System.out.println(new ClosestDessertCost().closestCost(baseCosts2,toppingCosts2,18));
        System.out.println(new ClosestDessertCost().closestCost(baseCosts3,toppingCosts3,9));
        System.out.println(new ClosestDessertCost().closestCost(baseCosts4,toppingCosts4,10));

        System.out.println(new ClosestDessertCost().closestCost(baseCosts2,toppingCosts2,10));
        System.out.println(new ClosestDessertCost().closestCost(baseCosts5,toppingCosts5,9));
        System.out.println(new ClosestDessertCost().closestCost(baseCosts6,toppingCosts6,6));
        //System.out.println();
    }
}
