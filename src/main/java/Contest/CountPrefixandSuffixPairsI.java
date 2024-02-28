package Contest;

public class CountPrefixandSuffixPairsI {
    public int countPrefixSuffixPairs(String[] words) {
        int count=0;
        for (int i=0;i<words.length-1;i++){
            for (int j=i+1;j< words.length;j++){
                int size = words[i].length();
                if(words[i].length()>words[j].length()) continue;

                if(words[j].substring(0,size).equals(words[i]) && words[j].substring(words[j].length() - size).equals(words[i]))
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountPrefixandSuffixPairsI().countPrefixSuffixPairs(new String[]{"a","aba","ababa","aa"}));
    }
}
