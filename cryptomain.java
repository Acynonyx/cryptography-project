import java.io.*;
import java.util.*;
import java.lang.*;

import java.security.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptoMain
{
  public static void main(String args[])
  {
    CryptoAES AES = new CryptoAES();
    boolean keepGoing = true;
    Scanner textInput = new Scanner(System.in);
    while (keepGoing = true)
    {
      System.out.print("Please enter what you would like to do:\n" +
      " 1 Enter a new phase for a key.\n" +
      " 2 View the current key phrase.\n" +
      " 3 Create a new file.\n" +
      " 4 Delete an existing file.\n" +
      " 5 Encrypt an existing file.\n" +
      " 6 Decrypt an existing file.\n" +
      " 7 Exit the program.\n");

      String textResponse = textInput.nextLine();
      //try
      //{
        int response = Integer.parseInt(textResponse);
      //}
      //catch(NumberFormatException e)
      //{
        //System.out.println("Please enter a number.");
      //}
      switch(response)
      {
        case 1:
          System.out.println("Please enter an exactly 16 character phrase for the key: ");
          textResponse = textInput.nextLine();
          if (textResponse.length() != 16)
          {
            System.out.println("The string you entered is not 16 characters. Please try again.");
          }
          else
          {
            try
            {
	      byte[] k = textResponse.getBytes("UTF-8");
              AES.SetKey(k);
            }
	    catch (UnsupportedEncodingException e)
	    {
 	      System.out.println(e.toString());
  	    }
	  }
          break;

        case 2:
          System.out.println("The current phrase is: " + AES.GetKey());
          break;

        case 3:
          Files.CreateFile();
          break;

        case 4:
          Files.DeleteFile();
          break;

        case 5:
          System.out.println("Enter name of file to encrypt (without extension): ");
          String fileName = textInput.nextLine();
          fileName = fileName + ".txt";
          File inFile = new File(fileName);
          if (!inFile.isFile() && !inFile.canRead())
          {
            System.out.println("The file cannot be read. Please check your filename.");
            break;
          }
          System.out.println("Enter name of the desired output filename (without extension): ");
          String OutFile = textInput.nextLine();
          OutFile = OutFile + ".txt";
          File oFile = new File(OutFile);
          if (oFile.exists())
          {
            System.out.println("The file already exists. If you would like to use it, please delete if from the menu first.");
            break;
          }
          AES.EncryptFile(inFile, oFile);
          break;

        case 6:
          System.out.println("Enter name of file to decrypt (without extension): ");
          fileName = textInput.nextLine();
          fileName = fileName + ".txt";
          File inpFile = new File(fileName);
          if (!inpFile.isFile() && !inpFile.canRead())
          {
            System.out.println("The file cannot be read. Please check your filename.");
            break;
          }
          System.out.println("Enter name of the desired output filename (without extension): ");
          String OutpFile = textInput.nextLine();
          OutpFile = OutpFile + ".txt";
          File opFile = new File(OutpFile);
          if (opFile.exists())
          {
            System.out.println("The file already exists. If you would like to use it, please delete if from the menu first.");
            break;
          }
          AES.DecryptFile(inpFile, opFile);
          break;

        case 7:
          System.out.println("Exiting . . .");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid input, please enter a number between 1 and 7, (just a number)");

      }
    }
  }
}
