package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Util {

	/**
	 * Gera senha codificada em MD5
	 * @param Senha_Plaintext: Formato "puro" da senha
	 * @return: Formato codificado MD5
	 */
    public static String GeraMD5(String Senha_Plaintext)
    {
        try {
            byte[] byteArray = Senha_Plaintext.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(byteArray));
            String senha_MD5 = hash.toString(16);
            return senha_MD5;
        } catch (Exception e){

        }
        return "";
    }
    
    public static String getServerAddress(){
		String serverAddress = null;// = "localhost";
		String line = null;
		String[] splt;
		File f = new File("config.txt");
		
		try {
			//se o config.txt n existe, eu crio ele
			if (f.createNewFile()){
				FileWriter fw = new FileWriter(f);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("SERVER_ADDRESS=localhost");
				pw.close();
				fw.close();
			}
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null){
				if ((line != null) && (!line.equals("")) && (line.length() > 0))
                {
                    splt = line.split("=");

                    //evitar uma possível linha em branco
                    if ((splt != null) && (splt[0].equals("SERVER_ADDRESS")))
                    {
                    	serverAddress = splt[1];
                    }
                    break;
                 }
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverAddress;
	}
	
}
