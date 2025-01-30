package ciphers;

public class CaesarCipher 
{
    private static final String charSet="abcdefghijklmnopqrstuvwxyz";
    private Integer key;
    private String cipherText;
    private String planeText;

    public CaesarCipher(int key,String planeText)
    {
        this.planeText=planeText;
        this.key=key;
    }

    public Integer getKey() {
        return key;
    }



    public void setKey(Integer key) {
        this.key = key;
    }



    public String getCipherText() {
        return cipherText;
    }



    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }



    public String getPlaneText() {
        return planeText;
    }



    public void setPlaneText(String planeText) {
        this.planeText = planeText;
    }



    public String encrypt()
    {
        StringBuilder output = new StringBuilder();

        for(int i=0;i<this.planeText.length();i++)
        {
            output.append(charSet.charAt((charSet.indexOf(this.planeText.charAt(i))+key)%26));
        }
        this.cipherText=output.toString();
        return cipherText;
    }

    public String decrypt()
    {
        StringBuilder output = new StringBuilder();
        for(int i=0;i<this.cipherText.length();i++)
        {
           output.append(charSet.charAt(Math.abs(charSet.indexOf(cipherText.charAt(i))-key)%26));
        }
        return output.toString().replaceAll("b", " ");
    }
}
