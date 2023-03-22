package Dynamic.Medium;

import Dynamic.GridTraversal.Values.Values;

public class VowelsofAllSubstrings {
    /*
     * countNoOfSubstringACharacterCanOccur1 and countNoOfSubstringACharacterCanOccur2 both are optimal solutions
     */

    public long countVowels(String word) {
        int[][] arr=new int[word.length()][word.length()];
        int sum=0;

        if(word.charAt(0)=='a' || word.charAt(0)=='e' || word.charAt(0)=='i' || word.charAt(0)=='o'
                || word.charAt(0)=='u')
            arr[0][0]=1;
        sum=arr[0][0];
        for(int i=1;i<word.length();i++){
            arr[0][i]=arr[0][i-1];
            if(word.charAt(i)=='a' || word.charAt(i)=='e' || word.charAt(i)=='i' || word.charAt(i)=='o'
                || word.charAt(i)=='u')
                arr[0][i]=arr[0][i-1]+1;

            sum+=arr[0][i];
            for(int j=1;j<=i;j++){
                arr[j][i]=arr[0][i]-arr[0][j-1];
                sum+=arr[j][i];
            }
        }
        return sum;
    }

    /*
     * In this approach we count the number of substrings a vowel appears in.
     *
     * Logic behind sum+=(i+1)*(n-i);
     * Consider String "abide"
     * Now we calculate all possible substrings the character 'i' appears in.
     *      Case 1: Substrings starting with index 2
     *              "i", "id", "ide"
     *      Case 2: Substrings starting with index 1
     *              "bi", "bid", "bide"
     *      Case 3: Substrings starting with index 0
     *              "abi", "abid", "abide"
     *
     *      Substrings cannot start form indexes after 'i' since 'i' is not included.
     *
     * If we observe the pattern the number of substrings possible at a index is equal to total number of characters from
     * 'i' till end of string i.e (n-i).
     * Total number of indexes from which we can start the substring is nothing but total number of characters from
     * beginning of string till 'i'  i.e (i+1)
     *
     * so total possible substrings for a vowel at index i is (i+1)*(n-i).
     */
    //!Optimal
    public long countNoOfSubstringACharacterCanOccur1(String word){
        long sum=0,n=word.length();
        for(int i=0;i<n;i++){
            if(word.charAt(i)=='a' || word.charAt(i)=='e' || word.charAt(i)=='i' || word.charAt(i)=='o'
                    || word.charAt(i)=='u')
                sum+=(i+1)*(n-i);
        }
        return sum;
    }

    //!Optimal
    public long countNoOfSubstringACharacterCanOccur2(String word) {
        long sum=0,n=word.length();
        for(int i=0;i<n;i++){
            if("aeiou".indexOf(word.charAt(i))>=0)
                sum+=(i+1)*(n-i);
        }
        return sum;
    }

    public static void main(String[] args) {
        long l=32003263569662l;
        System.out.println(l);
        System.out.println(new VowelsofAllSubstrings().countNoOfSubstringACharacterCanOccur2(Values.vowelsString));
        System.out.println(new VowelsofAllSubstrings().countNoOfSubstringACharacterCanOccur2("aei"));
        System.out.println(new VowelsofAllSubstrings().countNoOfSubstringACharacterCanOccur2("aba"));
        System.out.println(new VowelsofAllSubstrings().countNoOfSubstringACharacterCanOccur2("ltcd"));
    }
}
