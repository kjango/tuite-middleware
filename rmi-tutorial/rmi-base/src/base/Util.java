package base;

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
	
}
