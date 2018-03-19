package Model;

public class Student {
	

	private int numEtu;
	private String nom;
	private String prenom;
	private String resPair;
	private String resImpair;
	private String resultat;
	private String mobilite;
	private String groupe;
	
	
	public Student(String n, String p,int nE, String rP, String rI, String res, String mob, String grp){
		nom = n;
		prenom = p;
		numEtu = nE;
		resPair = rP;
		resImpair = rI;
		resultat = res;
		mobilite = mob;
		groupe = grp;
	}
	
	public Student(){
	}
	
	public boolean estAdmis(){
		return resultat.equals("Val");
	}
	
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public int getNumEtu() {
		return numEtu;
	}


	public void setNumEtu(int numEtu) {
		this.numEtu = numEtu;
	}


	public String getResPair() {
		return resPair;
	}


	public void setResPair(String resPair) {
		this.resPair = resPair;
	}


	public String getResImpair() {
		return resImpair;
	}


	public void setResImpair(String resImpair) {
		this.resImpair = resImpair;
	}


	public String getResultat() {
		return resultat;
	}


	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public String getMobilite() {
		return mobilite;
	}

	public void setMobilite(String mobilite) {
		this.mobilite = mobilite;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	@Override
	public String toString() {
		return "Student [numEtu=" + numEtu + ", nom=" + nom + ", prenom="
				+ prenom + ", resPair=" + resPair + ", resImpair=" + resImpair
				+ ", resultat=" + resultat + ", mobilite=" + mobilite
				+ ", groupe=" + groupe + "]";
	}

	
	
	

}
