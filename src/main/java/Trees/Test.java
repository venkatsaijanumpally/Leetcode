package Trees;

public class Test {
    public static void main(String[] args) {
        TestListNode head=new TestListNode(0);
        head.next=new TestListNode(5);
        head.next.next=new TestListNode(10);
        System.out.println("abcx".matches("abc(?:wr)"));
        TestListNode ptr=head;
        while (ptr!=null){
            System.out.println(ptr.val+" ");
            ptr=ptr.next;
        }
    }
}
