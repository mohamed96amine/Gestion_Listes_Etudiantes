package Model;

import junit.framework.TestCase;

public class StudentTest extends TestCase {

	
	private Student s;
	
	protected void setUp() throws Exception {
		s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("Val");
		s.setResImpair("Val");
		s.setResultat("Val");
		s.setMobilite("MobS7");
		s.setGroupe("A");
	}

	public void testEstAdmis() {
		assertTrue(s.estAdmis());
	}

}
