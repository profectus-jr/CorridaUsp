package teste;
import dao.CorredorDao;
import java.sql.SQLException;
import java.util.Calendar;

import modelo.Corredor;

public class TestaInsere {
	public static void main(String[] args) throws SQLException{
		 // pronto para gravar
		 Corredor corredor = new Corredor();
		 corredor.setNome("Antonio Junior");
		 corredor.setEmail("tocastro@ime.usp.br");
		 corredor.setSenha("castro");
		 corredor.setData_nascimento(Calendar.getInstance());
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