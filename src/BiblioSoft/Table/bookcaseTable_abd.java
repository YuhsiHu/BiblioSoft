package BiblioSoft.Table;

import java.io.Serializable;

public class bookcaseTable_abd implements Serializable{
	
	private int bookcase_id;
    private String bookcase_name;
	/**
	 * @return the bookcase_id
	 */
	public int getBookcase_id() {
		return bookcase_id;
	}
	/**
	 * @param bookcase_id the bookcase_id to set
	 */
	public void setBookcase_id(int bookcase_id) {
		this.bookcase_id = bookcase_id;
	}
	/**
	 * @return the bookcase_name
	 */
	public String getBookcase_name() {
		return bookcase_name;
	}
	/**
	 * @param bookcase_name the bookcase_name to set
	 */
	public void setBookcase_name(String bookcase_name) {
		this.bookcase_name = bookcase_name;
	}
    
}
