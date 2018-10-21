package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.*;
import BiblioSoft.core.ConnDB;

public class LibrarianDAO {
	private String sql=""; 
    private ConnDB conn = new ConnDB();

    //ͨ������password��name������µ�librarian,���ɹ�������true�����򷵻�false
    public boolean addLibrarian(String pawd,String name)throws SQLException{
    	sql = "insert into librarian(password,lib_name) values ('" + pawd + "','" + name +"')";
    	int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("add failed");
			conn.close();
			return false;
		}else {
			conn.close();
			return true;
		}
    }
    
  	//ͨ������id��ɾ��librarian,���ɹ�������true�����򷵻�false
    public boolean deleteLibrarian(int id)throws SQLException{
    	sql = "DELETE FROM librarian WHERE lib_id = " + id;
    	int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("delete failed");
			conn.close();
			return false;
		}else{
			conn.close();
			return true;
		}

    }
    
    //ͨ������password��id�����¸�librarian������,���ɹ�������true�����򷵻�false
    public boolean updateLibrarianPassword(int id , String pawd)throws SQLException{
    	sql = "update librarian SET password='" + pawd + "' where lib_id ='" + id +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("update password failed");
			conn.close();
			return false;
		}else {
			conn.close();
			return true;
		}
			
    }
    
    //ͨ������password��id�����¸�librarian��name,���ɹ�������true�����򷵻�false
    public boolean updateLibrarianName(int id , String name)throws SQLException{
    	sql = "update librarian SET lib_name='" + name + "' where lib_id ='" + id +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("update name failed");
			conn.close();
			return false;
		}else {
			conn.close();
			return true;
		}
			
    }
    
    //��ѯ���е�librarian������һ����������librarian��Collection
    public Collection<LibrarianTable> queryAll() throws SQLException {
		Collection<LibrarianTable> librarianColl=new ArrayList<LibrarianTable>();
		ResultSet rs = conn.executeQuery("select * from librarian");
		LibrarianTable librarian = new LibrarianTable();
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				librarian.setLib_id(rs.getInt(1));
				librarian.setPassword(rs.getString(2));
				librarian.setLib_name(rs.getString(3));
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
				librarianColl.add(librarian);
			}
		} else {
			System.out.println("no librarian...");
		}
		conn.close();
		return librarianColl;
	}
    
    //ͨ������lib_id��ѯ������һ��librarianForm����
  	public LibrarianTable queryFromId(int id) throws SQLException {
  		sql = "select * from librarian where lib_id ="+"'"+id+"'";
  		LibrarianTable librarianForm = new LibrarianTable();
  		ResultSet rs = conn.executeQuery(sql);
  		if(rs.next()){
  			rs.previous();
  			while (rs.next()) {
  				librarianForm.setLib_id(rs.getInt(1));
  				librarianForm.setPassword(rs.getString(2));
  				librarianForm.setLib_name(rs.getString(3));
//  				System.out.println(rs.getString(1));
//  				System.out.println(rs.getString(2));
//  				System.out.println(rs.getString(3));
  			}
  		}else{
  			System.out.println("no result for librarian_id is " + id);
  		}
  		conn.close();
  		return librarianForm;
  	}
  	
  	//ͨ������lib_name��ѯ������һ������nameΪ�����lib_name�ĺ�libForm�����Collection
  	public Collection<LibrarianTable> queryFromName(String name) throws SQLException {
  		Collection<LibrarianTable> librarianColl=new ArrayList<LibrarianTable>();
  		sql = "select * from librarian where lib_name ="+"'"+name+"'";
  		ResultSet rs = conn.executeQuery(sql);
  		LibrarianTable librarianForm = new LibrarianTable();
  		if(rs.next()){
  			rs.previous();
  			while (rs.next()) {
  				librarianForm.setLib_id(rs.getInt(1));
  				librarianForm.setPassword(rs.getString(2));
  				librarianForm.setLib_name(rs.getString(3));
//  				System.out.println(rs.getString(1));
//  				System.out.println(rs.getString(2));
//  				System.out.println(rs.getString(3));
  				librarianColl.add(librarianForm);
  			}
  		}else{
  			System.out.println("no result for lib_name is " + name);
  		}
  		conn.close();
  		return librarianColl;
  	}
}
