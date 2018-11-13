package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.PostDAO;
import BiblioSoft.Table.PostTable;

/**
 * Servlet implementation class DisplayThePost
 */
public class DisplayThePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayThePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PostDAO postdao = new PostDAO();
		PostTable posttable = new PostTable();
		String title = "";
		title = request.getParameter("title");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		try {
			posttable = postdao.searchByTitle1(title);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("title", title);
		session.setAttribute("body", posttable.getBody());
		session.setAttribute("time", posttable.getTime());
		
//		request.getRequestDispatcher("thePost.jsp");
		RequestDispatcher view = request.getRequestDispatcher("postShow.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
