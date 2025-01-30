import ciphers.CaesarCipher;

public class App {
    public static void main(String[] args) throws Exception {
        CaesarCipher cipher = new CaesarCipher(3, "hello world");

        System.out.println(cipher.encrypt());

        System.out.println(cipher.decrypt());
    }
}
