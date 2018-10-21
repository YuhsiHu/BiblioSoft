package BiblioSoft.Table;

import java.util.Date;

/**
 * Created by 鍒樺崜绋� on 2018/10/16.
 */
public class ReserveTable {
    private int reader_id;
    private int book_id;
    private String Start_time;
    private String DeadLine_time;

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setStart_time(String start_time) {
        Start_time = start_time;
    }

    public void setDeadLine_time(String deadLine_time) {
        DeadLine_time = deadLine_time;
    }

    public int getReader_id() {
        return reader_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getStart_time() {
        return Start_time;
    }

    public String getDeadLine_time() {
        return DeadLine_time;
    }

    public ReserveTable(int reader_id, int book_id, String start_time, String deadLine_time) {
        this.reader_id = reader_id;
        this.book_id = book_id;
        Start_time = start_time;
        DeadLine_time = deadLine_time;
    }

    public ReserveTable() {
    }
}
