import ciphers.PlayFairCipher;

public class App {
    public static void main(String[] args) throws Exception {
        String plainText = "MONARCHY";
        PlayFairCipher cipher = new PlayFairCipher(plainText);
        cipher.printKeyMatrix();
    }
}
