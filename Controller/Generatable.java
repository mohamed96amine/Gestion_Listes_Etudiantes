package Controller;


/*
 * Interface qui définit le structure d'une classe de génération.
 * @author Amine
 * @author Ayoub
 */
public interface Generatable {
	
	
	/**
	 * Définit le modèle de génération dans le fichier
	 */
	public void generate();
	
	/**
	 * Génére l'entête de la page  
	 */
	public void header();
	
	/**
	 * Génére le corps de la page  
	 */
	public void body();
	
	
	
	

}
