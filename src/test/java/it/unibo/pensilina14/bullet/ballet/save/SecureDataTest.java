package it.unibo.pensilina14.bullet.ballet.save;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SecureDataTest {

  @Test
  public void encryptAndDecryptTest()
      throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
          NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
          BadPaddingException {
    final String message = "Messaggio segreto";
    final byte[] encryptedMessage = SecureData.encrypt(message.getBytes(), SecureData.PASSWORD);

    assertNotNull(encryptedMessage);

    final byte[] decryptedMessage = SecureData.decrypt(encryptedMessage, SecureData.PASSWORD);
    final String clearMessage = new String(decryptedMessage, StandardCharsets.UTF_8);

    assertEquals(message, clearMessage);
  }
}
