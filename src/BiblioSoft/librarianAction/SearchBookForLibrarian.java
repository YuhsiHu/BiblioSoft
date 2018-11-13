package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.BookDAO;
import BiblioSoft.Table.BookTable;

/**
 * Servlet implementation class SearchBookForLibrarian
 */
public class SearchBookForLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookForLibrarian() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setCharacterEncoding("UTF-8");
				PrintWriter out=response.getWriter();
				BookDAO  bookDAO =new BookDAO();
				HttpSession session = request.getSession();  
				Collection<BookTable> books =new  ArrayList<BookTable>();
				BookTable booktable = new BookTable();
				int i = 0;

				String style = request.getParameter("style");
				String name = request.getParameter("name");
				name = name.trim();
				if(name == ""||name.isEmpty()) {
					out.print("<script>alert('Please enter the full keyword, the keyword cannot null!');window.location='searchBook.jsp';</script>");
				}
				if(style.equals("bookName")) {
					books = bookDAO.searchByTitle(name);
				}else if(style.equals("author")) {
					books = bookDAO.searchByAuthor(name);
				}else if(style.equals("publisher")) {
					books = bookDAO.searchByPublisher(name);
				}else if(style.equals("bookID")) {
					try{
						int id = Integer.parseInt(name);
						booktable = bookDAO.searchByID(id);
						if(booktable != null) {
							books.add(booktable);
						}
						
					}
					catch(NumberFormatException e)
					  {
						out.print("<script>alert('Please enter the number!');window.location='searchBook.jsp';</script>");
						  }
					
					
				}else {
					out.print("<script>alert('Please select the search type!');window.location='searchBook.jsp';</script>");
				}
				System.out.println(books.size());
				if(books.isEmpty()) {
					out.print("<script>alert('No related books!Please try new search terms! !');window.location='searchBook.jsp';</script>");
				}else {
					session.setAttribute("bookcount", books.size());
					for(BookTable book : books) {
						session.setAttribute("bookname"+i, book.getBookName());
						System.out.println(session.getAttribute("bookname"+i));
						session.setAttribute("bookisbn"+i, book.getIsbn());
						session.setAttribute("bookauthor"+i, book.getAuthor());
						session.setAttribute("bookpublisher"+i, book.getPublisher_name());
						session.setAttribute("bookedition"+i, book.getEdition());
						session.setAttribute("bookstatue"+i, book.getStatus());
						i++;
					}
					request.getRequestDispatcher( "searchBook.jsp").forward(request,response);
				}
	}

}
