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
public class DeleteSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String DeleteLibID = request.getParameter("LibID");
		if (DeleteLibID == ""){
			out.print("<script>alert('Wrong!');history.back();</script>");
		}else{
			LibrarianDAO DeleteLib = new LibrarianDAO();
			LibrarianTable Librarianlist = new LibrarianTable();
			try {
				Librarianlist = DeleteLib.queryFromId(Integer.parseInt(DeleteLibID));
				if(Librarianlist.getLib_id()==-1) {
					out.print("<script language='javascript'>alert('No Such Librarian!');history.back();;</script>");
				}else {
					session.setAttribute("DeleteLib_ID", DeleteLibID);
					session.setAttribute("DeleteLib_Name", Librarianlist.getLib_name());
					RequestDispatcher view = request.getRequestDispatcher("delete-search.jsp");
					view.forward(request, response);
				}
			} catch (NumberFormatException e) {
				out.print("<script language='javascript'>alert('Invild Input!');history.back();;</script>");
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
