package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.LocationTable;
import BiblioSoft.core.CharacterFilter;
import BiblioSoft.core.ConnDB;

public class LocationDAO {
	private ConnDB conn = new ConnDB();

	/**
	 * Add
	 * 
	 * @param location1
	 * @return
	 */
	public int addLocation(String location1) {
		int flag = 0;
		String location = CharacterFilter.filterStr(location1);
		String statussql = "select * from location where location_name = " + "\'" + location + "\'";
		ResultSet rs = conn.executeQuery(statussql);
		try {
			if (rs.next()) {
				// there is already exists this location
				flag = 2;
				return flag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (location != null) {
			String sql = "insert into location values (" + "\'" + location + "\'" + ")";
			flag = 1;
			conn.executeQuery(sql);
			return flag;
		} else {
			flag = 2;
		}
		return flag;
	}

	/**
	 * Search
	 * 
	 * @param location
	 * @return
	 */
	public Collection searchlocation(String location) {
		LocationTable locationTable = null;
		Collection locationColle = new ArrayList();
		String sql = null;
		if (location != "all" && location != "" && location != null) {
			sql = "select * from " + "location where location_name = " + "\'" + location + "\'";
		} else {

		}
		System.out.println("按照location查找的信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				locationTable = new LocationTable();
				locationTable.setLocation(rs.getString("location_name"));
				locationColle.add(locationTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return locationColle;
	}

	/**
	 * List All
	 * 
	 * @return
	 */
	public Collection listAlllocation() {
		LocationTable locationTable = null;
		Collection locationColle = new ArrayList();
		String sql = "select * from location";
		System.out.println("按照location查找的信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				locationTable = new LocationTable();
				locationTable.setLocation(rs.getString("location_name"));
				locationColle.add(locationTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return locationColle;
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public int deleteLocation(String location) {
		String statussql = "select * from bookinlib where location =" + "\'" + location + "\'";
		ResultSet rs = conn.executeQuery(statussql);
		System.out.println(statussql);

		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("Some books are below this location!");
					int can = 2;
					return can;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		//else {
			String sql = "delete from location where location_name = " + "\'" + location + "\'";
			System.out.println(sql);
			int flag = conn.executeUpdate(sql);
			if (flag == 0) {
				flag = 3;
				return flag;
			}
			System.out.println("删除时的SQL：" + sql);
			conn.close();
			return flag;
		//}
		
		//return 1;	
	}
}
