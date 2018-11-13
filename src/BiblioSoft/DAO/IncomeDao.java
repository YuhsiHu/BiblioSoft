package BiblioSoft.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;
import BiblioSoft.core.ConnDB;


public class IncomeDao {
	
	private ConnDB conn = new ConnDB();
	
	//main���������Թ���
	//public static void main(String args[]) throws SQLException, ParseException {
		//IncomeDao dao = new IncomeDao();
		//dao.addDeposit();
		//Collection<IncomeTable> coll = new ArrayList<IncomeTable>();
		//coll = dao.getIncomeHistoryWeekly("2018-10-16");
	    //for(IncomeTable table:coll) {
	    	//System.out.println(table.getIncome_id());
	    	//System.out.println(table.getIncome_date());
	    	//System.out.println(table.getIncome_value());
	    	//System.out.println(table.getIncome_from());
	    	
	    //}
	    //double value=0;
	    //value=dao.getTotalFine();
	    //System.out.println(value);
	//}
	
	//����һ����������ݿ���income��Ϣ��Collection��Collection�еĶ���ΪincomeTable�࣬����ͨ�����е�get������ȡ��Ϣ���ú�������һ���������ݿ�������income��Ϣ��collection
	public Collection<IncomeTable> query() throws SQLException {
		IncomeTable incometable = null;
		Collection<IncomeTable> incomeColl = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income");
		
		while (rs.next()) {
			incometable = new IncomeTable();
			incometable.setIncome_id(rs.getInt("income_id"));
			incometable.setIncome_date(rs.getDate("income_date"));
			incometable.setIncome_value(rs.getDouble("income_value"));
			incometable.setIncome_from(rs.getString("income_from"));
			incomeColl.add(incometable);
		}
		
		rs.close();
        conn.close();
		return incomeColl;
	
	}
	
	//����һ����������ݿ���income��deposit������Ϣ��Collection��Collection�еĶ���ΪincomeTable�࣬����ͨ�����е�get������ȡ��Ϣ���ú�������һ���������ݿ�income����income_fromΪdeposit����Ϣ��collection
	public Collection<IncomeTable> getDepositHistory() throws SQLException {
		IncomeTable incometable = null;
		Collection<IncomeTable> depositColl = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income where income_from='deposit'");
		
		while (rs.next()) {
			incometable = new IncomeTable();
			incometable.setIncome_id(rs.getInt("income_id"));
			incometable.setIncome_date(rs.getDate("income_date"));
			incometable.setIncome_value(rs.getDouble("income_value"));
			incometable.setIncome_from(rs.getString("income_from"));
			depositColl.add(incometable);
		}
		
		rs.close();
        conn.close();
		return depositColl;
	
	}
	
	//����һ����������ݿ���income��fine������Ϣ��Collection��Collection�еĶ���ΪincomeTable�࣬����ͨ�����е�get������ȡ��Ϣ���ú�������һ���������ݿ�income����income_fromΪfine����Ϣ��collection
		public Collection<IncomeTable> getFineHistory() throws SQLException {
			IncomeTable incometable = null;
			Collection<IncomeTable> fineColl = new ArrayList<IncomeTable>();
			ResultSet rs = conn.executeQuery("select * from Income where income_from='fine'");
			
			while (rs.next()) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				fineColl.add(incometable);
			}
			
