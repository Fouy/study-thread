package com.way361.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtils {

	public static final String url = "jdbc:mysql://127.0.0.1/school";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";

	/**
	 * 获取所有数据
	 * @return
	 */
	public static List<String> getPicPath() {
		String sql = "select pic_path_1, pic_path_2, pic_path_3, pic_path_4, pic_path_5 from bm_list";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();

		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				addPath(result, rs.getString("pic_path_1"));
				addPath(result, rs.getString("pic_path_2"));
				addPath(result, rs.getString("pic_path_3"));
				addPath(result, rs.getString("pic_path_4"));
				addPath(result, rs.getString("pic_path_5"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	private static void addPath(List<String> result, String path) {
//		System.out.println(path);
		if(null != path && !"".equals(path.trim()) && path.contains(".")){
			if(!path.startsWith("/")){
				result.add("/" + path);
			}else{
				result.add(path);
			}
		}
	}
	
	public static void main(String[] args) {
//		List<String> list = getPicPath();
//		for(String path : list){
//			System.out.println(path);
//		}
		String path = "/asdf/asdfa/ewrew/asdf2342.jpg";
		String tmp = path.substring(path.lastIndexOf('/') + 1).trim();
		System.out.println(tmp);
	}

}
