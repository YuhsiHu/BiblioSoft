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
public class LibrarianLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianLogin() {
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
		if(request.getParameter("LibrarianID")==""||request.getParameter("password")==""){
			out.print("<script language='javascript'>alert('Librarian ID And Password Can Not Be Empty!');window.location.href='librarianLogin.jsp';</script>");
		}else{
			int id = Integer.valueOf(request.getParameter("LibrarianID"));
	        String password=request.getParameter("password");
	        String ck=request.getParameter("ck");
	        
	        if("on".equals(ck)){
	        Cookie c=new Cookie("users", id+"-"+password);
	        c.setMaxAge(600);
	        response.addCookie(c);
	        }
			HttpSession session = request.getSession();
			
	        LibrarianDAO librarian = new LibrarianDAO();
	        LibrarianTable librarianTable = new LibrarianTable();
	        try {
	        	librarianTable = librarian.queryFromId(id);
				if(librarianTable.getLib_id()==-1){
		        	System.out.println("no librarian");
		        	out.print("<script language='javascript'>alert('No Such A Librarian !');window.location.href='librarianLogin.jsp';</script>");
		        }else{
		        	if(librarianTable.getPassword().equals(password)){
		        		System.out.println("login success");

		        		session.setAttribute("Librarian_ID", librarian.queryFromId(id).getLib_id());
		        		session.setAttribute("Librarian_Name", librarian.queryFromId(id).getLib_name());
		        		session.setAttribute("Librarian_Password", librarian.queryFromId(id).getPassword());
		        		response.sendRedirect("librarianHomepage.jsp");
		        	}
		        	else {
		        		out.print("<script language='javascript'>alert('Wrong Password !');window.location.href='librarianLogin.jsp';</script>"); 
		        	}
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
