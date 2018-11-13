package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import BiblioSoft.Table.AdminTable;
import BiblioSoft.Table.PostTable;
import BiblioSoft.core.ConnDB;

public class PostDAO {

	private String sql = "";
	private ConnDB conn = new ConnDB();
	public PostDAO() {
		// TODO Auto-generated constructor stub
	}

	public boolean addPost(String title,String body,int libID){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());System.out.println(date);
		sql = "insert into post values('" + title + "','" + body + "','" + date + "'," + libID+  ")";
		System.out.println(sql);
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
	
	public boolean deletePost(String title){
		sql = "delete from post where title ="+"'"+ title +"'";
		System.out.println(sql);
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
	
	public boolean updateTitle(String oldTitle, String newTitle){
		sql = "update post SET title='" + newTitle + "' where title ='" + oldTitle +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("update title failed");
			return false;
		}else {
			conn.close();
			return true;
		}
	}
	
	public boolean updateBody(String title, String body){
		sql = "update post SET body='" + body + "' where title ='" + title +"'";
		int result = -1;
		result = conn.executeUpdate(sql);
		if(result == 0){
			System.out.println("update body failed");
			return false;
		}else {
			conn.close();
			return true;
		}
	}
	
	public Collection<PostTable> searchAll() throws SQLException {
		Collection<PostTable> postColl=new ArrayList<PostTable>();
		ResultSet rs = conn.executeQuery("select * from post");
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				PostTable post = new PostTable();
				post.setTitle(rs.getString(1));
				post.setBody(rs.getString(2));
				post.setTime(rs.getDate(3));
				post.setLibID(rs.getInt(4));
				postColl.add(post);
			}
		} else {
			System.out.println("no post...");
		}
		conn.close();
		return postColl;
	}
	
	public PostTable searchByTitle1(String title) throws SQLException {
		sql = "select * from post where title = '" + title + "'";
		ResultSet rs = conn.executeQuery(sql);
		PostTable post = new PostTable();
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				post.setTitle(rs.getString(1));
				post.setBody(rs.getString(2));
				post.setTime(rs.getDate(3));
				post.setLibID(rs.getInt(4));
			}
		} else {
			System.out.println("no post...");
		}
		conn.close();
		return post;
	}
	
	public Collection<PostTable> searchByTitle(String title) throws SQLException {
		sql = "select * from post where title like \'%" + title + "%\'";
		ResultSet rs = conn.executeQuery(sql);
		Collection<PostTable> coll = new ArrayList<PostTable>();
		if(rs.next()){
			rs.previous();
			while (rs.next()) {
				PostTable post = new PostTable();
				post.setTitle(rs.getString(1));
				post.setBody(rs.getString(2));
				post.setTime(rs.getDate(3));
				post.setLibID(rs.getInt(4));
				coll.add(post);
			}
		} else {
			System.out.println("no post...");
		}
		conn.close();
		return coll;
	}

}
