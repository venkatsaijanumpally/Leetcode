package TestJava;

import java.text.MessageFormat;
import java.util.Locale;

public class TestMessageFormat {
    public static void main(String args[]) {
        Locale locale=new Locale("en_US");
        MessageFormat mf=new MessageFormat("{0} text can''t be one of {1}", locale);
        MessageFormat mf1=new MessageFormat("{0} text can''t'' be one of {1}", locale);
        MessageFormat mf2=new MessageFormat("{0} text can'{4}' be one of {1}", locale);
        MessageFormat mf3=new MessageFormat("You are not allowed to create an other user''s preference.", locale);
        System.out.println(mf3);
    }
}
