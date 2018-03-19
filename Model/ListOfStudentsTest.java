package Model;

import org.junit.Before;

import junit.framework.TestCase;

public class ListOfStudentsTest extends TestCase {

	private ListOfStudents lof ;
	
	@Before
	public void setUp(){
		 lof = new ListOfStudents("test");
	}
	public void testListOfStudents() {
		assertEquals(lof.getNom(), "test");
	}

	public void testAjouterEtudiant() {
		Student s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("ADM");
		s.setResImpair("ADM");
		s.setMobilite("MobS7");
		s.setGroupe("A");
		lof.ajouterEtudiant(s);
		assertTrue(lof.getListeEtudiants().get(0).equals(s));
		
	}

	public void testSupprimerEtudiant() {
		Student s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("ADM");
		s.setResImpair("ADM");
		s.setResultat("ADM");
		s.setMobilite("MobS7");
		s.setGroupe("A");
		lof.ajouterEtudiant(s);
		
		assertTrue(lof.getListeEtudiants().get(0).equals(s));
		lof.supprimerEtudiant(s);
		assertTrue(lof.getListeEtudiants().isEmpty());
	}

	public void testGetListeAdmis() {
		Student s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("S6Val");
		s.setResImpair("S5Val");
		s.setResultat("Val");
		s.setMobilite("MobS7");
		s.setGroupe("A");
		lof.ajouterEtudiant(s);
		
		assertTrue(lof.getListeAdmis().contains(s));
		
	}

	public void testGetListeNonAdmis() {
		Student s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("S6Val");
		s.setResImpair("S5AJ");
		s.setResultat("AJ");
		s.setMobilite("MobS7");
		s.setGroupe("A");
		lof.ajouterEtudiant(s);
		
		assertTrue(lof.getListeNonAdmis().contains(s));
	}

	public void testGetEtudiantsDuGrp() {
		Student s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("S6Val");
		s.setResImpair("S5AJ");
		s.setResultat("AJ");
		s.setMobilite("MobS7");
		s.setGroupe("A");
		lof.ajouterEtudiant(s);
		
		assertTrue(lof.getEtudiantsDuGrp("A").contains(s));
	}

	public void testGetAngGroups() {
		Student s = new Student();
		s.setNom("nom");
		s.setPrenom("prenom");
		s.setNumEtu(4000);
		s.setResPair("S6Val");
		s.setResImpair("S5AJ");
		s.setResultat("AJ");
		s.setMobilite("MobS7");
		s.setGroupe("A");
		lof.ajouterEtudiant(s);
		
		lof.getAngGroups().contains("A");
	}

}
