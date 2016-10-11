package com.ybs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class DbCategoryModular {
	private static Connection conn = DbUtil.getConnection();
	public static String category_ID = null;
	public static String module_ID = null;
	
	private static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.replace("-", "");
//        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	
	public static void selectCID(String Value) {
		String sql = "SELECT ID FROM modular_category WHERE Name = '"+Value+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				category_ID = rs.getString("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}
	}
	
	public static String selectMID(String Value) {
		String sql = "SELECT ID FROM module WHERE Name = '"+Value+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				module_ID = rs.getString("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}
		return module_ID;
	}
	
	public static void insert(){
		String sql = "INSERT INTO category_module (ID, Category_ID, Module_ID) VALUES (?,?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, getUUID());
			ptmt.setString(2, category_ID);
			ptmt.setString(3, module_ID);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
