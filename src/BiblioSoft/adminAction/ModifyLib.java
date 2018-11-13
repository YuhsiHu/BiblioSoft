package BiblioSoft.adminAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.*;

/**
 * Servlet implementation class ModifyLib
 */
public class ModifyLib extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		
		int LibID = Integer.valueOf((String) session.getAttribute("SearchLib_ID"));
		String NewLibName = request.getParameter("newLibName");
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		LibrarianDAO ModifyLib = new LibrarianDAO();
		
		try {
			ModifyLib.updateLibrarianName(LibID, NewLibName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out = response.getWriter();
		out.print("<script>alert('Success!');history.back();;</script>");
		out.close();
		
	}

}
