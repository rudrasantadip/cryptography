import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AESAlgorithm {
    private static SecretKey secretKey;

  
    public static void generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();
    }

    public static String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64 for readability
    }

    public static String decrypt(String ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            generateKey();

            String plaintext = "Hello, AES!";
            System.out.println("Plaintext: " + plaintext);

            String encryptedText = encrypt(plaintext);
            System.out.println("Encrypted: " + encryptedText);

            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
