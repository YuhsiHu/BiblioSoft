/**
 * 
 */
package BiblioSoft.DAO;

import java.sql.*;
import java.util.*;
import BiblioSoft.DAO.*;
import BiblioSoft.core.*;
import BiblioSoft.Table.*;


/**
 * @author 何龙翔
 *
 */
public class ReaderDAO  {
	private ConnDB conn = new ConnDB();
	
	/*
	 * @return boolean，返回true则添加成功，返回false则新增的reader的tele属性与现有的重复
	 * @param 向数据库里添加reader
	 */
	public boolean doCreate( String sex, String name, String email, String tele)throws Exception{
		ReaderDAO r = new ReaderDAO();
	    if(r.findByTele(tele).getTele()!= null) {
	    	return false;
	    }
	    DefaultValue value = new DefaultValue();
		String password = "12345678";
		int MaxBorrow = 3;
	    int longesttime =value.getLongesttime();
	    double deposit = value.getDeposit();
	    String sql = "insert into reader(password,sex,Reader_name,email,Tele,MaxBorrow,LongestTime,Deposit) values("
	    				+"'"+password+"'"+","+"'"+sex+"'"+","+"'"+name+"'"+","+"'"+email+"'"+","+"'"+tele+"'"+","+MaxBorrow+","+longesttime+","+deposit+
	    				")";
	    conn.executeUpdate(sql);
	    System.out.println("添加读者信息的SQL：" + sql);
	    conn.close();
	    return true;
	}
	
	/*
	 * @return  readerTable
	 * @param  通过reader.tele查找reader ，并返回readerTable，
	 * @param 如果没有reader，则返回的readerTable的所有属性为null，可通过reader.getTele()== null来判断没有reader
	 */
	public  ReaderTable findByTele(String tele) throws SQLException {
		ReaderTable reader = new ReaderTable();
		String sql = "select * from reader where tele = "+"'"+tele+"'";
		ResultSet rs=conn.executeQuery(sql);
		while (rs.next()) {
			reader.setID(rs.getInt("reader_id"));
        	reader.setPassword(rs.getString("password"));
            reader.setSex(rs.getString("sex"));
            reader.setReaderName(rs.getString("reader_name"));
            reader.setEmail(rs.getString("email"));
            reader.setTele(rs.getString("tele"));
            reader.setMaxborrow(rs.getInt("maxborrow"));
            reader.setLongesttime(rs.getInt("longesttime"));
            reader.setDeposit(rs.getDouble("deposit"));
		}
		conn.close();
		return reader;
	}
	
	/*
	 * @return  readerTable
	 * @param  通过reader.reader_id查找reader ，并返回readerTable，
	 * @param 如果没有reader，则返回的readerTable的所有属性为null，可通过reader.getTele()== null来判断没有reader
	 */
	public  ReaderTable findByID(int id) throws SQLException {
		ReaderTable reader = new ReaderTable();
		String sql = "select * from reader where reader_id = "+id;
		ResultSet rs=conn.executeQuery(sql);
		while (rs.next()) {
			reader.setID(rs.getInt("reader_id"));
        	reader.setPassword(rs.getString("password"));
            reader.setSex(rs.getString("sex"));
            reader.setReaderName(rs.getString("reader_name"));
            reader.setEmail(rs.getString("email"));
            reader.setTele(rs.getString("tele"));
            reader.setMaxborrow(rs.getInt("maxborrow"));
            reader.setLongesttime(rs.getInt("longesttime"));
            reader.setDeposit(rs.getDouble("deposit"));
		}
		conn.close();
		return reader;
	}
	
	/*
	 * @return Collection<readerTable> 即 readerTable的数组
	 * @param 查找全部的reader，并将其返回，如果没有reader则返回的结果的数组.isEmpty()为true
	 */
	public Collection<ReaderTable> findAll() throws SQLException {
	    Collection<ReaderTable> readerList=new ArrayList<ReaderTable>();
		String sql = "select * from reader";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()) {
			ReaderTable reader = new ReaderTable();
			reader.setID(rs.getInt("reader_id"));
			reader.setPassword(rs.getString("password"));
            reader.setSex(rs.getString("sex"));
            reader.setReaderName(rs.getString("reader_name"));
            reader.setEmail(rs.getString("email"));
            reader.setTele(rs.getString("tele"));
            reader.setMaxborrow(rs.getInt("maxborrow"));
            reader.setLongesttime(rs.getInt("longesttime"));
            reader.setDeposit(rs.getDouble("deposit"));
            readerList.add(reader);
		}
		conn.close();
		return readerList;
	}
	
	/*
	 * @return boolean，返回true则删除成功，返回false则该reader还有罚金未缴纳或者书未归还
	 * @param  通过reader.tele删除数据库中对应的reader
	 */
	public boolean deleteByTele(String tele) throws SQLException {
		RecordDAO record = new RecordDAO();
		ReserveDAO reserve = new ReserveDAO();
		ReaderDAO reader = new ReaderDAO();
		ReaderTable t = new ReaderTable();
		t = reader.findByTele(tele);
		//if 罚金已缴纳&书全归还
		if(record.getNeedtoFine(t.getID()).isEmpty()&&record.getCurrentBorrow(t.getID()).isEmpty()) {
			//删除reader在record和reserve中的记录
			record.deleteRecordByReadID(t.getID());
			reserve.deleteReserveByReadID(t.getID());
			String sql="Delete from reader where tele="+"'"+tele+"'";
			conn.executeUpdate(sql);
			System.out.println("删除时的SQL："+sql);
			conn.close();
			return true;
		}
		//存在罚金未缴纳或书未归还
		else {
			conn.close();
			return false;
		}
	}
	
	/*
	 * @return boolean，返回true则修改成功,返回false则修改失败
	 * @param  通过reader.tele修改数据库中对应的reader.password
	 */
	public boolean updatePasswordByTele(String tele, String password) {
		int flag = 0;
		String sql ="update reader set password =" +"'"+ password +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag = conn.executeUpdate(sql);
		System.out.println("修改password时的SQL："+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * @return boolean，返回true则修改成功,返回false则修改失败
	 * @param  通过reader.tele修改数据库中对应的reader.sex
	 */
	public boolean updateSexByTele(String tele, String sex) {
		int flag = 0;
		String sql ="update reader set sex =" +"'"+ sex +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag =conn.executeUpdate(sql);
		System.out.println("修改sex时的SQL："+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * @return boolean，返回true则修改成功,返回false则修改失败
	 * @param  通过reader.tele修改数据库中对应的reader.reader_name
	 */
	public boolean updateReaderNameByTele(String tele, String readername) {
		int flag =0;
		String sql ="update reader set reader_name =" +"'"+ readername +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag = conn.executeUpdate(sql);
		System.out.println("修改reader_name时的SQL："+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * @return boolean，返回true则修改成功,返回false则修改失败
	 * @param  通过reader.tele修改数据库中对应的reader.email
	 */
	public boolean updateEmailByTele(String tele, String email) {
		int flag =0;
		String sql ="update reader set email =" +"'"+ email +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag  = conn.executeUpdate(sql);
		System.out.println("修改email时的SQL："+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	
}