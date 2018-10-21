package BiblioSoft.DAO;

import java.sql.*;
import java.util.*;
import java.io.*;


import BiblioSoft.Table.bookcaseTable_abd;
import BiblioSoft.core.ConnDB;

public class bookcaseDAO_abd {

	private ConnDB conn = new ConnDB();
	// search
		public Collection query(String strif) {
			bookcaseTable_abd bookcasetable = null;
			Collection caseColl = new ArrayList();
			String sql = "";
			if (strif != "all" && strif != null && strif != "") {
				sql = "select * from "+'"'+"Bookcase"+'"'+" where " + strif + "";
			} else {
				sql = "select * from "+'"'+"Bookcase"+'"';
			}
			ResultSet rs = conn.executeQuery(sql);
			try {
				while (rs.next()) {
					bookcasetable = new bookcaseTable_abd();
					bookcasetable.setBookcase_id(Integer.valueOf(rs.getString("bookcase_id")));
					bookcasetable.setBookcase_name(rs.getString("bookcase_name"));
					caseColl.add(bookcasetable);
				}
			} catch (SQLException ex) {
			}
			conn.close();
			return caseColl;
		}
		
		public int addBookcase(int bookcaseID,String bookcaseName) {
			int flag = 0;
			String sql="Insert into "+'"'+"Bookcase"+'"'+"(bookcase_id,bookcase_name) values("+String.valueOf(bookcaseID)+",'"+bookcaseName+ "')";
			String sql1="select bookcase_id from "+'"'+"Bookcase"+'"'+" where bookcase_id="+String.valueOf(bookcaseID);
			ResultSet ifid=conn.executeQuery(sql1);
			try {
				if(ifid.next()) {
					flag=2;
					conn.close();
					return flag;
				}else {
					conn.executeQuery(sql);
					flag = 1;
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		
		public int updateBookcase(int bookcaseID,String bookcaseName) {
			int flag = 0;
			String sql = "Update "+'"'+"Bookcase"+'"'+" set bookcase_id=" + String.valueOf(bookcaseID) +",bookcase_name'"+bookcaseName+"' where bookcase_id=" + String.valueOf(bookcaseID) + "";
			flag = conn.executeUpdate(sql);
			System.out.println("修改数据时的SQL：" + sql);
			conn.close();
			return flag;
		}
		
		public int deleteBookcase(int bookcaseID) {
			int flag = 0;
			String sql = "DELETE from"+'"'+"Bookcase"+'"'+" where bookcase_id=" + String.valueOf(bookcaseID) +"";
			flag = conn.executeUpdate(sql);
			System.out.println("删除数据时的SQL：" + sql);
			conn.close();
			return flag;
		}
}
