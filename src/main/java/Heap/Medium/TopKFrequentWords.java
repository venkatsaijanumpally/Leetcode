package Heap.Medium;

import Heap.Utils.KFrequentWord;

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result=new ArrayList<>();
        HashMap<String,Integer> hm=new HashMap<>();
        for(String s: words){
            hm.put(s,hm.getOrDefault(s,0)+1);
        }
        Queue<KFrequentWord> queue=new PriorityQueue<>();
        for (Map.Entry entry: hm.entrySet()){
            queue.offer(new KFrequentWord((String) entry.getKey(), (Integer) entry.getValue()));
            if(queue.size()>k)
                queue.poll();
        }
        while (queue.size()!=0){
            KFrequentWord word=queue.poll();
            result.add(0, word.word);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] st1={"i","love","leetcode","i","love","coding"};
        String[] st2={"the","day","is","sunny","the","the","the","sunny","is","is"};
        String[] st3={"glarko","zlfiwwb","nsfspyox","pwqvwmlgri","qggx","qrkgmliewc","zskaqzwo","zskaqzwo","ijy","htpvnmozay","jqrlad","ccjel","qrkgmliewc","qkjzgws","fqizrrnmif","jqrlad","nbuorw","qrkgmliewc","htpvnmozay","nftk","glarko","hdemkfr","axyak","hdemkfr","nsfspyox","nsfspyox","qrkgmliewc","nftk","nftk","ccjel","qrkgmliewc","ocgjsu","ijy","glarko","nbuorw","nsfspyox","qkjzgws","qkjzgws","fqizrrnmif","pwqvwmlgri","nftk","qrkgmliewc","jqrlad","nftk","zskaqzwo","glarko","nsfspyox","zlfiwwb","hwlvqgkdbo","htpvnmozay","nsfspyox","zskaqzwo","htpvnmozay","zskaqzwo","nbuorw","qkjzgws","zlfiwwb","pwqvwmlgri","zskaqzwo","qengse","glarko","qkjzgws","pwqvwmlgri","fqizrrnmif","nbuorw","nftk","ijy","hdemkfr","nftk","qkjzgws","jqrlad","nftk","ccjel","qggx","ijy","qengse","nftk","htpvnmozay","qengse","eonrg","qengse","fqizrrnmif","hwlvqgkdbo","qengse","qengse","qggx","qkjzgws","qggx","pwqvwmlgri","htpvnmozay","qrkgmliewc","qengse","fqizrrnmif","qkjzgws","qengse","nftk","htpvnmozay","qggx","zlfiwwb","bwp","ocgjsu","qrkgmliewc","ccjel","hdemkfr","nsfspyox","hdemkfr","qggx","zlfiwwb","nsfspyox","ijy","qkjzgws","fqizrrnmif","qkjzgws","qrkgmliewc","glarko","hdemkfr","pwqvwmlgri"};
        //System.out.println(new TopKFrequentWords().topKFrequent(st1,2));
        //System.out.println(new TopKFrequentWords().topKFrequent(st2,4));
        System.out.println(new TopKFrequentWords().topKFrequent(st3,14));
    }
}
