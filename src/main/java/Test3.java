import TestJava.Child;
import TestJava.Parent;

public class Test3 {
    public static void main(String[] args) {
        Parent p=new Parent();
        Child c=new Child();
        //p=c;
        //c=(Child) p;

        Parent p2=new Parent();
        Child c2=new Child();
        p2=c2;
        p2.functionA();

        Parent p3=new Child();
        ((Parent)p3).functionA();
        p3.functionA();

        Parent c3= (Parent) new Child();
        c3.functionA();
        c3.functionParent();
    }
}
