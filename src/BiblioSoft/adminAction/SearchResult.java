package BiblioSoft.adminAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.*;
import BiblioSoft.Table.LibrarianTable;

/**
 * Servlet implementation class searchResult
 */
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String SearchID = request.getParameter("searchID");
		if (SearchID == ""){
			out.print("<script>alert('Wrong!');history.back();</script>");
		}else{
			LibrarianDAO SearchLib = new LibrarianDAO();
			LibrarianTable Librarianlist = new LibrarianTable();
			try {
				Librarianlist = SearchLib.queryFromId(Integer.parseInt(SearchID));
				if(Librarianlist.getLib_id()==-1) {
					out.print("<script language='javascript'>alert('No Such a Admin!');history.back();;</script>");
				}else {
					session.setAttribute("SearchLib_ID", SearchID);
					session.setAttribute("SearchLib_Name", Librarianlist.getLib_name());
					session.setAttribute("SearchLib_Password", Librarianlist.getPassword());
					RequestDispatcher view = request.getRequestDispatcher("adminSearchLibrarian.jsp");
					view.forward(request, response);
				}
			} catch (NumberFormatException e) {
				out.print("<script language='javascript'>alert('Invild Input!');window.location.href='manage-search.jsp';</script>");
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
