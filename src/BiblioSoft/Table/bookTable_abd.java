package BiblioSoft.Table;

import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author Hu Yuxi
 *
 *booktable contains all information of one book with specific ID
 */
public class bookTable_abd implements Serializable {
	private String author;
	private String bookName;
	private String bookcaseName;
	private String bookDescription;
	private int bookcaseid;
	private int bookID;
	private String bookEdition;
	private String ISBN;
	private String language;
	private int page;
	private Float price;
	private String publishing;//publisher_id
	private String status;
	private String translator;
	private int typeId;
	private String typeName;
	
	
	public bookTable_abd(String author,String bookName,String bookcaseName,String bookDescription,int bookcaseid,int bookId,String bookEdition
			,String ISBN,String language,int page,Float price,String publishing,String status, String translator,int typeId,String typeName)
	{
		this.author=author;
		this.bookName=bookName;
		this.bookcaseName=bookcaseName;
		this.bookDescription=bookDescription;
		this.bookcaseid=bookcaseid;
		this.bookID=bookId;
		this.bookEdition=bookEdition;
		this.ISBN=ISBN;
		this.language=language;
		this.page=page;
		this.price=price;
		this.publishing=publishing;
		this.status=status;
		this.translator=translator;
		this.typeId=typeId;
		this.typeName=typeName;
	}
	
	
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public bookTable_abd() {

	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName
	 *            the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the bookcaseName
	 */
	public String getBookcaseName() {
		return bookcaseName;
	}

	/**
	 * @param bookcaseName
	 *            the bookcaseName to set
	 */
	public void setBookcaseName(String bookcaseName) {
		this.bookcaseName = bookcaseName;
	}

	/**
	 * @return the bookDescription
	 */
	public String getBookDescription() {
		return bookDescription;
	}

	/**
	 * @param bookDescription
	 *            the bookDescription to set
	 */
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	/**
	 * @return the bookcaseid
	 */
	public int getBookcaseid() {
		return bookcaseid;
	}

	/**
	 * @param bookcaseid
	 *            the bookcaseid to set
	 */
	public void setBookcaseid(int bookcaseid) {
		this.bookcaseid = bookcaseid;
	}

	/**
	 * @return the bookEdition
	 */
	public String getBookEdition() {
		return bookEdition;
	}

	/**
	 * @param bookEdition
	 *            the bookEdition to set
	 */
	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN
	 *            the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * @return the publishing
	 */
	public String getPublishing() {
		return publishing;
	}

	/**
	 * @param publishing
	 *            the publishing to set
	 */
	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}



	/**
	 * @return the translator
	 */
	public String getTranslator() {
		return translator;
	}

	/**
	 * @param translator
	 *            the translator to set
	 */
	public void setTranslator(String translator) {
		this.translator = translator;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * @param 
	 *            return book info
	 */
	public String toString() {
		return "Book ["  + " bookname=" + bookName + ", price=" + price + ", author=" + author
				               + ", publish=" + publishing + "]";
	
	}
	
	/**
	 * Split the coauthor into a single author
	 * @return author
	 */
	public ArrayList getSingleAuthor(String coauthor) {
		ArrayList<String> author=new ArrayList<String>();
		String[] nameStrArray=coauthor.split(",");
        for(String name:nameStrArray){
            author.add(name);
        }

		return author;
	}
	

}