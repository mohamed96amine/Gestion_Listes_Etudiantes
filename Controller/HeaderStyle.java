
package Controller;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.ss.usermodel.Workbook;
/*
 * Classe qui définit le style des cases de l'entête des pages.
 * @author Amine
 * @author Ayoub
 */

public class HeaderStyle extends Style {
	
	
	/**
	 * Constructeur
	 * @param workbook le workbook
	 */
	public HeaderStyle(Workbook workbook){
		this.style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeight((short)(14*20));
        font.setColor(HSSFColor.PINK.index);
        font.setBold(true);
        this.style.setFont(font);
	}
	

}
