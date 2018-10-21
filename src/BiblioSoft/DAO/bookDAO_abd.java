package BiblioSoft.DAO;

import BiblioSoft.core.ConnDB;
import java.sql.*;
import java.util.*;
import BiblioSoft.Table.bookTable_abd;

/**
 * 
 * @author Hu Yuxi
 *
 */
public class bookDAO_abd {
	private ConnDB conn = new ConnDB();

	// Search for list
	public Collection query(String strif) {
		bookTable_abd booktable = null;
		Collection bookColl = new ArrayList();
		String sql = "";
		if (strif != "all" && strif != null && strif != "") {
			sql = "select \"Book\".*, \"BookInLib\".*" + 
					"from (\"Book\" left join \"BookInLib\" on \"Book\".isbn = \"BookInLib\".isbn ) " + 
					"left join \"Publisher\" on \"Publisher\".publisher_id = \"Book\".publisher_id " + 
					"where \"Book\".book_name = \'SPM\';";
		} else {
			sql = "select * from "+'"'+"Book"+'"'+" left join "+'"'+"BookInLib"+'"'+" on "+'"'+"Book"+'"'+".isbn="+'"'+"BookInLib"+'"'+".isbn"+"left join"+'"'+"Publisher"+'"'+"on publisher_id=publisher_id";
		}
		System.out.println("图书查询时的SQL：" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				booktable = new bookTable_abd();
				
				booktable.setAuthor(rs.getString("author"));
				booktable.setBookName(rs.getString("book_name"));
				booktable.setBookcaseName(rs.getString("bookcase_name"));
				booktable.setBookDescription(rs.getString("book_description"));
				booktable.setBookcaseid(rs.getInt("bookcase_id"));
				booktable.setBookID(Integer.valueOf(rs.getString("book_id")));
				booktable.setBookEdition(rs.getString("edition"));
				booktable.setISBN(rs.getString("isbn"));
				booktable.setLanguage(rs.getString("language"));
				booktable.setPage(rs.getInt("page"));
				booktable.setPrice(Float.valueOf(rs.getString("price"))); // 此处必须进行类型转换
				//booktable.setPublishing(rs.getString("publisher_name"));
				//booktable.setPublishing("Akizuki");
				booktable.setStatus(rs.getString("status"));
				booktable.setTranslator(rs.getString("translator"));
				booktable.setTypeId(Integer.valueOf(rs.getString("type_id")));
				booktable.setTypeName(rs.getString("type_name"));
				
				bookColl.add(booktable);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}


	// Search when borrow operation
	public bookTable_abd queryB(String f, String key) {
		bookTable_abd bookTable = null;
		String sql = "select "+'"'+"Book"+'"'+".*,"+'"'+"BookInLib"+'"'+".* from "+'"'+"Book"+'"'+" left join "+'"'+"BookInLib"+'"'+" on "+'"'+"Book"+'"'+".isbn="+'"'+"BookInLib"+'"'+".isbn "+"left join "+'"'+"Publisher"+'"'+"on publisher_id=publisher_id"+" where "+'"'+"Book"+'"'+"."
				+ f + "='" + key + "'";
		System.out.println("查询借阅信息时的SQL：" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				//you can only borrow one book every time
				bookTable = new bookTable_abd();
				bookTable.setAuthor(rs.getString("author"));
				bookTable.setBookName(rs.getString("book_name"));
				bookTable.setBookcaseName(rs.getString("bookcase_name"));
				bookTable.setBookDescription(rs.getString("book_description"));
				bookTable.setBookcaseid(rs.getInt("bookcase_id"));
				bookTable.setBookID(Integer.valueOf(rs.getString("book_id")));
				bookTable.setBookEdition(rs.getString("edition"));
				bookTable.setISBN(rs.getString("isbn"));
				bookTable.setLanguage(rs.getString("language"));
				bookTable.setPage(rs.getInt("page"));
				bookTable.setPrice(Float.valueOf(rs.getString("price"))); // 此处必须进行类型转换
				bookTable.setPublishing(rs.getString("publisher_name"));
				bookTable.setStatus(rs.getString("status"));
				bookTable.setTranslator(rs.getString("translator"));
				bookTable.setTypeId(Integer.valueOf(rs.getString("type_id")));
				bookTable.setTypeName(rs.getString("type_name"));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return bookTable;
	}

	// Add a book to library
	public int insert(bookTable_abd booktable) {
		//add a duplicate or a brand new book?
		String sql1 = "SELECT * FROM "+'"'+"BookInLib"+'"'+" WHERE book_id=" + booktable.getBookID() + "";
		String sql2 =  "SELECT * FROM "+'"'+"Book"+'"'+" WHERE isbn='" + booktable.getISBN() +  "'";
		
		ResultSet rs = conn.executeQuery(sql1);
		ResultSet rs2 = conn.executeQuery(sql2);
		
		String sql = "";
		String sql3="";
		int falg = 0;
		
		try {
			if (rs.next()) {
				//already have this book and id is repeated
				falg = 2;
			} else if(!rs.next() && rs2.next()) {
				//this is a dulipcation
				sql = "Insert into "+'"'+"BookInLib"+'"'+"(book_id,isbn,status) values('"+booktable.getBookID()+"','"+booktable.getISBN()+"','"+booktable.getStatus()+ "')";
				
				falg = conn.executeUpdate(sql);
				System.out.println("添加图书的SQL：" + sql);
				conn.close();

			}else if(!rs.next() && !rs2.next()){
				//this is a brand new book
				
				//if the publisher already exist
				String pubsql="";
				pubsql="select from "+'"'+"Publisher"+'"'+" where publisher_name="+booktable.getPublishing();
				ResultSet pubrs=conn.executeQuery(pubsql);
				int final_id=-1;
				if(!pubrs.next()) {
					//there is no this publisher
					int new_id=new Random().nextInt();
					String addpubsql="Insert INTO "+'"'+"Publisher"+'"'+" (publisher_id,publisher_name,address) values("+new_id+",'"+booktable.getPublishing()+"','Undefined')";
					conn.executeQuery(addpubsql);
					final_id=new_id;
				}else {
					//there it is,we get the publisher_id
					final_id=pubrs.getInt(1);
					
				}
				
				//if the bookcase already exist
				String casesql="";
				casesql="select from "+'"'+"Bookcase"+'"'+" where bookcase_name="+booktable.getBookcaseName();
				ResultSet casers=conn.executeQuery(pubsql);
				int case_id=-1;
				if(!casers.next()) {
					//there is no this bookcase
					int new_id=new Random().nextInt();
					String addcasesql="Insert INTO "+'"'+"Bookcase"+'"'+" (bookcase_id,bookcase_name) values("+new_id+",'"+booktable.getBookcaseName()+"')";
					conn.executeQuery(addcasesql);
					case_id=new_id;
				}else {
					//there it is,we get the publisher_id
					case_id=casers.getInt(1);
					
				}
				
				
				//insert this book
				sql = "Insert into "+'"'+"Book"+'"'+" (author,book_name,bookcase_name,book_description,bookcase_id,edition,isbn,"
						+ "language,page,price,publisher_id,translator,type_id,type_name) values('"
						+ booktable.getAuthor() + "','" 
						+ booktable.getBookName() + "','" 
						+ booktable.getBookcaseName() + "','" 
						+ booktable.getBookDescription() + "'," 
						+ case_id + ",'"
						+ booktable.getBookEdition() + "','" 
						+ booktable.getISBN() + "',"
						+ booktable.getPage() + ","
						+ booktable.getPrice() + ","
						+ String.valueOf(final_id) + ",'"
						+ booktable.getTranslator() + "',"
						+ booktable.getTypeId() + ",'"
						+ booktable.getTypeName()
						+ "')";
				sql3 = "Insert into "+'"'+"BookInLib"+'"'+"(book_id,isbn,status) values("+booktable.getBookID()+",'"+booktable.getISBN()+"','"+booktable.getStatus()+ "')";

				//add author list to author table
				String coauthor=booktable.getAuthor();
				ArrayList<String> authorlist = new ArrayList<String>();
				authorlist=booktable.getSingleAuthor(coauthor);
				String authorisbn=booktable.getISBN();
				for(String name:authorlist){
			            String authorsql="";
			            authorsql="Select author_name from "+'"'+"Author"+'"'+" where author_name='"+name+"'";
			            ResultSet authorrs=conn.executeQuery(authorsql);
			            
			            if(authorrs.next()) {
			            	//there is an author whose name equals 'name'
			            	String addisbnsql="Insert into "+'"'+"Write"+'"'+"(isbn,author_id) values('"+authorisbn
			            	+"',"+authorrs.getString("author_id")+")";
			            	conn.executeUpdate(addisbnsql);
			            }else {
			            	//this is a brand new author
			            	String addauthorsql="Insert into "+'"'+"Author"+'"'+"(author_id,author_name,author_description) values("+new Random().nextInt()
			            	+",'"+name+"','"+"Unedited"+"')";
			            	String addisbnsql="Insert into "+'"'+"Write"+'"'+"(isbn,author_id) values('"+authorisbn
					            	+"',"+String.valueOf(authorrs.getInt("author_id"))+")";
			            	conn.executeUpdate(addauthorsql);
			            	conn.executeUpdate(addisbnsql);
			            }
			            
			        }
			
				conn.executeUpdate(sql3);
				falg = conn.executeUpdate(sql);
				System.out.println("添加图书的SQL：" + sql);
				conn.close();
			}
		} catch (SQLException ex) {
			falg = 0;
		}
		System.out.println("falg:" + falg);
		return falg;
	}
	
	// Delete a book with ID
	public int delete(bookTable_abd bookTable) {
		String sql = "DELETE FROM "+'"'+"BookInLib"+'"'+" where book_id=" + bookTable.getBookID() + "";
		int falg = conn.executeUpdate(sql);
		System.out.println("删除时的SQL：" + sql);
		return falg;
	}

	// Update book status in BookInLib
	public int updateBookInLib(bookTable_abd booktable) {
		String sql = "Update "+'"'+"Book"+'"'+" set status='" + booktable.getStatus() + "' where book_id=" + booktable.getBookID() + "";

		int falg = conn.executeUpdate(sql);
		System.out.println("修改数据时的SQL：" + sql);
		conn.close();
		return falg;
	}
	
	// Update book information
	public int updateBookInfo(bookTable_abd booktable) throws SQLException {
		String pubsql="";
		pubsql="select from "+'"'+"Publisher"+'"'+" where publisher_name="+booktable.getPublishing();
		ResultSet pubrs=conn.executeQuery(pubsql);
		int final_id=-1;
		if(!pubrs.next()) {
			//there is no this publisher
			int new_id=new Random().nextInt();
			String addpubsql="Insert INTO "+'"'+"Publisher"+'"'+" (publisher_id,publisher_name,address) values("+new_id+",'"+booktable.getPublishing()+"','Undefined')";
			conn.executeQuery(addpubsql);
			final_id=new_id;
		}else {
			//there it is,we get the publisher_id
			final_id=pubrs.getInt(1);
			
		}
		
		String sql = "Update "+'"'+"Book"+'"'+" set author='" + booktable.getAuthor() + "',book_name='" + booktable.getBookName()
				+ "',bookcase_name='" + booktable.getBookcaseName() + "',book_description='" + booktable.getBookDescription() +
				"',bookcase_id=" + booktable.getBookcaseid() +
				",edition='" + booktable.getBookEdition() +
				"',isbn='" + booktable.getISBN() +
				"',language='" + booktable.getLanguage() +
				"',page=" + booktable.getPage() +
				",price="+ booktable.getPrice() + 
				",publisher_id=" + String.valueOf(final_id) + //id在book内，但name在publisher内
				",translator='" + booktable.getTranslator() +
				"',type_id='" + booktable.getTypeId() +
				"',type_name='" + booktable.getTypeName() +
				"' where book_id=" + booktable.getBookID() + "";

		int falg = conn.executeUpdate(sql);
		System.out.println("修改数据时的SQL：" + sql);
		conn.close();
		return falg;
	}

}