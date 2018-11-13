package BiblioSoft.adminAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.LibrarianDAO;
import BiblioSoft.Table.LibrarianTable;

/**
 * Servlet implementation class registerLib
 */
public class RegisterLib extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		
		String LibName = request.getParameter("LibName");
		String DefaultPswd = "00010001";
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		LibrarianDAO NewLib = new LibrarianDAO();
		
		try {
			NewLib.addLibrarian(DefaultPswd,LibName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collection<LibrarianTable> Librarianlist = new ArrayList<LibrarianTable>();
		try {
			Librarianlist = NewLib.queryAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int registerID = -1;
		for(LibrarianTable LibrarianInfo : Librarianlist) {
			registerID = LibrarianInfo.getLib_id();
		}
		
		if(registerID == -1){
			out = response.getWriter();
			out.print("<script>alert('Wrong!');history.back();</script>");
			out.close();
		}else{
			session.setAttribute("newRegisterID", registerID);
			session.setAttribute("newRegisterName", LibName);
			
			RequestDispatcher view = request.getRequestDispatcher("adminNewLibrarianInfo.jsp");
			view.forward(request, response);
		}
	}

}
