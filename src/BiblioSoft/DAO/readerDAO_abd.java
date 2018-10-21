package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.readerTable_abd;
import BiblioSoft.core.ConnDB;

/**
 * 
 * @author Hu Yuxi
 *
 */
public class readerDAO_abd {
	private ConnDB conn = new ConnDB();

	//search for reader list
	public Collection query(String strif){
	    readerTable_abd readertable=null;
	    Collection readerColl=new ArrayList();
	    String sql="";
	    if(strif!="all" && strif!=null && strif!=""){
	        sql="select "+'"'+"Reader"+'"'+".*from "+'"'+"Reader"+'"'+" where "+strif+"";
	    }else{
	        sql="select "+'"'+"Reader"+'"'+".* from "+'"'+"Reader"+'"';
	    }
	    ResultSet rs=conn.executeQuery(sql);
	    try {
	        while (rs.next()) {
	        	readertable=new readerTable_abd();
	        	readertable.setReaderID(Integer.valueOf(rs.getString("reader_id")));
	        	readertable.setPassword(rs.getString("password"));
	            readertable.setSex(rs.getString("sex"));
	            readertable.setReadername(rs.getString("reader_name"));
	            readertable.setEmail(rs.getString("email"));
	            readertable.setTele(rs.getString("tele"));
	            readertable.setMaxBorrow(rs.getString("maxborrow"));
	            readertable.setLongestTime(rs.getString("longesttime"));
	            readertable.setDeposit(Float.valueOf(rs.getString("deposit")));
	            readerColl.add(readertable);
	        }
	    } catch (SQLException ex) {
	    }
	    conn.close();
	    return readerColl;
	}
	
	//search when update reader information
	public readerTable_abd queryM(readerTable_abd readertable){
	    readerTable_abd readertable1=null;
	    String sql="";
	    if(readertable.getReaderID()!=null){
	            sql="select "+'"'+"Reader"+'"'+".* from "+'"'+"Reader"+'"'+" where "+'"'+"Reader"+'"'+".reader_id="+readertable.getReaderID()+"";
	    }
	    System.out.println("修改读者信息时的SQL："+sql);
	    ResultSet rs=conn.executeQuery(sql);
	    try {
	        while (rs.next()) {
	            readertable1=new readerTable_abd();
	            readertable1.setReaderID(Integer.valueOf(rs.getString("reader_id")));
	        	readertable1.setPassword(rs.getString("password"));
	            readertable1.setSex(rs.getString("sex"));
	            readertable1.setReadername(rs.getString("reader_name"));
	            readertable1.setEmail(rs.getString("email"));
	            readertable1.setTele(rs.getString("tele"));
	            readertable1.setMaxBorrow(rs.getString("maxborrow"));
	            readertable1.setLongestTime(rs.getString("longesttime"));
	            readertable1.setDeposit(Float.valueOf(rs.getString("deposit")));
	        }
	    } catch (SQLException ex) {
	    }
	    conn.close();
	    return readertable1;
	}
	
	//add a new reader
	public int insert(readerTable_abd readertable){
	String sql1="SELECT * FROM "+'"'+"Reader"+'"'+" WHERE reader_id='"+readertable.getReaderID()+"'";
	ResultSet rs = conn.executeQuery(sql1);
	String sql = "";
	int falg = 0;
	try {
	    if (rs.next()) {
	    	//ID already exist
	        falg = 2;
	    } else {
	    	//add a new reader with ID
	        sql ="Insert into "+'"'+"Reader"+'"'+" values('"
	    	+readertable.getReaderID()+"','"+readertable.getPassword()+"','"+readertable.getSex()+"','"
	        +readertable.getReadername()+"','"+readertable.getEmail()+"','"+readertable.getTele()+"','"
	    	+readertable.getMaxBorrow()+"','"+readertable.getLongestTime()+"',"+readertable.getDeposit()+")";
	        falg = conn.executeUpdate(sql);
	        System.out.println("添加读者信息的SQL：" + sql);
	        conn.close();
	    }
	} catch (SQLException ex) {
	    falg = 0;
	}
	System.out.println("falg:"+falg);
	return falg;
	}

	//update reader information
	public int update(readerTable_abd readertable){
	String sql="Update "+'"'+"Reader"+'"'+" set password='"+readertable.getPassword()
	+"',sex='"+readertable.getSex()
	+"',reader_name='"+readertable.getReadername()
	+"',email='"+readertable.getEmail()
	+"',tele='"+readertable.getTele()
	+"',maxborrow='"+readertable.getMaxBorrow()
	+"',longesttime='"+readertable.getLongestTime()
	+" where reader_id="+readertable.getReaderID()+"";
	int falg=conn.executeUpdate(sql);
	System.out.println("修改数据时的SQL："+sql);
	conn.close();
	return falg;
	}
	//delete a reader
	public int delete(readerTable_abd readertable){
	String sql="Delete from "+'"'+"Reader"+'"'+" where reader_id="+readertable.getReaderID()+"";
	int falg=conn.executeUpdate(sql);
	System.out.println("删除时的SQL："+sql);
	return falg;
	}
}


/**
 * readerDAO readerdao = new readerDAO();//每次使用某个DAO接口需要先构造
 * String str = "";
 * str = "readerid=" + getParameter(userID);//query函数里面传字符串，属性+条件
 * Collection<readerTable> readerlist=new ArrayList<readerTable>();
 * readerlist = readerdao.query(str);//每个query传回来的都是对应对象的collection或arraylist
 * for(readerTable reader : readerlist){
 * 		reader.getSex();//无论取到的理应是一个对象还是很多个对象，因为返回的都是collection，所以都需要使用list方法
 * }
 * 
 */
