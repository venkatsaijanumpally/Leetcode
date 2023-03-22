package LinkedList;

import LinkedList.Utils.SkiplistNode;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

//Ue DesignSkiplist1 for correct solution
public class DesignSkiplist {
    SkiplistNode head=null;
    SkiplistNode sentinel;
    final int MAXIMUM_LEVEL=8;
    int HeadLevel;
    public DesignSkiplist() {
        sentinel=new SkiplistNode(0);
        HeadLevel=1;
    }

    public boolean search(int target) {
        if(sentinel.next[0]==null) return false;

        int Level=HeadLevel-1;

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

    public void insert(int num){
        SkiplistNode temp=new SkiplistNode(num);
        int Level= getRandomLevel();
        SkiplistNode ptr=sentinel;

        Stack<SkiplistNode> stack=new Stack<>();
        while (Level>=0){
            if(ptr.next[Level]==null || ptr.next[Level].val>num){
                stack.push(ptr);
                Level--;
            }
            else ptr=ptr.next[Level];
        }
        Level=0;
        while (!stack.isEmpty()) {
            SkiplistNode prev=stack.pop();
            temp.next[Level]=prev.next[Level];
            prev.next[Level]=temp;
            Level++;
        }



        /*while (Level>=0){
            if(ptr.next[Level]==null || ptr.next[Level].val>num){
                temp.next[Level]=ptr.next[Level];
                ptr.next[Level]=temp;
                Level--;
            }
            else ptr=ptr.next[Level];
        }*/
        System.out.println("HEADLEVEL:"+HeadLevel);
    }

    private int getRandomLevel() {
        Random rand = new Random();
        int level=0;
        final int MaxLevel=HeadLevel<MAXIMUM_LEVEL?HeadLevel:MAXIMUM_LEVEL-1;
        while (rand.nextBoolean() && level<MaxLevel){
            level++;
        }
        if(level==HeadLevel)
            HeadLevel++;
        return level;
    }

    public void add(int num) {
        Random rand = new Random();
        boolean bool;
        //int Level=0;
        SkiplistNode temp=new SkiplistNode(num);
        /*do{
            SkiplistNode prev=sentinel;
            SkiplistNode next=sentinel.next[Level];
            while (next!=null && next.val<num){
                prev=prev.next[Level];
                next=next.next[Level];
            }

            prev.next[Level]=temp;
            temp.next[Level]=next;
            Level++;
            bool=rand.nextBoolean();
        } while (bool && Level<4);*/


        Stack<SkiplistNode> stack=new Stack<>();
        int Level= rand.nextInt(8);
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
        /*do {
            SkiplistNode prev=stack.pop();
            temp.next[Level]=prev.next[Level];
            prev.next[Level]=temp;
            Level++;
            bool=rand.nextBoolean();
        }while (bool && !stack.isEmpty());*/
        while (!stack.isEmpty()) {
            SkiplistNode prev=stack.pop();
            temp.next[Level]=prev.next[Level];
            prev.next[Level]=temp;
            Level++;
        }
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

    public boolean erase(int num) {
        //if(!search(num)) return false;

        boolean flag=false;
        int Level=HeadLevel-1;
        SkiplistNode ptr=sentinel;
        /*while (Level>=0){
            SkiplistNode prev=sentinel;
            SkiplistNode next=sentinel.next[Level];
            while (next !=null && next.val<num){
                prev=prev.next[Level];
                next=next.next[Level];
            }
            if(next!=null && next.val==num){
                prev.next[Level]=next.next[Level];
            }
            Level--;
        }*/

        while (Level>=0){
            if(ptr.next[Level]==null){
                Level--;
            }
            else if(ptr.next[Level].val==num){
                ptr.next[Level]=ptr.next[Level].next[Level];
                flag=true;
                if(ptr==sentinel && ptr.next[Level]==null)
                    HeadLevel--;
                Level--;
            }
            else if(ptr.next[Level].val>num){
                Level--;
            }
            else ptr=ptr.next[Level];
        }

        System.out.println("HeadLevel: "+HeadLevel);
        return flag;
    }


    public static void main(String[] args) {
        DesignSkiplist skiplistobject=new DesignSkiplist();
        /*skiplistobject.add(4);
        skiplistobject.add(1);
        skiplistobject.add(2);
        skiplistobject.add(3);*/
        //skiplistobject.add(4);
        //skiplistobject.add(5);
        /*skiplistobject.print();
        System.out.println(skiplistobject.search(3));
        System.out.println(skiplistobject.search(5));
        System.out.println(skiplistobject.erase(5));
        System.out.println(skiplistobject.erase(3));
        skiplistobject.print();*/



        /*skiplistobject.insert(0);
        skiplistobject.insert(5);
        skiplistobject.insert(2);
        skiplistobject.insert(1);
        skiplistobject.insert(4);
        skiplistobject.insert(7);
        skiplistobject.insert(8);
        skiplistobject.insert(10);
        skiplistobject.print();
        System.out.println(skiplistobject.search(0));
        System.out.println(skiplistobject.erase(5));
        System.out.println(skiplistobject.erase(12));
        skiplistobject.print();
        System.out.println(skiplistobject.search(2));
        System.out.println(skiplistobject.search(3));
        System.out.println(skiplistobject.search(2));
        skiplistobject.insert(3);
        skiplistobject.print();
        System.out.println(skiplistobject.erase(3));*/

        long start=System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            skiplistobject.getRandomLevel();
        }
        System.out.println(System.currentTimeMillis()-start);

        Scanner sc=new Scanner(System.in);
        System.out.println("1:Insert");
        System.out.println("2:Search");
        System.out.println("3:Erase");
        System.out.println("4:Print");
        System.out.println("5:Exit");
        while (true){
            System.out.print("Enter Option: ");
            int i=sc.nextInt();
            switch (i){
                case 1:{
                    System.out.print("Element to insert:");
                    int element=sc.nextInt();
                    skiplistobject.insert(element);
                    break;
                }
                case 2:{
                    System.out.print("Element to search:");
                    int element=sc.nextInt();
                    System.out.println(skiplistobject.search(element));
                    break;
                }
                case 3:{
                    System.out.print("Element to erase:");
                    int element=sc.nextInt();
                    System.out.println(skiplistobject.erase(element));
                    break;
                }
                case 4:{
                    skiplistobject.print();
                    break;
                }
                case 5:{
                    System.exit(0);
                }
            }
        }
    }
}
