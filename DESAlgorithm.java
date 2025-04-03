import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DESAlgorithm {
    private static SecretKey secretKey;

  
    public static void generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        secretKey = keyGenerator.generateKey();
    }


    public static String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Encode to Base64 for readability
    }


    public static String decrypt(String ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            generateKey();

            String plaintext = "Hello, DES!";
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
