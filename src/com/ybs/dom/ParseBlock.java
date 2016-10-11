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

import com.ybs.db.DbBlock;
import com.ybs.model.Block;

public class ParseBlock {
	public void block() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			Block block = new Block();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("File/block.xml");
			NodeList tableName = document.getElementsByTagName("MeteData");
			NamedNodeMap tableAttrs = tableName.item(0).getAttributes();
			for (int i = 0; i < tableAttrs.getLength(); i++) {
				 Node tableAttr = tableAttrs.item(i);
				 String NodeName = tableAttr.getNodeName();
				 String NodeValue = tableAttr.getNodeValue();
				 System.out.println(NodeName+":"+NodeValue);
				 block.setTableName(NodeValue);
			}
			NodeList node = document.getElementsByTagName("FirstClass");
			for (int i = 0; i < node.getLength(); i++) {
				Node iNode = node.item(i);
				NamedNodeMap attrs = iNode.getAttributes();
				for (int j = 0; j < attrs.getLength(); j++) {
					Node attr = attrs.item(j);
					String NodeName = attr.getNodeName();
					String NodeValue = attr.getNodeValue();
					System.out.println(NodeName+":"+NodeValue);
					block.setType_Name(NodeValue);
					DbBlock dbBlock = new DbBlock(block);
					dbBlock.insertPtmt();
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
}
