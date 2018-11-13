package BiblioSoft.Table;

import java.io.*;
import java.util.*;

/**
 * 
 * @author Yang Jian, Hu Yuxi
 * BookTable 包含book和bookinlib两张表的信息
 */
public class BookTable {
	private String isbn;
	private String bookName;
	private String bookDescription;
	private String edition;
	private String language;
	private String publisher_time;
	private String price;
	private int page;
	private String publisher_name;
	private String author;
	private String catagory;
	private String location;
	private String status;
	
	public BookTable() {}
	
	/**
	 * 含参构造函数
	 * @param isbn the isbn to set
	 * @param bookName the bookName to set
	 * @param bookDescription the description to set
	 * @param edition the edition to set
	 * @param language the language to set
	 * @param publisher_time the time of publishing to set
	 * @param price the price to set, be careful it is a String
	 * @param page the page to set
	 * @param publisher_name the publisher name of the book
	 * @param author the author of the book
	 * @param catagory the catagory of the book
	 * @param location the location of the book
	 * @param status the status of the book, contains available, unavailable, lost and reserved
	 */
	public BookTable(String isbn,String bookName,String bookDescription,
			String edition,String language,String publisher_time,String price,
			int page,String publisher_name,String author,String catagory,String location,
			String status) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.edition = edition;
		this.language = language;
		this.publisher_time = publisher_time;
		this.price = price;
		this.page = page;
		this.publisher_name = publisher_name;
		this.author = author;
		this.catagory = catagory;
		this.location = location;
		this.status = status;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the bookDescription
	 */
	public String getBookDescription() {
		return bookDescription;
	}

	/**
	 * @param bookDescription the bookDescription to set
	 */
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the publisher_time
	 */
	public String getPublisher_time() {
		return publisher_time;
	}

	/**
	 * @param publisher_time the publisher_time to set
	 */
	public void setPublisher_time(String publisher_time) {
		this.publisher_time = publisher_time;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the publisher_name
	 */
	public String getPublisher_name() {
		return publisher_name;
	}

	/**
	 * @param publisher_name the publisher_name to set
	 */
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the catagory
	 */
	public String getCatagory() {
		return catagory;
	}

	/**
	 * @param catagory the catagory to set
	 */
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookTable [isbn=" + isbn + ", bookName=" + bookName + ", bookDescription=" + bookDescription
				+ ", edition=" + edition + ", language=" + language + ", publisher_time=" + publisher_time + ", price="
				+ price + ", page=" + page + ", publisher_name=" + publisher_name + ", author=" + author + ", catagory="
				+ catagory + ", location=" + location + ", status=" + status + "]";
	}
	
	
}
