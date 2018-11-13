package BiblioSoft.Table;

import javax.swing.plaf.synth.SynthToolTipUI;

/**
 * Created by 鍒樺崜绋� on 2018/10/30.
 */
public class DeletebookTable {
    private int bookid;
    private int Lib_id;
    private String Lib_name;
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

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getLib_id() {
        return Lib_id;
    }

    public void setLib_id(int lib_id) {
        Lib_id = lib_id;
    }

    public String getLib_name() {
        return Lib_name;
    }

    public void setLib_name(String lib_name) {
        Lib_name = lib_name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher_time() {
        return publisher_time;
    }

    public void setPublisher_time(String publisher_time) {
        this.publisher_time = publisher_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public DeletebookTable() {
    }

    public DeletebookTable(int bookid, int lib_id, String lib_name, String isbn, String bookName, String bookDescription, String edition, String language, String publisher_time, String price, int page, String publisher_name, String author, String catagory) {
        this.bookid = bookid;
        Lib_id = lib_id;
        Lib_name = lib_name;
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
    }
}
