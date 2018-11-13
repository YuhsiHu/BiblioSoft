package BiblioSoft.Table;

import java.util.Date;

/**
 * Created by 鍒樺崜绋� on 2018/10/15.
 */
public class RecordTable {
    private int book_id;
    private int reader_id;
    private Date start_time;
    private Date return_time;
    private int current_fine;

    public RecordTable() {
        book_id = 0;
        reader_id = 0;
        start_time = null;
        return_time = null;
        current_fine = 0;
    }

    public RecordTable(int book_id, int reader_id, Date start_time, Date return_time, int current_fine) {
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.start_time = start_time;
        this.return_time = return_time;
        this.current_fine = current_fine;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public void setCurrent_fine(int current_fine) {
        this.current_fine = current_fine;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getReader_id() {
        return reader_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public int getCurrent_fine() {
        return current_fine;
    }
}
