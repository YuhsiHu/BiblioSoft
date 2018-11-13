package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.CategoryDAO;
import BiblioSoft.DAO.PostDAO;

/**
 * Servlet implementation class AddCategory
 */
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
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
		String category = request.getParameter("category");
	
		int libID = (Integer) session.getAttribute("Librarian_ID");
		//еп©у
		if (category == "" || category.isEmpty())
			out.print("<script>alert('The new categgory can not be null!');window.location='addCategory.jsp';</script>");

		CategoryDAO categorydao = new CategoryDAO();
		int flag=0;
		flag = categorydao.addCategory(category);
		
		if(flag == 1) {
			out.print("<script>alert('The category adding is successful!');window.location='addCategory.jsp';</script>");
		}else if(flag==2) {
			out.print("<script>alert('This category already exists! The category adding is failed!');window.location='addCategory.jsp';</script>");
		}else if(flag==3) {
			out.print("<script>alert('Please input a new category name!');window.location='addCategory.jsp';</script>");
		}
	}

}
