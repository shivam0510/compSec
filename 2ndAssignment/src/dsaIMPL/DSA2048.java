package dsaIMPL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.Timestamp;
import java.util.Date;

public class DSA2048 {

	public static void main(String[] args) {
		KeyPairGenerator keyPairGen;
		try {
			
			System.out.println("Start of DSA 2048 signing");
			//generate keys
			keyPairGen = KeyPairGenerator.getInstance("DSA","SUN");
			keyPairGen.initialize(2048);
			System.out.println("Start of 256 bit key generation: "+ new Timestamp((new Date()).getTime()));
			PublicKey pubKey = keyPairGen.generateKeyPair().getPublic();
			PrivateKey priKey = keyPairGen.generateKeyPair().getPrivate();
			System.out.println("End of 256 bit key generation: "+ new Timestamp((new Date()).getTime()));
			
			//initialize signature using private key
			Signature signature = Signature.getInstance("SHA256withDSA","SUN");
			signature.initSign(priKey);
			
			InputStream is = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing.txt");
			byte[] data = new byte[1024];
			//reading plain text
			while(is.read(data) != -1){
				signature.update(data);
			}
			
			//sign generated
			System.out.println("Start of signature genrate: "+ System.currentTimeMillis());
			byte[] signGenerated = signature.sign();
			System.out.println("End of signature genrate "+ System.currentTimeMillis());
			
			FileOutputStream fileOut = new FileOutputStream("C:/Users/Shivam Agarwal/Desktop/signature.txt");
			fileOut.write(signGenerated);
			
			System.out.println("End of DSA 2048 signing");
			
			System.out.println("Start of DSA 2048 verify sign");
			//Initializing signature
			Signature signatureNew = Signature.getInstance("SHA256withDSA","SUN");
			signatureNew.initVerify(pubKey);
			
			InputStream isNew = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing2.txt");
			byte[] dataNew = new byte[1024];
			//reading text file
			while(isNew.read(dataNew) != -1){
				signatureNew.update(dataNew);
			}
			//Verifying signature
			System.out.println("Start of signature verification: "+ System.currentTimeMillis());
			signatureNew.verify(signGenerated);
			System.out.println("End of signature genrate: "+ System.currentTimeMillis());
			System.out.println("End of DSA 2048 verify sign");
			is.close();
			isNew.close();
			fileOut.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
}
