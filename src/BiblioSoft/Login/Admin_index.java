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
import BiblioSoft.Table.AdminTable;

/**
 * Servlet implementation class Admin_index
 */
public class Admin_index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_index() {
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
			out.print("<script language='javascript'>alert('Admin ID And Password Can Not Be Empty!');window.location.href='adminLogin.jsp';</script>");
		}else{
			String id = request.getParameter("AdminID");
	        String password=request.getParameter("password");
	        if(id.length()!=6){
	        	System.out.println("test");
	        	out.print("<script language='javascript'>alert('Admin ID Length is 6');window.location.href='adminLogin.jsp';</script>");
	        }else{
	        	String ck=request.getParameter("ck");
		        
		        if("on".equals(ck)){
		        Cookie c=new Cookie("users", id+"-"+password);
		        c.setMaxAge(3*60);
		        response.addCookie(c);
		        }
				HttpSession session = request.getSession();
				
		        AdminDAO admin = new AdminDAO();
		        DefaultValue dValue = new DefaultValue();
		        AdminTable adminTable = new AdminTable();
		        try {
					adminTable = admin.queryFromId(Integer.valueOf(id));
					if(adminTable.getAdmin_id()==0){
			        	System.out.println("no admin");
			        	out.print("<script language='javascript'>alert('No Such A Librarian !');window.location.href='adminLogin.jsp';</script>");
			        }else{
			        	if(adminTable.getPassword().equals(password)){
			        		System.out.println("login success");
			        		session.setAttribute("LongestTime", dValue.getLongesttime());
			        		session.setAttribute("fine", dValue.getFine());
			        		session.setAttribute("deposit", dValue.getDeposit());
			        		session.setAttribute("reserve_time", dValue.getReserve_time());
			        		session.setAttribute("Admin_ID", id);
			        		session.setAttribute("Admin_Name", admin.queryFromId(Integer.valueOf(id)).getAdmin_name());
			        		session.setAttribute("Admin_Password", admin.queryFromId(Integer.valueOf(id)).getPassword());
			        		response.sendRedirect("index.jsp");
			        	}
			        	else {
			        		out.print("<script language='javascript'>alert('Wrong Password !');window.location.href='adminLogin.jsp';</script>"); 
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
