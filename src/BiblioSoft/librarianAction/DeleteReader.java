package BiblioSoft.librarianAction;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BiblioSoft.DAO.*;

/**
 * Servlet implementation class DeleteReader
 */
public class DeleteReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReader() {
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
		ReaderDAO readerDAO = new ReaderDAO();
		PrintWriter out=response.getWriter();
		String tele = request.getParameter("tele");
		if(tele.equals("null")) {
			out.print("<script>alert(' The account deletion failed, the account does not exist');window.location='deleteReader.jsp';</script>");
		}else {
		try {
			boolean flag;
			flag = readerDAO.deleteByTele(tele);
			if(flag == true) {
				out.print("<script>alert('The account deleted successfully !');window.location='deleteReader.jsp';</script>");
			}else {
				out.print("<script>alert(' The account deletion failed, the fine was not paid or the book was not returned in the account!');window.location='deleteReader.jsp';</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
