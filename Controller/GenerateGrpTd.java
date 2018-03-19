package Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import Model.ListOfStudents;
import Model.Student;


/*
 * Classe qui génére les groupes de TDs.
 * @author Amine
 * @author Ayoub
 */
public class GenerateGrpTd implements Generatable {

	private static final int NB_GRPS = 3;
	private ListOfStudents listOfStudents;
	private String promo;
	private HSSFWorkbook workbook;
	private List<HSSFSheet> sheets;
	private int size;
	private int nbAngGrps;
	private ArrayList<String> AngGrps;
	
	/**
	 * Constructeur
	 * @param listOfStudents la liste des étudiants 
	 * @param promo le nom de la promo
	 * @param workbook le fichier utilisé
	 */
	public GenerateGrpTd(ListOfStudents listOfStudents, String promo, HSSFWorkbook workbook){
		this.listOfStudents = listOfStudents;
		this.promo = promo;
		this.workbook = workbook;
		sheets = new ArrayList<HSSFSheet>();
		
		AngGrps = (ArrayList<String>) listOfStudents.getAngGroups();
		nbAngGrps =  AngGrps.size();
		size = nbAngGrps / 2;
		if(nbAngGrps % 2 != 0)
			size += 1;
		for(int idx = 0; idx < size; idx++){
			sheets.add(workbook.createSheet("DI." + promo.charAt(2) + "-" + (idx+1) ));
		}
	}
	
	
	@Override
	public void generate() {
		header();
		body();

	}

	@Override
	public void header() {
		HSSFRow row, row1, row2, row3, row4, row5, row6, row8;
		HeaderStyle hStyle = new HeaderStyle(workbook);
		TableStyle ts = new TableStyle(workbook, true);
		
		for(int idx = 0; idx < sheets.size() ; idx++){
			row = sheets.get(idx).createRow(0);
			row.createCell(0).setCellValue(promo+" - Gr "+(idx+1));
			row.createCell(1).setCellValue(listOfStudents.getEtudiantsDuGrp(String.valueOf((char)('A'+idx*2))).size()+listOfStudents.getEtudiantsDuGrp(String.valueOf((char)('A'+idx*2+1))).size());
			row.createCell(2).setCellValue("ETUDIANTS");
			hStyle.setStyle(sheets.get(idx), 0, 0, 3);
			CellStyle style1 = workbook.createCellStyle();
	        Font font1 = workbook.createFont();
	        font1.setFontName("Arial");
	        font1.setFontHeightInPoints((short) (10*20));
	        
			row1 = sheets.get(idx).createRow(1);
			row1.createCell(1).setCellValue("ENSEIGNANT :");
			row1.getCell(1).setCellStyle(style1);
			
			row2 = sheets.get(idx).createRow(2);
			row2.createCell(1).setCellValue("SALLE :");
			row2.getCell(1).setCellStyle(style1);
			
			row3 = sheets.get(idx).createRow(3);
			row3.createCell(1).setCellValue("MATIERE :");
			row3.getCell(1).setCellStyle(style1);
			
			row4 = sheets.get(idx).createRow(4);
			row4.createCell(1).setCellValue("Date Et Heure :");
			row4.getCell(1).setCellStyle(style1);
			
			row5 = sheets.get(idx).createRow(5);
			row5.createCell(2).setCellValue("GROUPE " + (idx+1));
			row5.getCell(2).setCellStyle(style1);
			sheets.get(idx).addMergedRegion( new CellRangeAddress(5,5,0,5));
			
			
			row6 = sheets.get(idx).createRow(7);
			String header[] = {"N° Carte","NOM", "PRENOM", "Date Séance","Date Séance", "Date Séance", "Date Séance"};
			
			sheets.get(idx).addMergedRegion(new CellRangeAddress(7,8,0,0));
			sheets.get(idx).addMergedRegion(new CellRangeAddress(7,8,1,1));
			sheets.get(idx).addMergedRegion(new CellRangeAddress(7,8,2,2));
			for(int i = 0; i< header.length; i++){
				row6.createCell(i).setCellValue(header[i]);
			}
			row8 = sheets.get(idx).createRow(8);
			for(int i = 0; i< header.length; i++){
				row8.createCell(i).setCellValue(" ");
			}
			ts.setStyle(sheets.get(idx), 7, 0, 7);
			ts.setStyle(sheets.get(idx), 8, 0, 7);
			
			for(int i = 0; i < 9;i++){
				sheets.get(idx).autoSizeColumn(i);
			}
		}

	}

	@Override
	public void body() {
		HSSFRow row;
		ArrayList<Student> studentList;
		int currentRow;
		for(int idx = 0; idx < sheets.size() ; idx++){
			studentList = (ArrayList<Student>) listOfStudents.getEtudiantsDuGrp(String.valueOf((char)('A'+idx*2)));
			studentList.addAll(listOfStudents.getEtudiantsDuGrp(String.valueOf((char)('A'+idx*2+1))));
			currentRow = 9;
			for(Student stu : studentList){
				row = sheets.get(idx).createRow(currentRow);
				row.createCell(0).setCellValue(stu.getNumEtu());
				row.createCell(1).setCellValue(stu.getNom());
				row.createCell(2).setCellValue(stu.getPrenom());
				row.createCell(3).setCellValue(" ");
				row.createCell(4).setCellValue(" ");
				row.createCell(5).setCellValue(" ");
				row.createCell(6).setCellValue(" ");
				if(!stu.estAdmis()){
					StyleRedoublant sr = new StyleRedoublant(workbook);
					sr.setStyle(sheets.get(idx), currentRow, 0, 7);
				}else if(stu.getMobilite().contains("Mob")){
					StyleMobilite sm = new StyleMobilite(workbook);
					sm.setStyle(sheets.get(idx), currentRow, 0, 7);
				}else{
					TableStyle ts = new TableStyle(workbook, false);
					ts.setStyle(sheets.get(idx), currentRow, 0, 7);
				}
				currentRow++;
			}
		}
	}
	
	
}
