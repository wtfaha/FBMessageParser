package ntou.FBParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class Output {
	private final HSSFWorkbook workbook;
	private final HSSFSheet[] sheet;

	public Output() {
		workbook = new HSSFWorkbook();
		sheet = new HSSFSheet[6];
			
		for(int i = 1; i <= 6; i++){
			if(i==1)
				sheet[i-1] =	workbook.createSheet("Group1");
			else if(i==2)
				sheet[i-1] =	workbook.createSheet("Group2");
			else if(i==3)
				sheet[i-1] =	workbook.createSheet("Group3");
			else if(i==4)
				sheet[i-1] =	workbook.createSheet("Group4");
			else if(i==5)
				sheet[i-1] =	workbook.createSheet("Group5");
			else if(i==6)
				sheet[i-1] =	workbook.createSheet("Group6");
		} 
	}

	public void insert(int i, ArrayList<ArrayList<String>> sources) {
		int rownum = 0;
		for (ArrayList<String> source : sources) {
			Row row = sheet[i].createRow(rownum++);
			int cellnum = 0;
			for (String item : source) {
				Cell cell = row.createCell(cellnum++);
				
				//set cs
				HSSFCellStyle cellStyle=workbook.createCellStyle();     
				cellStyle.setWrapText(true);     //自動換行
				cell.setCellStyle(cellStyle);
				
				//HSSFRichTextString 讀到\n在儲存格裡換行
				cell.setCellValue(new HSSFRichTextString(item));
			}
		}
	}

	public void wirteFile() {
		try {
		    FileOutputStream out = new FileOutputStream(new File("E:\\fbMessage\\FB.xls"));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
}
