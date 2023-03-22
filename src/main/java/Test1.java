import org.springframework.util.AntPathMatcher;

public class Test1 {
    {
        System.out.println("hgnh");
    }
    public static int lengthoflast(String s){
        s=s.trim();
        int index=s.lastIndexOf(" ")+1;
        return index!=-1?s.substring(index).length():s.length();
    }
    public static void main(String args[]){
        int[] arr={0,1,0,5};
        String s="abc sd asda  ";
        String pattern = "/repositories/*/users";
        String requestUri = "/repositories/testenv/users";
        System.out.println(lengthoflast(s));
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean b = pathMatcher.match(pattern, requestUri);
        System.out.println(b);
    }
}
