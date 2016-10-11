package com.ybs.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ybs.db.DbCategoryModular;
import com.ybs.db.DbModularCategory;


public class DomParse {
	
	
	
	public void parse(String url,String Value) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(url);
			DbCategoryModular.selectMID(Value);
			//遍历表名
			NodeList tableName = document.getElementsByTagName("MeteData");
			NamedNodeMap tableAttrs = tableName.item(0).getAttributes();
			for (int i = 0; i < tableAttrs.getLength(); i++) {
				 Node tableAttr = tableAttrs.item(i);
				 String tableNodeName = tableAttr.getNodeName();
				 String tableValue = tableAttr.getNodeValue();
				 System.out.println(tableNodeName+":"+tableValue);
				 DbModularCategory.SetTableName(tableValue);
			}
			System.out.println();
			
			//遍历一级目录
			NodeList fList = document.getElementsByTagName("FirstClass");
			for (int i = 0; i < fList.getLength(); i++) {
				Node item = fList.item(i);
				NamedNodeMap itemAttrs = item.getAttributes();
				for (int j = 0; j < itemAttrs.getLength(); j++) {
					Node itemAttr = itemAttrs.item(j);
					String AttrName = itemAttr.getNodeName();
					String AttrValue = itemAttr.getNodeValue();
					System.out.println(AttrName+":"+AttrValue);
					DbModularCategory.Pinsert(AttrValue);
					DbCategoryModular.selectCID(AttrValue);
					DbCategoryModular.insert();
					if (item.hasChildNodes()) {
						parseSecond(item,AttrValue);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//遍历二级目录
	public void parseSecond(Node item,String AttrValue){
		NodeList cList = item.getChildNodes();
		for (int k = 0; k < cList.getLength(); k++) {
			if (cList.item(k).getNodeType()==Node.ELEMENT_NODE) {
				Node citem = cList.item(k);
				NamedNodeMap citemAttrs = citem.getAttributes();
				for (int l = 0; l < citemAttrs.getLength(); l++) {
					Node citemAttr = citemAttrs.item(l);
					String cAttrName = citemAttr.getNodeName();
					String cAttrValue = citemAttr.getNodeValue();
					System.out.println("  |--"+cAttrName+":"+cAttrValue);
					DbModularCategory.Cinsert(cAttrValue,AttrValue);
					DbCategoryModular.selectCID(cAttrValue);
					DbCategoryModular.insert();
					if (citem.hasChildNodes()) {
						parseThird(citem,cAttrValue);
					}
				}
			}
		}
	}
	
	//遍历三级目录
	public void parseThird(Node citem,String cAttrValue){
		NodeList gList = citem.getChildNodes();
		for (int m = 0; m < gList.getLength(); m++) {
			if (gList.item(m).getNodeType()==Node.ELEMENT_NODE) {
				Node gNode = gList.item(m);
				String NodeName = gNode.getNodeName();
				String NodeValue = gNode.getFirstChild().getNodeValue();
				System.out.println("    |--"+NodeName+":"+NodeValue);
				DbModularCategory.Cinsert(NodeValue,cAttrValue);
				DbCategoryModular.selectCID(NodeValue);
				DbCategoryModular.insert();
			}
		}
	} 
	
	
}
