package BiblioSoft.adminAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;

/**
 * Servlet implementation class deleteLib
 */
public class DeleteLib extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		LibrarianDAO DeleteLib = new LibrarianDAO();
		
		//String LibID = (String) session.getAttribute("DeleteLib_ID");
		String LibID = request.getParameter("LibID");
		//int DeleteLibID = Integer.valueOf(LibID);
		int DeleteLibID = Integer.valueOf(String.valueOf(LibID));
		
		LibrarianTable Liblist = new LibrarianTable();
		try {
			Liblist = DeleteLib.queryFromId(DeleteLibID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Liblist != null) {
		try {
			DeleteLib.deleteLibrarian(DeleteLibID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out = response.getWriter();
		out.print("<script>alert('Success!');history.go(-2);</script>");
		out.close();
	}else {
		out = response.getWriter();
		out.print("<script>alert('Not Existing Librarian ID,Please Check Your Input!');history.back();</script>");
		out.close();
		}
	}

}
