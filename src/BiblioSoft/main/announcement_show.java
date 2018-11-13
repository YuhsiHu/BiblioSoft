package BiblioSoft.main;

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
import java.io.UnsupportedEncodingException;

/**
 * Servlet implementation class DisplayThePost
 */
public class announcement_show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public announcement_show() {
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
		System.out.println(title);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		try {
			posttable = postdao.searchByTitle1(title);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("title", title);
		session.setAttribute("writer", posttable.getLibID());
		session.setAttribute("time", posttable.getTime());
		session.setAttribute("body", posttable.getBody());
		

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
	
	public static String doEncoding(String string) {
		 try {
			 string= new String(string.getBytes("ISO-8859-1"), "UTF-8");
			 } catch (UnsupportedEncodingException e) {
			 // TODO Auto-generated catch block
			e.printStackTrace();
			}
			 return string;

	}

}
