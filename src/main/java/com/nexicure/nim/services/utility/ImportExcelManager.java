package com.nexicure.nim.services.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.nexicure.es.services.common.StringUtils;

@Service
public class ImportExcelManager {
	private Logger logger = LogManager.getLogger(ImportExcelManager.class);
			
	public ArrayList<HashMap> dataList;

	
	public ArrayList getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}

	public void init() {
        if(dataList == null) {
        	dataList = new ArrayList();
        } else {
        	dataList.clear();
        }
	}

	public void readExcelXLS(File excel) {
		FileInputStream fis = null;
        try {
            //ctxRoot = excel.substring(0, excel.lastIndexOf("/"));
        	fis = new FileInputStream(excel);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
 
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
 
            makeExcelToObject(wb.getSheetAt(0), "xls", "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try { fis.close(); } catch (IOException e) {}
        }
    }
	public void readExcelXLSX(File excel) {
		FileInputStream fis = null;
        try {
        	fis = new FileInputStream(excel);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFExcelExtractor extractor = new XSSFExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
 
            makeExcelToObject(wb.getSheetAt(0), "xlsx", "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try { fis.close(); } catch (IOException e) {}
        }
    }
    
    
    private void makeExcelToObject(Object sheet, String flag, String fileName) throws IOException {
        Row titles = null;
 
        for (Row row : (flag.equals("xls") ? (HSSFSheet) sheet : (XSSFSheet) sheet)) {
            if (row.getRowNum() == 0) {
                titles = row;
                continue;
            }
 
            updateData(titles, row);
        }
    }
    
    private void updateData(Row titles, Row row) throws IOException {
        String colName = null;
        String colValue = null;
        String name = null;
        String value = null;
        HashMap map = new HashMap();
 
        // FOR Section (S)
        for (Cell cell : row) {
            if (titles.getCell(cell.getColumnIndex()) == null || titles.getCell(cell.getColumnIndex()).equals("")) {
                break;
            }
 
            colName = titles.getCell(cell.getColumnIndex()).getRichStringCellValue().getString().trim();
 
            switch (cell.getCellType()) {
	            case STRING:
	                colValue = cell.getRichStringCellValue().getString();
	                break;
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    colValue = cell.getDateCellValue().toString();
	                } else {
	                    Long roundVal = Math.round(cell.getNumericCellValue());
	                    Double doubleVal = cell.getNumericCellValue();
	                    if (doubleVal.equals(roundVal.doubleValue())) {
	                        colValue = String.valueOf(roundVal);
	                    } else {
	                        colValue = String.valueOf(doubleVal);
	                    }
	                }
	                break;
	            case BOOLEAN:
	                colValue = String.valueOf(cell.getBooleanCellValue());
	                break;
	            case FORMULA:
	                colValue = cell.getCellFormula();
	                break;
	 
	            default:
	                colValue = "";
            }
 
            colValue = colValue.trim();
 /*
            if (colName.equals("NAME")) {
                if (!StringUtils.nullToString(colValue, "").isEmpty()) {
                    name = colValue;
                }
            }
 
            if (colName.equals("VALUE")) {
                if (!StringUtils.nullToString(colValue, "").isEmpty()) {
                    value = colValue;
                }
            }*/
            
            map.put(colName, colValue);
        }
        dataList.add(map);

    }
}
