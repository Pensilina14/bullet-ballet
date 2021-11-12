package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class SecureDataTest {

    private final static String PASSWORD = "BULLET-BALLET-PASSWORD"; //TODO: create better password
    private final static String INPUT_FILE_PATH = "secret.txt"; //TODO: modify path
    private final static String ENCRYPTED_FILE_PATH = "encrypted_secret.txt"; //TODO: modify path

    @Test
    public void encryptFileTest() throws Exception {
        //SecureData.encryptFile(INPUT_FILE_PATH, ENCRYPTED_FILE_PATH, PASSWORD);
    }

    @Test
    public void decryptFileTest() throws Exception {
        /*byte[] decryptedMessage = SecureData.decryptFile(ENCRYPTED_FILE_PATH, PASSWORD);
        String clearMessage = new String(decryptedMessage, StandardCharsets.UTF_8);
        System.out.println("clear message: " + clearMessage);*/ //TODO: remove
    }
}
