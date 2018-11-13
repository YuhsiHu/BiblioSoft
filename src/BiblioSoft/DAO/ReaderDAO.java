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
 * @author ������
 *
 */
public class ReaderDAO  {
	private ConnDB conn = new ConnDB();
	
	/*
	 * @return boolean������true����ӳɹ�������false��������reader��tele���������е��ظ�
	 * @param �����ݿ������reader
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
	    System.out.println("��Ӷ�����Ϣ��SQL��" + sql);
	    conn.close();
	    return true;
	}
	
	/*
	 * @return  readerTable
	 * @param  ͨ��reader.tele����reader ��������readerTable��
	 * @param ���û��reader���򷵻ص�readerTable����������Ϊnull����ͨ��reader.getTele()== null���ж�û��reader
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
	 * @param  ͨ��reader.reader_id����reader ��������readerTable��
	 * @param ���û��reader���򷵻ص�readerTable����������Ϊnull����ͨ��reader.getTele()== null���ж�û��reader
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
	 * @return Collection<readerTable> �� readerTable������
	 * @param ����ȫ����reader�������䷵�أ����û��reader�򷵻صĽ��������.isEmpty()Ϊtrue
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
	 * @return boolean������true��ɾ���ɹ�������false���reader���з���δ���ɻ�����δ�黹
	 * @param  ͨ��reader.teleɾ�����ݿ��ж�Ӧ��reader
	 */
	public boolean deleteByTele(String tele) throws SQLException {
		RecordDAO record = new RecordDAO();
		ReserveDAO reserve = new ReserveDAO();
		ReaderDAO reader = new ReaderDAO();
		ReaderTable t = new ReaderTable();
		t = reader.findByTele(tele);
		//if �����ѽ���&��ȫ�黹
		if(record.getNeedtoFine(t.getID()).isEmpty()&&record.getCurrentBorrow(t.getID()).isEmpty()) {
			//ɾ��reader��record��reserve�еļ�¼
			record.deleteRecordByReadID(t.getID());
			reserve.deleteReserveByReadID(t.getID());
			String sql="Delete from reader where tele="+"'"+tele+"'";
			conn.executeUpdate(sql);
			System.out.println("ɾ��ʱ��SQL��"+sql);
			conn.close();
			return true;
		}
		//���ڷ���δ���ɻ���δ�黹
		else {
			conn.close();
			return false;
		}
	}
	
	/*
	 * @return boolean������true���޸ĳɹ�,����false���޸�ʧ��
	 * @param  ͨ��reader.tele�޸����ݿ��ж�Ӧ��reader.password
	 */
	public boolean updatePasswordByTele(String tele, String password) {
		int flag = 0;
		String sql ="update reader set password =" +"'"+ password +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag = conn.executeUpdate(sql);
		System.out.println("�޸�passwordʱ��SQL��"+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * @return boolean������true���޸ĳɹ�,����false���޸�ʧ��
	 * @param  ͨ��reader.tele�޸����ݿ��ж�Ӧ��reader.sex
	 */
	public boolean updateSexByTele(String tele, String sex) {
		int flag = 0;
		String sql ="update reader set sex =" +"'"+ sex +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag =conn.executeUpdate(sql);
		System.out.println("�޸�sexʱ��SQL��"+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * @return boolean������true���޸ĳɹ�,����false���޸�ʧ��
	 * @param  ͨ��reader.tele�޸����ݿ��ж�Ӧ��reader.reader_name
	 */
	public boolean updateReaderNameByTele(String tele, String readername) {
		int flag =0;
		String sql ="update reader set reader_name =" +"'"+ readername +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag = conn.executeUpdate(sql);
		System.out.println("�޸�reader_nameʱ��SQL��"+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * @return boolean������true���޸ĳɹ�,����false���޸�ʧ��
	 * @param  ͨ��reader.tele�޸����ݿ��ж�Ӧ��reader.email
	 */
	public boolean updateEmailByTele(String tele, String email) {
		int flag =0;
		String sql ="update reader set email =" +"'"+ email +"'"+ "where reader.tele =" +"'"+ tele +"'";
		flag  = conn.executeUpdate(sql);
		System.out.println("�޸�emailʱ��SQL��"+sql);
		conn.close();
		if(flag == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	
}