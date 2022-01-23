package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

public class SecureDataTest {

    @Test
    public void encryptAndDecryptTest() throws Exception {
        final String message = "Messaggio segreto";
        final byte[] encryptedMessage = SecureData.encrypt(message.getBytes(), SecureData.PASSWORD);

        assertNotNull(encryptedMessage);
        //assertNotEquals(message.getBytes(), encryptedMessage);

        final byte[] decryptedMessage = SecureData.decrypt(encryptedMessage, SecureData.PASSWORD);
        final String clearMessage = new String(decryptedMessage, StandardCharsets.UTF_8);

        assertEquals(message, clearMessage);
    }
}
