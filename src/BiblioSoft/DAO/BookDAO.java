package BiblioSoft.DAO;

import BiblioSoft.core.ConnDB;
import java.sql.*;
import java.util.*;
import BiblioSoft.Table.BookTable;;

/**
 * 
 * @author Yang Jian, Hu Yuxi
 * 
 *         ADD:book 
 *         DELETE:bookinlib
 *         UPDATE:bookinlibstatus,bookinliblocation
 *         SEARCH:bytitle,byauthor,bypublisher,byID,byISBN
 *
 */
public class BookDAO {
	private ConnDB conn = new ConnDB();

	/**
	 * @param title
	 *            用户想要查询的书籍的标题
	 * @return 返回书籍信息Collection
	 */
	public Collection searchByTitle(String title) {
		BookTable bookTable = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (title != "all" && title != "" && title != null) {
			sql = "select * from " + "bookinlib left join book on " + "bookinlib.isbn = book.isbn "
					+ "where book.book_name = " + "\'" + title + "\'";
		} else {

		}
		System.out.println("按照title查找的图书信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookTable = new BookTable();
				bookTable.setIsbn(rs.getString("isbn"));
				bookTable.setBookName(rs.getString("book_name"));
				bookTable.setBookDescription(rs.getString("book_description"));
				bookTable.setEdition(rs.getString("edition"));
				bookTable.setLanguage(rs.getString("language"));
				bookTable.setPublisher_time(rs.getString("publisher_time"));
				bookTable.setPage(rs.getInt("page"));
				bookTable.setPrice(rs.getString("price"));
				bookTable.setAuthor(rs.getString("author"));
				bookTable.setPublisher_name(rs.getString("publisher_name"));
				bookTable.setCatagory(rs.getString("catagory"));
				bookTable.setLocation(rs.getString("location"));
				bookTable.setStatus(rs.getString("status"));
				bookColle.add(bookTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return bookColle;
	}

	/**
	 * 
	 * @param author
	 *            用户想要查找的作者的名称
	 * @return 返回书籍信息Collection
	 */
	public Collection searchByAuthor(String author) {
		BookTable bookTable = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (author != "all" && author != "" && author != null) {
			sql = "select * from " + "bookinlib left join book on " + "bookinlib.isbn = book.isbn "
					+ "where book.author = " + "\'" + author + "\'";
		} else {

		}
		System.out.println("按照title查找的图书信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookTable = new BookTable();
				bookTable.setIsbn(rs.getString("isbn"));
				bookTable.setBookName(rs.getString("book_name"));
				bookTable.setBookDescription(rs.getString("book_description"));
				bookTable.setEdition(rs.getString("edition"));
				bookTable.setLanguage(rs.getString("language"));
				bookTable.setPublisher_time(rs.getString("publisher_time"));
				bookTable.setPage(rs.getInt("page"));
				bookTable.setPrice(rs.getString("price"));
				bookTable.setAuthor(rs.getString("author"));
				bookTable.setPublisher_name(rs.getString("publisher_name"));
				bookTable.setCatagory(rs.getString("catagory"));
				bookTable.setLocation(rs.getString("location"));
				bookTable.setStatus(rs.getString("status"));
				bookColle.add(bookTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return bookColle;
	}

	/**
	 * 
	 * @param publisher
	 *            用户想要查找的出版社名称
	 * @return 查找的书籍信息Collection
	 */
	public Collection searchByPublisher(String publisher) {
		BookTable bookTable = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (publisher != "all" && publisher != "" && publisher != null) {
			sql = "select * from " + "bookinlib left join book on " + "bookinlib.isbn = book.isbn "
					+ "where book.publisher_name = " + "\'" + publisher + "\'";
		} else {

		}
		System.out.println("按照title查找的图书信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookTable = new BookTable();
				bookTable.setIsbn(rs.getString("isbn"));
				bookTable.setBookName(rs.getString("book_name"));
				bookTable.setBookDescription(rs.getString("book_description"));
				bookTable.setEdition(rs.getString("edition"));
				bookTable.setLanguage(rs.getString("language"));
				bookTable.setPublisher_time(rs.getString("publisher_time"));
				bookTable.setPage(rs.getInt("page"));
				bookTable.setPrice(rs.getString("price"));
				bookTable.setAuthor(rs.getString("author"));
				bookTable.setPublisher_name(rs.getString("publisher_name"));
				bookTable.setCatagory(rs.getString("catagory"));
				bookTable.setLocation(rs.getString("location"));
				bookTable.setStatus(rs.getString("status"));
				bookColle.add(bookTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return bookColle;
	}

	/**
	 * search book by id
	 * @param id
	 * @return
	 */
	public BookTable searchByID(int id) {
		BookTable bookTable = null;
		String sql = null;
		sql = "select * from " + "bookinlib left join book on " + "bookinlib.isbn = book.isbn "
					+ "where bookinlib.book_id = " + String.valueOf(id);

		System.out.println("按照id查找的图书信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookTable = new BookTable();
				bookTable.setIsbn(rs.getString("isbn"));
				bookTable.setBookName(rs.getString("book_name"));
				bookTable.setBookDescription(rs.getString("book_description"));
				bookTable.setEdition(rs.getString("edition"));
				bookTable.setLanguage(rs.getString("language"));
				bookTable.setPublisher_time(rs.getString("publisher_time"));
				bookTable.setPage(rs.getInt("page"));
				bookTable.setPrice(rs.getString("price"));
				bookTable.setAuthor(rs.getString("author"));
				bookTable.setPublisher_name(rs.getString("publisher_name"));
				bookTable.setCatagory(rs.getString("catagory"));
				bookTable.setLocation(rs.getString("location"));
				bookTable.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return bookTable;
	}
	

	/**
	 * 
	 * @param isbn
	 * @return search book information by isbn
	 */
	public BookTable searchByIsbn(String isbn) {
		BookTable bookTable = null;		
		String sql = null;
		if (isbn != "all" && isbn != "" && isbn != null) {
			sql = "select * from " + "bookinlib left join book on " + "bookinlib.isbn = book.isbn "
					+ "where book.isbn = " + "\'" + isbn + "\'";
		} else {

		}
		System.out.println("按照isbn查找的图书信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			if(rs.next()) {
				bookTable = new BookTable();
				bookTable.setIsbn(rs.getString("isbn"));
				bookTable.setBookName(rs.getString("book_name"));
				bookTable.setBookDescription(rs.getString("book_description"));
				bookTable.setEdition(rs.getString("edition"));
				bookTable.setLanguage(rs.getString("language"));
				bookTable.setPublisher_time(rs.getString("publisher_time"));
				bookTable.setPage(rs.getInt("page"));
				bookTable.setPrice(rs.getString("price"));
				bookTable.setAuthor(rs.getString("author"));
				bookTable.setPublisher_name(rs.getString("publisher_name"));
				bookTable.setCatagory(rs.getString("catagory"));
				bookTable.setLocation(rs.getString("location"));
				bookTable.setStatus(rs.getString("status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return bookTable;
	}
	
	/**
	 * Add a book to library
	 * 
	 * @param booktable
	 * @return
	 */
	public void addBook(BookTable bookTable, int number) {
		// add a duplicate or a brand new book?
		String iscopysql = "select isbn from book where isbn = " + "\'" + bookTable.getIsbn() + "\'";
		ResultSet iscopyrs = conn.executeQuery(iscopysql);
		try {
			if (iscopyrs.next()) {
				// we just insert into bookinlib
				String addinlibsql = "insert into bookinlib (isbn,status,location) values (" + "\'"
						+ bookTable.getIsbn() + "\'" + " , " + "\'" + bookTable.getStatus() + "\'" + " , " + "\'"
						+ bookTable.getLocation() + "\'" + ")";
				for (int i = 0; i < number; i++) {
					conn.executeQuery(addinlibsql);
					System.out.println("add a book into bookinlib:" + addinlibsql);

				}
			} else {
				// insert book information to book
				String isbn = bookTable.getIsbn();
				String name = bookTable.getBookName();
				String description = bookTable.getBookDescription();
				String edition = bookTable.getEdition();
				String language = bookTable.getLanguage();
				String date = bookTable.getPublisher_time();
				String price = bookTable.getPrice();
				int page = bookTable.getPage();
				String publisher_name = bookTable.getPublisher_name();
				String author = bookTable.getAuthor();
				String catagory = bookTable.getCatagory();

				String addbooksql = "insert into book values (" + "\'" + isbn + "\'" + "," + "\'" + name + "\'" + ","
						+ "\'" + description + "\'" + "," + "\'" + edition + "\'" + "," + "\'" + language + "\'" + ","
						+ "\'" + date + "\'" + "," + "\'" + price + "\'" + "," + page + "," + "\'" + publisher_name
						+ "\'" + "," + "\'" + author + "\'" + "," + "\'" + catagory + "\'" + ")";

				conn.executeQuery(addbooksql);
				System.out.println("insert book information to book:" + addbooksql);

				// insert book into bookinib
				String addinlibsql = "insert into bookinlib (isbn,status,location) values (" + "\'"
						+ bookTable.getIsbn() + "\'" + " , " + "\'" + bookTable.getStatus() + "\'" + " , " + "\'"
						+ bookTable.getLocation() + "\'" + ")";
				for (int i = 0; i < number; i++) {
					conn.executeQuery(addinlibsql);
					System.out.println("add a book into bookinlib:" + addinlibsql);
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// // add author list to author table
	// String coauthor = booktable.getAuthor();
	// ArrayList<String> authorlist = new ArrayList<String>();
	// authorlist = booktable.getSingleAuthor(coauthor);
	// String authorisbn = booktable.getISBN();
	// for (String name : authorlist) {
	// String authorsql = "";
	// authorsql = "Select author_name from " + '"' + "Author" + '"' + " where
	// author_name='" + name + "'";
	// ResultSet authorrs = conn.executeQuery(authorsql);
	//
	// if (authorrs.next()) {
	// // there is an author whose name equals 'name'
	// String addisbnsql = "Insert into " + '"' + "Write" + '"' + "(isbn,author_id)
	// values('"
	// + authorisbn + "'," + authorrs.getString("author_id") + ")";
	// conn.executeUpdate(addisbnsql);
	// } else {
	// // this is a brand new author
	// String addauthorsql = "Insert into " + '"' + "Author" + '"'
	// + "(author_id,author_name,author_description) values(" + new
	// Random().nextInt() + ",'"
	// + name + "','" + "Unedited" + "')";
	// String addisbnsql = "Insert into " + '"' + "Write" + '"' + "(isbn,author_id)
	// values('"
	// + authorisbn + "'," + String.valueOf(authorrs.getInt("author_id")) + ")";
	// conn.executeUpdate(addauthorsql);
	// conn.executeUpdate(addisbnsql);
	// }
	//
	// }
	//
	// conn.executeUpdate(sql3);
	// falg = conn.executeUpdate(sql);
	// System.out.println("添加图书的SQL：" + sql);
	// conn.close();
	// }
	// } catch (SQLException ex) {
	// falg = 0;
	// }
	// System.out.println("falg:" + falg);
	// return falg;
	// }

	/**
	 * Delete a bookinlib with ID
	 * 
	 * @param id
	 * @return flag
	 * flag = 1,success
	 * flag = 2,unavailable
	 * flag = 3,reserved
	 */
	public int deleteBookInLib(int id) {
		String statussql = "select * from bookinlib where book_id = " + String.valueOf(id);
		ResultSet rs = conn.executeQuery(statussql);
		try {
			if (rs.next()) {
				BookTable bookTable = new BookTable();
				bookTable.setIsbn(rs.getString("isbn"));
				bookTable.setLocation(rs.getString("location"));
				bookTable.setStatus(rs.getString("status"));
				String status = bookTable.getStatus();
				if (status.equals("unavaliable")) {
					System.out.println("该书本还未归还，不可删除");
					int can = 2;
					return can;
				}else if(status.equals("reserved")) {
					System.out.println("该书本被预约，不可删除");
					int can = 3;
					return can;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		String sql = "delete from bookinlib where book_id = " + String.valueOf(id);
		int flag = conn.executeUpdate(sql);
		System.out.println("删除时的SQL：" + sql);
		return flag;
	}

	/**
	 * Update book status in bookinlib with ID:status
	 * 
	 * @param id,status
	 * @return
	 */
	public int updateBookInLibstatus(int id, String status) {
		String sql = "update bookinlib set status = " + "\'" + status + "\'" + " where book_id = " + String.valueOf(id);
		int flag = conn.executeUpdate(sql);
		System.out.println("更新数据时的SQL：" + sql);
		conn.close();
		return flag;
	}

	/**
	 * Update book status in bookinlib with ID:location
	 * 
	 * @param id
	 * @param location
	 * @return
	 */
	public int updateBookInLiblocation(int id, String location) {
		String sql = "update bookinlib set location = " + "\'" + location + "\'" + " where book_id = "
				+ String.valueOf(id);
		int flag = conn.executeUpdate(sql);
		System.out.println("更新数据时的SQL：" + sql);
		conn.close();
		return flag;
	}
}