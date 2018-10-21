package BiblioSoft.Table;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author Hu Yuxi
 *
 *borrow record
 */

public class borrowTable_abd implements Serializable{
	private int bookID;
	private int readerID;
	private Date start_time;
	private Date return_time;
	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return bookID;
	}
	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	/**
	 * @return the readerID
	 */
	public int getReaderID() {
		return readerID;
	}
	/**
	 * @param readerID the readerID to set
	 */
	public void setReaderID(int readerID) {
		this.readerID = readerID;
	}
	/**
	 * @return the start_time
	 */
	public Date getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(Date start_time) {
		start_time = start_time;
	}
	/**
	 * @return the return_time
	 */
	public Date getReturn_time() {
		return return_time;
	}
	/**
	 * @param return_time the return_time to set
	 */
	public void setReturn_time(Date return_time) {
		return_time = return_time;
	}
	
	

}
