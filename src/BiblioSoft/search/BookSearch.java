package BiblioSoft.search;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.*;
import BiblioSoft.Table.*;

/**
 * Servlet implementation class BookSearch
 */
public class BookSearch extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String searchType = request.getParameter("style");
		String searchTarget = request.getParameter("name");
		BookDAO bd = new BookDAO();
		BookTable bt = new BookTable();
		Collection bookColle = new ArrayList();
		System.out.println(searchType);
		System.out.println(searchTarget);
		if(searchType.equals("bookName")) {
			bookColle=bd.searchByTitle(searchTarget);
			for(int i=0;i<bookColle.size();i++) {
				bt = (BookTable) ((ArrayList) bookColle).get(i);
				request.setAttribute("Status", bt.getStatus());
				System.out.println(bt.getStatus());
			}
		}
	}

}
