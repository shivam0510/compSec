package encryptionAESCTR;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CTR128Decryption {
	
	public boolean decryptCTR(InputStream inputStream, byte[] IV, SecretKey skey) {

			try {
				//file path for decrypted text
				FileWriter fileOut = new FileWriter("C:/Users/Shivam Agarwal/Desktop/decryptedData.txt");	//new file path
				
				CipherInputStream cipherInputStream;
				IvParameterSpec initialVectorSpec = new IvParameterSpec(IV);
				
				//initializing cipher
				Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
				
				cipher.init(Cipher.DECRYPT_MODE, skey, initialVectorSpec);
				System.out.println("Start of 128 bit decryption: "+Instant.now());
				
				cipherInputStream = new CipherInputStream(inputStream, cipher);
				
				InputStreamReader isr = new InputStreamReader(cipherInputStream);
				System.out.println("End of 128 bit decryption:" +Instant.now());
				
				//moving decrypted - plaintext back to file
				BufferedReader br = new BufferedReader(isr);
				String data;
				
				while(null != (data = br.readLine())){
					fileOut.write(data);
				}
				
				br.close();
				fileOut.flush();
				fileOut.close();
				inputStream.close();
				
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
			return true;
	}
}
