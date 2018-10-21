package BiblioSoft.DAO;

import java.util.*;
import java.sql.*;

import BiblioSoft.Table.publisherTable;
import BiblioSoft.core.ConnDB;

public class publisherDAO_abd {
	private ConnDB conn = new ConnDB();

	// search
	public Collection query(String strif) {
		publisherTable publishertable = null;
		Collection pubColl = new ArrayList();
		String sql = "";
		if (strif != "all" && strif != null && strif != "") {
			sql = "select * from "+'"'+"Publisher"+'"'+" where " + strif + "";
		} else {
			sql = "select * from "+'"'+"Publisher"+'"';
		}
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				publishertable = new publisherTable();
				publishertable.setPublisherID(Integer.valueOf(rs.getString("publisher_id")));
				publishertable.setPublisherName(rs.getString("publisher_name"));
				publishertable.setAddress(rs.getString("address"));
				pubColl.add(publishertable);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return pubColl;
	}
	
	public int addPublisher(int publisherID,String publisherName,String address) {
		int flag = 0;
		String sql="Insert into "+'"'+"Publisher"+'"'+"(publisher_id,publisher_name,address) values("+publisherID+",'"+publisherName+"','"+address+ "')";
		String sql1="select publisher_id from "+'"'+"Publisher"+'"'+" where publisher_id="+String.valueOf(publisherID);
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
	
	public int updatePublisher(int publisherID,String publisherName,String address) {
		int flag = 0;
		String sql = "Update "+'"'+"Publisher"+'"'+" set publisher_id=" + String.valueOf(publisherID) +",publisher_name'"+publisherName+"',address='"+address +"' where publisher_id=" + String.valueOf(publisherID) + "";
		flag = conn.executeUpdate(sql);
		System.out.println("修改数据时的SQL：" + sql);
		conn.close();
		return flag;
	}
	
	public int deletePublisher(int publisherID) {
		int flag = 0;
		String sql = "DELETE from"+'"'+"Publisher"+'"'+" where publisher_id=" + String.valueOf(publisherID) +"";
		flag = conn.executeUpdate(sql);
		System.out.println("删除数据时的SQL：" + sql);
		conn.close();
		return flag;
	}

}