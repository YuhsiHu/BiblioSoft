package BiblioSoft.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;
import BiblioSoft.core.*;

/**
 * Created by 鍒樺崜绋� on 2018/10/30.
 */
public class DeletebookDAO {
    private Connection connection = null;
    private Statement statement = null;
    private ConnDB connDB = new ConnDB();


    public Collection getDeleteByLibID(int Libid) {//閫氳繃LibrarianID 鏌ヨ鍒犻櫎璁板綍
        Collection<DeletebookTable> DeletebookTables = new ArrayList<DeletebookTable>();
        String sql = "select Librarian.Lib_id,Librarian.Lib_name,book.ISBN,Book_name,Book_Description,\n" +
                "Edition,Language,Publisher_time,price,page,publisher_name,author,catagory,\n" +
                "deletebook.book_id\n" +
                "from deleteBook,librarian,book,bookinlib\n" +
                "where deletebook.lib_id = librarian.lib_id\n" +
                "and book.isbn = bookinlib.isbn\n" +
                "and bookinlib.book_id = deletebook.Book_id\n" +
                "and deletebook.Lib_id =" + Libid;
        try {
            DeletebookTable DeletebookTable;
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                DeletebookTable = new DeletebookTable();
                DeletebookTable.setLib_id(Integer.valueOf(resultSet.getInt("lib_id")));
                DeletebookTable.setLib_name(resultSet.getString("Lib_name"));
                DeletebookTable.setIsbn(resultSet.getString("isbn"));
                DeletebookTable.setBookName(resultSet.getString("book_name"));
                DeletebookTable.setBookDescription(resultSet.getString("book_description"));
                DeletebookTable.setEdition(resultSet.getString("edition"));
                DeletebookTable.setLanguage(resultSet.getString("language"));
                DeletebookTable.setPublisher_time(resultSet.getString("publisher_time"));
                DeletebookTable.setPage(resultSet.getInt("page"));
                DeletebookTable.setPrice(resultSet.getString("price"));
                DeletebookTable.setAuthor(resultSet.getString("author"));
                DeletebookTable.setPublisher_name(resultSet.getString("publisher_name"));
                DeletebookTable.setCatagory(resultSet.getString("catagory"));
                DeletebookTable.setBookid(Integer.valueOf(resultSet.getInt("book_id")));

                DeletebookTables.add(DeletebookTable);
                // System.out.println("娣诲姞鎴愬姛");
            }
            connDB.close();
        } catch (Exception e) {
        }
        return DeletebookTables;
    }

    public Collection getDeleteByBookID(int Bookid) {//閫氳繃BookID 鏌ヨ鍒犻櫎璁板綍
        Collection<DeletebookTable> DeletebookTables = new ArrayList<DeletebookTable>();
        String sql = "select Librarian.Lib_id,Librarian.Lib_name,book.ISBN,Book_name,Book_Description,\n" +
                "Edition,Language,Publisher_time,price,page,publisher_name,author,catagory,\n" +
                "deletebook.book_id\n" +
                "from deleteBook,librarian,book,bookinlib\n" +
                "where deletebook.lib_id = librarian.lib_id\n" +
                "and book.isbn = bookinlib.isbn\n" +
                "and bookinlib.book_id = deletebook.Book_id\n" +
                "and deletebook.book_id ="+ Bookid;
        try {
            DeletebookTable DeletebookTable;
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                DeletebookTable = new DeletebookTable();
                DeletebookTable.setLib_id(Integer.valueOf(resultSet.getInt("lib_id")));
                DeletebookTable.setLib_name(resultSet.getString("Lib_name"));
                DeletebookTable.setIsbn(resultSet.getString("isbn"));
                DeletebookTable.setBookName(resultSet.getString("book_name"));
                DeletebookTable.setBookDescription(resultSet.getString("book_description"));
                DeletebookTable.setEdition(resultSet.getString("edition"));
                DeletebookTable.setLanguage(resultSet.getString("language"));
                DeletebookTable.setPublisher_time(resultSet.getString("publisher_time"));
                DeletebookTable.setPage(resultSet.getInt("page"));
                DeletebookTable.setPrice(resultSet.getString("price"));
                DeletebookTable.setAuthor(resultSet.getString("author"));
                DeletebookTable.setPublisher_name(resultSet.getString("publisher_name"));
                DeletebookTable.setCatagory(resultSet.getString("catagory"));
                DeletebookTable.setBookid(Integer.valueOf(resultSet.getInt("book_id")));

                DeletebookTables.add(DeletebookTable);
            }
            connDB.close();
        } catch (Exception e) {
        }
        return DeletebookTables;
    }

    public void Insertdeletebook(int BookID, int LibID) {

        BookDAO bookDAO = new BookDAO();
        bookDAO.updateBookInLibstatus(BookID, "deleted");

        String sql = "INSERT Into DeleteBook(book_id,lib_id)" +
                "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + LibID + "\'" + ")";
        try {
            DeletebookTable DeletebookTable = new DeletebookTable();
            // statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("娣诲姞鎴愬姛");
            connDB.close();
        } catch (Exception e) {
        }
    }

//    public Collection getAllDelete(int Bookid) {
//        Collection<DeletebookTable>deletebookTables = new ArrayList<>();
//        String sql = "select * from DeleteBook " +
//                "WHERE book_id =" + Bookid;
//        try {
//            DeletebookTable DeletebookTable;
//            ResultSet resultSet = connDB.executeQuery(sql);
//            while (resultSet.next()) {
//                //鍙栧嚭鍒楀��
//                DeletebookTable = new DeletebookTable();
//                DeletebookTable.setBookid(Integer.valueOf(resultSet.getInt("book_id")));
//                DeletebookTable.setLibrarianid(Integer.valueOf(resultSet.getInt("lib_id")));
//                deletebookTables.add(DeletebookTable);
//                // System.out.println("娣诲姞鎴愬姛");
//            }
//            connDB.close();
//        } catch (Exception e) {
//        }
//
//        return deletebookTables;
//    }


    public static void main(String[] args) {
        DeletebookDAO deletebookDAO = new DeletebookDAO();
        Collection<DeletebookTable>deletebookTables = deletebookDAO.getDeleteByLibID(1);
        for(DeletebookTable deletebookTable:deletebookTables){
            System.out.println(deletebookTable.getBookid());
        }
    }
}
