package com.ybs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.ybs.model.ModularCategory;

public class DbModularCategory {
	
	private static ModularCategory scrap = new ModularCategory();
	static Connection conn = DbUtil.getConnection();
	
	//获取表名
	public static void SetTableName(String tableName) {
		scrap.setTableName(tableName);
	}
	
	private static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.replace("-", "");
//        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	
	//使用预处理的方法
	private static void insertPtmt() {
		String sql = "INSERT INTO "+scrap.getTableName()+" (ID, parent_ID, Name) VALUES (?,?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, getUUID());
			ptmt.setString(2, scrap.getParent_ID());
			ptmt.setString(3, scrap.getName());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}
	}
		
		
	private static void insert() {
		String sql = "INSERT INTO "+scrap.getTableName()+" (ID, parent_ID,Name) VALUES "
				+ "('"+getUUID()+"','"+scrap.getParent_ID()+"','"+scrap.getName()+"')";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}
	}
		
	//String childValue
	private static String select(String childValue) {
		String sql = "SELECT ID FROM "+scrap.getTableName()+" WHERE Name = '"+childValue+"'";
		String parent_ID = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				parent_ID = rs.getString("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent_ID;
	}
		
	//插入一级目录
	public static void Pinsert(String Value){
		//传入一级分类名称,设置父类ID为空
		scrap.setName(Value);
		scrap.setParent_ID(null);
		//写入数据库
		insert();
	}
	//插入二级目录
	public static void Cinsert(String cValue,String pValue) {
		//传入二级分类，将父类ID设置为一级分类的ID
		scrap.setName(cValue);
		String FirstID = select(pValue);
		scrap.setParent_ID(FirstID);
		//写入数据库
		insertPtmt();
	}
}
