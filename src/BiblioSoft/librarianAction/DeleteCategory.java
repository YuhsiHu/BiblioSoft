package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.CategoryDAO;

/**
 * Servlet implementation class DeleteCategory
 */
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategory() {
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
			out.print("<script>alert('The categgory name can not be null!');window.location='deleteCategory.jsp';</script>");

		CategoryDAO categorydao = new CategoryDAO();
		int flag=0;
		flag = categorydao.deleteCategory(category);
		
		if(flag == 1) {
			out.print("<script>alert('The category is deleted successfully!');window.location='deleteCategory.jsp';</script>");
		}else if(flag==2) {
			out.print("<script>alert('This category cannot be deleted! Some books belong to this category!');window.location='deleteCategory.jsp';</script>");
		}else if(flag==3) {
			out.print("<script>alert('There is not such a category!');window.location='deleteCategory.jsp';</script>");
		}
	}

}
