package BiblioSoft.Table;

import java.util.Date;

/**
 * 
 * @author Hu Yuxi
 * 
 * the complete information for borrowed book
 *
 */
public class borrowBookTable_abd extends bookTable_abd{

	private Date start_time;
	private Date return_time;
	private int readerID;
	private String readerName;
	
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
		this.start_time = start_time;
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
		this.return_time = return_time;
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
	 * @return the readerName
	 */
	public String getReaderName() {
		return readerName;
	}
	/**
	 * @param readerName the readerName to set
	 */
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	
}
