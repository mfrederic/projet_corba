package testUnitaire;

import bdd.objetsdao.PersonneDAO;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import model.Personne;

public class PersonneDAOTest extends TestCase {

	private PersonneDAO repo = null;

	public void testFindInt() {
		assertTrue(repo.find(0)==null);
	} 

	public void testCreatePersonne() {
		Personne p = new Personne("nom", "prenom", "photo", "permanent", "accueil");
		Personne responseFunction = repo.create(p);
		System.out.println(p.equals(responseFunction));
		System.out.println(responseFunction.toString());
		assertTrue(p.equals(responseFunction));
	}

	public void testUpdatePersonne() {
		fail("Not yet implemented"); // TODO
	}

	public void testDeletePersonne() {
		fail("Not yet implemented"); // TODO
	}

	public void testGetByNomPrenom() {
		fail("Not yet implemented"); // TODO
	}

	public void testFindByPicture() {
		fail("Not yet implemented"); // TODO
	}

	protected void setUp() throws Exception {
		super.setUp();
		repo = new PersonneDAO();
	}
	protected void tearDown() throws Exception {
		super.tearDown();
		repo = null;
	}
	
	public static void main(String[] args) {
        TestRunner.run(PersonneDAOTest.class);
  }

}
