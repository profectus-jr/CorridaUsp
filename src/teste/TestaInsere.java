package teste;
import dao.CorredorDao;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import modelo.Corredor;

public class TestaInsere {
	public static void main(String[] args) throws SQLException{
		 // pronto para gravar
		
		String startDateString = "27-11-2007";
	    DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
	    java.util.Date startDate = null;
	    try {
	        startDate = df.parse(startDateString);
	        String newDateString = df.format(startDate);
	        System.out.println(newDateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		
		 Corredor corredor = new Corredor();
		 corredor.setNome("Exemplo B");
		 corredor.setEmail("toexemplob@ime.usp.br");
		 corredor.setSenha("castrob");
		 corredor.setData_nascimento(startDate);
		 corredor.setAltura(1.95f);
		 corredor.setPeso(85);
		 corredor.setSexo('M');
		 // grave nessa conexão!!!
		 CorredorDao dao = new CorredorDao();
		 
		 // método elegante
		 dao.adiciona(corredor);
		 
		 System.out.println("Novo corredor gravado com sucesso!");
	}


}