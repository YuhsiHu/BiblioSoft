package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.AdminDAO;
import BiblioSoft.DAO.DefaultValue;
import BiblioSoft.DAO.LibrarianDAO;
import BiblioSoft.Table.AdminTable;
import BiblioSoft.Table.LibrarianTable;

/**
 * Servlet implementation class Admin_index
 */
public class LibrarianForgetPswd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianForgetPswd() {
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
		
		PrintWriter out=response.getWriter();
		if(request.getParameter("LibrarianID")==""||request.getParameter("LibrarianName")==""||request.getParameter("newPassword")==""||request.getParameter("confirmPassword")==""){
			out.print("<script language='javascript'>alert('All Librarian Information Can Not Be Empty!');window.location.href='librarianForgetPassword.jsp';</script>");
		}else{
			
			int id = Integer.valueOf(request.getParameter("LibrarianID"));
	        String Libname = request.getParameter("LibrarianName");
	       
			HttpSession session = request.getSession();
			
	        LibrarianDAO librarian = new LibrarianDAO();
	        LibrarianTable librarianTable = new LibrarianTable();
	        try {
	        	librarianTable = librarian.queryFromId(id);
				if(librarianTable.getLib_id()==-1){
		        	System.out.println("no librarian");
		        	out.print("<script language='javascript'>alert('No Such A Librarian !');window.location.href='librarianForgetPassword.jsp';</script>");
		        }else{
		        	if(librarianTable.getLib_name().equals(Libname)){

		        		String newPassword = request.getParameter("newPassword");
		        		String confirmPassword = request.getParameter("confirmPassword");
		        		
		        		if(newPassword.equals(confirmPassword)) {
		        			librarian.updateLibrarianPassword(id, newPassword);
		        			out.print("<script language='javascript'>alert('Success !');window.history.go(-2);</script>");
		        		}else {
		        			out.print("<script language='javascript'>alert('The New Password Is Not Equals To The Confirm Password !');window.location.href='librarianLogin.jsp';</script>");
		        		}
		        		
		        	}
		        	else {
		        		out.print("<script language='javascript'>alert('Wrong Librarian Name !');window.location.href='librarianForgetPassword.jsp';</script>"); 
		        	}
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
