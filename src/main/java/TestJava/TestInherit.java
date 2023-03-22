package TestJava;

public class TestInherit {
    public static void fun(Parent p){
        System.out.println("\nIn Function");
        p.functionA();
        Child c = (Child) p;
        /*c.functionA();
        c.functionParent();
        c.childExclusiveFun();*/
    }

    public static void converttoChild(Parent p){
        Child c= (Child) p;
        p.functionA();
    }
    public static void main(String[] args){
        Child child=new Child();
        child.functionA();
        //child= (Child) new Parent();
        //child.functionA();
        Parent child1=new Child();
        child1.functionA();
        fun(child);
        System.out.println();
        //Parent p=new Parent();
        //p.functionA();
        //Child c= (Child) new Parent();
        //p.functionA();

        /*Parent p1=child;
        p1.functionA();*/
        //p1.functionParent();

        Parent parentobject = new Parent();
        converttoChild(parentobject);
        //Child chile1obj = (Child) parentobject;
    }
}
