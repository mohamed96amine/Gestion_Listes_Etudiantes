package Model;



import java.util.ArrayList;
import java.util.List;

public class ListOfStudents {
	

	private String nom;
	private List<Student> listeEtudiants;
	private int nombreEtudiants;
	
	public ListOfStudents(String nom){
		this.nom = nom;
		this.listeEtudiants = new ArrayList<>();
		this.nombreEtudiants = 0;
	}

	public void ajouterEtudiant(Student etu){
		listeEtudiants.add(etu);
		nombreEtudiants++;
	}
	
	public void supprimerEtudiant(Student etu){
		listeEtudiants.remove(etu);
		nombreEtudiants--;
	}
	
	public List<Student> getListeAdmis(){
		List<Student> admis = new ArrayList<>();
		for(Student etu : listeEtudiants){
			if(etu.estAdmis()){
				admis.add(etu);
			}
		}
		return admis;
	}
	
	public List<Student> getListeNonAdmis(){
		List<Student> nonadmis = new ArrayList<>();
		for(Student etu : listeEtudiants){
			if(!etu.estAdmis()){
				nonadmis.add(etu);
			}
		}
		return nonadmis;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Student> getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(List<Student> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	public int getNombreEtudiants() {
		return nombreEtudiants;
	}

	public void setNombreEtudiants(int nombreEtudiants) {
		this.nombreEtudiants = nombreEtudiants;
	}
	
	public List<Student> getEtudiantsDuGrp(String grp){
		List<Student> result = new ArrayList<>();
		for(Student stu : listeEtudiants){
			if(stu.getGroupe().equals(grp)){
				result.add(stu);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return the Ang groups 
	 */
	public List<String> getAngGroups(){
		List<String> AngGrps = new ArrayList<>();
		for(Student stu : listeEtudiants){
			if(!AngGrps.contains(stu.getGroupe()) && !stu.getGroupe().equals(" ")){
				AngGrps.add(stu.getGroupe());
			}
		}
		return AngGrps;
	}
	
	@Override
	public String toString() {
		return "ListOfStudents [nom=" + nom + ", listeEtudiants="
				+ listeEtudiants + ", nombreEtudiants=" + nombreEtudiants + "]";
	}
}
