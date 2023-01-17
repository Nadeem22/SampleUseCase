package com.nadeem.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GenerateExcel {
	
	public static void main(String [] arg) throws IOException {
		List<DataAdd> dataList = new ArrayList<DataAdd>();
		for (int i = 1; i <= 100; i++) {
		    DataAdd data = new DataAdd();
		    data.setSapCode("SAP" + i);
		    data.setSkuCode("SKU" + i);
		    data.setSkuName("SKU Name " + i);
		    data.setTargetQtyCtn(i);
		    data.setTargetQtyPcs(i * 10);
		    dataList.add(data);
		}
		
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // for xlsx file

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Data");

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create a Cell
		Cell sapCodeCell = headerRow.createCell(0);
		sapCodeCell.setCellValue("SAP Code");

		Cell skuCodeCell = headerRow.createCell(1);
		skuCodeCell.setCellValue("SKU Code");

		Cell skuNameCell = headerRow.createCell(2);
		skuNameCell.setCellValue("SKU Name");

		Cell targetQtyCtnCell = headerRow.createCell(3);
		targetQtyCtnCell.setCellValue("Target Qty(Ctn)");

		Cell targetQtyPcsCell = headerRow.createCell(4);
		targetQtyPcsCell.setCellValue("Target Qty(Pcs)");

		// Put data in the sheet
		for (int i = 0; i < dataList.size(); i++) {
		    Row dataRow = sheet.createRow(i+1);
		    dataRow.createCell(0).setCellValue(dataList.get(i).getSapCode());
		    dataRow.createCell(1).setCellValue(dataList.get(i).getSkuCode());
		    dataRow.createCell(2).setCellValue(dataList.get(i).getSkuName());
		    dataRow.createCell(3).setCellValue(dataList.get(i).getTargetQtyCtn());
		    dataRow.createCell(4).setCellValue(dataList.get(i).getTargetQtyPcs());
		}

		// Write the workbook to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("data.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workbook.write(fileOut);
		fileOut.close();
	}

}
