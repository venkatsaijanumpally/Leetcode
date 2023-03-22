package TestJava;

public class Child extends Parent {
    @Override
    public void functionA(){
        System.out.println("Child function A");
    }

    public void childExclusiveFun(){
        System.out.println("Child Exclusive");
    }

    public static void functionC(){
        System.out.println("Static Child");
    }
}
