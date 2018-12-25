package encryption;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class ReadingWritingMainClass {

	public static void main(String[] args) {
		List<byte[]> fileData = new ArrayList<byte[]>();
		byte[] data = new byte[8];
		InputStream inputStream;
		try {
			
			System.out.println("CBC 128bits AES CBC encryption start");
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			System.out.println("Start of 128 bit key generation: "+ new Timestamp((new Date()).getTime()));
			SecretKey skey = keyGen.generateKey();
			System.out.println("End of 128 bit key generation: "+ new Timestamp((new Date()).getTime()));
			
			inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing2.txt"); 		//file containing plain text
			
			//adding plain text in a list of file data
			while(inputStream.read(data, 0, 8) != -1){
				fileData.add(data);
				data = new byte[8];
			}
			
			CBC128encrypt cbc128obj = new CBC128encrypt();
			
			//call to encryption function in CBC128encrption class
			cbc128obj.encryptCBC(fileData,skey);
			
			System.out.println("CBC 128bits encryption complete");
			
			System.out.println("CBC 128bits decryption start");
			
			inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/encryptedData.txt");
			
			byte[] initialVector = new byte[16]; 
			
			//reading the initialization vector from encrypted file
			inputStream.read(initialVector, 0, 16);
			
			CBC128Decryption cbc128Decryption = new CBC128Decryption();
			
			//call to decryption function in CBC128Decryption class
			cbc128Decryption.decryptCBC(inputStream, initialVector, skey);
			
			System.out.println("CBC 128bits decryption complete");
			
			//closing the input stream
			inputStream.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
}
