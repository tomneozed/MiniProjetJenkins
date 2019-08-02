package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.exceptions.CompteIdNotFoundException;
import main.exceptions.NegativeCreditException;
import main.impl.GestionCompteImpl;

class TestGestionCompteImpl {

	private static Properties prop;
	private static FileInputStream propFile;
	private static GestionCompteImpl gestionCompte;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		prop = new Properties();
		propFile = new FileInputStream("src/compte.properties");
		gestionCompte = new GestionCompteImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		propFile.close();
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test()
	void testCrediter() throws Exception {
		
		int compteId;
		double credit, res;
		
		compteId = 1;
		res = 120;
		credit = 20;
		
		assertTrue("CompteId : 1 , credit : 20", gestionCompte.crediter(compteId, credit) == res);
		
		try {
			compteId = 1;
			res = 80;
			credit = -20;
			gestionCompte.crediter(compteId, credit);
			fail("Expected NegativeCreditException not thrown");
		} catch (NegativeCreditException nce) {
			assertEquals(nce.getErrCode(), 1);
		}
		
		try {
			compteId = 3;
			res = 120;
			credit = 20;
			gestionCompte.crediter(compteId, credit);
			fail("Expected CompteIdNotFoundException not thrown");
		} catch (CompteIdNotFoundException cinfe) {
			assertEquals(cinfe.getErrCode(), 1);
		}
	}

	@Test
	void testDebiter() throws Exception {
		int compteId;
		double res, debit;
		
		compteId = 1;
		res = 80;
		debit = 20;
		
		try {
			assertTrue(gestionCompte.debiter(compteId, debit) == res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		compteId = 1;
		res = 80;
		debit = -20;
		
		try {
			gestionCompte.debiter(compteId, debit);
			fail("Expected NegativeCreditException not thrown");
		} catch (NegativeCreditException nce) {
			assertEquals(nce.getErrCode(), 2);
		}
		
	}

	@Test
	void testConsulter() throws Exception {
		int compteId;
		
		compteId = 3;
		
		try {
			gestionCompte.consulter(compteId);
			fail("Expected CompteIdNotFoundException not thrown");
		} catch(CompteIdNotFoundException cinfe) {
			assertEquals(cinfe.getErrCode(), 1);
		}
	}
}
