package Heap.Utils;

public class KFrequentWord implements Comparable<KFrequentWord> {
    public String word;
    public int count;
    public KFrequentWord(String word,int count){
        this.word=word;
        this.count=count;
    }

    @Override
    public int compareTo(KFrequentWord o) {
        if(o.count==count)
            return o.word.compareTo(word);
        return count-o.count;
    }
}
