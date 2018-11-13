package BiblioSoft.librarianAction;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BiblioSoft.DAO.*;
import BiblioSoft.core.CharacterFilter;


/**
 * Servlet implementation class EditReader
 */
public class EditReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReaderDAO readerDAO = new ReaderDAO();
		PrintWriter out=response.getWriter();
		boolean p_flag;
		boolean e_flag;
		boolean n_flag;
		boolean s_flag;
		
		String tele = request.getParameter("tele");
		if(tele.equals("null")) {
			out.print("<script>alert('The account does not exist');window.location='librarianEditReader.jsp';</script>");
		}else {
		String password = request.getParameter("password");

		String email = request.getParameter("email");

		String name = request.getParameter("name");
		System.out.print(name);

		String sex = request.getParameter("gender");
		
		p_flag =readerDAO.updateEmailByTele(tele, email);
		e_flag =readerDAO.updatePasswordByTele(tele, password);
		n_flag =readerDAO.updateReaderNameByTele(tele, name);
		s_flag =readerDAO.updateSexByTele(tele, sex);
		
		if(p_flag ==true &&e_flag==true&&n_flag==true&&s_flag==true) {
			out.print("<script>alert('The information modified successfully !');window.location='librarianEditReader.jsp';</script>");
		}else {
			out.print("<script>alert('The information modified failed !');window.location='librarianEditReader.jsp';</script>");
		}
		}
	}

}
