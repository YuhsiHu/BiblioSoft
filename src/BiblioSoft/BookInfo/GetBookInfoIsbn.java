package BiblioSoft.BookInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

public class GetBookInfoIsbn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		String isbn = request.getParameter("ISBN");
		String uri = "https://api.douban.com/v2/book/isbn/" + isbn;
		String jsonData = "";
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();

		try {
			jsonData = getJsonData(uri, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jsonData!="") {
			System.out.println(jsonData);
			Map maps = (Map) JSON.parse(jsonData);
			String author = maps.get("author").toString();
			author = author.replace("[", "");
			author = author.replace("]", "");
			author = author.replace("\"", "");

			System.out.println("����: " + maps.get("price"));
			System.out.println("����: " + maps.get("image"));
			System.out.println("���ߣ�  " + author);
			System.out.println("��������: " + maps.get("pubdate"));
			System.out.println("������: " + maps.get("publisher"));
			System.out.println("����: " + maps.get("title"));
			System.out.println("ժҪ: " + maps.get("summary"));
			
			session.setAttribute("ISBN", isbn);
			session.setAttribute("BookName", maps.get("title"));
			session.setAttribute("BookDes", maps.get("summary"));
			session.setAttribute("PubTime", maps.get("pubdate"));
			session.setAttribute("Price", maps.get("price"));
			session.setAttribute("Page", maps.get("pages"));
			session.setAttribute("Author", author);
			session.setAttribute("PubName", maps.get("publisher"));

			out.print("<script language='javascript'>alert('The query is successful!');window.location.href='addBook.jsp';</script>");
			
		}else {
			out.print("<script language='javascript'>alert('Invailed ISBN!');window.location.href='addBook.jsp';</script>");
		}
		

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("ISBN");
		String uri = "https://api.douban.com/v2/book/isbn/:" + isbn;
		String jsonData = "";
		try {
			jsonData = getJsonData(uri, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonData);
		Map maps = (Map) JSON.parse(jsonData);
		String author = maps.get("author").toString();
		author = author.replace("[", "");
		author = author.replace("]", "");
		author = author.replace("\"", "");

		System.out.println("����: " + maps.get("price"));
		System.out.println("����: " + maps.get("image"));
		System.out.println("���ߣ�  " + author);
		System.out.println("��������: " + maps.get("pubdate"));
		System.out.println("������: " + maps.get("publisher"));
		System.out.println("����: " + maps.get("title"));
		System.out.println("ժҪ: " + maps.get("summary"));
	}

	/**
	 * 
	 * @param uri
	 * @see
	 * @since 1.0
	 */
	private String getJsonData(String url, String param) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			/*
			 * for (String key : map.keySet()) { System.out.println(key + "--->" +
			 * map.get(key)); }
			 */
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	

}
