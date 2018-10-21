package BiblioSoft.DAO;

import java.awt.print.Book;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import BiblioSoft.Table.RecordTable;
import BiblioSoft.core.*;

/**
 * Created by 鍒樺崜绋� on 2018/10/15.
 */
public class RecordDAO {
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
//            //  System.out.println("鎴愬姛杩炴帴pg鏁版嵁搴�" + connection);
//        } catch (Exception e) {
//        }
//    }

    public boolean ifExist(int ReaderID, int BookID) {
        //鏌ヨ鏄惁瀛樺湪鏌愪汉鏌愪功鐨勫�熼槄璁板綍,璇峰厛璋冪敤姝ゆ柟娉曡繘琛屽垽鏂�
        //false琛ㄧず涓嶅瓨鍦�
        //getConnection();
        Boolean ifexist = false;
        String sql = "select * from record where reader_id =" + ReaderID
                + "and book_id =" + BookID;
        try {
            // statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                String book_id = resultSet.getString(1);
                String reader_id = resultSet.getString(2);
                String start_time = resultSet.getString(3);
                String return_time = resultSet.getString(4);
                String current_fine = resultSet.getString(5);
                if (!book_id.isEmpty() && !reader_id.isEmpty() && !start_time.isEmpty()
                        && !return_time.isEmpty() && !current_fine.isEmpty()) ifexist = true;
            }
        } catch (Exception e) {
        }
        return ifexist;
    }

    public Collection getRecordByReaderID(int ReaderID) {//閫氳繃ReaderID鏌ヨ鍏朵綑鎵�鏈夊睘鎬�
        //getConnection();
        Collection records = new ArrayList();
        String sql = "select * from Record " +
                "WHERE reader_id =" + ReaderID;
        try {
            RecordTable record;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                record = new RecordTable();
                record.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                record.setStart_time(Date.valueOf(resultSet.getString("start_time")));
                if (resultSet.getString("return_time") != null)
                    record.setReturn_time(Date.valueOf(resultSet.getString("return_time")));
                record.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                record.setCurrent_fine(Integer.valueOf(resultSet.getInt("current_fine")));
                records.add(record);
                // System.out.println("娣诲姞鎴愬姛");
            }
        } catch (Exception e) {
        }
        return records;
    }

    public Collection getRecordByBookID(int BookID) {//閫氳繃ReaderID鏌ヨ鍏朵綑鎵�鏈夊睘鎬�
        //getConnection();
        Collection records = new ArrayList();
        String sql = "select * from Record " +
                "WHERE book_id =" + BookID;
        try {
            RecordTable record;
            //statement = connection.createStatement();
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
                //鍙栧嚭鍒楀��
                record = new RecordTable();
                record.setBook_id(Integer.valueOf(resultSet.getInt("book_id")));
                record.setStart_time(Date.valueOf(resultSet.getString("start_time")));
                if (resultSet.getString("return_time") != null)
                    record.setReturn_time(Date.valueOf(resultSet.getString("return_time")));
                record.setReader_id(Integer.valueOf(resultSet.getInt("reader_id")));
                record.setCurrent_fine(Integer.valueOf(resultSet.getInt("current_fine")));
                records.add(record);
                // System.out.println("娣诲姞鎴愬姛");
            }
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
                System.out.println("鍒犻櫎鎴愬姛");
            } catch (Exception e) {
            }
        }
    }

    public Boolean ifBookIsBrrowed(int BookID) {
        //鏌ヨ鏌愪功鏄惁琚�熼槄
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
                System.out.println("鍒犻櫎鎴愬姛");
            } catch (Exception e) {
            }
        }
    }

    public void InsertRecordByReaderID(int ReaderID, int BookID) {
        //涓嶉渶瑕乺eturn_time锛岄粯璁や负null
        //current_fine榛樿涓�0
        //start_date榛樿涓烘湰鏃�
        //getConnection();
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
            System.out.println("娣诲姞鎴愬姛");
        } catch (Exception e) {
        }
    }

    public void InsertRecordByReaderID(int ReaderID, int BookID, Date StartTime) {//娣诲姞鏌愯鑰呮柊鐨剅ecord
        //涓嶉渶瑕乺eturn_time锛岄粯璁や负null
        //current_fine榛樿涓�0
        // getConnection();
        //闇�瑕佷袱涓閿殑鍒ゆ柇淇℃伅锛屽垽鏂郴缁熶腑鏄惁瀛樺湪璇ookID涓嶳eaderID锛岄渶瑕佸埆鐨勭被涓殑鎺ュ彛
        //**********************************************************************************
        String sql = "INSERT Into record(book_id,reader_id,start_time,current_fine)" +
                "VALUES (" + "\'" + BookID + "\'" + "," + "\'" + ReaderID + "\'" + "," +
                "\'" + StartTime + "\'" + "," + "\'" + 0 + "\'" + ")";
        try {
            RecordTable record = new RecordTable();
            // statement = connection.createStatement();
            connDB.executeUpdate(sql);
            System.out.println("娣诲姞鎴愬姛");
        } catch (Exception e) {
        }
    }


    public boolean ifNeedtoFine(int ReaderID, int BookID) {
        //鏌ヨ鏌愪汉鐨勬煇涔︽槸鍚﹂渶瑕佺即绾崇綒閲�
        //榛樿涓嶅瓨鍦ㄦ煇浜哄娆″�熷悓涓�鏈功鐨勬儏鍐�
        //鑻ヤ负true鍒欒〃绀洪渶瑕佺即绾崇綒閲�
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
//                //鍙栧嚭鍒楀��
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
        //鍒ゆ柇鏄惁闇�瑕佸彂閫侀偖浠�
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
        //鏇存柊鎵�鏈夌殑Fine淇℃伅锛屽嵆瓒呰繃鏈�澶у�熼槄鏃ユ湡鏃舵瘡澶╃綒娆�+1
        //闇�瑕佷竴涓猂eadID鏁扮粍锛屽叾涓寘鍚墍鏈夌殑ReaderID
        //榛樿鏃ユ湡鍔�1鏈堜负鏈�鍚庢湡闄�
        for (int readerID : ReaderID) {
            Collection<RecordTable> RecordList = getRecordByReaderID(readerID);
            for (RecordTable recordTable : RecordList) {
                if (recordTable.getReturn_time() == null) {
                    //鏌愪汉鏌愪功鏈綊杩樻椂

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
//                    System.out.println(hs);                                  //鍔犱腑鍥芥椂鍖�
                    long date = (ys - 1970) * 12 * 30 * 24 * 60 * 60 * 1000 + (ms - 1 + 8) * 30 * 24 * 60 * 60 * 1000
                            + (ds - 1) * 24 * 60 * 60 * 1000 + (hs) * 60 * 60 * 1000;
                    //鑾峰彇褰撳墠鏃堕棿
                    java.util.Date nowTime = new Date(date);
                    Calendar rightNow = Calendar.getInstance();
                    rightNow.setTime(start_time);
                    rightNow.add(Calendar.MONTH, 1);// 鏃ユ湡鍔�1鏈�
                    java.util.Date dead_time = rightNow.getTime();
                    long day = 0;
                    int fine = 0;
                    System.out.println(nowTime.getTime());
                    //System.out.println(rightNow.getTime());
                    System.out.println(dead_time.getTime());
                    if (nowTime.getTime() > dead_time.getTime()) {
                        //nowTime.getTime()-dead_time.getTime()寰楀埌鐨勬槸姣鏁�
                        day = -((nowTime.getTime() - dead_time.getTime()) / (1000 * 60 * 60 * 24));
                        System.out.println(day);
                        fine = new Long(day).intValue();
                        int book_id = recordTable.getBook_id();
                        int read_id = recordTable.getReader_id();
                        int fines = fine;
                        //鏇存柊鏁版嵁搴�
                        //getConnection();
                        String sql = "UPDATE record SET current_fine = " + fines + "where reader_id="
                                + "\'" + read_id + "\'" + "AND book_id=" + "\'" + book_id + "\'";
                        try {
                            RecordTable record = new RecordTable();
                            // statement = connection.createStatement();
                            connDB.executeUpdate(sql);
                            // System.out.println("娣诲姞鎴愬姛");
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    public java.util.Date getStartDate(int ReaderID, int BookID) {
        //鏌ヨ鏌愪汉鏌愪功鐨勫紑濮嬫椂闂�
        //鍦ㄥ簲杩樹功鏃跺彂閫侀偖浠舵椂浣跨敤
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
//                //鍙栧嚭鍒楀��
//                String startDate = resultSet.getString(1);
//                startdate = new String(startDate);
//                System.out.println(startDate);
//            }
//        } catch (Exception e) {
//        }
//        return startdate;
    }

    public java.util.Date getReturnDate(int ReaderID, int BookID) {
        //鏌ヨ鏌愪汉鏌愪功鐨勮繕涔︽椂闂�,鏈綊杩樻椂涓簄ull
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
//                //鍙栧嚭鍒楀��
//                String returnDate = resultSet.getString(1);
//                returndate = new String(returnDate);
//                System.out.println(returnDate);
//            }
//        } catch (Exception e) {
//        }
//        return returndate;
    }

    public int getFine(int ReaderID, int BookID) {
        //鏌ヨ鏌愪汉鏌愪功鐨勭綒娆捐褰曪紝鑻ユ湭瓒呮椂鍒欎负0锛涘凡浜ょ綒娆句笌鏈氦缃氭鏈柟娉曢兘浠呰繑鍥炴鏁帮紝
        //鑻ヨ纭畾鏄惁浜よ繃缃氭锛岃鏌ラ槄鏈被涓殑ifNeedtoFine()鏂规硶
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
//                //鍙栧嚭鍒楀��
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
        //姝ゆ柟娉曠敤鏉ヨ繕涔︽椂浣跨敤锛屽皢Return_time浠巒ull鏀逛负褰撳墠鏃堕棿
        //搴斿綋鍚屾椂璋冪敤ifNeedtoFine()鍒ゆ柇鏄惁闇�瑕佺即绾崇綒娆�
        //鑻ラ渶瑕佺即绾崇綒娆撅紝鍒欏簲褰撳厛缂寸撼缃氭
        //鍦ㄧ即绾崇綒娆惧悗锛岃皟鐢╯etCurrentFine()鏂规硶鏇存柊缃氭淇℃伅
        if (getReturnDate(ReaderID, BookID) == null) {
            if (!ifNeedtoFine(ReaderID, BookID)) {
                //涓嶉渶瑕佺即绾崇綒閲�
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
                } catch (Exception e) {
                }
            }
        }
    }

    public void setCurrentFine(int ReaderID, int BookID) {
        //姝ゆ柟娉曠敤鏉ユ洿鏂扮綒娆撅紝鍗冲湪宸茬粡闇�瑕佺即绾崇綒娆句箣鍚庯紝杩涜缂寸撼鍚庯紝鏇存敼current_fine鐨勬搷浣溿��
        //鐢辫礋鏁版敼涓烘鏁�
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
                System.out.println("缃氭鏇存柊鎴愬姛");
            } catch (Exception e) {
            }
        }
    }

    public Collection getCurrentBorrow(int ReaderID) {//鑾峰緱褰撳墠鍊熼槄鐨勪功绫�
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
        //鑾峰緱鏌愪汉鐨勮繕涔﹀巻鍙诧紝鍗冲彧鑳芥煡璇㈠凡缁忓綊杩樼殑鍘嗗彶淇℃伅
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
        //鏌ヨ鏌愪汉鎵�鏈夊簲鏀粯鐨勭綒閲�
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
//       record.InsertRecordByReaderID(111,111);
//        record.deleteRecordByBookID(111);
        Collection<RecordTable> recordTables = record.getRecordByReaderID(111);
        for (RecordTable recordTable : recordTables) {
            System.out.println(recordTable.getBook_id());
        }
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
//    }娣诲姞鍑芥暟娴嬭瘯瀹屾垚
//    if(record.ifNeedtoFine(123,123))System.out.print("123 need fine");
//    else System.out.print("123 not need fine");
//    if(record.ifNeedtoFine(111,111))System.out.print("111 need fine");
//    }
        //ifneedtofine()娴嬭瘯瀹屾垚
    }
}



