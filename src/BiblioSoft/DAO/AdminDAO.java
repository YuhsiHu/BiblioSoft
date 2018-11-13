package BiblioSoft.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.*;
import BiblioSoft.core.ConnDB;

public class AdminDAO {
	String sql=""; 
    private ConnDB conn = new ConnDB();
    //Collection���ڱ������е�admin
    
    //��ѯ����admin����Ϣ
	public Collection<AdminTable> queryAll() throws SQLException {
		Collection<AdminTable> adminColl=new ArrayList<AdminTable>();
		ResultSet rs = conn.executeQuery("select * from admin");
		AdminTable adminForm = new AdminTable();
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				adminForm.setAdmin_id(rs.getInt(1));
				adminForm.setPassword(rs.getString(2));
				adminForm.setAdmin_name(rs.getString(3));
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
				adminColl.add(adminForm);
			}
		} else {
			System.out.println("no admin...");
		}
		conn.close();
		return adminColl;
	}
	
	//ͨ������admin_id��ѯ������һ��adminForm����
	public AdminTable queryFromId(int id) throws SQLException {
		sql = "select * from admin where admin_id ="+"'"+id+"'";
		AdminTable adminForm = new AdminTable();
		ResultSet rs = conn.executeQuery(sql);
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				adminForm.setAdmin_id(rs.getInt(1));
				adminForm.setPassword(rs.getString(2));
				adminForm.setAdmin_name(rs.getString(3));
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
			}
		}else{
			System.out.println("no result for admin_id is " + id);
		}
		conn.close();
		return adminForm;
	}
	
	//ͨ������admin_name��ѯ������һ������nameΪ�����admin_name�ĺ�adminForm�����Collection
	public Collection<AdminTable> queryFromName(String name) throws SQLException {
		Collection<AdminTable> adminColl=new ArrayList<AdminTable>();
		sql = "select * from admin where admin_name ="+"'"+name+"'";
		ResultSet rs = conn.executeQuery(sql);
		AdminTable adminForm = new AdminTable();
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				adminForm.setAdmin_id(rs.getInt(1));
				adminForm.setPassword(rs.getString(2));
				adminForm.setAdmin_name(rs.getString(3));
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
				adminColl.add(adminForm);
			}
		}else{
			System.out.println("no result for admin_name is " + name);
		}
		conn.close();
		return adminColl;
	}
	
	//ͨ������admin_id��password�����ĸ�admin������,���ɹ�������true�����򷵻�false
	public boolean updatePassword(int id,String pawd) throws SQLException{
		sql = "update admin SET password='" + pawd + "' where admin_id ='" + id +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("update password failed");
			return false;
		}else {
			conn.close();
			return true;
		}
	}
	
	//ͨ������admin_id��name�����ĸ�admin��name,���ɹ�������true�����򷵻�false
	public boolean updateAdminName(int id,String name) throws SQLException{
		sql = "update admin SET admin_name='" + name + "' where admin_id ='" + id +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("update name failed");
			return false;
		}else {
			conn.close();
			return true;
		}
	}
	
	//ͨ������admin_id��ɾ��һ��admin,���ɹ�������true�����򷵻�false
	public boolean deleteAdmin(int id)throws SQLException{
		sql = "delete from admin where admin_id ="+"'"+ id +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("delete failed");
			return false;
		}else {
			conn.close();
			return true;
		}
	}
	
	//ͨ������password��name������µ�admin,���ɹ�������true�����򷵻�false
	public boolean addAdmin(String pawd,String name)throws SQLException{
		sql = "insert into admin(password,admin_name) values ('"+ pawd + "','" + name + "')";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("add failed");
			return false;
		}else {
			conn.close();
			return true;
		}
			
	}
}
