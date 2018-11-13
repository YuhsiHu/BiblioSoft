package BiblioSoft.librarianAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.LocationDAO;
import BiblioSoft.DAO.LocationDAO;

/**
 * Servlet implementation class AddLocation
 */
public class AddLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLocation() {
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
	
		//int libID = (Integer) session.getAttribute("Librarian_ID");
		//еп©у
		if (location == "" || location.isEmpty())
			out.print("<script>alert('The new categgory can not be null!');window.location='addLocation.jsp';</script>");

		LocationDAO locationdao = new LocationDAO();
		int flag=0;
		flag = locationdao.addLocation(location);
		
		if(flag == 1) {
			out.print("<script>alert('The location adding is successful!');window.location='addLocation.jsp';</script>");
		}else if(flag==2) {
			out.print("<script>alert('This location already exists! The location adding is failed!');window.location='addLocation.jsp';</script>");
		}else if(flag==3) {
			out.print("<script>alert('Please input a new location name!');window.location='addLocation.jsp';</script>");
		}
	}

}
