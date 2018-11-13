package BiblioSoft.Table;

import java.util.Date;

/**
 * Created by Liu Zhuocheng on 2018/10/26.
 */
public class SendEmailTable {
    private String book_name;
    private String email;
    private String reader_name;
    private java.util.Date return_time;

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public SendEmailTable() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public String getBook_name() {
        return book_name;

    }

    public String getEmail() {
        return email;
    }

    public String getReader_name() {
        return reader_name;
    }

    public java.util.Date getReturn_time() {
        return return_time;
    }

    public SendEmailTable(String book_name, String email, String reader_name, Date return_time) {
        this.book_name = book_name;
        this.email = email;
        this.reader_name = reader_name;
        this.return_time = return_time;
    }

}
