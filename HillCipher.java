import java.util.Scanner;

public class HillCipher {
    private static final int MOD = 26; 
   
    public static String encrypt(String text, int[][] keyMatrix) {
        text = text.toUpperCase().replaceAll("[^A-Z]", ""); // Remove non-alphabetic characters
        if (text.length() % 2 != 0) text += "X"; // Pad with 'X' if needed

        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = {text.charAt(i) - 'A', text.charAt(i + 1) - 'A'};
            int[] result = matrixMultiply(keyMatrix, vector);

            encryptedText.append((char) ('A' + result[0]));
            encryptedText.append((char) ('A' + result[1]));
        }
        return encryptedText.toString();
    }


    public static String decrypt(String text, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = inverseMatrix(keyMatrix);
        if (inverseKeyMatrix == null) {
            System.out.println("The given key matrix is not invertible.");
            return null;
        }

        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = {text.charAt(i) - 'A', text.charAt(i + 1) - 'A'};
            int[] result = matrixMultiply(inverseKeyMatrix, vector);

            decryptedText.append((char) ('A' + (result[0] + MOD) % MOD));
            decryptedText.append((char) ('A' + (result[1] + MOD) % MOD));
        }
        return decryptedText.toString();
    }


    private static int[] matrixMultiply(int[][] matrix, int[] vector) {
        int[] result = new int[2];
        result[0] = (matrix[0][0] * vector[0] + matrix[0][1] * vector[1]) % MOD;
        result[1] = (matrix[1][0] * vector[0] + matrix[1][1] * vector[1]) % MOD;
        return result;
    }

  
    private static int[][] inverseMatrix(int[][] matrix) {
        int det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % MOD;
        if (det < 0) det += MOD;

        int detInverse = modInverse(det, MOD);
        if (detInverse == -1) return null; // No inverse exists

        int[][] inverseMatrix = {
            {matrix[1][1], -matrix[0][1]},
            {-matrix[1][0], matrix[0][0]}
        };

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                inverseMatrix[i][j] = ((inverseMatrix[i][j] * detInverse) % MOD + MOD) % MOD;

        return inverseMatrix;
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1; 
    }

 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] keyMatrix = {
            {3, 3},
            {2, 5}
        };

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String encryptedText = encrypt(plaintext, keyMatrix);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, keyMatrix);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }
}
