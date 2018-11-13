package BiblioSoft.readerAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BiblioSoft.DAO.ReaderDAO;
import BiblioSoft.Table.ReaderTable;
import BiblioSoft.core.CharacterFilter;
import BiblioSoft.core.SendEmailForPassword;

/**
 * Servlet implementation class ReaderRetirevePassword
 * 
 * @author Hu Yuxi
 *
 */
public class ReaderRetrievePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReaderRetrievePassword() {
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
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String reader_id_1=request.getParameter("reader_id");
		String telephone_1=request.getParameter("telephone");
		
		String reader_id=CharacterFilter.isEmpty(reader_id_1);
		String telephone=CharacterFilter.isEmpty(telephone_1);
		if(reader_id==null) {
			out.print("<script>alert('Please write Reader ID to retrieve password!');window.location='ReaderRetrievePassword.jsp'</script>");
		}
		if(telephone==null) {
			out.print("<script>alert('Please write your telephone to retrieve youe password! ');window.location='ReaderRetrievePassword.jsp'</script>");
		}
		
		String initpassword="12345678";
		
		ReaderDAO readerdao=new ReaderDAO();
		try {
			ReaderTable reader=readerdao.findByID(Integer.valueOf(reader_id));
			if (reader.getTele().equals(telephone)) {
				//reset reader's password and send a email
				readerdao.updatePasswordByTele(telephone, initpassword);
				String mail=reader.getEmail();
				String reader_name=reader.getReaderName();
				SendEmailForPassword.SendEmailForPassword(mail, reader_name, initpassword);			
				out.print("<script>alert('Reset your password successfully! Please check your e-mail!');window.location='ReaderRetrievePassword.jsp'</script>");
			}else {
				//tell reader the id and telephone does not match			
				out.print("<script>alert('Sorry! Your ID and telephone doesn't match!');window.location='ReaderRetrievePassword.jsp'</script>");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
