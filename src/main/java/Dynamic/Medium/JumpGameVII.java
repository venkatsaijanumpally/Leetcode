package Dynamic.Medium;

import java.util.*;

public class JumpGameVII {

    public boolean canReach2(String s, int minJump, int maxJump){
        if(s.charAt(s.length()-1)=='1') return false;

        List<Integer> count=new ArrayList<>();
        count.add(0);
        for (int i=1;i<s.length();i++){
            if(s.charAt(i)=='0')
                count.add(i);
        }
        boolean[] reachable=new boolean[count.size()];
        reachable[0]=true;
        Queue<Integer> queue=new PriorityQueue<>((o1, o2) -> o2-o1);
        queue.add(0);
        int index,right,left,rightIndex;
        while (!queue.isEmpty() && !reachable[count.size()-1]){
            index= queue.poll();
            left = count.get(index);
            rightIndex=index+1;
            while (rightIndex< count.size()){
                right=count.get(rightIndex);
                if(!reachable[rightIndex]){
                    if(right-left>maxJump)
                        break;

                    if(right-left<minJump){
                        rightIndex++;
                        continue;
                    }
                    reachable[rightIndex]=true;
                    queue.add(rightIndex);
                }
                rightIndex++;
            }
        }

        return reachable[count.size()-1];
    }

    public boolean canReach3(String s, int minJump, int maxJump){
        if(s.charAt(s.length()-1)=='1') return false;

        List<Integer> count=new ArrayList<>();
        count.add(0);
        for (int i=1;i<s.length();i++){
            if(s.charAt(i)=='0')
                count.add(i);
        }
        boolean[] reachable=new boolean[count.size()];
        reachable[0]=true;
        Deque<Integer> deque=new ArrayDeque<>();
        deque.addLast(0);

        int index,left,right;
        for (int rightIndex=1;rightIndex<count.size()&&!deque.isEmpty()&&!reachable[count.size()-1];rightIndex++){
            right=count.get(rightIndex);
            while (!deque.isEmpty()){
                index = deque.peekFirst();
                left = count.get(index);
                if(right-left<=maxJump) {
                    if(right-left>=minJump){
                        reachable[rightIndex]=true;
                        deque.addLast(rightIndex);
                    }
                    break;
                }
                deque.pollFirst();
            }
        }
        return reachable[count.size()-1];
    }

    public boolean canReach4(String s, int minJump, int maxJump){
        if(s.charAt(s.length()-1)=='1') return false;

        boolean[] reachable=new boolean[s.length()];
        reachable[0]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        int left;
        for (int right=1;right<s.length()&&!queue.isEmpty();right++){
            if(s.charAt(right)=='0'){
                while (!queue.isEmpty()){
                    left = queue.peek();
                    if(right-left<=maxJump) {
                        if(right-left>=minJump){
                            reachable[right]=true;
                            queue.add(right);
                        }
                        break;
                    }
                    queue.poll();
                }
            }
        }

        return reachable[reachable.length-1];
    }

    public static void main(String[] args) {
        //System.out.println(new JumpGameVII().canReach2("011010",2,3));
        //System.out.println(new JumpGameVII().canReach2("01101110",2,3));
        System.out.println(new JumpGameVII().canReach3("0000000000",8,8));
        System.out.println(new JumpGameVII().canReach4("0000000000",8,8));
    }
}
