package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.*;
import BiblioSoft.Table.BookTable;

/**
 * Servlet implementation class DeleteBookByID
 */
public class DeleteBookByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookByID() {
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
		HttpSession session = request.getSession();
		int id =Integer.valueOf(request.getParameter("bookID"));
		int lib_id = (Integer) session.getAttribute("Librarian_ID");

		String status = request.getParameter("status");
		System.out.println(status);
		
		BookDAO bookDAO = new BookDAO();
		DeletebookDAO deleteDAO = new DeletebookDAO();
		int flag = bookDAO.deleteBookInLib(id,status);
		PrintWriter out = response.getWriter();
		
		if(flag==1) {
		out.print("<script>alert('Delete book successfully!');window.location='deleteBook.jsp'</script>");
		deleteDAO.Insertdeletebook(id, lib_id);
		}else if (flag == 2) {
			out.print("<script>alert('This book is lent, you cannot delete!');window.location='deleteBook.jsp'</script>");
		}else if (flag ==3) {
			out.print("<script>alert('This book is reserved, you cannot delete!');window.location='deleteBook.jsp'</script>");
		}
		
		} 

}
