package it.unibo.pensilina14.bullet.ballet.save;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public final class SecureData {

  private static final String ENCRYPTION_STANDARD = "AES";
  private static final int KEY_SIZE = 256;
  private static final String TRANSFORMATION_ALGORITHM = "PBKDF2WithHmacSHA256";
  private static final String TRANSFORMATION_ALGORITHM_NO_PADDING = "AES/GCM/NoPadding";
  private static final int ITERATION_COUNTER = 65_536;
  private static final int IV_SIZE = 12;
  private static final int SALT_SIZE = 16;
  private static final int TAG_SIZE = 128;

  public static final String PASSWORD = "BULLET-BALLET-CODE";

  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  /** private constructor because the class doesn't have to be instantiated. */
  private SecureData() {}

  /**
   * @param bytesNumber : number of bytes We use SecureRandom to create a pseudo-random number which
   *     we will use to generate a random IV and Salt. IV: stands for initialization vector, it adds
   *     randomness to the start of the encryption process. It may also be called nonce since it's
   *     used only once. Salt: it is some bytes that gets added to the password before it goes
   *     through the hashing algorithm.
   * @return : random bytes.
   */
  public static byte[] getRandomBytes(final int bytesNumber) {

    final byte[] bytes = new byte[bytesNumber];
    SecureData.SECURE_RANDOM.nextBytes(bytes);

    return bytes;
  }

  /**
   * @param password: it's a user defined password.
   * @param salt: it is some bytes that gets added to the password before it goes through the
   *     hashing algorithm.
   * @return : a SecretKey
   * @throws NoSuchAlgorithmException: algorithm doesn't exist.
   * @throws InvalidKeySpecException: invalid key specifications.
   */
  public static SecretKey getKeyFromPassword(final String password, final byte[] salt)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    final SecretKeyFactory secretKeyFactory =
        SecretKeyFactory.getInstance(SecureData.TRANSFORMATION_ALGORITHM);

    final KeySpec keySpec =
        new PBEKeySpec(
            password.toCharArray(), salt, SecureData.ITERATION_COUNTER, SecureData.KEY_SIZE);

    return new SecretKeySpec(
        secretKeyFactory.generateSecret(keySpec).getEncoded(), SecureData.ENCRYPTION_STANDARD);
  }

  /**
   * @param message: the message, in plain text, that has to be encrypted.
   * @param password: it's a user defined password.
   * @return : the encrypted text.
   * @throws NoSuchAlgorithmException: algorithm doesn't exist.
   * @throws InvalidKeySpecException: invalid key specifications.
   * @throws NoSuchPaddingException: padding not available.
   * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
   * @throws InvalidKeyException: invalid key.
   * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
   * @throws BadPaddingException: input data not properly padded.
   */
  public static byte[] encrypt(final byte[] message, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
          InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException,
          BadPaddingException {

    final byte[] salt = SecureData.getRandomBytes(SecureData.SALT_SIZE);

    final byte[] iv = SecureData.getRandomBytes(SecureData.IV_SIZE);

    final SecretKey secretKey = SecureData.getKeyFromPassword(password, salt);

    final Cipher cipher = Cipher.getInstance(SecureData.TRANSFORMATION_ALGORITHM_NO_PADDING);

    cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(SecureData.TAG_SIZE, iv));

    final byte[] encryptMessage = cipher.doFinal(message);

    return ByteBuffer.allocate(iv.length + salt.length + encryptMessage.length)
        .put(iv)
        .put(salt)
        .put(encryptMessage)
        .array();
  }

  /**
   * @param message: the encrypted text that has to be decrypted.
   * @param password the user defined password. (it has to match with the one used in the
   *     encryption)
   * @return : the decrypted text.
   * @throws NoSuchAlgorithmException: algorithm doesn't exist
   * @throws InvalidKeySpecException: invalid key specifications.
   * @throws NoSuchPaddingException: padding not available.
   * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
   * @throws InvalidKeyException: invalid key.
   * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
   * @throws BadPaddingException: input data not properly padded.
   */
  public static byte[] decrypt(final byte[] message, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
          InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException,
          BadPaddingException {
    final ByteBuffer byteBuffer = ByteBuffer.wrap(message);

    final byte[] iv = new byte[SecureData.IV_SIZE];
    byteBuffer.get(iv);

    final byte[] salt = new byte[SecureData.SALT_SIZE];
    byteBuffer.get(salt);

    final byte[] encryptedMessage = new byte[byteBuffer.remaining()];
    byteBuffer.get(encryptedMessage);

    final SecretKey secretKey = SecureData.getKeyFromPassword(password, salt);

    final Cipher cipher = Cipher.getInstance(SecureData.TRANSFORMATION_ALGORITHM_NO_PADDING);

    cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(SecureData.TAG_SIZE, iv));

    return cipher.doFinal(encryptedMessage);
  }

  /**
   * @param inputFilePath: the path of the file with the plain text.
   * @param outputFilePath: the file with the encrypted text that will be created.
   * @param password: the user-defined password.
   * @throws IOException: fail or interrupted I/O operations.
   * @throws NoSuchAlgorithmException: algorithm doesn't exist
   * @throws InvalidKeySpecException: invalid key specifications.
   * @throws NoSuchPaddingException: padding not available.
   * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
   * @throws InvalidKeyException: invalid key.
   * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
   * @throws BadPaddingException: input data not properly padded.
   */
  public static void encryptFile(
      final String inputFilePath, final String outputFilePath, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
          InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException,
          BadPaddingException, IOException {

    final byte[] message = Files.readAllBytes(Paths.get(inputFilePath));

    final byte[] encryptedMessage = SecureData.encrypt(message, password);

    final Path filePath = Paths.get(outputFilePath);

    Files.write(filePath, encryptedMessage);
  }

  /**
   * @param encryptedFilePath: the path of the file with the encrypted text.
   * @param password: the user-defined password. (it has to match with the password used in the
   *     encryption)
   * @return : the decrypted text in plain text.
   * @throws IOException: fail or interrupted I/O operations.
   * @throws NoSuchAlgorithmException: algorithm doesn't exist
   * @throws InvalidKeySpecException: invalid key specifications.
   * @throws NoSuchPaddingException: padding not available.
   * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
   * @throws InvalidKeyException: invalid key.
   * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
   * @throws BadPaddingException: input data not properly padded.
   */
  public static byte[] decryptFile(final String encryptedFilePath, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
          InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException,
          BadPaddingException, IOException {

    final byte[] encryptedMessage = Files.readAllBytes(Paths.get(encryptedFilePath));

    return SecureData.decrypt(encryptedMessage, password);
  }

  public static byte[] decryptFile(final byte[] encryptedFilePath, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
          InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException,
          BadPaddingException, IOException {

    return SecureData.decrypt(encryptedFilePath, password);
  }
}
