import Trees.Utils.BinaryTreePrinter;
import Trees.Utils.TreeNode;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Test {
    {
        System.out.println("hgnh");
    }
    public static int lengthoflast(String s){
        s=s.trim();
        int index=s.lastIndexOf(" ")+1;
        return index!=-1?s.substring(index).length():s.length();
    }
    public static void main(String args[]) throws IOException {
        int[] arr={0,1,0,5};
        String s="abc sd asda  ";
        String pattern = "/**";
        String requestUri = "/repositories/testenv/vaa/q";
        System.out.println(lengthoflast(s));
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean b = pathMatcher.match(pattern, requestUri);
        System.out.println(b);

        String spaces = " abdc, asmhd gds ,nhdv , df  ,sd,fds";
        String[] spacesarray = spaces.split(",");
        System.out.println(Arrays.toString(spaces.split(",")));
        System.out.println(spacesarray[0].length());
        System.out.println(spacesarray[5].length());
        String spacestrim = " abdc, asmhd gds ,nhdv , df  ,sd,fds, dvnsfgd ";
        String[] spacesarraytrim = spacestrim.trim().split("\\s*,\\s*");
        System.out.println(Arrays.toString(spacesarraytrim));
        System.out.println(spacesarraytrim[0].length());
        System.out.println(spacesarraytrim[1].length());
        System.out.println(spacesarraytrim[3].length());
        System.out.println(spacesarraytrim[6].length());

        System.out.println("\nGuava Splitter");
        long startTime = System.currentTimeMillis();
        String spacestrim1 = " abdc, asmhd gds ,nhdv , df  ,sd,fds, dvnsfgd ";
        //String[] spacesarraytrim1 = Arrays.stream(spacestrim1.split(",")).map(String::trim).collect(Collectors.toList()).toArray(new String[]);
        List<String> results = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(spacestrim1);
        System.out.println(Long.toString(System.currentTimeMillis()-startTime));
        String[] resultsTrim= results.toArray(new String[0]);
        System.out.println(resultsTrim.length);
        System.out.println(Arrays.toString(resultsTrim));
        System.out.println(Long.toString(System.currentTimeMillis()-startTime));

        System.out.println("\nJava Split");
        long startTime1 = System.currentTimeMillis();
        String test = ", abdc, asmhd gds ,,     ,   ,ds , as    fd, ";
        //test = test.trim().replaceAll(",\\s*," , ",");
        test = test.trim().replaceAll("\\s*,\\s*" , ",");
        System.out.println(test);
        test = test.trim().replaceAll(",,+" , ",");
        System.out.println(test);
        String[] testresults= test.split(",");
        System.out.println(Arrays.toString(testresults));
        System.out.println(Long.toString(System.currentTimeMillis()-startTime1));

        System.out.println("\nJava Split Streams");
        String givenString = "      one,two,,      th  ree  ,,four,,    , ,";
        long startTime2 = System.currentTimeMillis();
        String[] resultArray = Arrays.stream(givenString.
                        trim().
                        replaceAll("\\s*,\\s*" , ",").
                        split(",")
                ).
                filter(e -> e.trim().length() > 0).
                toArray(String[]::new);
        System.out.println(Long.toString(System.currentTimeMillis()-startTime2));
        System.out.println("Array List");
        long startTime3 = System.currentTimeMillis();
        /*List<String> list = Arrays.asList(givenString.
                trim().
                replaceAll("\\s*,\\s*" , ",").
                split(","));
        list.removeAll(Arrays.asList("", null));*/
        System.out.println(Arrays.toString(resultArray));
        System.out.println(Long.toString(System.currentTimeMillis()-startTime3));


        System.out.println("\nISEMPTY");
        String givenString1=new String();
        if(!givenString1.trim().isEmpty())
            System.out.println("\nISEMPTY");
        String givenString2 = null;
        String givenString3 = new String("     ");
        String givenString4 = new String("");
        //System.out.println(givenString2.isEmpty());
        //System.out.println(StringUtils.isEmpty(givenString2.trim()));
        System.out.println(StringUtils.isEmpty(givenString3.trim()));
        System.out.println(StringUtils.isEmpty(StringUtils.trim(givenString3)));
        System.out.println(StringUtils.isBlank(givenString3));
        /*HashMap<Integer,Integer> hm= new HashMap();
        hm.entrySet();
        LinkedHashMap*/

        long start=System.currentTimeMillis();
        Random rand=new Random();
        for(int i=0;i<100000;i++){
            rand.nextBoolean();
        }
        System.out.println(System.currentTimeMillis()-start);
        start=System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            Math.random();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(8<8);

        int[] charcount=new int[26];
        String string="picturppe";
        for (int i=0;i<string.length();i++){

            charcount[string.charAt(i)-'a']++;
        }

        for(int i=0;i<26;i++){
            if(charcount[i]>0){
                System.out.println((char)('a'+i)+": "+charcount[i]);
            }
        }

        HashMap<String,Integer> hm=new HashMap<>();
        hm.put("adbc",3);
        hm.put("trans",5);
        hm.put("trans",6);
        for(String s1:hm.keySet()){

        }

        int testmax=2147483647;
        System.out.println(8<8);
        System.out.println(testmax+2);
        int[] arr3=new int[5];
        arr3[0]=1;
        arr3[1]=1;
        arr3[2]=1;
        int k=0;
        for(k=0;k<5;k++)
            if(arr3[k]>3)
                break;
        System.out.println("k:"+k);
        int testint=1;
        System.out.println(testint++);
        System.out.println(Arrays.toString("".split("\\*")));
        StringBuilder sb=new StringBuilder();
        Test test1=new Test();
        test1.stringbuilderapend(sb);
        System.out.println(sb);
        String stest="aa";
        test1.stringbuilderapend(s);
        System.out.println(stest);

        String serialized="1#2#3##12##8#";
        String[] integers=serialized.split("#");
        System.out.println(Arrays.toString(integers));

        SortedSet<String> ts
                = new TreeSet<String>();

        // Adding elements into the TreeSet
        // using add()
        ts.add("India");
        ts.add("Australia");
        ts.add("South Africa");

        // Adding the duplicate
        // element
        ts.add("India");

        System.out.println(ts);

        // Removing items from TreeSet
        // using remove()
        ts.remove("Australia");
        System.out.println("Set after removing "
                + "Australia:" + ts);

        // Iterating over Tree set items
        System.out.println("Iterating over set:");
        Iterator<String> i = ts.iterator();
        while (i.hasNext())
            System.out.println(i.next());


        HashSet<StringBuilder> hs=new HashSet<>();
        hs.add(new StringBuilder("ab"));
        System.out.println(hs);

        int[][] arrtest={{1,2},{2,3}};
        int[] arrtest1={};

        FileInputStream f=new FileInputStream("C:\\testout.txt");
        FileOutputStream fout=new FileOutputStream("C:\\testout.txt");
        String output="如能重排序也许有幫助如能重排序也许如能重排序也许有幫幫123.pdf";
        byte bytes[]=output.getBytes();//converting string into byte array
        fout.write(bytes);
        fout.close();

        Logger logger = Logger.getLogger(Test.class.getName());
        logger.log(Level.ALL,"TEST LOGGER");
    }
    public void stringbuilderapend(StringBuilder sb){
        sb.append("*appended*");
    }
    public void stringbuilderapend(String s){
        s.concat("*StringAppend*");
    }
}
