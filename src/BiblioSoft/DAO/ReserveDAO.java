package BiblioSoft.DAO;

import java.awt.print.Book;
import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;
import BiblioSoft.core.*;

/**
 * Created by 刘卓程 on 2018/10/16.
 */
public class ReserveDAO {
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
//           // System.out.println("成功连接pg数据库" + connection);
//        } catch (Exception e) {
//        }
//    }

    public Collection getReserveByReaderID(int ReaderID) {//通过ReaderID查询其余所有属性
        //getConnection();
        Collection reserves = new ArrayList();
        String sql = "select * from reserve " +
                "WHERE reader_id =" + ReaderID;
        try {
            ReserveTable reserveTable;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //取出列值
                reserveTable = new ReserveTable();
                reserveTable.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                reserveTable.setStart_time(resultSet.getString("start_time"));
                reserveTable.setDeadLine_time(resultSet.getString("deadline_time"));
                reserveTable.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                reserves.add(reserveTable);
                //System.out.println("添加成功");
            }
            connDB.close();
        } catch (Exception e) {
        }
        return reserves;
    }

    public Collection getReserveByBookID(int BookID) {//通过BookID查询其余所有属性
        //getConnection();
        Collection reserves = new ArrayList();
        String sql = "select * from reserve " +
                "WHERE book_id =" + BookID;
        try {
            ReserveTable reserveTable;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //取出列值
                reserveTable = new ReserveTable();
                reserveTable.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                reserveTable.setStart_time(resultSet.getString("start_time"));
                reserveTable.setDeadLine_time(resultSet.getString("deadline_time"));
                reserveTable.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                reserves.add(reserveTable);
                //System.out.println("添加成功");
            }
            connDB.close();
        } catch (Exception e) {
        }
        return reserves;
    }

    public void InsertRecordByReaderID(int ReaderID, int BookID, String StartTime,
                                       String DeadlineTime) {//添加某读者新的reserve
        //getConnection();
        //StartTime和DeadlineTime的格式必须为  2018/10/17 20:19:23
        //yyyy/MM//dd hh:mm:ss
        //需要两个外键的判断信息，判断系统中是否存在该BookID与ReaderID，需要别的类中的接口
        //**********************************************************************************
        BookDAO bookDAO = new BookDAO();
        if (bookDAO.searchByID(BookID).getStatus().equals("available")) {

                String sql = "INSERT Into reserve(book_id,reader_id,start_time,deadline_time)" +
                        "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                        "\'" + StartTime + "\'" + "," + "\'" + DeadlineTime + "\'" + ")";
            bookDAO.updateBookInLibstatus(BookID, "reserved");
            try {
                //statement = connection.createStatement();
                connDB.executeUpdate(sql);
                System.out.println("添加成功");
                connDB.close();
            } catch (Exception e) {
            }
        }
    }
    public void InsertRecordByReaderID(int ReaderID, int BookID) {
        BookDAO bookDAO = new BookDAO();
        if (bookDAO.searchByID(BookID).getStatus().equals("available")) {
            bookDAO.updateBookInLibstatus(BookID, "reserved");
            SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfm = new SimpleDateFormat("MM");
            SimpleDateFormat sdfd = new SimpleDateFormat("dd");
            SimpleDateFormat sdfh = new SimpleDateFormat("HH");
            SimpleDateFormat sdfmm = new SimpleDateFormat("mm");
            SimpleDateFormat sdfs = new SimpleDateFormat("ss");
            String yy = new String(sdfy.format(System.currentTimeMillis()));
            String mm = new String(sdfm.format(System.currentTimeMillis()));
            String dd = new String(sdfd.format(System.currentTimeMillis()));
            String hh = new String(sdfh.format(System.currentTimeMillis()));
            String mmm = new String(sdfmm.format(System.currentTimeMillis()));
            String ss = new String(sdfs.format(System.currentTimeMillis()));

            String nows = new String(yy + "/" + mm + "/" + dd + " " + hh + ":" + mmm + ":" + ss);
            //System.out.println(nows);
            Date nowtimes = new Date(nows);

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            long nowss = nowtimes.getTime();
            DefaultValue defaultValue = new DefaultValue();
            long deadlines = nowss + 2 * 60 * 60 * 1000;
            Date deadline = new Date(deadlines);
            String deadliness = new String(df.format(deadline));

            //2018/10/17 20:19:23
            String sql = "INSERT Into reserve(book_id,reader_id,start_time,deadline_time)" +
                    "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                    "\'" + nows + "\'" + "," + "\'" + deadliness + "\'" + ")";
            try {
                //statement = connection.createStatement();
                connDB.executeUpdate(sql);
                System.out.println("添加成功");
                connDB.close();
            } catch (Exception e) {
            }
        }
    }
    public String getStartTime(int ReaderID, int BookID) {
        //查询某人某书的开始预约时间
        String start_date = null;
        Collection<ReserveTable> RecserveList = getReserveByReaderID(ReaderID);
        for (ReserveTable recordTable : RecserveList) {
            if (BookID == recordTable.getBook_id())
                start_date = recordTable.getStart_time();
        }
        StringTokenizer start = new StringTokenizer(start_date, "+");
        if (start.hasMoreTokens()) start_date = start.nextToken();
        return start_date;
    }

    public String getdeadTime(int ReaderID, int BookID) {
        //查询某人某书的截止取书时间
        String dead_time = null;
        Collection<ReserveTable> RecserveList = getReserveByReaderID(ReaderID);
        for (ReserveTable recordTable : RecserveList) {
            if (BookID == recordTable.getBook_id())
                dead_time = recordTable.getDeadLine_time();
        }
        StringTokenizer dead = new StringTokenizer(dead_time, "+");
        if (dead.hasMoreTokens()) dead_time = dead.nextToken();
        return dead_time;
    }

    public void deleteReserveByReadID(int ReaderID) {
        Collection<ReserveTable> reserveTables = getReserveByReaderID(ReaderID);
        //getConnection();
        String sql = "DELETE from reserve WHERE reader_id = " + ReaderID;
        try {
            ReserveTable reserveTable = new ReserveTable();
            // statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("删除成功");
            connDB.close();
        } catch (Exception e) {
        }
    }

    public void deleteReserveByBookID(int BookID) {
        Collection<ReserveTable> reserveTables = getReserveByBookID(BookID);
        //getConnection();
        String sql = "DELETE from reserve WHERE book_id = " + BookID;
        try {
            ReserveTable reserveTable = new ReserveTable();
            // statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("删除成功");
            connDB.close();
        } catch (Exception e) {
        }
    }

    public boolean ifNeedtoDelete(int ReaderID, int BookID) {
        //查询某人的某书预约记录是否超时
        //true表示超时
        Boolean needtodelete = false;
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfm = new SimpleDateFormat("MM");
        SimpleDateFormat sdfd = new SimpleDateFormat("dd");
        SimpleDateFormat sdfh = new SimpleDateFormat("HH");
        SimpleDateFormat sdfmm = new SimpleDateFormat("mm");
        SimpleDateFormat sdfs = new SimpleDateFormat("ss");
        String yy = new String(sdfy.format(System.currentTimeMillis()));
        String mm = new String(sdfm.format(System.currentTimeMillis()));
        String dd = new String(sdfd.format(System.currentTimeMillis()));
        String hh = new String(sdfh.format(System.currentTimeMillis()));
        String mmm = new String(sdfmm.format(System.currentTimeMillis()));
        String ss = new String(sdfs.format(System.currentTimeMillis()));
        String nows = new String(yy + "/" + mm + "/" + dd + " " + hh + ":" + mmm + ":" + ss);

        Date date = new Date(nows);
        // System.out.println(date.getTime());
        String deadtime = getdeadTime(ReaderID, BookID);
        StringTokenizer dead = new StringTokenizer(deadtime, " ");
        String dy = new String();
        String dm = new String();
        String ddd = new String();
        String dh = new String();
        String dmm = new String();
        String ds = new String();
        //StringTokenizer deadday = new StringTokenizer();
        String days = new String();
        String inday = new String();
        if (dead.hasMoreTokens()) days = dead.nextToken();
        if (dead.hasMoreTokens()) inday = dead.nextToken();
        StringTokenizer day = new StringTokenizer(days, "-");
        if (day.hasMoreTokens()) dy = day.nextToken();
        if (day.hasMoreTokens()) dm = day.nextToken();
        if (day.hasMoreTokens()) ddd = day.nextToken();

        StringTokenizer indays = new StringTokenizer(inday, ":");
        if (indays.hasMoreTokens()) dh = indays.nextToken();
        if (indays.hasMoreTokens()) dmm = indays.nextToken();
        String delete = new String();
        if (indays.hasMoreTokens()) delete = indays.nextToken();
        StringTokenizer sss = new StringTokenizer(delete, "+");
        if (sss.hasMoreTokens()) ds = sss.nextToken();

//        System.out.println(dy);
//        System.out.println(dm);
//        System.out.println(ddd);
//
//        System.out.println("***"+dh);
//        System.out.println(nowhs);
//        System.out.println(dmm);
//        System.out.println(ds);

        long ddy = new Long(dy);
        long ddm = new Long(dm);
        long dddd = new Long(ddd);
        long ddh = new Long(dh);
        long ddmm = new Long(dmm);
        long dds = new Long(ds);
        String deadline = new String(ddy + "/" + ddm + "/" + dddd + " " + ddh + ":" + ddmm + ":" + dds);
        Date deadlines = new Date(deadline);
        long ifde = -(date.getTime() - deadlines.getTime()) / (1000 * 60 * 60);


        if (ifde < 0) needtodelete = true;
//        System.out.println("nowds: "+nowds);
//        System.out.println(dddd);
//        System.out.println("nowhs: "+nowhs);
//        System.out.println(ddh);
        // long deadys =
        return needtodelete;
    }

