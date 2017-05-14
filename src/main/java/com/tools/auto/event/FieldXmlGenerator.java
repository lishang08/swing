package com.tools.auto.event;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.tools.auto.model.StagingTable;

/**
* Author: fulishang
* Create Time  : 2017年5月14日,下午10:53:55
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class FieldXmlGenerator implements XMLGenerator<StagingTable>{

	public void buildXml(List<StagingTable> list) throws Exception {
		// TODO Auto-generated method stub
		Element root = new Element("fields").setAttribute("id","XXX-BODY");
		Document document = new Document(root);
		for (StagingTable t : list) {
			Element e = new Element("field").setAttribute("name",t.getPhysicalName());
			e.addContent(new Element("fieldType").setText(t.getDataType()));
			e.addContent(new Element("maxLength").setText(t.getMaxlength()));
			e.addContent(new Element("sequence").setAttribute("at", t.getPosition()));
			root.addContent(e);
		}
		Format format = Format.getPrettyFormat();
		XMLOutputter xmlOutputter = new XMLOutputter(format);
		File file = new File("fields/" + UUID.randomUUID() + "-Field.xml");
		if (!file.exists())
			file.createNewFile();
		xmlOutputter.output(document, new FileOutputStream(file));
	}


}
