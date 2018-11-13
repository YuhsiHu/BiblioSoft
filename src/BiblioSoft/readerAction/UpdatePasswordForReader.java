package BiblioSoft.readerAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.*;
import BiblioSoft.Table.*;

/**
 * Servlet implementation class UpdatePasswordForReader
 */
public class UpdatePasswordForReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordForReader() {
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
		int id = (Integer)session.getAttribute("Reader_ID");
		
		if(oldPassword==""||newPassword==""||confirmPassword==""){
			out.print("<script language='javascript'>alert('Please Fill All The From');window.location.href='readerModifyPassword.jsp';</script>");
		}else{
			try{
				ReaderDAO reader = new ReaderDAO();
				ReaderTable readerTable = new ReaderTable();
				if(confirmPassword.equals(newPassword)) {
					readerTable = reader.findByID(id);
					if(oldPassword.equals(readerTable.getPassword())) {
						reader.updatePasswordByTele(readerTable.getTele(), newPassword);
						session.invalidate();
						out.print("<script language='javascript'>alert('Update Successfully! Please Login Again');window.location.href='ReaderLogin.jsp';</script>");
					}else {
						out.print("<script language='javascript'>alert('Wrong Old Password!');window.location.href='readerModifyPassword.jsp';</script>");
					}
				}else {
					out.print("<script language='javascript'>alert('The New Password You Inputed Is Not Match!');window.location.href='readerModifyPassword.jsp';</script>");
				}
			}catch (NumberFormatException e) {
				out.print("<script language='javascript'>alert('Invild Input!');window.location.href='readerModifyPassword.jsp';</script>");
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
}