//   public static void main(String[] args) {
//        ReserveDAO reserveDAO = new ReserveDAO();
//        reserveDAO.getdeadTime(1,1);
//    }
///
//////reserveDAO.deleteReserveByBookID(1);
//        //reserveDAO.deleteReserveByReadID(1);
//        System.out.println(reserveDAO.getStartTime(1, 1));
////        System.out.println(reserveDAO.getStartTime(1,1));
    // reserveDAO.InsertRecordByReaderID(1, 1);
//        Collection<ReserveTable> reserveTables = reserveDAO.getReserveByBookID(1);
//        for (ReserveTable reserveTable : reserveTables) {
//            System.out.println(reserveTable.getBook_id());
//            System.out.println(reserveTable.getStart_time());
//        }
//       SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式  默认时间格式：yyyy-MM-dd HH:mm:ss
//       //SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
//       String passTime = pwoEditForm.getPasstime();
//       java.util.Date passUtilDate = simpleTime.parse(passTime);
//       java.sql.TimeStamp passSqlDate = new java.sql.TimeStamp(passUtilDate.getTime());
//       pwOrder.setPasstime(passSqlDate);

    //       java.util.Date start_time = recordTable.getStart_time();
//       SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
//       SimpleDateFormat sdfm = new SimpleDateFormat("MM");
//       SimpleDateFormat sdfd = new SimpleDateFormat("dd");
//       SimpleDateFormat sdfh = new SimpleDateFormat("HH");
//       String yy = new String(sdfy.format(System.currentTimeMillis()));
//       String mm = new String(sdfm.format(System.currentTimeMillis()));
//       String dd = new String(sdfd.format(System.currentTimeMillis()));
//       String hh = new String(sdfh.format(System.currentTimeMillis()));
//       long ys = new Long(yy);
//       long ms = new Long(mm);
//       long ds = new Long(dd);
//       long hs = new Long(hh);
}
