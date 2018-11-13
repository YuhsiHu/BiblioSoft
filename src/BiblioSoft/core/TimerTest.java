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
	              
	        /*** ����ÿ��23:00ִ�з��� ***/
	       
	        calendar.set(Calendar.HOUR_OF_DAY, 9);
	        calendar.set(Calendar.MINUTE, 00);
	        calendar.set(Calendar.SECOND, 00);
	         
	        Date date=calendar.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
	        System.out.println("Send Email date:"+String.valueOf(date));
	         
	        //�����һ��ִ�ж�ʱ�����ʱ��С�ڵ�ǰ��ʱ��
	        //��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ�� ��һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�
//	        if (date.before(new Date())) {
//	            date = this.addDay(date, 1);
//	        }
	         
	        Timer timer = new Timer();	         
	        SendDueEmailTask task = new SendDueEmailTask();
	       //����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
	        timer.schedule(task,date,PERIOD_DAY);
	       }
	       
	       // ���ӻ��������
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
