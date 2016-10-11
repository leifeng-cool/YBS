package com.ybs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.ybs.model.Block;


public class DbBlock {
	private Block block = new Block();
	static Connection conn = DbUtil.getConnection();

	public DbBlock(Block block) {
		// TODO Auto-generated constructor stub
		this.block = block;
	}
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.replace("-", "");
//        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	//使用预处理的方法
	public void insertPtmt() {
		String sql = "INSERT INTO "+block.getTableName()+" (ID, Name) Values (?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, getUUID());
			ptmt.setString(2, block.getType_Name());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}
	}
	//使用普通方法
	public void insert() {
		String sql = "INSERT INTO "+block.getTableName()+" (ID, Name) VALUES ('"+getUUID()+"','"+block.getType_Name()+"')";
		try {
			java.sql.Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}
	}
	//String childValue
	public String select(String childValue) {
		String sql = "SELECT ID FROM "+block.getTableName()+" WHERE Name = '"+childValue+"'";
		String ID = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ID = rs.getString("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ID;
	}
}
