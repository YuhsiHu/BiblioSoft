package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.PostDAO;
import BiblioSoft.Table.PostTable;

/**
 * Servlet implementation class AllPost
 */
public class AllPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDAO postdao = new PostDAO();
		HttpSession session = request.getSession();
		
		try {
			Collection<PostTable> posts = postdao.searchAll();
			
			for(PostTable p : posts) {
				int longth = Math.min(100,p.getBody().length());
				p.setBody(p.getBody().substring(0, longth));
			}
			
			session.setAttribute("posts", posts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print("<script>window.location='searchPost.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDAO postdao = new PostDAO();
		HttpSession session = request.getSession();
		
		try {
			Collection<PostTable> posts = postdao.searchAll();
			for(PostTable p : posts) {
				int longth = Math.min(100,p.getBody().length());
				p.setBody(p.getBody().substring(0, longth));
			}
			
			session.setAttribute("posts", posts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print("<script>window.location='searchPost.jsp'</script>");
	}

}
