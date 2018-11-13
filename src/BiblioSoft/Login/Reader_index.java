package BiblioSoft.Login;

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
import BiblioSoft.DAO.ReaderDAO;
import BiblioSoft.Table.AdminTable;
import BiblioSoft.Table.ReaderTable;

/**
 * Servlet implementation class Admin_index
 */
public class Reader_index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reader_index() {
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
		if(request.getParameter("AdminID")==""||request.getParameter("password")==""){
			out.print("<script language='javascript'>alert('Tele And Password Can Not Be Empty!');window.location.href='ReaderLogin.jsp';</script>");
		}else{
			String id = request.getParameter("AdminID");
	        String password=request.getParameter("password");
	        if(id.length()!=11){
	        	System.out.println("test");
	        	out.print("<script language='javascript'>alert('Tele Length is 11');window.location.href='ReaderLogin.jsp';</script>");
	        }else{
	        	String ck=request.getParameter("ck");
		        
		        if("on".equals(ck)){
		        Cookie c=new Cookie("users", id+"-"+password);
		        c.setMaxAge(3*60);
		        response.addCookie(c);
		        }
				HttpSession session = request.getSession();
		        DefaultValue dValue = new DefaultValue();
		        ReaderTable readerTable=new ReaderTable();
		        ReaderDAO readerDAO=new ReaderDAO();
		        try {
		        	readerTable = readerDAO.findByTele(id);
					if(readerTable.getID()==0){
			        	System.out.println("no Reader");
			        	out.print("<script language='javascript'>alert('No Such A Reader !');window.location.href='ReaderLogin.jsp';</script>");
			        }else{
			        	if(readerTable.getPassword().equals(password)){          
			        		session.setAttribute("Reader_ID", readerTable.getID());
			        		session.setAttribute("Tele", id);
			        		response.sendRedirect("readerDashboard.jsp");
			        	}
			        	else {
			        		out.print("<script language='javascript'>alert('Wrong Password !');window.location.href='ReaderLogin.jsp';</script>"); 
			        	}
			        }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
	}
}


