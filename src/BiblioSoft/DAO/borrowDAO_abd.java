package BiblioSoft.DAO;

import java.util.*;
import java.sql.*;
import java.util.Date;

import BiblioSoft.Table.bookTable_abd;
import BiblioSoft.Table.borrowBookTable_abd;
import BiblioSoft.Table.borrowTable_abd;
import BiblioSoft.Table.readerTable_abd;
import BiblioSoft.core.ConnDB;

/**
 * 
 * @author HuYuxi
 *
 */
public class borrowDAO_abd {
	private ConnDB conn = new ConnDB();
	
    public int insert() {
        String sql = "INSERT INTO "+'"'+"Record"+'"'+" (book_id) vlaues(1) ";
        int ret = conn.executeUpdate(sql);
        return ret;
    }
    
    //*****************************lend a book to a reader******************************
    public int insertBorrow(String readerid,String bookid){
        
    	//get current time
        Date dateU=new Date();
        java.sql.Date date=new java.sql.Date(dateU.getTime());
        String sql1="select "+'"'+"BookInLib"+'"'+".* from "+'"'+"BookInLib"+'"'+" where book_id="+bookid+"";
        ResultSet rs=conn.executeQuery(sql1);
        int days=0;
        try {
            if (rs.next()) {
                days = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        
        //calculate return date
          String date_str=String.valueOf(date);
          String dd = date_str.substring(8,10);
          String DD = date_str.substring(0,8)+String.valueOf(Integer.parseInt(dd) + days);
          java.sql.Date return_time= java.sql.Date.valueOf(DD);

          String sql ="Insert into "+'"'+"Record"+'"'+" (reader_id,book_id,start_time,return_time) values("+readerid+","+bookid+",'"+date+"','"+return_time+"')";
           int falg = conn.executeUpdate(sql);
          System.out.println("添加图书借阅信息的SQL：" + sql);
          conn.close();
          return falg;
}
    
      //*************************************book delay*********************************
      public int renew(int bookid,int readerid){
          String sql0="SELECT * FROM "+'"'+"Record"+'"'+" WHERE book_id="+ bookid +" and reader_id="+readerid+"";
          ResultSet rs1=conn.executeQuery(sql0);
          int flag=0;
          try {
            if (rs1.next()) {
                //get current time
                Date dateU = new Date();
                java.sql.Date date = new java.sql.Date(dateU.getTime());
                String sql1 = "select returntime from "+'"'+"Record"+'"'+" where book_id=" + rs1.getInt("book_id") + "";
                ResultSet rs = conn.executeQuery(sql1);
                int days = 0;
                try {
                    if (rs.next()) {
                        days = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                }
                
                //calculate return date
                String date_str = String.valueOf(date);
                String dd = date_str.substring(8, 10);
                String DD = date_str.substring(0, 8) +
                            String.valueOf(Integer.parseInt(dd) + days);
                java.sql.Date return_time = java.sql.Date.valueOf(DD);

                String sql = "UPDATE "+'"'+"Record"+'"'+" SET return_time='" + return_time +"where book_id="+ bookid +" and reader_id="+readerid+"";
                flag = conn.executeUpdate(sql);
            }
          } catch (Exception ex1) {}
          conn.close();
          return flag;
      }
      
      //*************************************book return*********************************
      public int back(int bookid,String operator){
          String sql0="SELECT reader_id,book_id FROM "+'"'+"Record"+'"'+" WHERE book_id="+bookid+"";
          ResultSet rs1=conn.executeQuery(sql0);
          int flag=0;
        try {
            if (rs1.next()) {
                //get current time
                Date dateU = new Date();
                java.sql.Date date = new java.sql.Date(dateU.getTime());
                int readerid=rs1.getInt("readerid");
                
                String sqlforisbn="SELECT isbn from "+'"'+"BookInLib"+'"'+" WHERE book_id="+bookid+"";
                ResultSet rsisbn=conn.executeQuery(sqlforisbn);
                String isbn=rsisbn.getString(1);
                
                //update the status of this book in BookInLib
                String sql1="Update "+'"'+"BookInLib"+'"'+" SET book_id="+bookid+",isbn='"+isbn+"',status='available'"+"";
                int ret=conn.executeUpdate(sql1);
                if(ret==1){
//                    String sql2 = "UPDATE tb_borrow SET ifback=1 where id=" + id +
//                                 "";
//                    flag = conn.executeUpdate(sql2);
                	flag=1;
                }else{
                    flag=0;
                }
            }
        } catch (Exception ex1) {
        }
          conn.close();
          return flag;
      }
      
    //*****************************search for one book lending information************************
      public Collection borrowinfo(String str) throws SQLException{
      String sql="select "+'"'+"Record"+'"'+".*,"+'"'+"Book"+'"'+".book_name,"+'"'+"Book"+'"'+".publisher_id from "
      		+ "(select book_id,isbn from "+'"'+"BookInLib"+'"'+" where status= \'no status\') as borr left join "+'"'+"Book"+'"'+" on borr.isbn="+'"'+"Book"+'"'+".isbn"
      		+ " left join "+'"'+"Record"+'"'+" on borr.book_id="+'"'+"Record"+'"'+".book_id where "+str;
      ResultSet rs=conn.executeQuery(sql);
      
//      int flag=0;
//	    if (rs.next()) {
//	    	flag=1;
//	    }else {
//	    	flag=2;
//	    }
      
      Collection coll=new ArrayList();
      borrowBookTable_abd borrow=null;
      try {
          while (rs.next()) {
        	  borrow = new borrowBookTable_abd();
        	  borrow.setBookID(Integer.valueOf(rs.getInt("book_id")));
        	  borrow.setStart_time(java.sql.Date.valueOf(rs.getString("start_time")));
        	  borrow.setReturn_time(java.sql.Date.valueOf(rs.getString("return_time")));
        	  borrow.setBookName(rs.getString("book_name"));
        	  //borrow.setPrice(Float.valueOf(rs.getFloat("price")));
        	  //borrow.setPublishing(rs.getString("publishing"));
              coll.add(borrow);
          }
      } catch (SQLException ex) {
          System.out.println("借阅信息："+ex.getMessage());
      }
      conn.close();
      
//      if(flag==1) {
//	    	return coll;
//	    }
//	    else if(flag==2) {
//	    	borrowBookTable borrowtable = new borrowBookTable();
//      	    borrowtable.setBookID(-1);
//      	    coll.add(borrowtable);
//      	return coll;
//	    }
		
      return coll;
      }
      
      //*************************Expiration reminder******************************************
    public Collection bremind(){
    Date dateU = new Date();
    java.sql.Date date = new java.sql.Date(dateU.getTime());
    String sql="select "+'"'+"Record"+'"'+".start_time,"+'"'+"Record"+'"'+".return_time,"+'"'+"Book"+'"'+".book_name,"+'"'+"Reader"+'"'+".reader_name,"+'"'+"Reader"+'"'+".reader_id from "+'"'+"Record"+'"'+" join "+'"'+"Book"+'"'+" on "+'"'+"Book"+'"'+".id="+'"'+"Record"+'"'+".book_id join "+'"'+"Reader"+'"'+" on "+'"'+"Reader"+'"'+".reader_id="+'"'+"Record"+'"'+".reader_id where "+'"'+"Record"+'"'+".return_time <='"+date+"'";
    ResultSet rs=conn.executeQuery(sql);
    System.out.println("到时提醒的SQL："+sql);
    Collection coll=new ArrayList();
    borrowBookTable_abd borrow=null;
    try {
        while (rs.next()) {
          borrow = new borrowBookTable_abd();
      	  borrow.setBookID(Integer.valueOf(rs.getInt("book_id")));
      	  borrow.setStart_time(java.sql.Date.valueOf(rs.getString("start_time")));
      	  borrow.setReturn_time(java.sql.Date.valueOf(rs.getString("return_time")));
      	  borrow.setBookName(rs.getString("book_name"));
      	  borrow.setReaderName(rs.getString("reader_name"));
      	  borrow.setReaderID(Integer.valueOf(rs.getString("reader_id")));

            coll.add(borrow);
            System.out.println("图书编码："+rs.getString(3));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    conn.close();
    return coll;
}
    
//*************************图书借阅查询******************************************
public Collection borrowQuery(String strif){
    String sql="";
    if(strif!="all" && strif!=null && strif!=""){
    	 sql="select "+'"'+"Record"+'"'+".*,"+'"'+"Book"+'"'+".book_name,"+'"'+"Book"+'"'+".publishing from "
          		+ "(select book_id,isbn from "+'"'+"BookInLib"+'"'+" where status=borrowed) as borr left join "+'"'+"Book"+'"'+" on borr.isbn="+'"'+"Book"+'"'+".isbn"
          		+ "left join "+'"'+"Record"+'"'+" on book_id="+'"'+"Record"+'"'+".book_id where "+strif+"";
    }else{
        sql="select "+'"'+"Record"+'"'+".*,"+'"'+"Book"+'"'+".book_name,"+'"'+"Book"+'"'+".publishing from "
          		+ "(select book_id,isbn from "+'"'+"BookInLib"+'"'+" where status=borrowed) as borr left join "+'"'+"Book"+'"'+" on borr.isbn="+'"'+"Book"+'"'+".isbn"
          		+ "left join "+'"'+"Record"+'"'+" on book_id="+'"'+"Record"+'"'+".book_id";
}
ResultSet rs=conn.executeQuery(sql);
System.out.println("图书借阅查询的SQL："+sql);
Collection coll=new ArrayList();
borrowBookTable_abd borrow=null;
try {
    while (rs.next()) {
        borrow = new borrowBookTable_abd();
        borrow.setBookID(Integer.valueOf(rs.getInt("book_id")));
    	borrow.setStart_time(java.sql.Date.valueOf(rs.getString("start_time")));
    	borrow.setReturn_time(java.sql.Date.valueOf(rs.getString("return_time")));
    	borrow.setBookName(rs.getString("book_name"));
    	borrow.setReaderName(rs.getString("reader_name"));
    	borrow.setReaderID(Integer.valueOf(rs.getString("reader_id")));
        coll.add(borrow);
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
conn.close();
return coll;
    }
      //*************************Book leaderboard******************************************

//unfinished,we can finish it in release 2


//    public Collection bookBorrowSort() {
//       String sql = "select * from (SELECT bookid,count(bookid) as degree FROM tb_borrow group by bookid) as borr join (select b.*,c.name as bookcaseName,p.pubname,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.del=0) as book on borr.bookid=book.id order by borr.degree desc limit 10 ";
//        System.out.println("图书借阅排行："+sql);
//        Collection coll = new ArrayList();
//        BorrowForm form = null;
//        ResultSet rs = conn.executeQuery(sql);
//
//
//        try {
//            while (rs.next()) {
//                form = new BorrowForm();
//                form.setBookId(rs.getInt(1));
//                form.setDegree(rs.getInt(2));
//                form.setBookBarcode(rs.getString(3));
//                form.setBookName(rs.getString(4));
//                form.setAuthor(rs.getString(6));
//                form.setPrice(Float.valueOf(rs.getString(9)));
//          
//                form.setBookcaseName(rs.getString(16));
//                form.setPubName(rs.getString(17));
//                form.setBookType(rs.getString(18));
//                coll.add(form);
//                System.out.print("RS："+rs.getString(4));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        conn.close();
//        return coll;
//    }
}
