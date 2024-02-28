package Heap.Hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsIII {
    /*public int mostBooked(int n, int[][] meetings) {
        if(n==1) return 0;

        int maxRoomNo=0;
        long[] t=new long[n];
        int[] noOfMeetings=new int[n];
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return t[o1]!=t[o2]? (int) (t[o1] - t[o2]) :o1-o2;
            }
        });
        PriorityQueue<int[]> orderedMeeting=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        for (int i=0;i<n;i++)
            queue.add(i);
        for (int[] i: meetings)
            orderedMeeting.add(i);

        while (!orderedMeeting.isEmpty()){
            int[] meeting = orderedMeeting.poll();
            int meetingRoom = queue.poll();
            while (t[meetingRoom]<meeting[0]){
                t[meetingRoom]=meeting[0];
                queue.add(meetingRoom);
                meetingRoom = queue.poll();
            }
            t[meetingRoom] = Math.max(t[meetingRoom],meeting[0]) + meeting[1]-meeting[0];
            noOfMeetings[meetingRoom]++;
            queue.add(meetingRoom);
            if(noOfMeetings[meetingRoom]>noOfMeetings[maxRoomNo]){
                maxRoomNo=meetingRoom;
            }
            else if(noOfMeetings[meetingRoom]==noOfMeetings[maxRoomNo]){
                maxRoomNo=Math.min(meetingRoom,maxRoomNo);
            }
        }
        return maxRoomNo;
    }*/

    public int mostBooked(int n, int[][] meetings) {
        if(n==1) return 0;

        int maxRoomNo=0;
        long[] t=new long[n];
        int[] noOfMeetings=new int[n];
        PriorityQueue<Integer> queue=new PriorityQueue<>((o1, o2) -> t[o1]!=t[o2]? (int) (t[o1] - t[o2]) :o1-o2);
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));

        for (int i=0;i<n;i++)
            queue.add(i);

        for (int[] meeting: meetings){
            int meetingRoom = queue.poll();
            while (t[meetingRoom]<meeting[0]){
                t[meetingRoom]=meeting[0];
                queue.add(meetingRoom);
                meetingRoom = queue.poll();
            }
            t[meetingRoom] += meeting[1]-meeting[0];
            noOfMeetings[meetingRoom]++;
            queue.add(meetingRoom);
            if(noOfMeetings[meetingRoom]>=noOfMeetings[maxRoomNo]){
                maxRoomNo = noOfMeetings[meetingRoom]!=noOfMeetings[maxRoomNo] ? meetingRoom : Math.min(meetingRoom,maxRoomNo);
            }
        }
        return maxRoomNo;
    }

    public static void main(String[] args) {
        int[][] meetings1={{0,10},{1,5},{2,7},{3,4}};
        int[][] meetings2={{1,20},{2,10},{3,5},{4,9},{6,8}};
        int[][] meetings3={{18,19},{3,12},{17,19},{2,13},{7,10}};
        System.out.println(new MeetingRoomsIII().mostBooked(4,meetings3));
        System.out.println(new MeetingRoomsIII().mostBooked(2,meetings1));
        System.out.println(new MeetingRoomsIII().mostBooked(3,meetings2));
    }
}
