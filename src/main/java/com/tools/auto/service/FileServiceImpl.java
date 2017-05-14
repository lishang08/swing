package com.tools.auto.service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.tools.auto.event.FieldXmlGenerator;
import com.tools.auto.model.StagingTable;

/**
* Author: fulishang
* Create Time  : 2017年5月14日,上午11:29:26
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class FileServiceImpl implements FileService {

	/** 引入Logger */
	private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	public String loadFile(File file) {
		logger.info("Current processing file is " + file.getAbsolutePath());
		String returnInfo = "";
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (suffix.equalsIgnoreCase("csv") || suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx")) {
			returnInfo  = "File format is expected";
			//process csv format
			if (suffix.equalsIgnoreCase("csv")) {
				try {
					List<StagingTable> records= this.readCSV(file);
					FieldXmlGenerator xmlGenerator = new FieldXmlGenerator();
					xmlGenerator.buildXml(records);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			//process xls format, to be developed
			
			
		} else {
			returnInfo = "Expected file format should be excel!";
		}
		return returnInfo;
	}
	
	/**
	 * 读取csv文件
	 * @param file
	 * @throws Exception
	 */
	public List<StagingTable> readCSV (File file) throws Exception {
		FileReader fileReader = new FileReader(file);
		CSVReader csvReader = new CSVReader(fileReader);
		List<StagingTable> records = new ArrayList<StagingTable>();
		List<String[]> list = csvReader.readAll(); 
		for(String[] ss : list){  
			StagingTable table = new StagingTable();
			table.setLogicalName(ss[0].trim());
			table.setOnboard(ss[1].trim());
			table.setPhysicalName(ss[2].trim());
			table.setDataType(ss[3].trim());
			table.setNullable(ss[4].trim());
			table.setPosition(ss[5].trim());
			table.setDataLength(ss[6].trim());
			table.setMaxlength(ss[7].trim());
			table.setTableName(ss[8].trim());
			
			records.add(table);
		}
//		for (StagingTable t : records) {
//			logger.info(t.getLogicalName());
//			logger.info(t.getOnboard());
//			logger.info(t.getPhysicalName());
//			logger.info(t.getDataType());
//			logger.info(t.getNullable());
//			logger.info(t.getPosition());
//			logger.info(t.getTableName());
//			logger.info("------------------");
//		}
		
		csvReader.close();
		return records;
	}

}
