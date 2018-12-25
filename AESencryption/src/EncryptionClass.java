import java.util.Date;
import java.sql.Timestamp;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionClass {
	
	public static void main(String[] args) throws Exception {
	
		byte[] plainText = "Two One Nine Two".getBytes("UTF-8");
		byte[] key = new byte[16];
		
		key = "Thats my Kung Fu".getBytes("UTF-8"); 
		
		//System.out.println(plainText.toString());
		
		System.out.println("Encryption started at : " + new Timestamp((new Date()).getTime()) );
		
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		for(int i=0; i < 1000; i++){
			byte[] cipherText = new byte[cipher.getOutputSize(plainText.length)];
			
			//this is for continuation of encryption
			int ctLength = cipher.update(plainText, 0, plainText.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);
			//System.out.println(new String(cipherText).getBytes("UTF-8").toString());
		}
		
		//;
		System.out.println("Encryption ended: " + new Timestamp((new Date()).getTime()));
		 
	}

}
