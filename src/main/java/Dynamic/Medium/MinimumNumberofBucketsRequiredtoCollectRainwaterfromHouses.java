package Dynamic.Medium;

public class MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses {
    public int minimumBuckets(String street) {
        if(street.equals("H") || street.startsWith("HH") || street.endsWith("HH") || street.contains("HHH"))
            return -1;
        int p=0;
        int result=0;
        for(int i=0;i<street.length();i++){
            if(street.charAt(i)=='H'){
                if(p==1) p=0;
                else if(p==-1)result++;
                else p=-1;
            }
            else if(p==-1){
                result++;
                p=1;
            }
            else if(p==1) p=0;
        }
        if(p==-1) result++;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("HH..."));
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("..HHH..."));
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("..HH..."));
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("..HH.HH.."));
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("..H.HH..HH..."));
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("..HH.HHH.."));
        System.out.println(new MinimumNumberofBucketsRequiredtoCollectRainwaterfromHouses().minimumBuckets("H..HH.H..H"));
    }
}
