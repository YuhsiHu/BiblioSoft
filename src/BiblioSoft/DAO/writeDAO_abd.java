package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.publisherTable;
import BiblioSoft.Table.writeTable;
import BiblioSoft.core.ConnDB;

public class writeDAO_abd {
	private ConnDB conn = new ConnDB();
	
	// search
		public Collection query(String strif) {
			writeTable writetable = null;
			Collection writeColl = new ArrayList();
			String sql = "";
			if (strif != "all" && strif != null && strif != "") {
				sql = "select * from "+'"'+"Write"+'"'+" where " + strif + "";
			} else {
				sql = "select * from "+'"'+"Write"+'"';
			}
			ResultSet rs = conn.executeQuery(sql);
			try {
				while (rs.next()) {
					writetable = new writeTable();
					writetable.setIsbn(rs.getString("isbn"));
					writetable.setAuthor_id(Integer.parseInt(rs.getString("publishername")));
					writeColl.add(writetable);
				}
			} catch (SQLException ex) {
			}
			conn.close();
			return writeColl;
		}
		
		public int addWrite(String isbn,int author_id) {
			int flag = 0;
			String sql="Insert into "+'"'+"Write"+'"'+"(isbn,author_id) values('"+isbn+"',"+ String.valueOf(author_id)+")";
			String sql1="select isbn,authot_id from "+'"'+"Write"+'"'+" where author_id="+String.valueOf(author_id)+"and isbn='"+isbn+"' ";
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
		
		public int updateWrite(String isbn,int author_id) {
			int flag = 0;
			String sql = "Update "+'"'+"Write"+'"'+" set author_id=" + String.valueOf(author_id) +",isbn='"+isbn+"' where author_id="+String.valueOf(author_id)+"and isbn='"+isbn+"' ";
			flag = conn.executeUpdate(sql);
			System.out.println("修改数据时的SQL：" + sql);
			conn.close();
			return flag;
		}
		
		public int deleteWrite(String isbn ,int author_id) {
			int flag = 0;
			String sql = "DELETE from"+'"'+"Write"+'"'+" where author_id="+String.valueOf(author_id)+"and isbn='"+isbn+"' ";
			flag = conn.executeUpdate(sql);
			System.out.println("删除数据时的SQL：" + sql);
			conn.close();
			return flag;
		}

}
