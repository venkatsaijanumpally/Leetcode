package Graphs.Hard;

import Graphs.Utils.WordLadderIIPair;

import java.util.*;

public class WordLadderII {
    List<List<String>> result=new ArrayList<>();
    String endWord;
    HashMap<String,Integer> set;
    ArrayList<ArrayList<Integer>> ways;
    ArrayList<Integer> shortestPathLength;
    boolean[] visited;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.endWord=endWord;
        set=new HashMap<>();
        wordList.remove(beginWord);
        wordList.add(beginWord);
        for(int i=0;i< wordList.size();i++){
            set.put(wordList.get(i),i);
        }

        if(!set.containsKey(endWord))
            return result;

        ways=new ArrayList<>();
        visited=new boolean[wordList.size()];
        shortestPathLength=new ArrayList<>();
        for(int i=0;i< wordList.size();i++){
            ways.add(new ArrayList<>());
            shortestPathLength.add(Integer.MAX_VALUE);
        }
        shortestPathLength.set(wordList.size()-1, 0);
        visited[wordList.size()-1]=true;

        Queue<WordLadderIIPair> queue=new LinkedList<>();
        queue.add(new WordLadderIIPair(wordList.size()-1,0));
        while (!queue.isEmpty()){
            WordLadderIIPair pair= queue.poll();
            String item=wordList.get(pair.strIndex);
            for(int i=0;i<item.length();i++){
                for(char c='a';c<='z';c++){
                    if(item.charAt(i)==c)
                        continue;
                    char[] charArray=item.toCharArray();
                    charArray[i]=c;
                    String replacedString=String.valueOf(charArray);
                    if(set.containsKey(replacedString)){
                        int replacedIndex=set.get(replacedString);
                        if(pair.steps+1<=shortestPathLength.get(replacedIndex)){
                            shortestPathLength.set(replacedIndex, pair.steps+1);
                            ways.get(replacedIndex).add(pair.strIndex);
                            if(!visited[replacedIndex]){
                                queue.add(new WordLadderIIPair(replacedIndex, pair.steps+1));
                                visited[replacedIndex]=true;
                            }
                        }
                    }
                }
            }
        }

        ArrayList<String> sequence=new ArrayList<>();
        //sequence.push(endWord);
        //Queue<String> sequence=new LinkedList<>();
        recursion(sequence,set.get(endWord),shortestPathLength.get(set.get(endWord)),wordList);

        return result;
    }

    private void recursion(ArrayList<String> sequence, Integer index, Integer length, List<String> wordList) {
        if(length==0){
            //sequence.push(wordList.get(index));
            sequence.add(0,wordList.get(index));
            result.add(new ArrayList<>(sequence));
            sequence.remove(0);
            return;
        }

        sequence.add(0,wordList.get(index));
        //sequence.push(wordList.get(index));
        for(int nextIndex: ways.get(index)){
            recursion(sequence,nextIndex,length-1,wordList);
        }

        //sequence.pop();
        sequence.remove(0);
    }

    public static void main(String[] args) {
        String[] wordList={"hot","dot","dog","lot","log","cog"};
        List<String> list1=new ArrayList<>(List.of(wordList));
        System.out.println(new WordLadderII().findLadders("hit","cog",list1));
    }
}
