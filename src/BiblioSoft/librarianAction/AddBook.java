package BiblioSoft.librarianAction;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BiblioSoft.DAO.BookDAO;
import BiblioSoft.Table.BookTable;

/**
 * Servlet implementation class UpdateBookLocation
 */
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
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
 		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		BookTable newbook = new BookTable();
		BookDAO bookdao = new BookDAO();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String number1= "";
		String page1 = "";
		String isbn = "";
		String bookname = "";
		String bookdes = "";
		String author = "";
		String catagory = "";
		String edition = "";
		String language = "";
		String location = "";
		String price = "";
		String pubname = "";
		String pubtime = "";
		String status = "";
		
		
		number1= request.getParameter("Number");
		page1 = request.getParameter("Page");
		isbn = request.getParameter("ISBN");
		bookname = request.getParameter("Book Name");
		bookdes = request.getParameter("Book Description");
		author = request.getParameter("Author");
		catagory = request.getParameter("Category");
		edition = request.getParameter("Edition");
		language = request.getParameter("Language");
		location = request.getParameter("Location");
		price = request.getParameter("Price");
		pubname = request.getParameter("Publisher Name");
		pubtime = request.getParameter("Publish Time");
		status = request.getParameter("Status");
		
		if(number1 == "" || page1 == "" || isbn == "" || bookname == "" || bookdes == "" || author == "" || catagory == "" ||
			edition == "" || language == "" || location == "" || price == "" || pubname == "" || pubtime == "" || status == "")
			out.print("<script>alert('Please input the all imformation.');window.location='addBook.jsp'</script>");
		else {
			
		int number= Integer.parseInt(request.getParameter("Number"));
		int page = Integer.parseInt(request.getParameter("Page"));
		newbook.setIsbn(request.getParameter("ISBN"));
		newbook.setBookName(request.getParameter("Book Name"));
		newbook.setBookDescription(request.getParameter("Book Description"));
		newbook.setAuthor(request.getParameter("Author"));
		newbook.setCatagory(request.getParameter("Catagory"));
		newbook.setEdition(request.getParameter("Edition"));
		newbook.setLanguage(request.getParameter("Language"));
		newbook.setLocation(request.getParameter("Location"));
		newbook.setPage(page);
		newbook.setPrice(request.getParameter("Price"));
		newbook.setPublisher_name(request.getParameter("Publisher Name"));
		newbook.setPublisher_time(request.getParameter("Publish Time"));
		newbook.setStatus(request.getParameter("Status"));
		
		
		//add book
		ArrayList<Integer> idlist = bookdao.addBook(newbook, number);
		ArrayList<String> code = new ArrayList<String>();
		session.setAttribute("bookidList", idlist);
//		
//		session.setAttribute("Number", number);
//		session.setAttribute("idList", idList);
//		
//		for (int i = 0; i < idList.size(); i++) {
//			session.setAttribute("bookID"+i, idList.get(i));
//		}
		
		session.setAttribute("newBookName", request.getParameter("Book Name"));
		session.setAttribute("newAuthor", request.getParameter("Author"));
		session.setAttribute("newISBN", request.getParameter("ISBN"));
		//Ïú»Ù session
		session.removeAttribute("ISBN");
		session.removeAttribute("BookName");
		session.removeAttribute("BookDes");
		session.removeAttribute("PubTime");
		session.removeAttribute("Price");
		session.removeAttribute("Page");
		session.removeAttribute("Author");
		session.removeAttribute("PubName");
		ArrayList<String> idStringlist = new ArrayList<String>();
		for(int i=0; i < idlist.size(); i++){
        	
        	String id = String.valueOf(idlist.get(i));
        	for (int j = 0;id.length() < 10; j++){
        		id = "0" + id;
            } 
        	idStringlist.add(id);
		}
		session.setAttribute("idStringlist", idStringlist);
            
		request.getRequestDispatcher("librarianNewBookInfo.jsp").forward(request, response);
		
		out.print("<script>alert('Add book location successfully!');window.location='addBook.jsp'</script>");
		
	}
	}

}
