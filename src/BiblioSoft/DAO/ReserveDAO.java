package BiblioSoft.DAO;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import BiblioSoft.Table.*;
import BiblioSoft.core.*;

/**
 * Created by 鍒樺崜绋� on 2018/10/16.
 */
public class ReserveDAO {
    private Connection connection = null;
    private Statement statement = null;
    private ConnDB connDB = new ConnDB();

//    public void getConnection() {//涓庢暟鎹簱寤虹珛杩炴帴
//        try {
//            String url = "jdbc:postgresql://localhost:4869/Library";
//            String user = "postgres";
//            String password = "aptx4869";
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//           // System.out.println("鎴愬姛杩炴帴pg鏁版嵁搴�" + connection);
//        } catch (Exception e) {
//        }
//    }

    public Collection getReserveByReaderID(int ReaderID) {//閫氳繃ReaderID鏌ヨ鍏朵綑鎵�鏈夊睘鎬�
        //getConnection();
        Collection reserves = new ArrayList();
        String sql = "select * from reserve " +
                "WHERE reader_id =" + ReaderID;
        try {
            ReserveTable reserveTable;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                reserveTable = new ReserveTable();
                reserveTable.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                reserveTable.setStart_time(resultSet.getString("start_time"));
                reserveTable.setDeadLine_time(resultSet.getString("deadline_time"));
                reserveTable.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                reserves.add(reserveTable);
                //System.out.println("娣诲姞鎴愬姛");
            }
        } catch (Exception e) {
        }
        return reserves;
    }

    public Collection getReserveByBookID(int BookID) {//閫氳繃BookID鏌ヨ鍏朵綑鎵�鏈夊睘鎬�
        //getConnection();
        Collection reserves = new ArrayList();
        String sql = "select * from reserve " +
                "WHERE book_id =" + BookID;
        try {
            ReserveTable reserveTable;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                reserveTable = new ReserveTable();
                reserveTable.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                reserveTable.setStart_time(resultSet.getString("start_time"));
                reserveTable.setDeadLine_time(resultSet.getString("deadline_time"));
                reserveTable.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                reserves.add(reserveTable);
                //System.out.println("娣诲姞鎴愬姛");
            }
        } catch (Exception e) {
        }
        return reserves;
    }

    public void InsertRecordByReaderID(int ReaderID, int BookID, String StartTime,
                                       String DeadlineTime) {//娣诲姞鏌愯鑰呮柊鐨剅eserve
        //getConnection();
        //StartTime鍜孌eadlineTime鐨勬牸寮忓繀椤讳负  2018/10/17 20:19:23
        //yyyy/MM//dd hh:mm:ss
        //闇�瑕佷袱涓閿殑鍒ゆ柇淇℃伅锛屽垽鏂郴缁熶腑鏄惁瀛樺湪璇ookID涓嶳eaderID锛岄渶瑕佸埆鐨勭被涓殑鎺ュ彛
        //**********************************************************************************
        String sql = "INSERT Into reserve(book_id,reader_id,start_time,deadline_time)" +
                "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                "\'" + StartTime + "\'" + "," + "\'" + DeadlineTime + "\'" + ")";
        try {
            //statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("娣诲姞鎴愬姛");
        } catch (Exception e) {
        }
    }

    public void InsertRecordByReaderID(int ReaderID, int BookID) {

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

        String nows = new String(yy+"/"+mm+"/"+dd+" "+hh+":"+mmm+":"+ss);
        //System.out.println(nows);
        Date nowtimes = new Date(nows);

        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        long nowss = nowtimes.getTime();
        long deadlines = nowss + 2*60*60*1000;
        Date deadline = new Date(deadlines);
        String deadliness = new String(df.format(deadline));

        //2018/10/17 20:19:23
        String sql = "INSERT Into reserve(book_id,reader_id,start_time,deadline_time)" +
                "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                "\'" + nows + "\'" + "," + "\'" + deadliness + "\'" + ")";
        try {
            //statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("娣诲姞鎴愬姛");
        } catch (Exception e) {
        }
    }

    public String getStartTime(int ReaderID, int BookID) {
        //鏌ヨ鏌愪汉鏌愪功鐨勫紑濮嬮绾︽椂闂�
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
        //鏌ヨ鏌愪汉鏌愪功鐨勬埅姝㈠彇涔︽椂闂�
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
            System.out.println("鍒犻櫎鎴愬姛");
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
            System.out.println("鍒犻櫎鎴愬姛");
        } catch (Exception e) {
        }
    }

    public boolean ifNeedtoDelete(int ReaderID, int BookID) {
        //鏌ヨ鏌愪汉鐨勬煇涔﹂绾﹁褰曟槸鍚﹁秴鏃�
        //true琛ㄧず瓒呮椂
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

    public static void main(String[] args) {
        ReserveDAO reserveDAO = new ReserveDAO();
        reserveDAO.InsertRecordByReaderID(1,1);
    }
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
//       SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//瀹氫箟鏃ユ湡鏍煎紡  榛樿鏃堕棿鏍煎紡锛歽yyy-MM-dd HH:mm:ss
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
