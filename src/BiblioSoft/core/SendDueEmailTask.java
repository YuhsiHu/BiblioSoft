package BiblioSoft.core;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Logger;
import BiblioSoft.DAO.SendEmailDAO;
import BiblioSoft.Table.SendEmailTable;
/**
 * Send Due Email Task
 * @author Hu Yuxi
 *
 */
public class SendDueEmailTask extends TimerTask{
	     @Override
	     public void run() {
	         try {
	        	System.out.println("SendDueEmailTask****************");
	            SendEmailDAO sendemaildao=new SendEmailDAO();
	            ArrayList<SendEmailTable> list=new ArrayList<SendEmailTable>();
	            list=(ArrayList<SendEmailTable>) sendemaildao.getAllNeedtoSend();
	            
	            System.out.println("list is empty:"+list.isEmpty());
	            System.out.println(list);
	            
	            if(list==null) {
	            	System.out.println("list is null!");
	            }
	            
	            for (SendEmailTable now : list) {
	            	System.out.println("send email to: "+now.getEmail());
	            	SendDueEmail.SendDueEmail(now.getEmail(), now.getBook_name(), now.getReader_name(), String.valueOf(now.getReturn_time()));
	            }
	            
	        } catch (Exception e) {
	            System.out.println("-------------解析信息发生异常--------------");
	       }
	     }
}
