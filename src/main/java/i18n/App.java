package i18n;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ibm.icu.text.MessageFormat;

public class App {

    public static void runTest(Locale locale) {
        final String messageBundle = "messages";
        ResourceBundle b2 = ResourceBundle.getBundle(messageBundle, locale);
        System.out.println(b2.getString("task"));
        System.out.println(b2.getString("report"));
        String format = b2.getString("report");
        MessageFormat mf = new MessageFormat(format, locale);
        final Date date1 = new Date(System.currentTimeMillis());
        final Date date2 = new Date(System.currentTimeMillis() * 2);
        Object objectsToFormat[] = { date1, date2, "a Disturbance in the Force", 5 };
        // FieldPosition fp = new FieldPosition(1);
        // StringBuffer sb = new StringBuffer();
        try {
            // sb = mf.format(objectsToFormat, sb, fp);
            // System.out.println(sb.toString());
            System.out.println(mf.format(objectsToFormat));
        } catch (IllegalArgumentException e) {
            System.out.println("Exception during formating of type :" + e);
        }

        MessageFormat mf2 = new MessageFormat(b2.getString("msg2"));
        System.out.println(mf2.format(new Object[] { "Alice", 1, "female" }));
        System.out.println(mf2.format(new Object[] { "Bob", 10, "male" }));
    }

    public static String getMessage(Locale locale, String name, String gender, int num) {
        final String messageBundle = "messages";
        ResourceBundle b2 = ResourceBundle.getBundle(messageBundle, locale);
        MessageFormat mf2 = new MessageFormat(b2.getString("msg2"));
        return mf2.format(new Object[] { name, gender, num });
    }

    public static void main(String[] args) {
        runTest(Locale.ITALIAN);
        runTest(Locale.ENGLISH);
    }

}
