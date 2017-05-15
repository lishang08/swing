package com.tools.auto.service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.tools.auto.event.DDLGenerator;
import com.tools.auto.event.FieldXmlGenerator;
import com.tools.auto.model.TableDefinition;
import com.tools.auto.utils.Constants;

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
	
	public String loadFile(File file, String event) {
		logger.info("Current processing file is " + file.getAbsolutePath());
		String returnInfo = "";
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (suffix.equalsIgnoreCase("csv") || suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx")) {
			//process csv format
			if (suffix.equalsIgnoreCase("csv")) {
				try {
					List<TableDefinition> definitions= this.readCSV(file);
					if (event.equals(Constants.EVENT_GENERATE_XML)) {
						FieldXmlGenerator xmlGenerator = new FieldXmlGenerator();
						xmlGenerator.buildXml(definitions);
						returnInfo = "Xml file generated successfully !!!";
					} else {
						//generate ddl
						DDLGenerator ddlGenerator = new DDLGenerator();
						ddlGenerator.buildDDL(definitions);
						returnInfo = "DDL file generated successfully  !!!";
					}


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
	public List<TableDefinition> readCSV (File file) throws Exception {
		FileReader fileReader = new FileReader(file);
		CSVReader csvReader = new CSVReader(fileReader);
		List<TableDefinition> definitions = new ArrayList<TableDefinition>();
		List<String[]> list = csvReader.readAll(); 
		for(String[] ss : list){  
			TableDefinition definition = new TableDefinition();
			definition.setLogicalName(ss[0].trim());
			definition.setOnboard(ss[1].trim());
			definition.setPhysicalName(ss[2].trim());
			definition.setDataType(ss[3].trim());
			definition.setNullable(ss[4].trim());
			definition.setPosition(ss[5].trim());
			definition.setDataLength(ss[6].trim());
			definition.setMaxlength(ss[7].trim());
			definition.setTableName(ss[8].trim());
			
			definitions.add(definition);
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
		return definitions;
	}

}
