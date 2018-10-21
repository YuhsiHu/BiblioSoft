package BiblioSoft.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import BiblioSoft.Table.*;
import BiblioSoft.DAO.*;
import BiblioSoft.core.ConnDB;

public class IncomeDao {
	
	private ConnDB conn = new ConnDB();
	
	//main函数，测试功能
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
	
	//返回一个存放着数据库中income信息的Collection，Collection中的对象为incomeTable类，可以通过类中的get方法获取信息，该函数返回一个存有数据库中所有income信息的collection
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
	
	//返回一个存放着数据库中income里deposit类型信息的Collection，Collection中的对象为incomeTable类，可以通过类中的get方法获取信息，该函数返回一个存有数据库income表中income_from为deposit的信息的collection
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
	
	//返回一个存放着数据库中income里fine类型信息的Collection，Collection中的对象为incomeTable类，可以通过类中的get方法获取信息，该函数返回一个存有数据库income表中income_from为fine的信息的collection
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
		
	
	
	//图书馆管理员为读者注册账户时收取押金后添加收入记录
	public void addDeposit() throws SQLException {
		
		double depositValue=0;
		
		//取出Default_value中存放的设定的deposit值
		ResultSet rs = conn.executeQuery("select * from Default_value");
		while(rs.next()){  
		depositValue = rs.getDouble("deposit");
		}
		
		//获取当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        
        //将数据添加到数据库中
        String sql = "INSERT INTO Income (income_date,income_value,income_from) "
				+ "VALUES (to_date('"+date+"','yyyy-mm-dd'), '"+depositValue+"', 'deposit' );";
        conn.executeUpdate(sql);
        
        rs.close();
        conn.close();
		
	}
	
	//图书馆管理员收取罚金时添加收入记录,需调用Record类中的getFine方法
	public void addFine(int ReaderID, int BookID) throws SQLException {
		
		double fineValue=0;
		
		//调用Record类中的getFine方法获取需交罚款值
		RecordDAO record = new RecordDAO();
		fineValue = record.getFine(ReaderID, BookID);
		
		//获取当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        
        //将数据添加到数据库中
        String sql = "INSERT INTO Income (income_date,income_value,income_from) "
				+ "VALUES (to_date('"+date+"','yyyy-mm-dd'), '"+fineValue+"', 'fine' );";
        conn.executeUpdate(sql);
        
        conn.close();
	}
	
	//按日期查询income记录，输入为字符类型日期，格式为“yyyy-MM-dd”，例如dao.GetIncomeHistoryDaily("2018-10-16")，返回一个存有该日期下所有income信息的collection
	public Collection<IncomeTable> getIncomeHistoryDaily(String date) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String dateString=null;
		Collection<IncomeTable> incomeDaily = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income");
		
		//遍历数据库，如果income_date与要查询的日期相同，将该行数据中的各个值付给一个incometable对象，并将该对象插入到collection中
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
	
	//按周查询income记录，输入为所要查询周的第一天的字符类型日期，格式为“yyyy-MM-dd”，例如dao.GetIncomeHistoryWeekly("2018-10-16")，返回一个存有从2018-10-16至2018-10-22一周income信息的collection
	public Collection<IncomeTable> getIncomeHistoryWeekly(String date) throws SQLException, ParseException {
		IncomeTable incometable = null;
		Date sqldate = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = df.parse(date);
		Date endDate = new Date(startDate.getTime() + 6 * 24 * 60 * 60 * 1000);
		Collection<IncomeTable> incomeWeekly = new ArrayList<IncomeTable>();
		ResultSet rs = conn.executeQuery("select * from Income");
		
		//遍历数据库，如果income_date在以输入的date为第一天的一周范围内，将该行数据中的各个值付给一个incometable对象，并将该对象插入到collection中
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
	
	//按月份查询income记录，输入为字符类型月份，格式为“yyyy-MM”，例如dao.GetIncomeHistoryMonthly("2018-09")，返回一个存有该月所有income信息的collection
	public Collection<IncomeTable> getIncomeHistoryMonthly(String month) throws SQLException {
		IncomeTable incometable = null;
		Date sqldate = null;
		String MonthString=null;
		Collection<IncomeTable> incomeMonthly = new ArrayList<IncomeTable>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		ResultSet rs = conn.executeQuery("select * from Income");
		
		//遍历数据库，如果income_date中的yyyy-MM部分与要查询的月份相同，将该行数据中的各个值付给一个incometable对象，并将该对象插入到collection中
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
	
	//判断日期dt是否在dt1到dt2之间，是返回true，否返回false，日期输入为Date类型，格式为“yyyy-MM-dd”
	private boolean isInDate(Date dt, Date dt1, Date dt2) throws ParseException
    {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//Date date =df.parse(dt); 
		//Date date1 =df.parse(dt1); 
		//Date date2 = df.parse(dt2); 
        return dt.compareTo(dt1) >= 0 && dt.compareTo(dt2) <= 0;
    }
	
	//返回数据库中deposit收入的总数
	private double getTotalDeposit() throws SQLException {
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
	
	//返回数据库中fine收入的总数
	private double getTotalFine() throws SQLException {
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
	
	
	
}
