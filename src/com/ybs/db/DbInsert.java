package com.ybs.db;

import com.ybs.dom.DomParse;
import com.ybs.dom.ParseBlock;

public class DbInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 将模块信息加载到数据库，Module【通用模块信息】表
		 */
		ParseBlock block = new ParseBlock();
		block.block();
		/**
		 * 将商城/余废料/机械设备,数据加载到数据库中Modular_Category【通用分类信息】表，
		 * 同步加载到Category_Module【模块与分类关系】表
		 */
		DomParse parse = new DomParse();
		//商城数据
		parse.parse("File/commodity.xml","商城");
		//余废料数据
		parse.parse("File/waste.xml","余废料");
		parse.parse("File/residues.xml","余废料");
		//机械设备数据
		parse.parse("File/equipment.xml","机械设备");
		
	}

}
