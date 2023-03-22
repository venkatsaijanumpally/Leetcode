import java.util.Arrays;
import java.util.Scanner;

public class TestDom {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc=new Scanner(System.in);
        String a=sc.next();
        String b=sc.nextLine();
        int c=sc.nextInt();
        System.out.println(a);
        System.out.println(b);
        System.out.println(Arrays.toString(b.split(" ")));
        System.out.println(c);
    }
}
