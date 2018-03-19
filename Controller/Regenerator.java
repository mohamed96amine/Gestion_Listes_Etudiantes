

package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import Model.ListOfStudents;
import Model.Student;
/*
 * Classe qui définit le moteur de régénaration .
 * @author Amine
 * @author Ayoub
 */
public class Regenerator {
	
	private static String RESULT_FILE = "Listes Récapitulatives ";
	private Map<String, Object> properties;
	private List<ListOfStudents> listOfGroups;
	private String[] paths;
	private String outputPath;
	
	/**
	 * Constructeur du régénerateur de fichiers
	 * @param properties les propriètés des fichiers demandés par l'utilisateur
	 * @param paths les chemins des fichiers
	 * @param outputPath le chemin du fichier de sortie 
	 */
	public Regenerator(Map<String, Object> properties, String[] paths,String outputPath){
		this.properties = properties;
		this.paths = paths;
		listOfGroups = new ArrayList<>();
		this.outputPath = outputPath;
	}
	
	/**
	 * moteur de régénération 
	 * @throws Exception renvoie une exception si il y'a un problème de lecture dans le fichier
	 */
	public void regenerate() throws Exception{
	
		createLists();
		int promoNumber = 2;
		for(Entry<String,Object> pair : properties.entrySet()){ // looping over the map 
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
			if(outputPath != null){
				resultWorkbook.write(new FileOutputStream(outputPath+"\\"+RESULT_FILE+pair.getKey()+".xls"));
			}else{
				resultWorkbook.write(new FileOutputStream(RESULT_FILE+pair.getKey()+".xls"));
			}
			resultWorkbook.close();
			promoNumber--;
		}
	}

	/**
	 * Crée la liste des étudiants 
	 * @throws Exception renvoie une exception si il y'a un problème de lecture dans le fichier
	 */
	public void createLists() throws Exception {
		String nom = null, prenom= null, resPair = null, resImpair = null, resultat = null, mobilite = null, groupe = null;
		int numEtu = 0;
		ListOfStudents lisEtuDI3 = new ListOfStudents(RESULT_FILE + " DI3.xls");
		ListOfStudents lisEtuDI4 = new ListOfStudents(RESULT_FILE + " DI4.xls");
		ListOfStudents lisEtuDI5 = new ListOfStudents(RESULT_FILE + " DI5.xls");
		listOfGroups.add(lisEtuDI3);
		listOfGroups.add(lisEtuDI4);
		listOfGroups.add(lisEtuDI5);
		int index = 0;
		for(String path : paths){
			//HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
			HSSFWorkbook workbook = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(path);
			} catch (Exception e2) {
				throw new FileNotFoundException("Le fichier "+path+ " est introuvable");
			}
			try {
				workbook = new HSSFWorkbook(fis);
			} catch (Exception e2) {
				throw new Exception("Le fichier "+ path + " que vous avez selectionné n'est pas un fichier excel");
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
					nom = row.getCell(1).getStringCellValue();
					prenom = row.getCell(2).getStringCellValue();
					resImpair = row.getCell(3).getStringCellValue();
					resPair = row.getCell(4).getStringCellValue();
					resultat = row.getCell(5).getStringCellValue();
					mobilite = row.getCell(6).getStringCellValue();
					groupe = row.getCell(7).getStringCellValue();
				}catch(Exception e){
					throw new Exception("Erreur dans le fichier "+ path + " à la ligne "+(row.getRowNum()+1)+ " à la colonne");
				}
				if(!resultat.toLowerCase().equals("aj") && !resultat.toLowerCase().equals("val")){
					throw new Exception("Erreur dans le fichier "+ path + " à la ligne "+(row.getRowNum()+1)+ " à la colonne F\nUtilisez Val, VAL, val, aj, Aj ...");
				}
				if(!resImpair.toLowerCase().contains("aj") && !resImpair.toLowerCase().contains("val") ){
					throw new Exception("Erreur dans le fichier "+ path + " à la ligne "+(row.getRowNum()+1)+ " à la colonne D\nUtilisez S+numero+Val ou S+numero+Aj \nExemple : S5Val, S5val, S7aj ...");
				}
				if(!resPair.toLowerCase().contains("aj") && !resPair.toLowerCase().contains("val") ){
					throw new Exception("Erreur dans le fichier "+ path + " à la ligne "+(row.getRowNum()+1)+ " à la colonne E\nUtilisez S+numero+Val ou S+numero+Aj \nExemple : S6Val, S4val, S8aj ...");
				}
				listOfGroups.get(index).ajouterEtudiant(new Student(nom, prenom, numEtu, resPair, resImpair, resultat, mobilite, groupe));
			}
			index++;
		}
	}
		
		
	
}
