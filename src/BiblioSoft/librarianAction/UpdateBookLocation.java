package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BiblioSoft.DAO.BookDAO;

/**
 * Servlet implementation class UpdateBookLocation
 */
public class UpdateBookLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookLocation() {
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
		int id =Integer.valueOf(request.getParameter("bookID"));
		String location=request.getParameter("location");
		System.out.println(location);
		System.out.println(id);
		BookDAO bookDAO = new BookDAO();
		int flag=bookDAO.updateBookInLiblocation(id, location);
		PrintWriter out = response.getWriter();
		out.print("<script>alert('Update book location successfully!');window.location='searchEditBook.jsp'</script>");
	}

}
