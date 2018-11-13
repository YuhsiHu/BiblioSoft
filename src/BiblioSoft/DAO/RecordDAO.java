package BiblioSoft.DAO;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.awt.print.Book;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;
import BiblioSoft.core.*;

/**
 * Created by 刘卓程 on 2018/10/15.
 */
public class RecordDAO {
    private Connection connection = null;
    private Statement statement = null;
    private ConnDB connDB = new ConnDB();

//    public void getConnection() {//与数据库建立连接
//        try {
//            String url = "jdbc:postgresql://localhost:4869/Library";
//            String user = "postgres";
//            String password = "aptx4869";
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//            //  System.out.println("成功连接pg数据库" + connection);
//        } catch (Exception e) {
//        }
//    }

    public boolean ifExist(int ReaderID, int BookID) {
        //查询是否存在某人某书的借阅记录,请先调用此方法进行判断
        //false表示不存在
        //getConnection();
        Boolean ifexist = false;
        String sql = "select * from record where reader_id =" + ReaderID
                + "and book_id =" + BookID;
        try {
            // statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //取出列值
                String book_id = resultSet.getString(1);
                String reader_id = resultSet.getString(2);
                String start_time = resultSet.getString(3);
                String return_time = resultSet.getString(4);
                String current_fine = resultSet.getString(5);
                if (!book_id.isEmpty() && !reader_id.isEmpty() && !start_time.isEmpty()
                        && !return_time.isEmpty() && !current_fine.isEmpty()) ifexist = true;
            }
            connDB.close();
        } catch (Exception e) {
        }
        return ifexist;
    }

    public Collection getRecordByReaderID(int ReaderID) {//通过ReaderID查询其余所有属性
        //getConnection();
        Collection records = new ArrayList();
        String sql = "select * from Record " +
                "WHERE reader_id =" + ReaderID;
        try {
            RecordTable record;
            //statement = connection.prepareStatement(sql);
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //取出列值
                record = new RecordTable();
                record.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                record.setStart_time(Date.valueOf(resultSet.getString("start_time")));
                if (resultSet.getString("return_time") != null)
                    record.setReturn_time(Date.valueOf(resultSet.getString("return_time")));
                record.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                record.setCurrent_fine(Integer.valueOf(resultSet.getInt("current_fine")));
                records.add(record);
                // System.out.println("添加成功");
            }
            connDB.close();
        } catch (Exception e) {
        }
        return records;
    }

    public Collection getRecordByBookID(int BookID) {//通过ReaderID查询其余所有属性
        //getConnection();
        Collection records = new ArrayList();
        String sql = "select * from Record " +
                "WHERE book_id =" + BookID;
        try {
            RecordTable record;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //取出列值
                record = new RecordTable();
                record.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                record.setStart_time(Date.valueOf(resultSet.getString("start_time")));
                if (resultSet.getString("return_time") != null)
                    record.setReturn_time(Date.valueOf(resultSet.getString("return_time")));
                record.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                record.setCurrent_fine(Integer.valueOf(resultSet.getInt("current_fine")));
                records.add(record);
                // System.out.println("添加成功");
            }
            connDB.close();
        } catch (Exception e) {
        }
        return records;
    }

    public void deleteRecordByReadID(int ReaderID) {
        Collection<RecordTable> recordTables = ifNeedtoSendEmail(ReaderID);
        if (recordTables.isEmpty()) {
            //getConnection();
            String sql = "DELETE from record WHERE reader_id = " + ReaderID;
            try {
                RecordTable record = new RecordTable();
                // statement = connection.createStatement();
                connDB.executeUpdate(sql);
                System.out.println("删除成功");
                connDB.close();
            } catch (Exception e) {
            }
        }
    }

    public Boolean ifBookIsBrrowed(int BookID) {
        //查询某书是否被借阅
        Collection<RecordTable> recordTables = getRecordByBookID(BookID);
        boolean isbrrowed = false;
        for (RecordTable recordTable : recordTables) {
            if (recordTable.getReturn_time() == null) {
                isbrrowed = true;
            }
        }

        return isbrrowed;
    }

    /////////////////////////////////////////////////////////////////
    public void deleteRecordByBookID(int BookID) {
        Collection<RecordTable> recordTables = getRecordByBookID(BookID);
        if (!ifBookIsBrrowed(BookID)) {
            // getConnection();
            String sql = "DELETE from record WHERE book_id = " + BookID;
            try {
                RecordTable record = new RecordTable();
                // statement = connection.createStatement();
                connDB.executeUpdate(sql);
                System.out.println("delete successful");
                connDB.close();
            } catch (Exception e) {
            }
        }
    }

    public void deleteRecordByReaderandBookID(int ReaderID, int BookID) {
        Collection<RecordTable> recordTables = getRecordByBookID(BookID);
        if (!ifBookIsBrrowed(BookID)) {
            // getConnection();
            String sql = "DELETE from record WHERE book_id = " + BookID + "and reader_id = " + ReaderID;
            try {
                RecordTable record = new RecordTable();
                // statement = connection.createStatement();
                connDB.executeUpdate(sql);
                System.out.println("删除成功");
                connDB.close();
            } catch (Exception e) {
            }
        }
    }

    public void InsertRecordByReaderID(int ReaderID, int BookID) {
        //不需要return_time，默认为null
        //current_fine默认为0
        //start_date默认为本日
        //getConnection();
        BookDAO bookDAO = new BookDAO();
        if(bookDAO.searchByID(BookID).getStatus().equals("available")){
        bookDAO.updateBookInLibstatus(BookID,"unavailable");
        SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdm = new SimpleDateFormat("MM");
        SimpleDateFormat sdd = new SimpleDateFormat("dd");
        int dy = new Long(sdy.format(System.currentTimeMillis())).intValue();
        int dm = new Long(sdm.format(System.currentTimeMillis())).intValue();
        int dd = new Long(sdd.format(System.currentTimeMillis())).intValue();
        //                Date nowTimey = new Date(dy);
//                Date nowTimem = new Date(dm);
//                Date nowTimed = new Date(dd);
        //String date = new String(dy.toString()+"_"+dm.toString()+"_"+dd.toString());
        Date StartTime = new Date(dy - 1900, dm - 1, dd);
        String sql = "INSERT Into record(book_id,reader_id,start_time,current_fine)" +
                "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                "\'" + StartTime + "\'" + "," + "\'" + 0 + "\'" + ")";
        try {
            RecordTable record = new RecordTable();
            // statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("添加成功");
            connDB.close();
        } catch (Exception e) {
        }
    }}

    public void InsertRecordByReaderID(int ReaderID, int BookID, Date StartTime) {//添加某读者新的record
        //不需要return_time，默认为null
        //current_fine默认为0
        // getConnection();
        //需要两个外键的判断信息，判断系统中是否存在该BookID与ReaderID，需要别的类中的接口
        //**********************************************************************************
        BookDAO bookDAO = new BookDAO();
        if(bookDAO.searchByID(BookID).getStatus().equals("available")){
        bookDAO.updateBookInLibstatus(BookID,"unavailable");
        String sql = "INSERT Into record(book_id,reader_id,start_time,current_fine)" +
                "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                "\'" + StartTime + "\'" + "," + "\'" + 0 + "\'" + ")";
        try {
            RecordTable record = new RecordTable();
            // statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("添加成功");
            connDB.close();
        } catch (Exception e) {
        }
    }}


    public boolean ifNeedtoFine(int ReaderID, int BookID) {
        //查询某人的某书是否需要缴纳罚金
        //默认不存在某人多次借同一本书的情况
        //若为true则表示需要缴纳罚金
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        boolean ifFine = true;
        for (RecordTable recordTable : RecordList) {
            if (BookID == recordTable.getBook_id()) {
                if (recordTable.getCurrent_fine() < 0) {
                    ifFine = false;
                }
            }
        }
        return !ifFine;
//        getConnection();
//        Boolean ifFine = true;
//        String sql = "select current_fine from record where reader_id =" + ReaderID
//                + "and book_id =" + BookID;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                //取出列值
//                String id = resultSet.getString(1);
//                int fine = Integer.getInteger(id);
//                if (fine < 0) ifFine = false;
//                System.out.println(id);
//            }
//        } catch (Exception e) {
//        }
//        return ifFine;
    }

    public Collection ifNeedtoSendEmail(int ReaderID) {
        //判断是否需要发送邮件
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        Collection<RecordTable> NeedtoEmail = new ArrayList();
        for (RecordTable recordTable : RecordList) {
            if (recordTable.getCurrent_fine() < 0 && recordTable.getReturn_time() == null) {
                NeedtoEmail.add(recordTable);
            }
        }
        return NeedtoEmail;
    }

    public void updateFine(int[] ReaderID) {
        //更新所有的Fine信息，即超过最大借阅日期时每天罚款+1
        //需要一个ReadID数组，其中包含所有的ReaderID
        //默认日期加1月为最后期限
        for (int readerID : ReaderID) {
            Collection<RecordTable> RecordList = getRecordByReaderID(readerID);
            for (RecordTable recordTable : RecordList) {
                if (recordTable.getReturn_time() == null) {
                    //某人某书未归还时

                    java.util.Date start_time = recordTable.getStart_time();
                    SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                    SimpleDateFormat sdfm = new SimpleDateFormat("MM");
                    SimpleDateFormat sdfd = new SimpleDateFormat("dd");
                    SimpleDateFormat sdfh = new SimpleDateFormat("HH");
                    String yy = new String(sdfy.format(System.currentTimeMillis()));
                    String mm = new String(sdfm.format(System.currentTimeMillis()));
                    String dd = new String(sdfd.format(System.currentTimeMillis()));
                    String hh = new String(sdfh.format(System.currentTimeMillis()));
                    long ys = new Long(yy);
                    long ms = new Long(mm);
                    long ds = new Long(dd);
                    long hs = new Long(hh);
//                    System.out.println(ys);
//                    System.out.println(ms);
//                    System.out.println(ds);
//                    System.out.println(hs);                                  //加中国时区
                    long date = (ys - 1970) * 12 * 30 * 24 * 60 * 60 * 1000 + (ms - 1 + 8) * 30 * 24 * 60 * 60 * 1000
                            + (ds - 1) * 24 * 60 * 60 * 1000 + (hs) * 60 * 60 * 1000;
                    //获取当前时间
                    java.util.Date nowTime = new Date(date);
                    Calendar rightNow = Calendar.getInstance();
                    rightNow.setTime(start_time);
                    rightNow.add(Calendar.MONTH, 1);// 日期加1月
                    java.util.Date dead_time = rightNow.getTime();
                    long day = 0;
                    int fine = 0;
                    System.out.println(nowTime.getTime());
                    //System.out.println(rightNow.getTime());
                    System.out.println(dead_time.getTime());
                    if (nowTime.getTime() > dead_time.getTime()) {
                        //nowTime.getTime()-dead_time.getTime()得到的是毫秒数
                        day = -((nowTime.getTime() - dead_time.getTime()) / (1000 * 60 * 60 * 24));
                        System.out.println(day);
                        fine = new Long(day).intValue();
                        int book_id = recordTable.getBook_id();
                        int read_id = recordTable.getReader_id();
                        int fines = fine;
                        //更新数据库
                        //getConnection();
                        String sql = "UPDATE record SET current_fine = " + fines + "where reader_id="
                                + "\'" + read_id + "\'" + "AND book_id=" + "\'" + book_id + "\'";
                        try {
                            RecordTable record = new RecordTable();
                            // statement = connection.createStatement();
                            connDB.executeUpdate(sql);
                            // System.out.println("添加成功");
                            connDB.close();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    public java.util.Date getStartDate(int ReaderID, int BookID) {
        //查询某人某书的开始时间
        //在应还书时发送邮件时使用
        java.util.Date start_date = new java.util.Date();
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        for (RecordTable recordTable : RecordList) {
            if (BookID == recordTable.getBook_id())
                start_date = recordTable.getStart_time();
        }
        return start_date;
//        getConnection();
//        String startdate = null;
//        String sql = "select start_time from RecordDAO where reader_id =" + ReaderID
//                + "and book_id =" + BookID;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                //取出列值
//                String startDate = resultSet.getString(1);
//                startdate = new String(startDate);
//                System.out.println(startDate);
//            }
//        } catch (Exception e) {
//        }
//        return startdate;
    }

    public java.util.Date getDeadDate(int ReaderID, int BookID) {
        //查询某人某书的最大应还开始时间
        //在应还书时发送邮件时使用
        java.util.Date start_date = new java.util.Date();
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        for (RecordTable recordTable : RecordList) {
            if (BookID == recordTable.getBook_id())
                start_date = recordTable.getStart_time();
        }
        DefaultValue defaultValue = new DefaultValue();
        long time = defaultValue.getLongesttime();
        long deadtime = start_date.getTime() + time * 24L * 60L * 60L * 1000L;
       // SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date deadline = new java.util.Date(deadtime);
        //String deadliness = new String(df.format(deadline));
        return deadline;
    }

    public java.util.Date getReturnDate(int ReaderID, int BookID) {
        //查询某人某书的还书时间,未归还时为null
        java.util.Date return_date = null;
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        for (RecordTable recordTable : RecordList) {
            if (BookID == recordTable.getBook_id())
                return_date = recordTable.getReturn_time();
        }
        return return_date;

//        getConnection();
//        String returndate = null;
//        String sql = "select start_time from RecordDAO where reader_id =" + ReaderID
//                + "and book_id =" + BookID;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                //取出列值
//                String returnDate = resultSet.getString(1);
//                returndate = new String(returnDate);
//                System.out.println(returnDate);
//            }
//        } catch (Exception e) {
//        }
//        return returndate;
    }

    public int getFine(int ReaderID, int BookID) {
        //查询某人某书的罚款记录，若未超时则为0；已交罚款与未交罚款本方法都仅返回正数，
        //若要确定是否交过罚款，请查阅本类中的ifNeedtoFine()方法
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        int fine = 0;
        for (RecordTable recordTable : RecordList) {
            if (BookID == recordTable.getBook_id()) {
                fine = recordTable.getCurrent_fine();
            }
        }
        if (fine < 0) fine = 0 - fine;
        return fine;
//        int fine = 0;
//        getConnection();
//        String sql = "select current_fine from RecordDAO where reader_id =" + ReaderID
//                + "and book_id =" + BookID;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                //取出列值
//                String id = resultSet.getString(1);
//                int fines = Integer.getInteger(id);
//                System.out.println(id);
//            }
//        } catch (Exception e) {
//        }
//        if (fine < 0) fine = 0 - fine;
//        return fine;
    }

    public void setReturnTime(int ReaderID, int BookID) {
        //此方法用来还书时使用，将Return_time从null改为当前时间
        //应当同时调用ifNeedtoFine()判断是否需要缴纳罚款
        //若需要缴纳罚款，则应当先缴纳罚款
        //在缴纳罚款后，调用setCurrentFine()方法更新罚款信息
        if (getReturnDate(ReaderID, BookID) == null) {
            if (!ifNeedtoFine(ReaderID, BookID)) {
                //不需要缴纳罚金
                BookDAO bookDAO = new BookDAO();
                bookDAO.updateBookInLibstatus(BookID,"available");
                SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdm = new SimpleDateFormat("MM");
                SimpleDateFormat sdd = new SimpleDateFormat("dd");
                int dy = new Long(sdy.format(System.currentTimeMillis())).intValue();
                int dm = new Long(sdm.format(System.currentTimeMillis())).intValue();
                int dd = new Long(sdd.format(System.currentTimeMillis())).intValue();
//                Date nowTimey = new Date(dy);
//                Date nowTimem = new Date(dm);
//                Date nowTimed = new Date(dd);
                //String date = new String(dy.toString()+"_"+dm.toString()+"_"+dd.toString());
                Date nowTime = new Date(dy - 1900, dm - 1, dd);
                //getConnection();
                String sql = "UPDATE record SET return_time = " + "\'" + nowTime + "\'" + "where reader_id="
                        + "\'" + ReaderID + "\'" + "AND book_id=" + "\'" + BookID + "\'";
                try {
                    RecordTable record = new RecordTable();
                    // statement = connection.createStatement();
                    connDB.executeUpdate(sql);
                    connDB.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void setCurrentFine(int ReaderID, int BookID) {
        //此方法用来更新罚款，即在已经需要缴纳罚款之后，进行缴纳后，更改current_fine的操作。
        //由负数改为正数
        Boolean ifFine = !ifNeedtoFine(ReaderID, BookID);
        int fine = getFine(ReaderID, BookID);
        if (!ifFine) {
            //  getConnection();
            String sql = "UPDATE record SET current_fine = " + fine + "where reader_id="
                    + "\'" + ReaderID + "\'" + "AND book_id=" + "\'" + BookID + "\'";
            try {
                RecordTable record = new RecordTable();
                //statement = connection.createStatement();
                connDB.executeUpdate(sql);
                System.out.println("罚款更新成功");
                connDB.close();
            } catch (Exception e) {
            }
        }
    }

    public Collection getCurrentBorrow(int ReaderID) {//获得当前借阅的书籍
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        Collection<RecordTable> CurrentBorrow = new ArrayList();
        for (RecordTable recordTable : RecordList) {
            if (recordTable.getReturn_time() == null) {
                CurrentBorrow.add(recordTable);
            }
        }
        return CurrentBorrow;
    }

    public Collection getReturnHistory(int ReaderID) {
        //获得某人的还书历史，即只能查询已经归还的历史信息
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        Collection<RecordTable> ReturnHistory = new ArrayList();
        for (RecordTable recordTable : RecordList) {
            if (recordTable.getReturn_time() != null) {
                ReturnHistory.add(recordTable);
            }
        }
        return ReturnHistory;
    }

    public Collection getNeedtoFine(int ReaderID) {
        //查询某人所有应支付的罚金
        Collection<RecordTable> RecordList = getRecordByReaderID(ReaderID);
        Collection<RecordTable> Needtofine = new ArrayList();
        for (RecordTable recordTable : RecordList) {
            if (recordTable.getCurrent_fine() < 0)
                Needtofine.add(recordTable);
        }
        return Needtofine;
    }

   public static void main(String[] args) {
       RecordDAO record = new RecordDAO();
       System.out.println(record.getStartDate(1,1));
       System.out.println(record.getDeadDate(1,1));
   }
//      record.deleteRecordByReaderandBookID(1,1);}
//       System.out.print(record.getFine(1,1));}
////       record.InsertRecordByReaderID(111,111);
////        record.deleteRecordByBookID(111);
//       Collection<RecordTable> recordTables = record.getRecordByReaderID(1);
//        for (RecordTable recordTable : recordTables) {
//           System.out.println(recordTable.getBook_id());
//           System.out.println(recordTable.getCurrent_fine());
//        }
    //  if(record.ifBookIsBrrowed(111))System.out.print(1);
//        Collection<RecordTable> recordTables = record.getRecordByBookID(123);
//        for(RecordTable recordTable : recordTables){
    //   System.out.println(recordTable.getReader_id());
    //record.deleteRecordByID(111);
////        Collection<RecordTable> recordTables = record.getNeedtoFine(111);
//        for(RecordTable recordTable :recordTables){
//            System.out.print(recordTable.getCurrent_fine());
//        }
    // if(record.ifNeedtoFine(111,111))System.out.print("11");
    //System.out.println(record.getFine(111,111));
    //record.setCurrentFine(111,111);
    //record.setReturnTime(111, 111);
//            java.util.Date date =  record.getReturnDate(111,111);
//            System.out.println(date);
//        int[] a = {111};
//        record.updateFine(a);
//        if(record.ifNeedtoSendEmail(111).isEmpty())System.out.print("111 not need");
//        else System.out.print("111 need to send");
//        Date date = new Date(118,3,1);
//        record.InsertRecordByReaderID(111,111,date,0);
    //}
//        Collection<RecordTable> recordTables = record.ifNeedtoSendEmail(123);
//        System.out.print(record.getReturnDate(123,123));
//        System.out.print(record.getFine(111,111));
//        if(recordTables.isEmpty())System.out.print("123 not need to send");
//        else System.out.print("123 need to send");
//        Collection<RecordTable> recordTables1 = record.ifNeedtoSendEmail(111);
//        if(recordTables1.isEmpty())System.out.print("111 not need to send");
//        else System.out.print("111 need to send");
//        Collection<RecordTable> recordTables = record.getCurrentBorrow(123);
//        for(RecordTable recordTable:recordTables){
//            System.out.println(recordTable.getBook_id());
//        }
//        Collection<RecordTable> recordTables = record.getRecordByReaderID(123);
//        for (RecordTable recordTable : recordTables) {
//            System.out.print("Bookid: " + recordTable.getBook_id());
//            System.out.print("Readerid: " + recordTable.getReader_id());
//            System.out.print("StartTime: " + recordTable.getStart_time());
//            System.out.print("ReturnTime: " + recordTable.getReturn_time());
//            System.out.print("Currentfine: " + recordTable.getCurrent_fine());
//            System.out.print("\n");
//        }
//        if (record.ifExist(123, 123)) System.out.println("is exist");
////        String dateString = "2018-02-23";
//        String datedead = "2018-03-23";
//        Date date = new Date(118,7,13);
//
//        Date returntime = new Date(118,9,13);
//        try {
//             date = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
//            // Date date = new Date(20*1000*60*60*24*30*12);
//            //Date returntime = new Date(30 * 1000 * 60 * 60 * 24 * 30 * 12);
//             returntime = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(datedead);
//              } catch (Exception e) {
//        }
//        record.InsertRecordByReaderID(111, 111, date, returntime, 0);
//    }添加函数测试完成
//    if(record.ifNeedtoFine(123,123))System.out.print("123 need fine");
//    else System.out.print("123 not need fine");
//    if(record.ifNeedtoFine(111,111))System.out.print("111 need fine");
//    }
    //ifneedtofine()测试完成
}