			rs.close();
	        conn.close();
			return fineColl;
		
		}
		
	
	
	//ͼ��ݹ���ԱΪ����ע���˻�ʱ��ȡѺ�����������¼
	public void addDeposit() throws SQLException {
		
		double depositValue=0;
		
		//ȡ��Default_value�д�ŵ��趨��depositֵ
		ResultSet rs = conn.executeQuery("select * from Default_value");
		while(rs.next()){  
		depositValue = rs.getDouble("deposit");
		}
		
		//��ȡ��ǰ����
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        
        //��������ӵ����ݿ���
        String sql = "INSERT INTO Income (income_date,income_value,income_from) "
				+ "VALUES (to_date('"+date+"','yyyy-mm-dd'), '"+depositValue+"', 'deposit' );";
        conn.executeUpdate(sql);
        
        rs.close();
        conn.close();
		
	}
	
	//ͼ��ݹ���Ա��ȡ����ʱ��������¼,�����Record���е�getFine����
	public void addFine(int ReaderID, int BookID) throws SQLException {
		
		double fineValue=0;
		
		//����Record���е�getFine������ȡ�轻����ֵ
		RecordDAO record = new RecordDAO();
		fineValue = record.getFine(ReaderID, BookID);
		
		//��ȡ��ǰ����
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
        
        //��������ӵ����ݿ���
        String sql = "INSERT INTO Income (income_date,income_value,income_from) "
				+ "VALUES (to_date('"+date+"','yyyy-mm-dd'), '"+fineValue+"', 'fine' );";
        conn.executeUpdate(sql);
        
        conn.close();
	}
	
	//�����ڲ�ѯincome��¼������Ϊ�ַ��������ڣ���ʽΪ��yyyy-MM-dd��������dao.GetIncomeHistoryDaily("2018-10-16")������һ�����и�����������income��Ϣ��collection
	public Collection<IncomeTable> getIncomeHistoryDaily(String date) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String dateString=null;
		Collection<IncomeTable> incomeDaily = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income");
		
		//�������ݿ⣬���income_date��Ҫ��ѯ��������ͬ�������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			dateString = sqldate.toString();
			if(dateString.equals(date)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeDaily.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeDaily;
		
	}
	
	//���ܲ�ѯincome��¼������Ϊ��Ҫ��ѯ�ܵĵ�һ����ַ��������ڣ���ʽΪ��yyyy-MM-dd��������dao.GetIncomeHistoryWeekly("2018-10-16")������һ�����д�2018-10-16��2018-10-22һ��income��Ϣ��collection
	public Collection<IncomeTable> getIncomeHistoryWeekly(String date) throws SQLException, ParseException {
		IncomeTable incometable = null;
		Date sqldate = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = df.parse(date);
		Date endDate = new Date(startDate.getTime() + 6 * 24 * 60 * 60 * 1000);
		Collection<IncomeTable> incomeWeekly = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income");
		
		//�������ݿ⣬���income_date���������dateΪ��һ���һ�ܷ�Χ�ڣ������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			if(isInDate(sqldate,startDate,endDate)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeWeekly.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeWeekly;
		
	}
	
	//���·ݲ�ѯincome��¼������Ϊ�ַ������·ݣ���ʽΪ��yyyy-MM��������dao.GetIncomeHistoryMonthly("2018-09")������һ�����и�������income��Ϣ��collection
	public Collection<IncomeTable> getIncomeHistoryMonthly(String month) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String MonthString=null;
		Collection<IncomeTable> incomeMonthly = new ArrayList<IncomeTable>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		ResultSet rs = conn.executeQuery("select * from Income");
		
		//�������ݿ⣬���income_date�е�yyyy-MM������Ҫ��ѯ���·���ͬ�������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			MonthString = df.format(sqldate);
			if(MonthString.equals(month)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeMonthly.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeMonthly;
		
	}
	
	//�ж�����dt�Ƿ���dt1��dt2֮�䣬�Ƿ���true���񷵻�false����������ΪDate���ͣ���ʽΪ��yyyy-MM-dd��
	private boolean isInDate(Date dt, Date dt1, Date dt2) throws ParseException
    {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//Date date =df.parse(dt); 
		//Date date1 =df.parse(dt1); 
		//Date date2 = df.parse(dt2); 
        return dt.compareTo(dt1) >= 0 && dt.compareTo(dt2) <= 0;
    }
	
	//�������ݿ���deposit���������
	
	public double getTotalDeposit() throws SQLException {
		double totalDeposit = 0;
		double incomeValue = 0;
		ResultSet rs = conn.executeQuery("select * from Income where income_from='deposit'");
		while(rs.next()) {
			incomeValue = rs.getDouble("income_value");
			totalDeposit = totalDeposit+incomeValue;
		}
		
		rs.close();
        conn.close();
		return totalDeposit;
	}
	
	//�������ݿ���fine���������
	
	public double getTotalFine() throws SQLException {
		double totalFine = 0;
		double incomeValue = 0;
		ResultSet rs = conn.executeQuery("select * from Income where income_from='fine'");
		while(rs.next()) {
			incomeValue = rs.getDouble("income_value");
			totalFine = totalFine+incomeValue;
		}
		
		rs.close();
        conn.close();
		return totalFine;
	}
	
	
	public double getTodayIncome() throws SQLException {
		double totalIncome = 0;
		double incomeValue = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
        ResultSet rs = conn.executeQuery("select * from Income where income_date=to_date('"+date+"','yyyy-mm-dd')");
        while(rs.next()) {
			incomeValue = rs.getDouble("income_value");
			totalIncome = totalIncome+incomeValue;
		}
        
        rs.close();
        conn.close();
		return totalIncome;
	}
	
	public double getThisWeekIncome() throws SQLException, ParseException {
		double totalIncome = 0;
		double incomeValue = 0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //����ʱ���ʽ  
		String date = sdf.format(new Date());
		Calendar cal = Calendar.getInstance();  
		Date time=sdf.parse(date);
		cal.setTime(time);  
		 
		//�ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��  
	    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//��õ�ǰ������һ�����ڵĵڼ���  
		if(1 == dayWeek) {  
		      cal.add(Calendar.DAY_OF_MONTH, -1);  
	    }  
		cal.setFirstDayOfWeek(Calendar.MONDAY);//����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ  
		int day = cal.get(Calendar.DAY_OF_WEEK);//��õ�ǰ������һ�����ڵĵڼ���  
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ   
		String mondayDate = sdf.format(cal.getTime());
		
		Date startDate = sdf.parse(mondayDate);
		Date endDate = sdf.parse(date);
		Date sqldate = null;
        ResultSet rs = conn.executeQuery("select * from Income");
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			if(isInDate(sqldate,startDate,endDate)) {
				incomeValue = rs.getDouble("income_value");
				totalIncome = totalIncome+incomeValue;
			}
		}
		
		rs.close();
        conn.close();
		return totalIncome;
	}
	
	public double getMonthIncome() throws SQLException, ParseException {
		double totalIncome = 0;
		double incomeValue = 0;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();//��ȡ��ǰ���� 
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH,1);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ�� 
        String firstDay = df.format(cal.getTime());
        String date = df.format(new Date());
        
        Date startDate = df.parse(firstDay);
		Date endDate = df.parse(date);
		Date sqldate = null;
		ResultSet rs = conn.executeQuery("select * from Income");
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			if(isInDate(sqldate,startDate,endDate)) {
				incomeValue = rs.getDouble("income_value");
				totalIncome = totalIncome+incomeValue;
			}
		}
		
		rs.close();
        conn.close();
		return totalIncome;
	}
	
	public double getYearIncome() throws SQLException, ParseException {
		double totalIncome = 0;
		double incomeValue = 0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		SimpleDateFormat df=new SimpleDateFormat("yyyy");
        String Year = df.format(new Date()) ;
        String firstDay = Year+"-01-01";
        Date startDate = sdf.parse(firstDay);
		Date endDate = sdf.parse(date);
		Date sqldate = null;
		ResultSet rs = conn.executeQuery("select * from Income");
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			if(isInDate(sqldate,startDate,endDate)) {
				incomeValue = rs.getDouble("income_value");
				totalIncome = totalIncome+incomeValue;
			}
		}
		
		rs.close();
        conn.close();
		return totalIncome;
	}
	
	public Collection<IncomeTable> getDepositHistoryDaily(String date) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String dateString=null;
		Collection<IncomeTable> incomeDaily = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income where income_from='deposit'");
		
		//�������ݿ⣬���income_date��Ҫ��ѯ��������ͬ�������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			dateString = sqldate.toString();
			if(dateString.equals(date)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeDaily.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeDaily;
		
	}
	
	public Collection<IncomeTable> getFineHistoryDaily(String date) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String dateString=null;
		Collection<IncomeTable> incomeDaily = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income where income_from='fine'");
		
		//�������ݿ⣬���income_date��Ҫ��ѯ��������ͬ�������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			dateString = sqldate.toString();
			if(dateString.equals(date)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeDaily.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeDaily;
		
	}
	
	public Collection<IncomeTable> getDepositHistoryMonthly(String month) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String MonthString=null;
		Collection<IncomeTable> incomeMonthly = new ArrayList<IncomeTable>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		ResultSet rs = conn.executeQuery("select * from Income where income_from='deposit'");
		
		//�������ݿ⣬���income_date�е�yyyy-MM������Ҫ��ѯ���·���ͬ�������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			MonthString = df.format(sqldate);
			if(MonthString.equals(month)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeMonthly.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeMonthly;
		
	}
	
	public Collection<IncomeTable> getFineHistoryMonthly(String month) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String MonthString=null;
		Collection<IncomeTable> incomeMonthly = new ArrayList<IncomeTable>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		ResultSet rs = conn.executeQuery("select * from Income where income_from='fine'");
		
		//�������ݿ⣬���income_date�е�yyyy-MM������Ҫ��ѯ���·���ͬ�������������еĸ���ֵ����һ��incometable���󣬲����ö�����뵽collection��
		while(rs.next()) {
			sqldate = rs.getDate("income_date");
			MonthString = df.format(sqldate);
			if(MonthString.equals(month)) {
				incometable = new IncomeTable();
				incometable.setIncome_id(rs.getInt("income_id"));
				incometable.setIncome_date(rs.getDate("income_date"));
				incometable.setIncome_value(rs.getDouble("income_value"));
				incometable.setIncome_from(rs.getString("income_from"));
				incomeMonthly.add(incometable);
			}
		}
		
		rs.close();
        conn.close();
		return incomeMonthly;
		
	}
	
}
