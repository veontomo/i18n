package i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class MessageTest {

    @Test
    public void whenInEnglish_thenAlice() {
        assertEquals("Alice has no mails in her mailbox.", App.getMessage(Locale.ENGLISH, "Alice", "female", 0));
    }

    @Test
    public void whenInItalian_thenAlice() {
        assertEquals("Alice non ha nessuna mail nella sua mailbox.", App.getMessage(Locale.ITALIAN, "Alice", "female", 0));
    }

    
}
