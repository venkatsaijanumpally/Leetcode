package TestJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detector {
    public void algorithm(String path1, String path2) throws IOException {
        Path filePath = Path.of(path1);
        String text1 = Files.readString(filePath);
        Path filePath2 = Path.of(path2);
        String text2 = Files.readString(filePath2);

        algorithmLogic(text1,text2);
        System.out.println();
    }

    private void algorithmLogic(String text1, String text2) {
        long startTime = System.nanoTime();
        text1 = process(text1);
        text2 = process(text2);

        findAllSubstrings(text1,text2);
        long endTime   = System.nanoTime();
        System.out.println("Time Elapsed: "+(endTime-startTime)/Math.pow(10,9));
    }


    private void findAllSubstrings(String s1, String s2) {
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
        float similarity1=(float)count1*100/ text1Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 1: " +similarity1);
        float similarity2=(float)count2*100/ text2Chunks.length;
        System.out.println("SIMILARITY by sentences relative to text 2: " +similarity2);
        System.out.println("Count 1: "+count1+" Count 2: "+count2);
        System.out.println("Count 1: "+text1Chunks.length+" Count 2: "+text2Chunks.length);
        //System.out.println(res);

    }

    private String process(String text) {
        if(isCode(text))
            return processCode(text);
        String newString = removeReferences(text);
        //String newString = text;
        newString=newString.replaceAll("\n"," ");
        newString=newString.replaceAll("\\s+"," ");
        /*newString=newString.replaceAll(",","");
        newString=newString.replaceAll("\\.","");
        newString=newString.replaceAll("\"","");
        newString=newString.replaceAll("\\(","");
        newString=newString.replaceAll("\\)","");*/
        newString=newString.replaceAll("[\"\\.\\(\\),]","").toLowerCase();
        //newString=newString.toLowerCase();
        return newString;
    }

    private String processCode(String text) {
        System.out.println("CODE");
        text=text.replaceAll("\n"," ");
        text=text.replaceAll("(\\{|\\}|\\;)","");
        //text=text.replaceAll("(<<|>>|\"|')","");
        text=text.replaceAll("(<<|>>|\")","");
        text=text.replaceAll("\\( \\)","()");
        text=text.trim().replaceAll("\\s+"," ");
        return text;
    }

    private boolean isCode(String text) {
        Pattern codePattern=Pattern.compile(".*(#include|cout|\\(\\)|println|def |main()|\\{).*",Pattern.DOTALL);
        /*Pattern codePattern2=Pattern.compile(".*(#include|cout|public|static|void|int|\\(\\)|println).*",Pattern.DOTALL);
        if(codePattern.matcher(text).matches())
            System.out.println(codePattern2.matcher(text).find());*/
        return codePattern.matcher(text).matches();
    }

    private String removeReferences(String text) {
        Pattern p=Pattern.compile(".*References.*",Pattern.DOTALL);
        if(p.matcher(text).matches()){
            //System.out.println("Found");
            return text.substring(0,text.lastIndexOf("References"));
        }
        text=text.trim();
        int lastIndex=text.lastIndexOf("\n\n");
        if(lastIndex==-1) return text;
        //System.out.println(text.substring(lastIndex));
        String s= text.substring(lastIndex).replaceAll("\n"," ").trim();
        //System.out.println(s.trim());
        Pattern editionPattern=Pattern.compile(".* \\d+(?i)(nd|rd|th|st) .*");
        Pattern linkPattern=Pattern.compile(".* (https|http|ftp)://.* .*");
        Pattern institutionPattern=Pattern.compile(".*(School|University|Institute|College|Academy).*");
        Pattern sourcePattern=Pattern.compile(".* (?i)(Press|Journal|Conference|Thesis|Research Paper|Seminar) .*");
        Matcher editionMatcher = editionPattern.matcher(s);
        Matcher linkMatcher = linkPattern.matcher(s);
        Matcher institutionMatcher = institutionPattern.matcher(s);
        Matcher sourceMatcher = sourcePattern.matcher(s);
        /*System.out.println(editionMatcher.matches());
        System.out.println(linkMatcher.matches());
        System.out.println(institutionMatcher.matches());
        System.out.println(sourceMatcher.matches());*/

        /*while (editionMatcher.matches() || linkMatcher.matches() || institutionMatcher.matches() || sourceMatcher.matches()){
            text=text.substring(0,lastIndex).trim();
            lastIndex=text.lastIndexOf("\n");
            if(lastIndex!=-1) s=text.substring(lastIndex).trim();
        }*/
        if (editionMatcher.matches() || linkMatcher.matches() || institutionMatcher.matches() || sourceMatcher.matches()){
            return text.substring(0,lastIndex).trim();
        }

        //System.out.println(text);
        return text;
    }

    public static void main(String[] args) throws IOException {

        String path1="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test5/1.txt";
        String path2="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test5/2.txt";
        new Detector().algorithm(path1,path2);

        String pathTemplate="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/okay0";
        for(int i=1;i<=6;i++){
            System.out.println("Okay File: "+i);
            new Detector().algorithm(pathTemplate+i+"/1.txt",pathTemplate+i+"/2.txt");
        }

        String pathTemplate2="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/plagiarism0";
        for(int i=1;i<=7;i++){
            System.out.println("Plagiarism File: "+i);
            new Detector().algorithm(pathTemplate2+i+"/1.txt",pathTemplate2+i+"/2.txt");
        }

        String pathTestTemplate="/Volumes/Seagate Mac 1/Downloads/sample_data_and_submission/data/test";
        new Detector().algorithm(pathTestTemplate+"3/1.txt",pathTestTemplate+"3/2.txt");
        new Detector().algorithm(pathTestTemplate+"4/1.txt",pathTestTemplate+"4/2.txt");
        new Detector().algorithm(pathTestTemplate+"5/1.txt",pathTestTemplate+"5/2.txt");
        new Detector().algorithm(pathTestTemplate+"7/1.txt",pathTestTemplate+"7/2.txt");

    }
}
