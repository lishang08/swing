package com.tools.auto.event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tools.auto.model.TableDefinition;
import com.tools.auto.utils.Constants;
import com.tools.auto.utils.Datatype;

/**
 * Author: fulishang 
 * Create Time : 2017年5月15日,上午12:55:42 
 * Modify Time : 
 * Desc : DDL 自动生成器
 * Blog : https://lishang08.github.io/
 */

public class DDLGenerator implements DocumentGenerator<TableDefinition> {

	/** 引入logger */
	private Logger logger = LoggerFactory.getLogger(DDLGenerator.class);

	/**
	 * 生成ddl
	 */
	public void buildDDL(List<TableDefinition> list) throws Exception {
		File file = new File("ddls/template.sql");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String tempStr = null;
		StringBuilder sb = new StringBuilder();
		while ((tempStr = bufferedReader.readLine()) != null) {
			sb.append(tempStr).append("\n");
		}
		bufferedReader.close();

		String tableName = list.get(1).getTableName();
		StringBuilder fieldStr = new StringBuilder();
		// counter for header and footer handler
		int i = 0;
		for (TableDefinition d : list) {
			if (i == 0) {
				// filter header
				logger.info("Nothing to do, as it is the header!");
			} else if (i == (list.size() - 1)) {
				// footer should be end without comma
				fieldStr.append(d.getPhysicalName())
				.append(" ")
				.append(getType(d.getDataType()))
				.append(getType(d.getDataType()).equals("VARCHAR2")?"(" + d.getDataLength()+ ")":"")
				.append(d.getNullable().toUpperCase().equals("Y") ? " NULL" : " NOT NULL");
			} else {
				fieldStr.append(d.getPhysicalName())
				.append(" ")
				.append(getType(d.getDataType()))
				.append(getType(d.getDataType()).equals("VARCHAR2")?"(" + d.getDataLength()+ ")":"")
				.append(d.getNullable().toUpperCase().equals("Y") ? " NULL" : " NOT NULL")
				.append(",\n");
			}
            i++;
		}
		logger.info(fieldStr.toString());
		// replace
		String data = sb.toString().replaceAll(Constants.PLACEHOLDER_TABLE_NAME, tableName.toUpperCase())
				.replaceAll(Constants.PLACEHOLDER_FIELDS, fieldStr.toString().toUpperCase());
		logger.info(sb.toString());
		// write to new file
		File ddlFile = new File("ddls/" + tableName.toUpperCase() + ".sql");
		if (!ddlFile.exists())
			ddlFile.createNewFile();
		// true means append file
		FileWriter fw = new FileWriter(ddlFile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.close();
	}

	/**
	 * Get the table type
	 * 
	 * @param type
	 * @return
	 */
	public String getType(String type) {
		String realType = null;
		switch (type.toUpperCase()) {
		case "NUMBER":
			realType = Datatype.NUMBER.getType();
			break;
		case "DATE":
			realType = Datatype.DATE.getType();
		default:
			realType = Datatype.STRING.getType();
			break;
		}
		return realType;
	}

	public void buildXml(List<TableDefinition> list) throws Exception {
		// TODO Auto-generated method stub

	}

}
