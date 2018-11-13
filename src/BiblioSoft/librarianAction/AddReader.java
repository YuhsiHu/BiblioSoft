package BiblioSoft.librarianAction;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.*;
import BiblioSoft.Table.ReaderTable;
import BiblioSoft.core.*;

/**
 * Servlet implementation class AddReader
 */
public class AddReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReader() {
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
		ReaderTable readerTable = new ReaderTable();
		IncomeDao incomeDao = new IncomeDao();
		ReaderDAO readerDAO = new ReaderDAO();
		PrintWriter out=response.getWriter();
		
		String tele = request.getParameter("tele");
		tele =  CharacterFilter.filterStr(tele);
		System.out.print(tele);
		if(tele == ""||tele.isEmpty()) {
			out.print("<script>alert('The  account creation failed, Phone Number cannot null!');window.location='addReader.jsp';</script>");
		}
		
		String readerName = request.getParameter("readerName");
		readerName =  CharacterFilter.filterStr(readerName);
		if(readerName.isEmpty() || readerName =="") {
			out.print("<script>alert('The  account creation failed, Reader Name cannot null!');window.location='addReader.jsp';</script>");
		}
		
		String email = request.getParameter("email");
		email =  CharacterFilter.filterStr(email);
		if(email.isEmpty() || email == "") {
			out.print("<script>alert('The  account creation failed, Email cannot null!');window.location='addReader.jsp';</script>");
		}
		
		String paid = request.getParameter("paid");
		if(paid == null) {
			out.print("<script>alert('The  account creation failed, Deposit not paid');window.location='addReader.jsp';</script>");
		}
		String sex = request.getParameter("gender");
		if(sex == null) {
			out.print("<script>alert('The  account creation failed, Sex cannot null!');window.location='addReader.jsp';</script>");
		}
		
		try {
			boolean flag;
			flag = readerDAO.doCreate(sex, readerName, email, tele);
			
			//存储新加的账户信息
			HttpSession session = request.getSession();
			session.setAttribute("readerName", readerName);
			session.setAttribute("sex", sex);
			session.setAttribute("tele", tele);
			session.setAttribute("email", email);
			
			readerTable = readerDAO.findByTele(tele);
			String id = String.valueOf(readerTable.getID());
        	for (int j = 0;id.length() < 10; j++){
        		id = "0" + id;
            } 
			session.setAttribute("readerID",id);
			
			
			if(flag == true) {
				incomeDao.addDeposit();
				out.print("<script>alert('The account creation success!');window.location='librarianNewReaderInfo.jsp';</script>");
			}else {
				out.print("<script>alert('The  account creation failed, phone number may repeat!');window.location='addReader.jsp';</script>");
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
