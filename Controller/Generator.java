package Controller;


import Model.ListOfStudents;
import Model.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;



public class Generator {
	
	
	private static String RESULT_FILE = "Listes Récapitulatives ";
	private Map<String, Object> properties;
	private String[] paths;
	private String[] mobilite;
	private String[] grpAng;
	private List<ListOfStudents> listOfGroups;
	private String outputPath;
	
	
	/**
	 * Constructeur du generateur de fichiers
	 * @param properties les propriètés des fichiers demandés par l'utilisateur
	 * @param paths les chemins des fichiers
	 * @param mobilite les chemins des fichiers de mobilité
	 * @param grpAng les chemines des fichiers de groupes d'anglais
	 * @param outputPath le chemin du fichier de sortie 
	 */
	public Generator(Map<String, Object> properties, String[] paths, String[] mobilite, String[] grpAng, String outputPath){
		this.properties = properties;
		this.paths = paths;
		this.mobilite = mobilite;
		this.grpAng = grpAng;
		listOfGroups = new ArrayList<>();
		this.outputPath = outputPath;
	}

	/**
	 * Methode qui appelle les generateurs de fichiers selon la demande de l'utilisateur
	 * @throws Exception renvoie une exception si il y'a un problème de lecture dans le fichier 
	 */
	public void generate() throws Exception   {
		createLists();
		int promoNumber = 2;
		for(Entry<String,Object> pair : properties.entrySet()){ // looping over the map 
			@SuppressWarnings("unchecked")
			Map<String,Boolean> propValues = (Map<String, Boolean>) pair.getValue();
			HSSFWorkbook resultWorkbook = new HSSFWorkbook(); // creation du fichier de sortie.
			if(propValues.get("listEtu")){
				GenerateListeEtudiants gle = new GenerateListeEtudiants(listOfGroups.get(promoNumber), pair.getKey(), resultWorkbook);
				gle.generate();
			}
			if(propValues.get("affNotes")){
				GenerateAffichageNotes gan = new GenerateAffichageNotes(listOfGroups.get(promoNumber), pair.getKey(), resultWorkbook);
				gan.generate();
			}
			if(propValues.get("emargement")){
				GenerateListeEmargement genLisEma = new GenerateListeEmargement(listOfGroups.get(promoNumber), pair.getKey(), resultWorkbook);
				genLisEma.generate();
			}
			if(propValues.get("grpAng")){
				GenerateGrpAng gra = new GenerateGrpAng(listOfGroups.get(promoNumber), pair.getKey(), resultWorkbook);
				gra.generate();
			}
			if(propValues.get("grpTd")){
				GenerateGrpTd ggt = new GenerateGrpTd(listOfGroups.get(promoNumber), pair.getKey(), resultWorkbook);
				ggt.generate();
			}
			try {
				if(outputPath != null){
					resultWorkbook.write(new FileOutputStream(outputPath+"\\"+RESULT_FILE+pair.getKey()+".xls"));
				}else{
					resultWorkbook.write(new FileOutputStream(RESULT_FILE+pair.getKey()+".xls"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				resultWorkbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			promoNumber--;
		}
		
	}
	/**
	 * Crée les listes des étudiants
	 * @throws Exception renvoie une exception si il y'a un problème de lecture dans le fichier 
	 */
	public void createLists() throws Exception  {
		String nom, prenom, resPair, resImpair, resultat;
		int numEtu;
		int fileNumber = 0;
		ListOfStudents lisEtuDI3 = new ListOfStudents(paths[0]);
		ListOfStudents lisEtuDI4 = new ListOfStudents(paths[1]);
		ListOfStudents lisEtuDI5 = new ListOfStudents(paths[2]);
		listOfGroups.add(lisEtuDI3);
		listOfGroups.add(lisEtuDI4);
		listOfGroups.add(lisEtuDI5);
		for(String path : paths){
			Map<String,Boolean> propValues = (Map<String, Boolean>) properties.get("DI"+(fileNumber+3));
			if(propValues.containsValue(true)){
				HSSFWorkbook workbook = null;
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(path);
				} catch (Exception e2) {
					throw new FileNotFoundException("Le fichier de résultat DI"+(fileNumber+3)+ " est introuvable");
				}
				try {
					workbook = new HSSFWorkbook(fis);
				} catch (Exception e2) {
					throw new Exception("Le fichier de résultat DI"+ (fileNumber+3) + " que vous avez selectionné n'est pas un fichier excel");
				}
				HSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext() ) {
					Row row = rowIterator.next();
					if(row.getRowNum() == 6)
						break;
				}
				while (rowIterator.hasNext() ) {
					Row row = rowIterator.next();
					try{
						numEtu = (int) row.getCell(0).getNumericCellValue();
						nom = row.getCell(1).getStringCellValue();
						prenom = row.getCell(2).getStringCellValue();
						resImpair = row.getCell(3).getStringCellValue();
						resPair = row.getCell(4).getStringCellValue();
						resultat = row.getCell(5).getStringCellValue();
						if(!resultat.toLowerCase().equals("aj") && !resultat.toLowerCase().equals("val")){
							throw new Exception("Erreur dans le fichier de résultat DI"+ (fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+ " à la colonne F\nUtilisez Val, VAL, val, aj, Aj ...");
						}
						if(!resImpair.toLowerCase().contains("aj") && !resImpair.toLowerCase().contains("val") ){
							throw new Exception("Erreur dans le fichier de résultat DI"+ (fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+ " à la colonne D\nUtilisez S+numero+Val ou S+numero+Aj \nExemple : S5Val, S5val, S7aj ...");
						}
						if(!resPair.toLowerCase().contains("aj") && !resPair.toLowerCase().contains("val") ){
							throw new Exception("Erreur dans le fichier de résultat DI"+ (fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+ " à la colonne E\nUtilisez S+numero+Val ou S+numero+Aj \nExemple : S6Val, S4val, S8aj ...");
						}
						if(fileNumber == 0 ){
							if(resultat.toLowerCase().equals("aj")){
								lisEtuDI3.ajouterEtudiant(new Student(nom, prenom, numEtu, resPair, resImpair, resultat," "," "));
							}else if(resultat.toLowerCase().equals("val")){
								lisEtuDI4.ajouterEtudiant(new Student(nom, prenom, numEtu, resPair, resImpair, resultat," "," "));
							}
						}else if(fileNumber == 1){
							if(resultat.toLowerCase().equals("aj")){
								lisEtuDI4.ajouterEtudiant(new Student(nom, prenom, numEtu, resPair, resImpair, resultat," "," "));
							}else if(resultat.toLowerCase().equals("val")){
								lisEtuDI5.ajouterEtudiant(new Student(nom, prenom, numEtu, resPair, resImpair, resultat," "," "));
							}
						}else if(fileNumber == 2){
							if(resultat.toLowerCase().equals("aj")){
								lisEtuDI5.ajouterEtudiant(new Student(nom, prenom, numEtu, resPair, resImpair, resultat," "," "));
							}
						}
					}catch(Exception e){
						
						if(e.getMessage() == null){
							throw new Exception ("Erreur dans le fichier de résultat DI"+(fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+" à la colonne "+(char)('A'+row.getLastCellNum()-1));
						}else{
							throw new Exception(e.getMessage());
						}			
						
					}
				}
				if(mobilite[fileNumber] != null)
					createMobiliteList(fileNumber);
				
				if(propValues.get("grpAng") || propValues.get("grpTd"))
					createGrpAnglais(fileNumber);
			}
			fileNumber++;
		}
		Collections.shuffle(lisEtuDI3.getListeEtudiants());
		
	}
	/**
	 * crée la liste des etudiants en mobilité
	 * @param fileNumber le numero du fichier a creer
	 * @throws Exception renvoie une exception si il y'a un problème de lecture dans le fichier 
	 */
	public void createMobiliteList(int fileNumber) throws Exception{
		if(mobilite[fileNumber] == null)
			return;
		
		int numEtu;
		String mobiliteFilePath = mobilite[fileNumber];
		HSSFWorkbook workbook = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(mobiliteFilePath);
		} catch (Exception e) {
			throw new FileNotFoundException("Le fichier de mobilité DI"+(fileNumber+3)+ " est introuvable");
		}
		try{
			workbook = new HSSFWorkbook(fis);
		}catch(Exception e){
			throw new Exception("Le fichier de mobilité DI"+ (fileNumber+3) + " que vous avez selectionné n'est pas un fichier excel");
		}
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext() ) {
			Row row = rowIterator.next();
			if(row.getRowNum() == 6){
				break;
			}
		}
		while (rowIterator.hasNext() ) {
			Row row = rowIterator.next();
			try{
				numEtu = (int) row.getCell(0).getNumericCellValue();
			}catch(Exception e){
				throw new Exception ("Erreur dans le fichier de mobilité DI"+(fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+" à la colonne "+(char)('A'));
			}
			ListOfStudents lisEtu = listOfGroups.get(fileNumber);
			for(Student stu : lisEtu.getListeEtudiants()){
				if(stu.getNumEtu() == numEtu){
					try{
						stu.setMobilite(row.getCell(3).getStringCellValue());
					}catch(Exception e){
						throw new Exception ("Erreur dans le fichier de mobilité DI"+(fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+" à la colonne "+(char)('A'+3));
					}
				}
			}
		}
	}
	
	
	/** 
	 * crée les listes des groupes d'anglais
	 * @param fileNumber le numero du fichier a creer
	 * @throws Exception renvoie une exception si il y'a un problème de lecture dans le fichier 
	 */
	public void createGrpAnglais(int fileNumber) throws Exception {
		if(grpAng[fileNumber] == null)
			return;
		
		int numEtu;
		String grpAngFilePath = grpAng[fileNumber];
		HSSFWorkbook workbook = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(grpAngFilePath);
		} catch (Exception e) {
			throw new FileNotFoundException("Le fichier de groupes d'anglais DI"+(fileNumber+3)+ " est introuvable");
		}
		try{
			workbook = new HSSFWorkbook(fis);
		}catch(Exception e){
			throw new Exception("Le fichier de groupes d'anglais DI"+ (fileNumber+3) + " que vous avez selectionné n'est pas un fichier excel");
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext() ) {
			Row row = rowIterator.next();
			if(row.getRowNum() == 6)
				break;
		}
		while (rowIterator.hasNext() ) {
			Row row = rowIterator.next();
			try{
				numEtu = (int) row.getCell(0).getNumericCellValue();
			}catch(Exception e){
				throw new Exception ("Erreur dans le fichier de groupes d'anglais DI"+(fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+" à la colonne "+(char)('A'+row.getLastCellNum()-1));
			}
			ListOfStudents lisEtu = listOfGroups.get(fileNumber);
			for(Student stu : lisEtu.getListeEtudiants()){
				if(stu.getNumEtu() == numEtu)
					try{
						stu.setGroupe(row.getCell(3).getStringCellValue());
					}catch(Exception e){
						throw new Exception ("Erreur dans le fichier de groupes d'anglais DI"+(fileNumber+3) + " à la ligne "+(row.getRowNum()+1)+" à la colonne "+(char)('A'+row.getLastCellNum()-1));
					}
			}
		}
	}

	
	
}
