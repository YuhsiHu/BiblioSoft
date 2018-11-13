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
 * Servlet implementation class SearchPostByTitle
 */
public class SearchPostByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPostByTitle() {
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
		response.setCharacterEncoding("UTF-8");
		PostDAO postdao = new PostDAO();
		HttpSession session = request.getSession();
		String title = "";
		title = request.getParameter("thetitle");
		PrintWriter out = response.getWriter();
		System.out.println(title);
		
//		if(title == "" || title.isEmpty())
//			out.print("<script>alert('Please input the post title.');window.location='searchPost.jsp';</script>");
		
		try {
			Collection<PostTable> posts = postdao.searchByTitle(title);
			
			for(PostTable p : posts) {
				int longth = Math.min(100,p.getBody().length());
				p.setBody(p.getBody().substring(0,longth
						));
				
			}
			
			session.setAttribute("posts", posts);
			if(posts.isEmpty())
				out.print("<script>alert('There is no post!');window.location='librarianHomepage.jsp';</script>");
			else
				out.print("<script>window.location='searchPost.jsp'</script>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
