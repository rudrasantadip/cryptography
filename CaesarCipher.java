public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // Decrypts text using a shift value
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        String message = "Hello, World!";
        int shift = 3;

        String encrypted = encrypt(message, shift);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted: " + decrypted);
    }
}
