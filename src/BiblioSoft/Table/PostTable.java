package BiblioSoft.Table;

import java.sql.Timestamp;
import java.util.Date;

public class PostTable {

	private String title;
	private String body;
	private Date time;
	private int libID;
	public PostTable() {
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getLibID() {
		return libID;
	}
	public void setLibID(int libID) {
		this.libID = libID;
	}

	
}
