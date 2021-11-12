package it.unibo.pensilina14.bullet.ballet.save;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
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
import java.util.Arrays;

public final class SecureData {

    //TODO: al posto di salvare dati in formato di caratteri strani nel file, inserire l'encoding in hex o base64.

    private final static String ENCRYPTION_STANDARD = "AES";
    private final static int KEY_SIZE = 256;
    private final static String TRANSFORMATION_ALGORITHM = "PBKDF2WithHmacSHA256"; //TODO: rename it better
    private final static String TRANSFORMATION_ALGORITHM_NO_PADDING = "AES/GCM/NoPadding";
    private final static int ITERATION_COUNTER = 65536;
    private final static int IV_SIZE = 12;
    private final static int SALT_SIZE = 16;
    private final static int TAG_SIZE = 128;

    private final static String PASSWORD = "BULLET-BALLET-CODE"; //TODO: either i keep it here or in the Save class. rename it BULLET-BALLET-KEY or something.

    /**
     * private constructor because the class doesn't have to be instantiated.
     */
    private SecureData(){}

    /**
     *
     * @param bytesNumber : number of bytes
     * We use SecureRandom to create a pseudo-random number
     * which we will use to generate a random IV and Salt.
     * IV: stands for initialization vector, it adds randomness to the start of the encryption process.
     * It may also be called nonce since it's used only once.
     * Salt: it is some bytes that gets added to the password before it goes through the hashing algorithm.
     * @return : random bytes.
     */
    public static byte[] getRandomBytes(final int bytesNumber){
        SecureRandom secureRandom = new SecureRandom();

        byte[] bytes = new byte[bytesNumber];
        secureRandom.nextBytes(bytes);

        return bytes;
    }

    /**
     *
     * @param password: it's a user defined password.
     * @param salt: it is some bytes that gets added to the password before it goes through the hashing algorithm.
     * @return : a SecretKey
     * @throws NoSuchAlgorithmException: algorithm doesn't exist.
     * @throws InvalidKeySpecException: invalid key specifications.
     */
    public static SecretKey getKeyFromPassword(final String password, final byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SecureData.TRANSFORMATION_ALGORITHM);

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, SecureData.ITERATION_COUNTER, SecureData.KEY_SIZE);

        return new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), SecureData.ENCRYPTION_STANDARD);
    }

    /**
     *
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
    public static byte[] encrypt(final byte[] message, final String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] salt = SecureData.getRandomBytes(SecureData.SALT_SIZE);

        byte[] iv = SecureData.getRandomBytes(SecureData.IV_SIZE);
        //TODO: byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV(); , meglio quella sotto.
        //TODO: IVParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKey secretKey = SecureData.getKeyFromPassword(password, salt);

        Cipher cipher = Cipher.getInstance(SecureData.TRANSFORMATION_ALGORITHM_NO_PADDING);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(SecureData.TAG_SIZE, iv));

        byte[] encryptMessage = cipher.doFinal(message);

        //TODO: Base64.getEncoder().encodeToString(encryptMessage);

        //TODO: ivSpec.getIv().length
        return ByteBuffer.allocate(iv.length + salt.length + encryptMessage.length).put(iv).put(salt).put(encryptMessage).array();
    }

    /**
     *
     * @param message: the encrypted text that has to be decrypted.
     * @param password the user defined password. (it has to match with the one used in the encryption)
     * @return : the decrypted text.
     * @throws NoSuchAlgorithmException: algorithm doesn't exist
     * @throws InvalidKeySpecException: invalid key specifications.
     * @throws NoSuchPaddingException: padding not available.
     * @throws InvalidAlgorithmParameterException: invalid or inappropriate algorithm parameters.
     * @throws InvalidKeyException: invalid key.
     * @throws IllegalBlockSizeException: provided wrong length of data to the block cipher.
     * @throws BadPaddingException: input data not properly padded.
     */
    public static byte[] decrypt(final byte[] message, final String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(message);

        byte[] iv = new byte[SecureData.IV_SIZE];
        byteBuffer.get(iv);

        byte[] salt = new byte[SecureData.SALT_SIZE];
        byteBuffer.get(salt);

        //TODO: byte[] bytes = message.digest(password.getBytes(StandardCharsets.UTF_8));

        byte[] encryptedMessage = new byte[byteBuffer.remaining()];
        byteBuffer.get(encryptedMessage);

        SecretKey secretKey = SecureData.getKeyFromPassword(password, salt);

        Cipher cipher = Cipher.getInstance(SecureData.TRANSFORMATION_ALGORITHM_NO_PADDING);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(SecureData.TAG_SIZE, iv));

        return cipher.doFinal(encryptedMessage);
    }

    /**
     *
     * @param inputFilePath: the path of the file with the plain text.
     * @param outputFilePath: the file with the encrypted text that will be created.
     * @param password: the user-defined password.
     * @throws Exception: InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
     */
    public static void encryptFile(final String inputFilePath, final String outputFilePath, final String password) throws Exception {

        byte[] message = Files.readAllBytes(Paths.get(inputFilePath));

        //System.out.println("message[]: " + Arrays.toString(message)); //TODO: remove

        byte[] encryptedMessage = SecureData.encrypt(message, password);

        Path filePath = Paths.get(outputFilePath);

        Files.write(filePath, encryptedMessage);
    }

    /**
     *
     * @param encryptedFilePath: the path of the file with the encrypted text.
     * @param password: the user-defined password. (it has to match with the password used in the encryption)
     * @return : the decrypted text in plain text.
     * @throws Exception: InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
     */
    public static byte[] decryptFile(final String encryptedFilePath, final String password) throws Exception {

        byte[] encryptedMessage = Files.readAllBytes(Paths.get(encryptedFilePath));

        //System.out.println("encryptedMessage[]: " + Arrays.toString(encryptedMessage)); //TODO: remove

        return SecureData.decrypt(encryptedMessage, password);
    }
}
