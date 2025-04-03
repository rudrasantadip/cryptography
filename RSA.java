import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSA {
    private static KeyPair keyPair;

    // Generate RSA key pair
    public static void generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // RSA-2048 (recommended)
        keyPair = keyGen.generateKeyPair();
    }

    // Encrypt plaintext using the public key
    public static String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64
    }

    // Decrypt ciphertext using the private key
    public static String decrypt(String ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            generateKeyPair(); // Generate RSA keys

            String plaintext = "Hello, RSA!";
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
