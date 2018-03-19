package Controller;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import Model.ListOfStudents;
import Model.Student;
/*
 * Classe qui génére les listes d'affichage de notes.
 * @author Amine
 * @author Ayoub
 */

public class GenerateAffichageNotes implements Generatable{

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
	public GenerateAffichageNotes(ListOfStudents listOfStudents, String promo, HSSFWorkbook workbook){
		this.listOfStudents = listOfStudents;
		this.promo = promo;
		this.workbook = workbook;
		sheet = workbook.createSheet("Affichage Notes DI." + promo.charAt(2));
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
		row.createCell(0).setCellValue(promo + "-" + listOfStudents.getNombreEtudiants());
		row.createCell(1).setCellValue("ETUDIANTS");
		hStyle.setStyle(sheet, 0, 0, 2);
		
		HSSFRow row2 = sheet.createRow(2);
		row2.createCell(0).setCellValue("CC / ET");
		hStyle.setStyle(sheet, 2, 0, 1);
		
		TableStyle ts = new TableStyle(workbook, true);
		HSSFRow row4 = sheet.createRow(4);
		row4.createCell(0).setCellValue("Nom de l'enseignant : ");
		for(int i = 1; i < 4 ; i++){
			row4.createCell(i).setCellValue(" ");
		}
		ts.setStyle(sheet, 4, 0, 4);
		
		HSSFRow row5 = sheet.createRow(5);
		row5.createCell(0).setCellValue("Matière :");
		for(int i = 1; i < 4 ; i++){
			row5.createCell(i).setCellValue(" ");
		}
		ts.setStyle(sheet, 5, 0, 4);
		
		HSSFRow row6 = sheet.createRow(6);
		row6.createCell(0).setCellValue("Date : ");
		for(int i = 1; i < 4 ; i++){
			row6.createCell(i).setCellValue(" ");
		}
		ts.setStyle(sheet, 6, 0, 4);
	
		HSSFRow row7 = sheet.createRow(7);
		row7.createCell(0).setCellValue("Affichage : ");
		for(int i = 1; i < 4 ; i++){
			row7.createCell(i).setCellValue("  ");
		}
		ts.setStyle(sheet, 7, 0, 4);
		
		HSSFRow row8 = sheet.createRow(8);
		HSSFRow row9 = sheet.createRow(9);
		String header[] = {"N° Carte","NOM", "PRENOM", "Note/20"};
		for(int i = 0; i< header.length; i++){
			sheet.addMergedRegion(new CellRangeAddress(8,9,i,i));
			row8.createCell(i).setCellValue(header[i]);
			row9.createCell(i).setCellValue(" ");
		}
		
		ts.setStyle(sheet, 8, 0, 4);
		ts.setStyle(sheet, 9, 0, 4);
		
		sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));
		sheet.addMergedRegion(new CellRangeAddress(5,5,1,3));
		sheet.addMergedRegion(new CellRangeAddress(6,6,1,3));
		sheet.addMergedRegion(new CellRangeAddress(7,7,1,3));
	}

	@Override
	public void body() {
		int currentRow = 10;
		for(Student stu : listOfStudents.getListeEtudiants()){
			HSSFRow row = sheet.createRow(currentRow);
			row.createCell(0).setCellValue(stu.getNumEtu());
			row.createCell(1).setCellValue(stu.getNom()+"            ");
			row.createCell(2).setCellValue(stu.getPrenom()+"            ");
			row.createCell(3).setCellValue("            ");
			if(!stu.estAdmis()){
				StyleRedoublant sr = new StyleRedoublant(workbook);
				sr.setStyle(sheet, currentRow, 0, 4);
			}else if(stu.getMobilite().contains("Mob")){
				StyleMobilite sm = new StyleMobilite(workbook);
				sm.setStyle(sheet, currentRow, 0, 4);
			}else{
				TableStyle ts = new TableStyle(workbook, false);
				ts.setStyle(sheet, currentRow, 0, 4);
			}
			currentRow++;
			row.setHeight((short) (20*20));
		}
		for(int i = 0; i < 9;i++){
			sheet.autoSizeColumn(i);
		}
	}

}
