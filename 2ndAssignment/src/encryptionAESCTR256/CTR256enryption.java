package encryptionAESCTR256;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CTR256enryption {

	public void encryptCTR(List<byte[]> fileData, SecretKey skey) {
	
		try {
			
			//path of file that will have the encrypted data
			FileOutputStream fileOut = new FileOutputStream("C:/Users/Shivam Agarwal/Desktop/encryptedData.txt");
			
			CipherOutputStream cipherOutput;
			
			byte[] initialVector = new byte[16];
			
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(initialVector);
			//Calculating initialization vector
			IvParameterSpec initialVectorSpec = new IvParameterSpec(initialVector);
			
			//initializing cipher mode for encryption
			Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
			
			cipher.init(Cipher.ENCRYPT_MODE, skey, initialVectorSpec);
			//writing IV into the file
			fileOut.write(initialVectorSpec.getIV());
			fileOut.flush();
			
			cipherOutput = new CipherOutputStream(fileOut, cipher);
			
			//reading cipher data from file in cipher stream and encrypting it
			System.out.println("Start of 256 bit encryption: "+ System.currentTimeMillis());
			for(int i=0; i < fileData.size(); i++){
				cipherOutput.write(fileData.get(i));
			}	
			System.out.println("End of 256 bit encryption "+ System.currentTimeMillis());
			
			//moving the encrypted text to the file
			cipherOutput.flush();
			cipherOutput.close();
			fileOut.close();
			
		}  catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
