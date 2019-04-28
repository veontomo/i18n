package i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ibm.icu.util.GregorianCalendar;

//import com.ibm.icu.text.MessageFormat;

public class App {

    public static void runTest(Locale locale) {
        final String messageBundle = "messages";
        ResourceBundle b2 = ResourceBundle.getBundle(messageBundle, locale);
        System.out.println(b2.getString("task"));
        System.out.println(b2.getString("report"));
        String format = b2.getString("report");
        com.ibm.icu.text.MessageFormat mf = new com.ibm.icu.text.MessageFormat(format, locale);
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

        com.ibm.icu.text.MessageFormat mf2 = new com.ibm.icu.text.MessageFormat(b2.getString("msg2"));
        System.out.println(mf2.format(new Object[] { "Alice", 1, "female" }));
        System.out.println(mf2.format(new Object[] { "Bob", 10, "male" }));
    }

    public static String getMessage(Locale locale, String name, String gender, int num) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        com.ibm.icu.text.MessageFormat formatter = new com.ibm.icu.text.MessageFormat(bundle.getString("mailbox"));
        return formatter.format(new Object[] { name, gender, num });
    }

    public static String format(Locale locale, Date date, double quantity) {
        ResourceBundle bundle = ResourceBundle.getBundle("formats", locale);
        final String pattern = bundle.getString("label");
        final MessageFormat mf = new MessageFormat(pattern, locale);
        return mf.format(new Object[] { date, "Alice", quantity });
    }

    public static String currency(Locale locale, Double value) {
        final MessageFormat mf = new MessageFormat("Currency in {0}: {1, number, currency}", locale);
        return mf.format(new Object[] { locale.toLanguageTag(), value });

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // runTest(Locale.ITALIAN);
        // runTest(Locale.ENGLISH);
        // System.out.println(Locale.getDefault());
        // System.out.println(ResourceBundle.getBundle("messages").getString("label"));
        // Locale.setDefault(Locale.JAPAN);
        System.out.println(Locale.getDefault());
        System.out.println(ResourceBundle.getBundle("messages")
            .getString("label"));
        System.out.println(ResourceBundle.getBundle("messages", Locale.forLanguageTag("PL"))
            .getString("label"));
        System.out.println(ResourceBundle.getBundle("messages", Locale.ENGLISH)
            .getString("label"));
        final Date date = new Date(System.currentTimeMillis());
        Locale[] locales = new Locale[] { Locale.UK, Locale.ITALY, Locale.FRANCE, Locale.forLanguageTag("pl-PL") };
        double[] values = new double[] { 0, 1, 2, 5 };
        for (Locale locale : locales) {
            for (double value : values) {
                System.out.println(locale.getCountry() + ": " + format(locale, date, value));
            }
        }
        System.out.println(currency(Locale.ITALY, 1.2345));
        System.out.println(currency(Locale.FRANCE, 1.2345));
        String pattern = "On {0, date} {1} has sent you {2, choice, 0#no messages|1#a message|2#two messages|2<{2, number, integer} messages}.";
        MessageFormat mf = new MessageFormat(pattern, Locale.UK);
        System.out.println(mf.format(new Object[] { date, "Alice", 1 }));

        String pattern2 = "You''ve got {0, choice, 0#no messages|1#a message|2#two messages|2<{0, number, integer} messages}.";
        MessageFormat mf2 = new MessageFormat(pattern2, Locale.UK);

        for (double value : values) {
            System.out.println(mf2.format(new Object[] { value }));
        }
    }

}
