package HashedinTraining;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileHandleing {
	public void readExcel(String[] ColumnName,String fileName) throws IOException{
		List<String> li=new ArrayList<String>();
		File file = new File(System.getProperty("user.dir")+"\\Input\\"+fileName);  
		FileInputStream fis = new FileInputStream(file);  
		
		FileInputStream inputStream = new FileInputStream(file);
	    Workbook wb = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));

	    if(fileExtensionName.equals(".xlsx")){
	    wb = new XSSFWorkbook(inputStream);

	    }
	    else if(fileExtensionName.equals(".xls")){
	        wb = new HSSFWorkbook(inputStream);
	    }
	    Sheet sheet = wb.getSheetAt(0);
	    Row  row = sheet.getRow(0);
	    int rowCount = 0;
		for(int count=0;count<ColumnName.length;count++){
			int col_NumVal=0;
	        for(int i = 0; i < row.getLastCellNum(); i++)
	        {
	        	String val=row.getCell(i).getStringCellValue().toString();
	        	String val2=val.substring(0, val.length()-1);
	            if(val2.equalsIgnoreCase(ColumnName[count]))
	            {
	            	col_NumVal = i;
	            	break;
	            }
	        }
	        rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	        for (int i = 0; i < rowCount+1; i++) {
	
	            Row rowexcel = sheet.getRow(i);
	            String val=rowexcel.getCell(col_NumVal).getStringCellValue().toString();
	        	String val2=val.substring(0, val.length()-1);
	            li.add(val2);
	            
	        } 
        }
        writeExcel(li,rowCount+1,ColumnName.length);
	}
	
	public void writeExcel(List<String> li,int rowCount,int columnSize) throws IOException{
		Workbook wb = new XSSFWorkbook(); 
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMM_HHmmss");
	    String dateTime = myDateObj.format(myFormatObj);
	    XSSFSheet Wsheet = (XSSFSheet) wb.createSheet("Data");  
		
		int k=0;
		
		while(k<li.size()){
			for(int col=0;col<columnSize;col++){
				
				for(int row=0;row<rowCount;row++){
					if(col==0){
						Row createrow = Wsheet.createRow(row);
						createrow.createCell(col).setCellValue(li.get(k));
						k++;
					}
					else{
						Wsheet.getRow(row).createCell(col).setCellValue(li.get(k));
						k++;
					}
				}
			}
		}
		try (OutputStream WfileOut = new FileOutputStream(System.getProperty("user.dir")+"\\Output\\Output"+dateTime+".xlsx");) {
			wb.write(WfileOut);
        }
		System.out.println("Excel File has been created successfully."); 
		
	}

	public static void main(String[] args) throws FileNotFoundException, IOException   
	{   
		FileHandleing fh=new FileHandleing();
		String[] header = {"Region", "Item", "Units"};
		fh.readExcel(header,"ReadExcel.xlsx");
	   
	}   
		}

