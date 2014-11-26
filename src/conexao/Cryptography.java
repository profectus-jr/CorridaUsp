package conexao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	
	/* Criptografando a senha como sugerido em:
	* http://www.spiration.co.uk/post/1199/Java-md5-example-with-MessageDigest
	* http://blog.caelum.com.br/guardando-senhas-criptografadas-em-java/
	*/
	
	public String geraMd5(String oldPassword){
		
		try{
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(oldPassword.getBytes());
			byte messageDigest[] = algorithm.digest();
			            
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
				
			return hexString.toString();
				
		}catch(NoSuchAlgorithmException nsae){
				//FIXME Redirecionar para uma pagina de erro???        
		}
			
		//FIXME Isso deve ser assim mesmo??
		return oldPassword;
	}
}