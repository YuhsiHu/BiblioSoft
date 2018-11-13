package BiblioSoft.core;

import java.util.Date;
import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

public class TimerTest extends HttpServlet{

	 private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	        
	       public TimerTest() {
	        Calendar calendar = Calendar.getInstance();
	              
	        /*** 定制每日23:00执行方法 ***/
	       
	        calendar.set(Calendar.HOUR_OF_DAY, 9);
	        calendar.set(Calendar.MINUTE, 00);
	        calendar.set(Calendar.SECOND, 00);
	         
	        Date date=calendar.getTime(); //第一次执行定时任务的时间
	        System.out.println("Send Email date:"+String.valueOf(date));
	         
	        //如果第一次执行定时任务的时间小于当前的时间
	        //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
//	        if (date.before(new Date())) {
//	            date = this.addDay(date, 1);
//	        }
	         
	        Timer timer = new Timer();	         
	        SendDueEmailTask task = new SendDueEmailTask();
	       //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
	        timer.schedule(task,date,PERIOD_DAY);
	       }
	       
	       // 增加或减少天数
	       public Date addDay(Date date, int num) {
//	    	   RecordDAO record = new RecordDAO();
//	    	   ReaderDAO reader = new ReaderDAO();
//	    	   Collection<ReaderTable>readerTable = new Arraylist<ReaderTable>();
//	    	   record.updateFine(ReaderID);
	        Calendar startDT = Calendar.getInstance();
	        startDT.setTime(date);
	        startDT.add(Calendar.DAY_OF_MONTH, num);
	        return startDT.getTime();
	       }
	       
	       public  void init() {  
	    	  System.out.println("Initialize the TimerTest");
	          TimerTest tm=new TimerTest();  
	       }  
}
