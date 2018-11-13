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

import BiblioSoft.DAO.AdminDAO;
import BiblioSoft.Table.AdminTable;

/**
 * Servlet implementation class UpdatePassword
 */
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
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
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("ConfirmPassword");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		int id = (Integer)session.getAttribute("Admin_ID");
		
		if(oldPassword==""||newPassword==""||confirmPassword==""){
			out.print("<script language='javascript'>alert('Please Fill All The From');window.location.href='adminModifyPassword.jsp';</script>");
		}else{
			try{
				AdminDAO admin = new AdminDAO();
				AdminTable adminTable = new AdminTable();
				if(confirmPassword.equals(newPassword)){
					adminTable = admin.queryFromId(id);
					if(oldPassword.equals(adminTable.getPassword())){
						admin.updatePassword(id, newPassword);
						session.setAttribute("Admin_Password", newPassword);
						session.invalidate();
						out.print("<script language='javascript'>alert('Update Successfully! Please Ligin Again');window.location.href='AdminLogin.jsp';</script>");
					}else{
						out.print("<script language='javascript'>alert('Wrong Old Password!');window.location.href='adminModifyPassword.jsp';</script>");
					}
				}else {
					out.print("<script language='javascript'>alert('The New Password You Inputed Is Not Match!');window.location.href='adminModifyPassword.jsp';</script>");
				}
			}catch (NumberFormatException e) {
				out.print("<script language='javascript'>alert('Invild Input!');window.location.href='adminModifyPassword.jsp';</script>");
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
