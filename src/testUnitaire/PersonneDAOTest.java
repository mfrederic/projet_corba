package testUnitaire;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import bdd.objetsdao.PersonneDAO;

public class PersonneDAOTest extends TestCase {

	private PersonneDAO repo = null;

	public void testFindInt() {
		fail("Not yet implemented"); // TODO
	}

	public void testCreatePersonne() {
		fail("Not yet implemented"); // TODO
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
