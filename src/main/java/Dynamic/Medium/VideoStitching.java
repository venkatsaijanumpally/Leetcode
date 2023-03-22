package Dynamic.Medium;

import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching {
    /*
     * prevStart and prevEnd used to track last selected clip
     * end is used to track the maximum Start we can push for last clip.
     * example: [0,4],[2,6],[4,7]
     *      At index 0 we take [0,4] and end set to 4.
     *      when at index 1 we take [2,6] but we can take a clip that has Start <=4 and end is still 4
     *      when at index 2 we see that we can ignore [2,6] and take [4,7] since the Start<=4 so [4,7] is taken and end=7
     */
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o2[1]-o1[1];
                else return o1[0]-o2[0];
            }
        });
        System.out.println(Arrays.deepToString(clips));

        int prevStart=-1,prevEnd=0;
        int end=0;
        int count=0;
        boolean update=false;
        for(int i=0;i< clips.length;i++){
            if(prevEnd>=time || clips[i][0]>prevEnd){
                break;
            }
            else if(prevStart==clips[i][0])
                continue;
            else if(clips[i][1]>prevEnd){
                if(clips[i][0]==end){
                    end=clips[i][1];
                    System.out.println("end:"+end);
                    count++;
                    update=false;
                }
                else if(end<clips[i][0]) {
                    count+=2;
                    end=Math.max(prevEnd,clips[i][0]);
                    System.out.println("end="+end);
                    update=false;
                }
                else {
                    update=true;
                }
                prevStart=clips[i][0];
                prevEnd=clips[i][1];
            }
        }

        if(prevEnd<time)return -1;
        if(update) count++;
        return count;
    }

    public static void main(String[] args) {
        int[][] clips1={{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int[][] clips2={{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        int[][] clips3={{0,1},{1,2}};
        int[][] clips4={{0,4},{3,8},{8,9}};
        System.out.println(new VideoStitching().videoStitching(clips1,10));
        System.out.println(new VideoStitching().videoStitching(clips2,9));
        System.out.println(new VideoStitching().videoStitching(clips3,3));
        System.out.println(new VideoStitching().videoStitching(clips4,9));
    }
}
