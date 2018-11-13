package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.LocationDAO;

/**
 * Servlet implementation class DeleteLocation
 */
public class DeleteLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLocation() {
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
		String location = request.getParameter("location");
	
		int libID = (Integer) session.getAttribute("Librarian_ID");
		//еп©у
		if (location == "" || location.isEmpty())
			out.print("<script>alert('The location name can not be null!');window.location='deleteLocation.jsp';</script>");

		LocationDAO locationdao = new LocationDAO();
		int flag=0;
		flag = locationdao.deleteLocation(location);
		if(flag == 1) {
			out.print("<script>alert('The location is deleted successfully!');window.location='deleteLocation.jsp';</script>");
		}else if(flag==2) {
			out.print("<script>alert('This location cannot be deleted! Some books belong to this location!');window.location='deleteLocation.jsp';</script>");
		}else if(flag==3) {
			out.print("<script>alert('There is not such a location!');window.location='deleteLocation.jsp';</script>");
		}
	}

}
