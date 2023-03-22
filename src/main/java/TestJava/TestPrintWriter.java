package TestJava;

import javax.servlet.ServletOutputStream;
import java.io.*;

public class TestPrintWriter {
    public static void main(String[] args) throws IOException {
        PrintWriter printWriter= new PrintWriter(new File("C:\\Users\\vjanumpally\\Downloads\\estwriter.txt"));
        printWriter.write("Testing String print\n");
        printWriter.write("Testing String print1\n");
        printWriter.write("Testing String print2\n");
        //printWriter.write(2);
        char[] c={'s','a'};
        printWriter.write(c);
        printWriter.flush();
        printWriter.close();
        System.out.println("test");
        String s = "Test Output Stream String";
        /*ServletOutputStream sos;
        sos.write(s.getBytes());*/
        OutputStream os = new FileOutputStream("C:\\Users\\vjanumpally\\Downloads\\estwriter.txt");
        //os.write(s.getBytes());
        PrintWriter printWriterOutputStream= new PrintWriter(os);
        printWriterOutputStream.write(s);
        printWriterOutputStream.flush();
        printWriterOutputStream.close();
    }
}
