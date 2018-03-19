package Controller;


/*
 * Interface qui d�finit le structure d'une classe de g�n�ration.
 * @author Amine
 * @author Ayoub
 */
public interface Generatable {
	
	
	/**
	 * D�finit le mod�le de g�n�ration dans le fichier
	 */
	public void generate();
	
	/**
	 * G�n�re l'ent�te de la page  
	 */
	public void header();
	
	/**
	 * G�n�re le corps de la page  
	 */
	public void body();
	
	
	
	

}
