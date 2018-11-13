package BiblioSoft.librarianAction;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;



/**
 * Servlet implementation class FindReaderForEdit
 */
public class FindReaderForEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindReaderForEdit() {
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
		// TODO Auto-generated method stub
		try {
			// 从editReader.jsp取得readerID
			PrintWriter out=response.getWriter();
			String ID = request.getParameter("readerID");
			ID =ID.trim();
			int id = Integer.parseInt(ID);		
			System.out.println(id);
			ReaderTable reader = new ReaderTable();
			ReaderDAO readerDAO= new ReaderDAO();

			//通过ID从数据库搜索reader
			reader = readerDAO.findByID(id);
			//如果找不到相应的reader，则给出提示
			if(reader.getTele() == null) {
				out.print("<script>alert(' The account does not exist!');window.location='librarianEditReader.jsp';</script>");

			}else {
				//如果找到相应的reader，则返回数据
				request.setAttribute( "id",reader.getID()); 
				request.setAttribute("readerName", reader.getReaderName());
				System.out.println( reader.getReaderName());
				request.setAttribute( "sex",reader.getSex());
				System.out.println( reader.getSex());
				request.setAttribute( "tele",reader.getTele());
				request.setAttribute( "email",reader.getEmail());
				request.setAttribute( "maxBorrow",reader.getMaxborrow());
				request.setAttribute( "deposit",reader.getDeposit());
				request.setAttribute( "password",reader.getPassword());
	            request.getRequestDispatcher( "librarianEditReader.jsp").forward(request,response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NumberFormatException e)
		  {
			PrintWriter out=response.getWriter();
			out.print("<script>alert('Please enter the number!');window.location='librarianEditReader.jsp';</script>");
			  }
	}

}
