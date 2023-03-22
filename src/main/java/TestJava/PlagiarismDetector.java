package TestJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlagiarismDetector {

    public PlagiarismDetector(){}

    public PlagiarismDetector(String path1, String path2) throws IOException {

    }

    public void algorithm1(String path1, String path2) throws IOException {
        Path filePath = Path.of(path1);
        String text1 = Files.readString(filePath);
        Path filePath2 = Path.of(path2);
        String text2 = Files.readString(filePath2);

        text1 = process(text1);
        text2 = process(text2);

        //System.out.println(text1+"\n\n"+text2+"\n");

        String[] text1Chunks=text1.split(" ");
        String[] text2Chunks=text2.split(" ");

        //System.out.println("Text 1 length: "+text1Chunks.length);
        //System.out.println("\nText 2 length: "+text2Chunks.length);
        System.out.println();

        HashMap<String,Integer> text2ChunkMap = new HashMap<>();
        for(int i=0;i< text2Chunks.length;i++){
            //if(!text2ChunkMap.containsKey(text2Chunks[i]))
            //    text2ChunkMap.put(text2Chunks[i],1);
            text2ChunkMap.put(text2Chunks[i],text2ChunkMap.getOrDefault(text2Chunks[i],0)+1);
        }

        int count=0;
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<text1Chunks.length;i++){
            RabinKarp compare=new RabinKarp();
            boolean success=compare.search(text2,text1Chunks[i]);
            //System.out.println(text1Chunks[i]);
            if(success && text2ChunkMap.containsKey(text1Chunks[i]) && text2ChunkMap.get(text1Chunks[i])>0){
                count++;
                text2ChunkMap.put(text1Chunks[i],text2ChunkMap.get(text1Chunks[i])-1);
                list.add(text1Chunks[i]);
            }
        }
        System.out.println(count);
        System.out.println(list);

        System.out.println();

        float similarity=(float)count*100/text1Chunks.length;
        System.out.println("SIMILARITY:"+similarity);
    }

    public void algorithm2(String path1, String path2) throws IOException {
        Path filePath = Path.of(path1);
        String text1 = Files.readString(filePath);
        Path filePath2 = Path.of(path2);
        String text2 = Files.readString(filePath2);

        algorithm2Logic(text1,text2);
    }

    private void algorithm2Logic(String text1, String text2) {
        long startTime = System.nanoTime();
        text1 = process2(text1);
        text2 = process2(text2);
        System.out.println();

        System.out.println(findAllSubstrings2(text1,text2));
        long endTime   = System.nanoTime();
        System.out.println("Time Elapsed: "+(endTime-startTime)/Math.pow(10,9));
    }

    private List<String> findAllSubstrings(String s1, String s2) {
        List<String> res = new ArrayList<>();
        String[] text1Chunks = s1.split(" "), text2Chunks = s2.split(" ");
        int[][] dp = new int[text1Chunks.length+1][text2Chunks.length+1];
        for(int i=1;i<=text1Chunks.length;i++) {
            for(int j=1;j<=text2Chunks.length;j++) {
                if(text1Chunks[i-1].equals(text2Chunks[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        int count=0;
        for(int i=dp.length - 1;i>=0;i--) {
            for(int j=dp[0].length-1;j>=0;j--) {
                StringBuilder sb = new StringBuilder();
                if(dp[i][j] >= 3) {
                    count+=dp[i][j];
                    int ci = i, cj = j;
                    while(dp[ci][cj] > 0) {
                        dp[ci][cj] = 0;
                        sb.insert(0, text1Chunks[ci-1] + " ");
                        ci--;cj--;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                }
            }
        }
        float similarity1=(float)count*100/ text1Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 1: " +similarity1);
        float similarity2=(float)count*100/ text2Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 2: " +similarity2);
        System.out.println("Count:"+count);
        return res;
    }

    private List<String> findAllSubstrings2(String s1, String s2) {
        List<String> res = new ArrayList<>();
        String[] text1Chunks = s1.split(" "), text2Chunks = s2.split(" ");
        int[][] dp = new int[text1Chunks.length+1][text2Chunks.length+1];

        HashMap<String,Integer> text2ChunkMap = new HashMap<>();

        for(int i=1;i<=text1Chunks.length;i++) {
            for(int j=1;j<=text2Chunks.length;j++) {
                if(text1Chunks[i-1].equals(text2Chunks[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                }else {
                    dp[i][j] = 0;
                }
            }
        }

        boolean[] visited1=new boolean[text1Chunks.length+1];
        boolean[] visited2=new boolean[text2Chunks.length+1];
        int count1=0;
        int count2=0;
        for(int i=dp.length - 1;i>=0;i--) {
            for(int j=dp[0].length-1;j>=0;j--) {
                StringBuilder sb = new StringBuilder();
                if(dp[i][j] >= 3) {
                    int ci = i, cj = j;
                    while(dp[ci][cj] > 0) {
                        dp[ci][cj] = 0;
                        sb.insert(0, text1Chunks[ci-1] + " ");
                        if(!visited1[ci]){visited1[ci]=true;count1++;}
                        if(!visited2[cj]){visited2[cj]=true;count2++;}
                        ci--;cj--;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                }
            }
        }
        float similarity1=(float)count1*100/ text1Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 1: " +similarity1);
        float similarity2=(float)count2*100/ text2Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 2: " +similarity2);
        System.out.println("Count 1: "+count1+" Count 2: "+count2);
        return res;
    }

    private String process(String text) {
        String newString=text.replaceAll("\n"," ");
        newString=newString.replaceAll("  "," ");
        newString=newString.replaceAll(",","");
        newString=newString.replaceAll("\\.","");
        return newString;
    }

    private String process2(String text) {
        String newString=text.replaceAll("\n"," ");
        newString=newString.replaceAll("  "," ");
        newString=newString.replaceAll(",","");
        newString=newString.replaceAll("\\.","");
        newString=newString.replaceAll("\"","");
        newString=newString.replaceAll("\\(","");
        newString=newString.replaceAll("\\)","");
        newString=newString.toLowerCase();
        return newString;
    }

    public static void main(String[] args) throws IOException {
        String text="AAAABCDCCBABCDAABDCABCD";
        String pattern="ABCD";
        System.out.println(new RabinKarp().search(text,pattern));

        String path1="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/okay01/1.txt";
        String path2="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/okay01/2.txt";
        String path3="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism01/1.txt";
        String path4="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism01/2.txt";
        String path5="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism06/1.txt";
        String path6="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism06/2.txt";
        String path7="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism05/1.txt";
        String path8="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism05/2.txt";
        String path9="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test/1.txt";
        String path10="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test/2.txt";
        String path11="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test2/1.txt";
        String path12="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test2/2.txt";
        //new PlagiarismDetector().algorithm1(path1,path2);
        //new PlagiarismDetector().algorithm1(path3,path4);
        //new PlagiarismDetector().algorithm1(path5,path6);
        System.out.println();
        System.out.println();
        //new PlagiarismDetector().algorithm1(path7,path8);

        /*new PlagiarismDetector().algorithm2(path1,path2);
        new PlagiarismDetector().algorithm2(path3,path4);
        new PlagiarismDetector().algorithm2(path5,path6);
        new PlagiarismDetector().algorithm2(path7,path8);
        new PlagiarismDetector().algorithm2(path9,path10);
        new PlagiarismDetector().algorithm2(path11,path12);*/

        String pathTemplate="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/okay0";
        for(int i=1;i<=6;i++){
            new PlagiarismDetector().algorithm2(pathTemplate+i+"/1.txt",pathTemplate+i+"/2.txt");
        }

        String pathTemplate2="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism0";
        for(int i=1;i<=6;i++){
            new PlagiarismDetector().algorithm2(pathTemplate2+i+"/1.txt",pathTemplate2+i+"/2.txt");
        }
        new PlagiarismDetector().algorithm2Logic(SampleStringPlagiarism.s,SampleStringPlagiarism.s2);

        String pathTestTemplate="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test";
        new PlagiarismDetector().algorithm2(pathTestTemplate+"3/1.txt",pathTestTemplate+"3/2.txt");
        new PlagiarismDetector().algorithm2(pathTestTemplate+"4/1.txt",pathTestTemplate+"4/2.txt");


        for(int i=1;i<=6;i++){
            new PlagiarismDetector().algorithm3(pathTemplate+i+"/1.txt",pathTemplate+i+"/2.txt");
        }
        for(int i=1;i<=6;i++){
            new PlagiarismDetector().algorithm3(pathTemplate2+i+"/1.txt",pathTemplate2+i+"/2.txt");
        }
        new PlagiarismDetector().algorithm3Logic(SampleStringPlagiarism.s,SampleStringPlagiarism.s2);
        new PlagiarismDetector().algorithm3(pathTestTemplate+"3/1.txt",pathTestTemplate+"3/2.txt");
        new PlagiarismDetector().algorithm3(pathTestTemplate+"4/1.txt",pathTestTemplate+"4/2.txt");
    }




    private String process3(String text) {

        String newString=text.replaceAll("\n"," ");
        newString=newString.replaceAll("  "," ");
        //newString=newString.replaceAll("[,\\.\"\\(\\)]","");
        newString=newString.replaceAll(",","");
        newString=newString.replaceAll("\\.","");
        newString=newString.replaceAll("\"","");
        newString=newString.replaceAll("\\(","");
        newString=newString.replaceAll("\\)","");
        newString=newString.toLowerCase();
        return newString;
    }

    private List<String> findAllSubstrings3(String s1, String s2) {
        List<String> res = new ArrayList<>();
        String[] text1Chunks = s1.split(" "), text2Chunks = s2.split(" ");
        int[][] dp = new int[text1Chunks.length+1][text2Chunks.length+1];

        HashMap<String,Integer> text2ChunkMap = new HashMap<>();

        for(int i=1;i<=text1Chunks.length;i++) {
            for(int j=1;j<=text2Chunks.length;j++) {
                if(text1Chunks[i-1].equals(text2Chunks[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                }else {
                    dp[i][j] = 0;
                }
            }
        }

        boolean[] visited1=new boolean[text1Chunks.length+1];
        boolean[] visited2=new boolean[text2Chunks.length+1];
        int count1=0;
        int count2=0;
        for(int i=0;i < dp.length;i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<dp[0].length;j++) {
                sb.insert(0, text1Chunks[j]);
                if(dp[i][j] >= 3) {
                    int ci = i, cj = j;
                    while(dp[ci][cj] > 0) {
                        dp[ci][cj] = 0;
                        sb.insert(0, text1Chunks[ci-1] + " ");
                        if(!visited1[ci]){visited1[ci]=true;count1++;}
                        if(!visited2[cj]){visited2[cj]=true;count2++;}
                        ci--;cj--;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                }
            }
        }
        float similarity1=(float)count1*100/ text1Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 1: " +similarity1);
        float similarity2=(float)count2*100/ text2Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 2: " +similarity2);
        System.out.println("Count 1: "+count1+" Count 2: "+count2);
        return res;
    }


    public void algorithm3(String path1, String path2) throws IOException {
        Path filePath = Path.of(path1);
        String text1 = Files.readString(filePath);
        Path filePath2 = Path.of(path2);
        String text2 = Files.readString(filePath2);

        algorithm3Logic(text1,text2);
    }

    private void algorithm3Logic(String text1, String text2) {
        long startTime = System.nanoTime();
        text1 = process2(text1);
        text2 = process2(text2);
        System.out.println();

        findAllSubstrings4(text1,text2);
        long endTime   = System.nanoTime();
        System.out.println("Time Elapsed: "+(endTime-startTime)/Math.pow(10,9));
    }


    private void findAllSubstrings4(String s1, String s2) {
        //List<String> res = new ArrayList<>();
        String[] text1Chunks = s1.split(" "), text2Chunks = s2.split(" ");
        int[][] dp = new int[text1Chunks.length+1][text2Chunks.length+1];

        //HashMap<String,Integer> text2ChunkMap = new HashMap<>();

        for(int i=1;i<=text1Chunks.length;i++) {
            for(int j=1;j<=text2Chunks.length;j++) {
                if(text1Chunks[i-1].equals(text2Chunks[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                }else {
                    dp[i][j] = 0;
                }
            }
        }

        boolean[] visited1=new boolean[text1Chunks.length+1];
        boolean[] visited2=new boolean[text2Chunks.length+1];
        int count1=0;
        int count2=0;
        for(int i=dp.length - 1;i>=0;i--) {
            for(int j=dp[0].length-1;j>=0;j--) {
                //StringBuilder sb = new StringBuilder();
                if(dp[i][j] >= 3) {
                    int ci = i, cj = j;
                    while(dp[ci][cj] > 0) {
                        dp[ci][cj] = 0;
                        //sb.insert(0, text1Chunks[ci-1] + " ");
                        if(!visited1[ci]){visited1[ci]=true;count1++;}
                        if(!visited2[cj]){visited2[cj]=true;count2++;}
                        ci--;cj--;
                    }
                    //sb.deleteCharAt(sb.length() - 1);
                    //res.add(sb.toString());
                }
            }
        }
        float similarity1=(float)count1*100/ text1Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 1: " +similarity1);
        float similarity2=(float)count2*100/ text2Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 2: " +similarity2);
        System.out.println("Count 1: "+count1+" Count 2: "+count2);
        //return res;
    }
}
