package Controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import Model.*;

/*
 * Classe qui génére les groupes d'anglais.
 * @author Amine
 * @author Ayoub
 */
public class GenerateGrpAng implements Generatable{

	private ListOfStudents listOfStudents;
	private String promo;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private Map<String,String> enseignants;
	/**
	 * Constructeur
	 * @param listOfStudents la liste des étudiants 
	 * @param promo le nom de la promo
	 * @param workbook le fichier utilisé
	 */
	public GenerateGrpAng(ListOfStudents listOfStudents, String promo, HSSFWorkbook workbook){
		this.listOfStudents = listOfStudents;
		this.promo = promo;
		this.workbook = workbook;
		sheet = workbook.createSheet("DI." + promo.charAt(2) +" Anglais");
		enseignants = new HashMap<String, String>();
		enseignants.put("A", "Enseignant : Sarah WARDEN\nSalle : 228-229 au DI" );
		enseignants.put("B", "Enseignant : Brynhild Drain\nSalle : 113 à 116 au DAE");
		enseignants.put("C", "Enseignant : Brynhild Drain\nSalle : 113 à 116 au DAE" );
		enseignants.put("D", "Enseignant : Tifaine Bachet\nSalle : 228-229 au DI");
		enseignants.put("E", "Enseignant : Marie-Anne Lachance\nSalle : 228-229 au DI");
	}
	
	@Override
	public void generate() {
		header();
		body();
		
	}

	@Override
	public void header() {
		
		// Title (Groupes d'anglais) 
		sheet.createRow(0);
		CellRangeAddress cra = new CellRangeAddress(0,0,1,3);
		sheet.addMergedRegion(cra);
		RegionUtil.setBorderTop(BorderStyle.MEDIUM, cra, sheet);
		RegionUtil.setBorderLeft(BorderStyle.MEDIUM, cra, sheet);
		RegionUtil.setBorderRight(BorderStyle.MEDIUM, cra, sheet);
		RegionUtil.setBorderBottom(BorderStyle.MEDIUM, cra, sheet);
		sheet.getRow(0).createCell(1).setCellValue("Groupes d'anglais");
		
		
		HSSFCellStyle tableHeader = workbook.createCellStyle();
		tableHeader.setVerticalAlignment(VerticalAlignment.JUSTIFY);
		tableHeader.setAlignment(HorizontalAlignment.CENTER);
		StyleGrpAng sga = new StyleGrpAng(workbook);
		sga.setStyle(sheet, 0, 1, 4);
		
		
		ArrayList<String> AngGrps = (ArrayList<String>) listOfStudents.getAngGroups();
		int nbAngGrps =  AngGrps.size();
		sheet.createRow(2);
		sheet.getRow(2).setHeight((short) (20*30));
		int size = nbAngGrps / 2;
		if(nbAngGrps % 2 != 0)
			size += 1;
		
		for(int i = 0; i < size; i++){
			sheet.getRow(2).createCell(3*i).setCellValue("Le groupe " +(i+1)+ " de TD/TP d'info est\ncomposé des groupes "+(char)('A'+i*2)+" et "+(char)('A'+i*2+1));
			sheet.getRow(2).getCell(3*i).setCellStyle(tableHeader);
			sheet.addMergedRegion(new CellRangeAddress(2,2,i*3,i*3+1));
		}
		
		for(int i = 0; i< 15 ; i++ ){
			sheet.setColumnWidth(i, (short)(20*200));
		}
	}

	@Override
	public void body() {
		ArrayList<String> AngGrps = (ArrayList<String>) listOfStudents.getAngGroups();
		Collections.sort(AngGrps);
		int nbAngGrps =  AngGrps.size();
		int currentRow = 4;
		int firstCol, secondCol, indice;
		for(int i = 0; i < nbAngGrps; i++){
			if(i % 2 == 0){
				currentRow = 4;
				firstCol = i + (i/2);
				secondCol = firstCol + 1;
			}else{
				currentRow += 3;
				indice = i-1;
				firstCol = indice + (indice/2);
				secondCol = firstCol +1  ;
			}
			currentRow = generateGroupe(AngGrps.get(i), currentRow, firstCol, secondCol);
		}	
	}

	
	public int generateGroupe(String grp,int currentRow, int firstCol, int secondCol){
		HSSFRow cuRow;
		if(sheet.getRow(currentRow) == null)
			sheet.createRow(currentRow);
		
		sheet.getRow(currentRow).createCell(firstCol).setCellValue("Groupe " + grp);
		sheet.getRow(currentRow).createCell(secondCol).setCellValue("Groupe " + grp);
		StyleGrpAng sga = new StyleGrpAng(workbook);
		sga.setStyle(sheet, currentRow, firstCol, secondCol+1);
		sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, firstCol, secondCol));
		currentRow++;
		if(sheet.getRow(currentRow) == null)
			sheet.createRow(currentRow);
		
		sheet.getRow(currentRow);
		TableStyle ts = new TableStyle(workbook, true);
		sheet.getRow(currentRow).createCell(firstCol).setCellValue("NOM");
		sheet.getRow(currentRow).createCell(secondCol).setCellValue("PRENOM");
		ts.setStyle(sheet, currentRow, firstCol, secondCol+1);
		
		currentRow++;
		for(Student stu : listOfStudents.getListeEtudiants()){
			if(stu.getGroupe().equals(grp)){
				if(!stu.getMobilite().contains("Mob")){
					if(sheet.getRow(currentRow) == null){
						cuRow = sheet.createRow(currentRow);
					}
					cuRow = sheet.getRow(currentRow);
					cuRow.createCell(firstCol).setCellValue(stu.getNom());
					cuRow.createCell(secondCol).setCellValue(stu.getPrenom());
					if(!stu.estAdmis()){
						StyleRedoublant sr = new StyleRedoublant(workbook);
						sr.setStyle(sheet, currentRow, firstCol, secondCol+1);
					}else{
						ts = new TableStyle(workbook, false);
						ts.setStyle(sheet, currentRow, firstCol, secondCol+1);
					}
					currentRow++;
				}
			}
		}
		CellRangeAddress cra = new CellRangeAddress(currentRow, currentRow+1, firstCol, secondCol);
		sheet.addMergedRegion(cra);
		if(sheet.getRow(currentRow) == null)
			sheet.createRow(currentRow);
		
		if(sheet.getRow(currentRow+1) == null)
			sheet.createRow(currentRow+1);
		
		HSSFCellStyle lStyle1 = workbook.createCellStyle();
		lStyle1.setAlignment(HorizontalAlignment.CENTER);
		lStyle1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
		sheet.getRow(currentRow).createCell(firstCol).setCellValue(enseignants.get(grp));
		sheet.getRow(currentRow).getCell(firstCol).setCellStyle(lStyle1);
		RegionUtil.setBorderTop(BorderStyle.DOUBLE, cra, sheet);
		RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cra, sheet);
		RegionUtil.setBorderRight(BorderStyle.DOUBLE, cra, sheet);
		RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cra, sheet);
		return currentRow + 2;
	}
	
	
	
	
}
