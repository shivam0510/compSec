package encryptionAESCTR256;

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
			
			System.out.println("CBC 256 bits AES CTR encryption start");
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256);
			System.out.println("Start of 256 bit key generation: "+ new Timestamp((new Date()).getTime()));
			SecretKey skey = keyGen.generateKey();
			System.out.println("End of 256 bit key generation: "+ new Timestamp((new Date()).getTime()));
			
			inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing2.txt");
			
			//adding plain text in a list of file data
			while(inputStream.read(data, 0, 8) != -1){
				fileData.add(data);
				data = new byte[8];
			}
			
			CTR256enryption ctr256obj = new CTR256enryption();
			
			//call to encryption function in CBC128encrption class
			ctr256obj.encryptCTR(fileData,skey);
			
			System.out.println("CTR 256 bits encryption complete");
			
			System.out.println("CTR 256 bits decryption start");
			
			inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/encryptedData.txt");
			
			//reading the initialization vector from encrypted file
			byte[] initialVector = new byte[16]; 
			inputStream.read(initialVector, 0, 16);
			
			CTR256Decryption ctr256Decryption = new CTR256Decryption();
			
			//call to decryption function in CBC128Decryption class
			ctr256Decryption.decryptCTR(inputStream, initialVector, skey);
			
			System.out.println("CBC 256 bits decryption complete");
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
