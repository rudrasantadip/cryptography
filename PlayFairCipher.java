import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlayFairCipher {
     private char[][] keySquare;
    private static final int SIZE = 5;

    // Constructor to generate the key square
    public PlayFairCipher(String key) {
        keySquare = generateKeySquare(key);
    }

    public String encrypt(String text) {
        text = prepareText(text);
        return processText(text, true);
    }


    public String decrypt(String text) {
        return processText(text, false);
    }

    // Generates the 5x5 key square
    private char[][] generateKeySquare(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        Set<Character> used = new LinkedHashSet<>();
        for (char ch : key.toCharArray()) used.add(ch);
        for (char ch = 'A'; ch <= 'Z'; ch++) if (ch != 'J') used.add(ch);

        char[][] square = new char[SIZE][SIZE];
        Iterator<Character> iter = used.iterator();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                square[i][j] = iter.next();
        return square;
    }

    // Formats the text for encryption
    private String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1))
                sb.append('X');
        }
        if (sb.length() % 2 != 0) sb.append('X');  // Ensure even length
        return sb.toString();
    }


    private String processText(String text, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] posA = findPosition(a), posB = findPosition(b);

            if (posA[0] == posB[0]) {  // Same row
                result.append(keySquare[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % SIZE]);
                result.append(keySquare[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % SIZE]);
            } else if (posA[1] == posB[1]) {  // Same column
                result.append(keySquare[(posA[0] + (encrypt ? 1 : 4)) % SIZE][posA[1]]);
                result.append(keySquare[(posB[0] + (encrypt ? 1 : 4)) % SIZE][posB[1]]);
            } else {  // Rectangle swap
                result.append(keySquare[posA[0]][posB[1]]);
                result.append(keySquare[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    // Finds the position of a letter in the key square
    private int[] findPosition(char ch) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (keySquare[i][j] == ch)
                    return new int[]{i, j};
        return null;
    }

    // Main function to test Playfair Cipher
    public static void main(String[] args) {
        String key = "KEYWORD";
        PlayFairCipher cipher = new PlayFairCipher(key);

        String plaintext = "HELLO WORLD";
        String encrypted = cipher.encrypt(plaintext);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
