package i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import com.ibm.icu.text.*;
import java.text.FieldPosition;
import java.util.Date;

public class App {

    public void runTest(Locale locale) {
        final String messageBundle = "messages";
        ResourceBundle b2 = ResourceBundle.getBundle(messageBundle, locale);
        System.out.println(b2.getString("task"));
        String format = b2.getString("report");
        MessageFormat mf = new MessageFormat(format);
        Object objectsToFormat[] = { new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "a Disturbance in the Force", 5 };
        FieldPosition fp = new FieldPosition(1);
        StringBuffer sb = new StringBuffer();
        try {
            sb = mf.format(objectsToFormat, sb, fp);
            System.out.println(sb.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Exception during formating of type :" + e);
        }
    }

    public static void main(String[] args) {
        new App().runTest(Locale.ITALIAN);
        new App().runTest(Locale.ENGLISH);
    }

}
