package ciphers;

public class PlayFairCipher {

    private char[][] keyMatrix;

    public PlayFairCipher(String plainText)
    {
        this.keyMatrix = new char[5][5];
        int count=0;
        for(int i=0;i<5 && count<plainText.length();i++)
        {
            for(int j=0;j<5 && count<plainText.length();j++)
            {
                keyMatrix[i][j]=plainText.charAt(count);
                count++;
            }
        }
        
    }


    public void printKeyMatrix()
    {
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                System.out.print(this.keyMatrix[i][j]+" , ");
            }
            System.out.println();
        }
    }

    public char[][] getKeyMatrix()
    {
        return keyMatrix;
    }
}
