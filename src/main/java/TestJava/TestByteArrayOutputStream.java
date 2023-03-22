package TestJava;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class TestByteArrayOutputStream {
    private static ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public static void testByteStream() throws IOException {
        String s="Test String Hi Hello World new abc xyz";
        InputStream is = new ByteArrayInputStream(s.getBytes()); // not really known
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[4];
        int size = is.available();
        System.out.println(size);
        /*for(int i=0;i<4;i++){
            is.read(data, 0, data.length);
            System.out.println(new String(data));
        }*/
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
            /*byte[] targetArray = buffer.toByteArray();
            System.out.println("Data:"+new String(data)+nRead+"  String:"+ new String(targetArray));*/
        }

        buffer.flush();
        byte[] targetArray = buffer.toByteArray();
        System.out.println("\nString:"+ new String(targetArray));
    }
    public static void testByteStreamSize() throws IOException {
        String s="Test String Hi Hello World new abc xyz";
        InputStream is = new ByteArrayInputStream(s.getBytes()); // not really known
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(10);

        int nRead;
        byte[] data = new byte[4];
        int size = is.available();
        System.out.println(size);
        /*for(int i=0;i<4;i++){
            is.read(data, 0, data.length);
            System.out.println(new String(data));
        }*/
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
            /*byte[] targetArray = buffer.toByteArray();
            System.out.println("Data:"+new String(data)+nRead+"  String:"+ new String(targetArray));*/
        }

        buffer.flush();
        byte[] targetArray = buffer.toByteArray();
        System.out.println("\nString with Size:"+ new String(targetArray));
    }
    public static void abc(){
        byte[] b=new String("TestOutput").getBytes();
        baos.writeBytes(b);
        System.out.println(baos.toString());
        System.out.println(baos.toString());
        System.out.println(new String(baos.toByteArray()));
    }
    public static void testarrayCopy(){
        char[] chars1={'h','e','l','l','o'};
        char[] chars2={'a','b','c','d','e'};
        char[] chars3= new char[20];
        System.arraycopy(chars1,0, chars3,3,chars1.length);
        System.out.println(Arrays.toString(chars3) +""+chars3.length);
        System.arraycopy(chars2,0, chars3,0,chars2.length);
        System.out.println(Arrays.toString(chars3) +""+chars3.length);
    }
    public static void main(String[] args) throws IOException {
        testByteStream();
        testByteStreamSize();
        //testarrayCopy();
    }
}
