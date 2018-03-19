package Controller;



import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import Model.ListOfStudents;
import Model.Student;
/*
 * Classe qui génére les les listes d'étudiants.
 * @author Amine
 * @author Ayoub
 */

public class GenerateListeEtudiants implements Generatable{

	
	private ListOfStudents listOfStudents;
	private String promo;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	/**
	 * Constructeur
	 * @param listOfStudents la liste des étudiants 
	 * @param promo le nom de la promo
	 * @param workbook le fichier utilisé
	 */
	public GenerateListeEtudiants(ListOfStudents listOfStudents, String promo, HSSFWorkbook workbook){
		this.listOfStudents = listOfStudents;
		this.promo = promo;
		this.workbook = workbook;
		sheet = workbook.createSheet("Liste Etudiants " + promo.charAt(2));
	}
	
	@Override
	public void generate() {
		header();
		body();
	}

	
	@Override
	public void header() {
		HSSFRow row = sheet.createRow(0);
		HeaderStyle hStyle = new HeaderStyle(workbook);
		row.createCell(1).setCellValue(promo);
		row.createCell(2).setCellValue(listOfStudents.getNombreEtudiants());
		row.createCell(3).setCellValue("ETUDIANTS");
		hStyle.setStyle(sheet, 0, 1, 4);
		CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setFontName("Arial");
        font1.setFontHeightInPoints((short) (10*20));
		HSSFRow row1 = sheet.createRow(1);
		row1.createCell(1).setCellValue("ENSEIGNANT :");
		row1.getCell(1).setCellStyle(style1);
		HSSFRow row2 = sheet.createRow(3);
		row2.createCell(1).setCellValue("MATIERE :");
		row2.getCell(1).setCellStyle(style1);
		
		
		sheet.createRow(6);
		String header[] = {"N° Carte","NOM","PRENOM", "S IMPAIR", "S PAIR", "ANNEE", "MOBILITE", "GROUPE ANGLAIS"};
		for(int i = 0; i < header.length; i++){
			sheet.getRow(6).createCell(i).setCellValue(header[i]);
		}
		TableStyle ts = new TableStyle(workbook, true);
		ts.setStyle(sheet, 6, 0, 8);
		
		for(int i = 0; i < 9;i++){
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public void body() {
		
		int currentRow = 7;
		for(Student stu : listOfStudents.getListeEtudiants()){
			HSSFRow row = sheet.createRow(currentRow);
			row.createCell(0).setCellValue(stu.getNumEtu());
			row.createCell(1).setCellValue(stu.getNom());
			row.createCell(2).setCellValue(stu.getPrenom());
			row.createCell(3).setCellValue(stu.getResImpair());
			row.createCell(4).setCellValue(stu.getResPair());
			row.createCell(5).setCellValue(stu.getResultat());
			row.createCell(6).setCellValue(stu.getMobilite());
			row.createCell(7).setCellValue(stu.getGroupe());
			if(!stu.estAdmis()){
				StyleRedoublant sr = new StyleRedoublant(workbook);
				sr.setStyle(sheet, currentRow, 0, 8);
			}else if(stu.getMobilite().contains("Mob")){
				StyleMobilite sm = new StyleMobilite(workbook);
				sm.setStyle(sheet, currentRow, 0, 8);
			}else{
				TableStyle ts = new TableStyle(workbook, false);
				ts.setStyle(sheet, currentRow, 0, 8);
			}
			currentRow++;
		}
		
	}

	 
	
	


	

}
