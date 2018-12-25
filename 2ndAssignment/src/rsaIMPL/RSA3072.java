package rsaIMPL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA3072 {

	public static void main(String[] args) {
		KeyPairGenerator keyPairGen;
		try {
			System.out.println("Start of RSA 3072 encryption");
			
			//generating key pair
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(3072);
			System.out.println("Start of 3072 bit key generation: "+ new Timestamp((new Date()).getTime()));
			PublicKey pubKey = keyPairGen.generateKeyPair().getPublic();
			PrivateKey priKey = keyPairGen.generateKeyPair().getPrivate();
			System.out.println("End of 3072 bit key generation: "+ new Timestamp((new Date()).getTime()));
			
			//input of text file
			InputStream inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing2.txt");
			byte[] data = new byte[222];
			
			//initializing cipher with padding
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			
			//output file
			FileOutputStream fileOut = new FileOutputStream("C:/Users/Shivam Agarwal/Desktop/encryptedData.txt");
			
			long time = 0;
			while(inputStream.read(data) != -1){
				long x = System.currentTimeMillis();
				byte[] encryptedData = cipher.doFinal(data);
				long y = System.currentTimeMillis();
				time = time + y-x;
				fileOut.write(encryptedData);
			}
			System.out.println("time taken for encryption:" + time);
			
			//moving encrypted data to a file
			fileOut.flush();
			System.out.println("End of RSA 3072 encryption");
			
			fileOut.close();
			inputStream.close();
			
			System.out.println("Start of RSA 3072 decryption");
			//path of encrypted data
			InputStream inputStreamNew = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/encryptedData.txt");
			
			//initializing decipher
			Cipher deCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
			deCipher.init(Cipher.DECRYPT_MODE, priKey);
			byte[] dataNew = new byte[222];
			
			//file that will contain decrypted data
			FileOutputStream fileOutNew = new FileOutputStream("C:/Users/Shivam Agarwal/Desktop/decryptedData.txt");

			long time1 = 0;
			while(inputStreamNew.read(dataNew) != -1){
				long x = System.currentTimeMillis();
				byte[] plainText = cipher.doFinal(dataNew);
				long y = System.currentTimeMillis();
				time1 = time1 + y-x;
				fileOutNew.write(plainText);
			}
			System.out.println("total time in decryption is:"+ time1);

			//moving deciphered data to file 
			fileOutNew.flush();
			
			System.out.println("End of RSA 3072 decryption");
			
			fileOutNew.close();
			inputStreamNew.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
}
