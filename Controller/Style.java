package Controller;



import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
/*
 * Classe qui définit la structure générale d'un style de case.
 * @author Amine
 * @author Ayoub
 */

public abstract class Style {

	
	protected CellStyle style;
	/**
	 * Fonction qui applique le style a un ensemble de cases 
	 * @param sheet la page 
	 * @param currentRow la ligne courante 
	 * @param firstColumn la premiere colonne 
	 * @param lastColumn la deuxieme colonne
	 */
	public void setStyle(Sheet sheet, int currentRow, int firstColumn, int lastColumn){
		Row row = sheet.getRow(currentRow);
		for(int column = firstColumn; column < lastColumn; column++){
			row.getCell(column).setCellStyle(style);
		}
		row.setHeight((short)(20*20));
	}
	
	/**
	 * change le style 
	 * @param style Un nouveau style
	 */
	public void changeStyle(CellStyle style){
		this.style = style;
	}
}
