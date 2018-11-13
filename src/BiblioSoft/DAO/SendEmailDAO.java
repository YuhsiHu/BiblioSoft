package BiblioSoft.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.SendEmailTable;
import BiblioSoft.core.ConnDB;

/**
 * Created by Liu Zhuocheng on 2018/10/26.
 */
public class SendEmailDAO {
    private Connection connection = null;
    private Statement statement = null;
    private ConnDB connDB = new ConnDB();

    public Collection getAllEmail() {
        Collection<SendEmailTable> sendEmailTables = new ArrayList<SendEmailTable>();
        String sql = "select reader_name,start_time,book_name,email from record,reader,bookinlib,book where record.book_id=bookinlib.book_id " +
                "and record.reader_id=reader.reader_id and bookinlib.isbn=book.isbn" ;
        try {
            SendEmailTable sendEmailTable;
            ResultSet resultSet = connDB.executeQuery(sql);
            while (resultSet.next()) {
            
                sendEmailTable = new SendEmailTable();
                sendEmailTable.setBook_name(resultSet.getString("book_name"));
                sendEmailTable.setEmail(resultSet.getString("email"));
                sendEmailTable.setReader_name(resultSet.getString("reader_name"));
                sendEmailTable.setReturn_time(Date.valueOf(resultSet.getString("start_time")));
                sendEmailTables.add(sendEmailTable);
                
            }
            connDB.close();
        } catch (Exception e) {
        }
        return sendEmailTables;
    }

    public java.util.Date getDeadLineTime(java.util.Date start_time) {
        long deadtime = start_time.getTime() + 30L * 24L * 60L * 60L * 1000L;
        // SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date deadline = new java.util.Date(deadtime);
        //String deadliness = new String(df.format(deadline));
        return deadline;
    }

    public Collection setAllDeadLineEmail() {
        Collection<SendEmailTable>sendEmailTables = new ArrayList<SendEmailTable>();
        sendEmailTables = getAllEmail();
        for (SendEmailTable sendEmailTable : sendEmailTables) {
            sendEmailTable.setReturn_time(getDeadLineTime(sendEmailTable.getReturn_time()));
        }
        return sendEmailTables;
    }

    public boolean ifNeedtoSend(java.util.Date deadlinetime){
        boolean ifneed = false;
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
        java.util.Date nowTime = new Date(dy - 1900, dm - 1, dd);
        long time = (deadlinetime.getTime() - nowTime.getTime())/(1000L * 60L * 60L * 24L * 3L);
        if(time<=3&&time>0)ifneed = true;
        return ifneed;
    }

    public Collection getAllNeedtoSend(){
        Collection<SendEmailTable>sendEmailTables = setAllDeadLineEmail();
        Collection<SendEmailTable>sendEmailTables1 = new ArrayList<SendEmailTable>();
        for(SendEmailTable sendEmailTable:sendEmailTables){
            if(ifNeedtoSend(sendEmailTable.getReturn_time()))sendEmailTables1.add(sendEmailTable);
        }
    return sendEmailTables1;
    }
  
}

