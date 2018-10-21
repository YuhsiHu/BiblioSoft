package BiblioSoft.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BiblioSoft.DAO.BookDAO;
import BiblioSoft.Table.BookTable;

/**
 * Servlet implementation class SearchBookBeforeEdit
 */
public class SearchBookBeforeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookBeforeEdit() {
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
		if (request.getParameter("bookID") != "") {
			int id = Integer.valueOf(request.getParameter("bookID"));
			BookDAO bookDAO = new BookDAO();
			BookTable bookTable = new BookTable();
			bookTable = bookDAO.searchByID(id);
			if (bookTable != null) {
				// there is one book in collection
				//here are show
				request.setAttribute("ISBN", bookTable.getIsbn());
				request.setAttribute("book_name", bookTable.getBookName());
				request.setAttribute("publisher", bookTable.getPublisher_name());
				request.setAttribute("edition", bookTable.getEdition());
				request.setAttribute("catagory", bookTable.getCatagory());
				request.setAttribute("location", bookTable.getLocation());
				//here are hide
				request.setAttribute("bookID", id);
				request.setAttribute("description", bookTable.getBookDescription());
				request.setAttribute("language",bookTable.getLanguage());
				request.getRequestDispatcher("searchEditBook.jsp").forward(request, response);

			} else {
				// what to do if there is no such a book?
				PrintWriter out = response.getWriter();
				out.print("<script>alert('There is no such a book in library.');window.location='deleteBook.jsp'</script>");
			}
		}else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Please input book ID to edit it.');window.location='deleteBook.jsp'</script>");
		}
	}
	
}
