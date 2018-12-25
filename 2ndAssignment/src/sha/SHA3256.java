package sha;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi.SHA3_256;
import org.bouncycastle.jcajce.provider.digest.SHA3;

public class SHA3256 {

public static void main(String[] args) {
		
		try {
			System.out.println("Start of SHA3 256");
			SHA3.DigestSHA3 digest = new SHA3.Digest256();
			
			//read below file
			InputStream inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing2.txt");
			byte[] data = new byte[1024];
			
			while(inputStream.read(data) != -1){
				digest.update(data);
			}
			
			//calculate digest
			System.out.println("Start generate hash value" + new Timestamp((new Date()).getTime()));
			byte[] result = digest.digest();
			System.out.println("End generation of hash value" + new Timestamp((new Date()).getTime()));
			
			StringBuffer sb = new StringBuffer();
			
			//compute its hex value
			for(int i=0; i<result.length; i++){
				String hexValue = Integer.toHexString(0xff & result[i]);
				if(hexValue.length() == 1){
					sb.append('0');
				}
				sb.append(hexValue);
			}
			
			FileWriter fw = new FileWriter("C:/Users/Shivam Agarwal/Desktop/encryptedData.txt");
			
			fw.write(sb.toString());
			
			fw.flush();
			
			fw.close();
			inputStream.close();
			
			System.out.println("End of SHA3-256");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
