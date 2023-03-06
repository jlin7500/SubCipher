import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cipher
{
    //format the frequency values
    private static final DecimalFormat formatter = new DecimalFormat("0.00000");
    public static void main(String args[]) throws IOException
    {
        //setting up the cipher text and plaintext and some strings needed to store values
        Scanner kb = new Scanner((System.in));
        char[] plaintext = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k', 'l', 'm', 'n', 'o', 'p', 'q', 'r','s', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] cipherText = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k', 'l', 'm', 'n', 'o', 'p', 'q', 'r','s', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String encryptText = "";
        String decryptText = "";
        String readFile = "";
        String encryptedFile = "";

        //randomizes the cipher text
        int size = plaintext.length;
        for(int i = 0; i < size; i++)
        {
            int randomize = (int)(Math.random() * size);
            char temp = cipherText[0];
            cipherText[0] = cipherText[randomize];
            cipherText[randomize] = temp;
        }
        //prints it out to show that the cipher text has been encrypted and prompts user to enter message
        System.out.println("Plaintext = " + Arrays.toString(plaintext));
        System.out.println("CipherText = " + Arrays.toString(cipherText));
        System.out.println("Please enter a message to be encrypted, only in lowercase letters please and without punctation");
        //recieves user input, turns it to lower case and then encrypts it.
        String message = kb.nextLine();
        message.toLowerCase();
        for(int i = 0 ; i < message.length(); i++)
        {
            //checks if blank, if so adds a space to the encrypted text
            if(message.charAt(i) == ' ')
            {
                encryptText += " ";
            }
            //encrypts the function with the same index 
            else
            {
                for(int j = 0; j < 26; j ++)
                {
                    if(message.charAt(i) == plaintext[j])
                    {
                        encryptText += cipherText[j];
                    }
                }
            }
        }
        System.out.println("original message = " + message);
        System.out.println("encrypted message = " + encryptText);

        System.out.println("Decryption in process");
        //decrypts the message that was just encrypted
        for(int i = 0; i < encryptText.length(); i++)
        {
            //same as above, adds a space to the decrypted message
            if(encryptText.charAt(i) == ' ')
            {
                decryptText += " ";
            }
            else
            {
                for(int j = 0; j < 26; j++)
                {
                    if(encryptText.charAt(i) == cipherText[j])
                    {
                        decryptText += plaintext[j];
                    }
                }
            }
        }
        System.out.println("Decrypted = " + decryptText);

        System.out.println("Encrypting your file");
        //file reader that has a while the file includes a next line
        BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        while(br.readLine() != null)
        {
            //sets readFile equal to the next string that is read
            readFile = br.readLine();
            //encrypts the string just read
            for(int i = 0; i < readFile.length(); i++)
            {
                if(readFile.charAt(i) == ' ')
                {
                    encryptedFile += " ";
                }
                else
                for(int j = 0; j < 26; j++)
                {
                    if(readFile.charAt(i) == plaintext[j])
                    {
                        encryptedFile += cipherText[j];
                    }
                }
            }
        }
        //writes a output into the folder called "output.txt" and leaves a message letting you know
        try(FileWriter writer = new FileWriter("output.txt");
        BufferedWriter bw = new BufferedWriter(writer)) 
        {
            bw.write(encryptedFile);
        }
        System.out.println("A encrypted file has been written into your computer");
        //frequency value check, adds to frequency value whenver there is a matching letter
        double[] freq = new double[26];
        for(int i = 0; i < encryptedFile.length(); i++)
        {
            for(int j = 0; j < 26; j++)
            {
                if(encryptedFile.charAt(i) == plaintext[j])
                {
                    freq[j]++;
                }
            }
        }
        //divides the value to find the % frequency
        double[] frequency = new double[26];
        for(int i = 0; i < 26; i++)
        {
            frequency[i] = freq[i]/ (double)encryptedFile.length();
        }
        double value;
        char character;
        //replaces the letters to match their frequency
        for(int i = 1; i < plaintext.length; i++)
        {
            for(int j = 0; j < plaintext.length; j++)
            {
                if(freq[i] > freq[j])
                {
                    value = freq[i];
                    freq[i] = freq[j];
                    freq[j] = value;
                    character = plaintext[i];
                    plaintext[i] = plaintext[j];
                    plaintext[j] = character;
                }
            }
        }
        //prints out each letter and their frequency formmated to 5 decimal places
        for(int i = 0; i < 26; i++)
        {
            System.out.println(plaintext[i] + " " + formatter.format(frequency[i]));
        }

    }
}
