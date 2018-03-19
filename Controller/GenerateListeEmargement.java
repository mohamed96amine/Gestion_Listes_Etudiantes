package Controller;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import Model.ListOfStudents;
import Model.Student;

/*
 * Classe qui génére les groupes d'anglais.
 * @author Amine
 * @author Ayoub
 */
public class GenerateListeEmargement implements Generatable {

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
	public GenerateListeEmargement(ListOfStudents listOfStudents, String promo, HSSFWorkbook workbook){
		this.listOfStudents = listOfStudents;
		this.promo = promo;
		this.workbook = workbook;
		sheet = workbook.createSheet("Emargement Cours DI." + promo);
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
		
		HSSFRow row2 = sheet.createRow(2);
		row2.createCell(1).setCellValue("SALLE :");
		row2.getCell(1).setCellStyle(style1);
		
		HSSFRow row3 = sheet.createRow(3);
		row3.createCell(1).setCellValue("MATIERE :");
		row3.getCell(1).setCellStyle(style1);
		
		HSSFRow row4 = sheet.createRow(4);
		row4.createCell(1).setCellValue("Date Et Heure :");
		row4.getCell(1).setCellStyle(style1);
		
		CellStyle style7 = workbook.createCellStyle();
        Font font7 = workbook.createFont();
        font7.setBold(true);
        style7.setFont(font7);
		HSSFRow row6 = sheet.createRow(5);
		String header[] = {"N° Carte","NOM", "PRENOM", "Date Séance","Date Séance", "Date Séance", "Date Séance"};
		
		sheet.addMergedRegion(new CellRangeAddress(5,6,0,0));
		sheet.addMergedRegion(new CellRangeAddress(5,6,1,1));
		sheet.addMergedRegion(new CellRangeAddress(5,6,2,2));
		for(int i = 0; i< header.length; i++){
			row6.createCell(i).setCellValue(header[i]);
		}
		HSSFRow row8 = sheet.createRow(6);
		for(int i = 0; i< header.length; i++){
			row8.createCell(i).setCellValue(" ");
		}
		TableStyle ts = new TableStyle(workbook, true);
		ts.setStyle(sheet, 5, 0, 7);
		ts.setStyle(sheet, 6, 0, 7);
		
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
			row.createCell(3).setCellValue(" ");
			row.createCell(4).setCellValue(" ");
			row.createCell(5).setCellValue(" ");
			row.createCell(6).setCellValue(" ");
			if(!stu.estAdmis()){
				StyleRedoublant sr = new StyleRedoublant(workbook);
				sr.setStyle(sheet, currentRow, 0, 7);
			}else if(stu.getMobilite().contains("Mob")){
				StyleMobilite sm = new StyleMobilite(workbook);
				sm.setStyle(sheet, currentRow, 0, 7);
			}else{
				TableStyle ts = new TableStyle(workbook, false);
				ts.setStyle(sheet, currentRow, 0, 7);
			}
			currentRow++;
		}
	}
	
	
	

}
