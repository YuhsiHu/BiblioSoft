package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.PostDAO;
import BiblioSoft.core.CharacterFilter;

/**
 * Servlet implementation class DeleteOrEditPost
 */
public class DeleteOrEditPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteOrEditPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PostDAO postdao = new PostDAO();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String newtitle1 = request.getParameter("newTitle");
		String oldtitle = (String) session.getAttribute("title");
		String newbody1 = request.getParameter("newBody");

		String update = request.getParameter("update");
		String delete = request.getParameter("delete");

		String newtitle=CharacterFilter.isEmpty(newtitle1);
		String newbody=CharacterFilter.isEmpty(newbody1);
		
		if (newtitle == null || newtitle.isEmpty())
			out.print("<script>alert('The post title can not be null!');window.location='postShow.jsp';</script>");
		if (newbody == null || newbody.isEmpty())
			out.print("<script>alert('The post body can not be null!');window.location='postShow.jsp';</script>");

			if (delete != null) {
				postdao.deletePost(oldtitle);
				out.print(
						"<script>alert('The post is deleted successfully!');window.location='searchPost.jsp';</script>");
			}
			if (update != null) {
				postdao.updateBody(oldtitle, newbody);
				postdao.updateTitle(oldtitle, newtitle);
				out.print(
						"<script>alert('The post is updated successfully!!');window.location='searchPost.jsp';</script>");
			}
		

	}

}
