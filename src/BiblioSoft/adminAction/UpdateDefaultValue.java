package BiblioSoft.adminAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import BiblioSoft.DAO.DefaultValue;

/**
 * Servlet implementation class UpdateDefaultValue
 */
public class UpdateDefaultValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDefaultValue() {
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
		PrintWriter out=response.getWriter();
		if(request.getParameter("oldDefaultValue")==""||request.getParameter("newDefaultValue")==""){
			out.print("<script language='javascript'>alert('The Old Default Value Or New Default Value Can Not Be Empty!');window.location.href='adminModifyDefaultValue.jsp?Param=30&type=LongestTime';</script>");
		}else{
			try{
				String oldDefaultValue = request.getParameter("oldDefaultValue");
				String newDefaultValue = request.getParameter("newDefaultValue");
				String type = request.getParameter("type");
				System.out.println(type);
				HttpSession session = request.getSession();
				DefaultValue dv = new DefaultValue();
				if (type.equals("LongestTime")) {
					dv.setLongesttime(Integer.valueOf(newDefaultValue));
					session.setAttribute("LongestTime", newDefaultValue);
					out.print("<script language='javascript'>alert('Update LongestTime Successfully!');window.location.href='adminModifyDefaultValue.jsp';</script>");
				} else if (type.equals("fine")) {
					dv.setFine(Double.valueOf(newDefaultValue));
					session.setAttribute("fine", newDefaultValue);
					out.print("<script language='javascript'>alert('Update Fine Successfully!');window.location.href='adminModifyDefaultValue.jsp';</script>");
				} else if(type.equals("deposit")){
					dv.setDeposit(Double.valueOf(newDefaultValue));
					session.setAttribute("deposit", newDefaultValue);
					out.print("<script language='javascript'>alert('Update Deposit Successfully!');window.location.href='adminModifyDefaultValue.jsp';</script>");
				} else if(type.equals("reserve_time")){
					dv.setReserve_time(Double.valueOf(newDefaultValue));
					session.setAttribute("reserve_time", newDefaultValue);
					out.print("<script language='javascript'>alert('Update Reserve Time Successfully!');window.location.href='adminModifyDefaultValue.jsp';</script>");
				} else {
					out.print("<script language='javascript'>alert('Error!');window.location.href='adminModifyDefaultValue.jsp';</script>");
				}
				System.out.println(oldDefaultValue);
				System.out.println(newDefaultValue);
				System.out.println(type);
			}catch (NumberFormatException e) {
					out.print("<script language='javascript'>alert('Invild Input!');window.location.href='adminModifyDefaultValue.jsp';</script>");
			}
		}
	}

}
