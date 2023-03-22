package LinkedList;

import LinkedList.Utils.SkiplistNode;

import java.util.Random;
import java.util.Stack;

public class DesignSkiplist1{
    SkiplistNode sentinel;
    int HeadLevel;
    public DesignSkiplist1() {
        sentinel=new SkiplistNode(0);
        HeadLevel=1;
    }

    public boolean search(int target) {
        if(sentinel.next[0]==null) return false;

        int Level=HeadLevel;
        SkiplistNode ptr=sentinel;
        while (Level>=0){
            if(ptr.next[Level] ==null){
                Level--;
                continue;
            }
            else if(ptr.next[Level].val==target)
                return true;
            else if(ptr.next[Level].val>target){
                Level--;
                continue;
            }
            ptr=ptr.next[Level];
        }
        return false;
    }

    public void add(int num) {
        Random rand = new Random();
        boolean bool;
        SkiplistNode temp=new SkiplistNode(num);

        Stack<SkiplistNode> stack=new Stack<>();
        int Level=Math.min(HeadLevel,7);
        SkiplistNode ptr=sentinel;

        while (Level>=0){
            if(ptr.next[Level]==null){
                stack.push(ptr);
                Level--;
            }
            else if(ptr.next[Level].val>num){
                stack.push(ptr);
                Level--;
            }
            else ptr=ptr.next[Level];
        }

        Level=0;
        do {
            SkiplistNode prev=stack.pop();
            temp.next[Level]=prev.next[Level];
            prev.next[Level]=temp;
            Level++;
            bool=rand.nextBoolean();
        }while (bool && !stack.isEmpty() && Level<HeadLevel);

        if(Level==HeadLevel) HeadLevel++;
    }

    public boolean erase(int num) {
        boolean flag=false;
        int Level=HeadLevel-1;
        SkiplistNode ptr=sentinel;
        while (Level>=0){
            if(ptr.next[Level]==null){
                Level--;
            }
            else if(ptr.next[Level].val==num){
                ptr.next[Level]=ptr.next[Level].next[Level];
                Level--;
                flag=true;
            }
            else if(ptr.next[Level].val>num){
                Level--;
            }
            else ptr=ptr.next[Level];
        }
        return flag;
    }

    public void print(){
        int level=0;
        SkiplistNode ptr;
        while (level<8){
            ptr=sentinel.next[level];
            System.out.print("Level "+(level+1)+"\t");
            while (ptr !=null){
                System.out.print(ptr.val+"-->");
                ptr=ptr.next[level];
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        DesignSkiplist1 skiplistobject=new DesignSkiplist1();
        skiplistobject.add(0);
        skiplistobject.add(5);
        skiplistobject.add(2);
        skiplistobject.add(1);
        skiplistobject.add(4);
        skiplistobject.add(7);
        skiplistobject.add(8);
        skiplistobject.add(10);
        skiplistobject.print();
        System.out.println(skiplistobject.search(0));
        System.out.println(skiplistobject.erase(5));
        System.out.println(skiplistobject.erase(12));
        skiplistobject.print();
        System.out.println(skiplistobject.search(2));
        System.out.println(skiplistobject.search(3));
        System.out.println(skiplistobject.search(2));
        skiplistobject.add(3);
        skiplistobject.print();
        System.out.println(skiplistobject.erase(3));
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
