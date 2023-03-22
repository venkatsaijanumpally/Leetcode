package TestJava;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Detector_40201444 {
    public void algorithm(String path1, String path2) throws IOException {

        String text1 = readStringFromFile(path1);
        String text2 = readStringFromFile(path2);

        algorithmLogic(text1,text2);
        System.out.println();
    }

    private String readStringFromFile(String filePath){
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {

            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) { }

        return contentBuilder.toString();
    }

    private void algorithmLogic(String text1, String text2) {
        long startTime = System.nanoTime();
        text1 = process(text1);
        text2 = process(text2);

        findAllSubstrings(text1,text2);
        long endTime   = System.nanoTime();
        System.out.println("Time Elapsed: "+(endTime-startTime)/Math.pow(10,9));
    }


    private int findAllSubstrings(String s1, String s2) {
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
        //List<String> res = new ArrayList<>();
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
        /*float similarity1=(float)count1*100/ text1Chunks.length;
        float similarity2=(float)count2*100/ text2Chunks.length;
        System.out.println(similarity1 +" "+similarity2);
        if(similarity1<30f || similarity2<30f) return 0;
        return 1;*/
        float similarity1=(float)count1*100/ text1Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 1: " +similarity1);
        float similarity2=(float)count2*100/ text2Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 2: " +similarity2);
        System.out.println("Count 1: "+count1+" Count 2: "+count2);
        System.out.println("Count 1: "+text1Chunks.length+" Count 2: "+text2Chunks.length);
        //System.out.println(res);
        return 1;
    }

    private String process(String text) {
        if(isCode(text))
            return processCode(text);
        String newString = removeReferences(text);
        newString=newString.replaceAll("\n"," ");
        newString=newString.replaceAll("\\s{2,}"," ");
        return newString.replaceAll("[\"\\.\\(\\),]","").toLowerCase();
    }

    private String processCode(String text) {
        text=text.replaceAll("\n"," ");
        text=text.replaceAll("(\\{|\\}|\\;)","");
        text=text.replaceAll("(<<|>>|\")","");
        text=text.replaceAll("\\( \\)","()");
        return text.trim().replaceAll("\\s{2,}"," ");
    }

    private boolean isCode(String text) {
        Pattern codePattern=Pattern.compile(".*(#include|cout|\\(\\)|println|def |main()|\\{).*",Pattern.DOTALL);
        return codePattern.matcher(text).matches();
    }

    private String removeReferences(String text) {
        Pattern p=Pattern.compile(".*References.*",Pattern.DOTALL);
        if(p.matcher(text).matches())
            return text.substring(0,text.lastIndexOf("References"));

        text=text.trim();
        int lastIndex=text.lastIndexOf("\n\n");
        if(lastIndex==-1) return text;
        String s= text.substring(lastIndex).replaceAll("\n"," ").trim();

        Pattern editionPattern=Pattern.compile(".* \\d+(?i)(nd|rd|th|st) .*");
        Pattern linkPattern=Pattern.compile(".* (https|http|ftp)://.* .*");
        Pattern institutionPattern=Pattern.compile(".*(School|University|Institute|College|Academy).*");
        Pattern sourcePattern= Pattern.compile(".* (?i)(Press|Journal|Conference|Thesis|Research Paper|Seminar) .*");
        Matcher editionMatcher = editionPattern.matcher(s);
        Matcher linkMatcher = linkPattern.matcher(s);
        Matcher institutionMatcher = institutionPattern.matcher(s);
        Matcher sourceMatcher = sourcePattern.matcher(s);

        if (editionMatcher.matches() || linkMatcher.matches() || institutionMatcher.matches() || sourceMatcher.matches()){
            return text.substring(0,lastIndex).trim();
        }

        return text;
    }

    public static void main(String[] args) throws IOException {
        /*Scanner sc=new Scanner(System.in);
        String path1=sc.nextLine();
        String path2=sc.nextLine();
        new Detector_40201444().algorithm(path1,path2);*/
        String path1="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test5/1.txt";
        String path2="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test5/2.txt";
        new Detector_40201444().algorithm(path1,path2);

        String pathTemplate="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/okay0";
        for(int i=1;i<=6;i++){
            System.out.println("Okay File: "+i);
            new Detector_40201444().algorithm(pathTemplate+i+"/1.txt",pathTemplate+i+"/2.txt");
        }

        String pathTemplate2="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism0";
        for(int i=1;i<=7;i++){
            System.out.println("Plagiarism File: "+i);
            new Detector_40201444().algorithm(pathTemplate2+i+"/1.txt",pathTemplate2+i+"/2.txt");
        }

        String pathTestTemplate="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test";
        new Detector_40201444().algorithm(pathTestTemplate+"3/1.txt",pathTestTemplate+"3/2.txt");
        new Detector_40201444().algorithm(pathTestTemplate+"4/1.txt",pathTestTemplate+"4/2.txt");
        new Detector_40201444().algorithm(pathTestTemplate+"5/1.txt",pathTestTemplate+"5/2.txt");
        new Detector_40201444().algorithm(pathTestTemplate+"7/1.txt",pathTestTemplate+"7/2.txt");
    }
}
