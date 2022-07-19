package app;

import static org.junit.Assert.*;

import org.junit.Test;
import app.LoginTecnicoController;

public class LoginTecnicoControllerTest {

	@Test
	public void testuser1() {
		String prova = "CiaoProvaLogin";
		int risultato = LoginTecnicoController.ControlloInputUser(prova);
		assertEquals(risultato, 0);
	}
	@Test
	public void testuser2() {
		String prova = "";
		int risultato = LoginTecnicoController.ControlloInputUser(prova);
		assertEquals(risultato, 1);
	}
	@Test
	public void testuser3() {
		String prova = "SQL.infoversion";
		int risultato = LoginTecnicoController.ControlloInputUser(prova);
		assertEquals(risultato, 2);
	}
	@Test
	public void testuser4() {
		String prova = "DELETE * FROM *";
		int risultato = LoginTecnicoController.ControlloInputUser(prova);
		assertEquals(risultato, 2);
	}
	@Test
	public void testpass1() {
		String prova = "CiaoProvaPass";
		int risultato = LoginTecnicoController.ControlloInputPass(prova);
		assertEquals(risultato, 0);
	}
	@Test
	public void testpass2() {
		String prova = "";
		int risultato = LoginTecnicoController.ControlloInputPass(prova);
		assertEquals(risultato, 1);
	}
	@Test
	public void testpass3() {
		String prova = "DELETE *";
		int risultato = LoginTecnicoController.ControlloInputPass(prova);
		assertEquals(risultato, 2);
	}
	@Test
	public void testpass4() {
		String prova = "Breve";
		int risultato = LoginTecnicoController.ControlloInputPass(prova);
		assertEquals(risultato, 3);
	}
	@Test
	public void testpass5() {
		String prova = "tuttominuscolo";
		int risultato = LoginTecnicoController.ControlloInputPass(prova);
		assertEquals(risultato, 4);
	}

}
