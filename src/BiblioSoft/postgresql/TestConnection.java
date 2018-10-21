package BiblioSoft.postgresql;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.BookTable;
import BiblioSoft.DAO.BookDAO;;
public class TestConnection {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/bibliosoft";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root";
	private Connection conn;
	public TestConnection() throws Exception{
		Class.forName(DRIVER);
		this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public void close() throws Exception{
		if(this.conn!=null) {
			try {
				this.conn.close();
			}catch(Exception e) {
				throw e;
			}
		}
	}
	
	public static void main(String[] args) {
		testbookDao();
	}
	
	public static void testbookDao() {
		BookTable booktable1=new BookTable("isbn123456","name1","description1","3rd edition","English","2018-10-15","12.1",33,"publishername1","author1","testcatagory","3F","avaliable");
		BookTable booktable2=new BookTable("isbn123456","name1","description1","3rd edition","English","2018-10-15","12.1",33,"publishername1","author1","testcatagory","3F","avaliable");
		BookTable booktable3=new BookTable("isbn123456","name1","description1","3rd edition","English","2018-10-15","12.1",33,"publishername1","author1","testcatagory","3F","avaliable");
		
		BookTable booktable4=new BookTable("isbn123457","name2","description2","3rd edition","English","2018-10-15","12.1",33,"publishername2","author2","testcatagory","4F","avaliable");
		BookTable booktable5=new BookTable("isbn123457","name2","description2","3rd edition","English","2018-10-15","12.1",33,"publishername2","author2","testcatagory","4F","avaliable");
	
		BookDAO bookdao=new BookDAO();
		
		//test add book
		bookdao.addBook(booktable1, 0);
		bookdao.addBook(booktable2, 1);
		bookdao.addBook(booktable3, 2);
		bookdao.addBook(booktable4, 1);
		bookdao.addBook(booktable5, 1);
		
		//test search
		Collection<BookTable> search1=new ArrayList<BookTable>();
		Collection<BookTable> search2=new ArrayList<BookTable>();
		Collection<BookTable> search3=new ArrayList<BookTable>();
		
		search1=bookdao.searchByAuthor("author1");
		System.out.println("search by author:");
		for(BookTable now : search1) {
			now.toString();
		}
		
		search2=bookdao.searchByPublisher("publishername2");
		System.out.println("search by author:");
		for(BookTable now : search2) {
			now.toString();
		}
		
		search3=bookdao.searchByTitle("name1");
		System.out.println("search by author:");
		for(BookTable now : search3) {
			now.toString();
		}

		//test update
		bookdao.updateBookInLiblocation(2, "1F");
		bookdao.updateBookInLibstatus(3, "unavaliable");
		
		//test delete
		bookdao.deleteBookInLib(7);
		bookdao.deleteBookInLib(8);

	}
	
}
