package Controller;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/*
 * Classe qui définit le style des cases qui font partie d'un tableau.
 * @author Amine
 * @author Ayoub
 */
public class TableStyle extends Style{
	
	/**
	 * Constructeur
	 * @param workbook le workbook
	 * @param bold true mettre en gras , false ne pas mettre en gras
	 */
	public TableStyle(Workbook workbook,Boolean bold){
		this.style = workbook.createCellStyle();
		this.style.setBorderBottom(BorderStyle.MEDIUM);
		this.style.setBorderTop(BorderStyle.MEDIUM);
		this.style.setBorderRight(BorderStyle.MEDIUM);
		this.style.setBorderLeft(BorderStyle.MEDIUM);
        Font font = workbook.createFont();
        if(bold)
        	font.setBold(true);
        this.style.setFont(font);
	}

}
