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

public class SHA256 {

	public static void main(String[] args) {
			
		try {
			System.out.println("Start of SHA 256");
			MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
			
			//read below file
			InputStream inputStream = new FileInputStream("C:/Users/Shivam Agarwal/Desktop/testing2.txt");
			byte[] data = new byte[1024];
			
			while(inputStream.read(data) != -1){
				msgDigest.update(data);
			}
			
			//calculate digest
			System.out.println("Start generate hash value" + new Timestamp((new Date()).getTime()));
			byte[] result = msgDigest.digest();
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
			
			System.out.println("End of SHA 256");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
