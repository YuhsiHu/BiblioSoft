package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.PostDAO;
import BiblioSoft.Table.PostTable;

/**
 * Servlet implementation class AddPost
 */
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPost() {
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int libID = (Integer) session.getAttribute("Librarian_ID");
		//еп©у
		if (title == "" || title.isEmpty())
			out.print("<script>alert('The title can not be null!');window.location='addPost.jsp';</script>");
		if (body == "" || body.isEmpty())
			out.print("<script>alert('The body can not be null!');window.location='addPost.jsp';</script>");
		
		PostDAO postdao = new PostDAO();
		boolean flag = false;
		flag = postdao.addPost(title, body, libID);
		
		if(flag == true)
			out.print("<script>alert('The post adding is successful!');window.location='addPost.jsp';</script>");
		else 
			out.print("<script>alert('The post adding is failed!');window.location='addPost.jsp';</script>");
	}

}
