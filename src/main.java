import main.impl.GestionCompteImpl;
import main.interfaces.GestionCompte;
import main.pojo.Compte;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GestionCompte gc = new GestionCompteImpl();
		Compte c = new Compte(1, "bonjour", 300.0);
		
		try {
			gc.consulter(2);
			gc.create(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
