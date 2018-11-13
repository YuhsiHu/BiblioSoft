<%@ page import="BiblioSoft.core.*" %>
<%@ page import="BiblioSoft.DAO.*" %>
<%@ page import="BiblioSoft.DAO.RecordDAO" %>
<%@ page import="BiblioSoft.DAO.BookDAO" %>
<%@ page import="BiblioSoft.DAO.DefaultValue" %>
<%@ page import="BiblioSoft.Table.RecordTable" %>
<%@ page import="BiblioSoft.Table.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC"-//W3C//DTD HTML 4.01Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<!doctype html>
<html lang="en">

<head>
	<title>Panels</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="brand">
			<a href="readerDashboard.jsp"><img src="assets/img/BiblioSoft Logo.png"
				alt="BiblioSoft Logo" class="img-responsive logo"></a>
		</div>
		<div class="container-fluid">
			<div class="navbar-btn">
				<button type="button" class="btn-toggle-fullwidth">
					<i class="lnr lnr-arrow-left-circle"></i>
				</button>
			</div>
			<form class="navbar-form navbar-left"></form>
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <img src="assets/img/user.png"
							class="img-circle" alt="Avatar"> <span>${sessionScope.Tele}</span></a></li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="readerModifyPassword.jsp" class=""><i
							class="lnr lnr-code"></i> <span>Modify Password</span></a></li>
					<li><a href="readerSearchBook.jsp" class=""><i
							class="lnr lnr-chart-bars"></i> <span>Search</span></a></li>
					<li><a href="readerBorrowHistory.jsp" class="active"><i
							class="lnr lnr-cog"></i> <span>Borrow History</span></a></li>
					<li><a href="readerReturnHistory.jsp" class=""><i
							class="lnr lnr-alarm"></i> <span>Return History</span></a></li>
					<li><a href="ReaderIndex.jsp" class=""><i
							class="lnr lnr-dice"></i> <span>Reader Index</span></a></li>
					<li><a href="readerFineRecord.jsp" class=""><i
							class="lnr lnr-text-format"></i> <span>Fine</span></a></li>
					<li><a href="readerReserve.jsp" class=""><i
							class="lnr lnr-linearicons"></i> <span>Reserve</span></a></li>
					<li><a href="#" onclick="logout()" class=""><i class="lnr lnr-linearicons"></i> <span>Logout</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		
				<!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <h3 class="page-title">Borrow History</h3>
                <div class="row">
                  
                    </div>
                </div>

                <div class="row">
                
                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">Borrow History</h3>
                            </div>
                            <div class="panel-body">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Book ID</th>
                                        <th>Book Name</th>
                                        <th>Start Time</th>
                                        <th>DeadLine</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                            			String tele=(String)session.getAttribute("Tele");//get tele
                                    
                                        int Reader_id = 0;
                                        RecordDAO recordDAO = new RecordDAO();
                                        ReaderDAO readerDAO= new ReaderDAO();
                                        int count = 1;
                                        BookDAO bookDAO = new BookDAO();
                                     
                                        DefaultValue defaultValue = new DefaultValue();
                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    
                                        
                                        ReaderTable readerTable=new ReaderTable();
                                    	
                                        
                                    	try {
                                    		if(tele!=null)
                                            {
                                      				readerTable=readerDAO.findByTele(tele);
                                            		Reader_id=readerTable.getID();
                                            }
                                		} catch (NumberFormatException e) {
                                		
                                		} catch (SQLException e) {
                                			
                                		}
                                        Collection<RecordTable> recordTables = recordDAO.getCurrentBorrow(Reader_id);
                                        for (RecordTable recordTable : recordTables) {
                                   
                                    %>
                                    <tr>
                                        <td><%=count%>
                                        </td>
                                        <td><%=recordTable.getBook_id()%>
                                        </td>
                                        <td><%=bookDAO.searchByID(recordTable.getBook_id()).getBookName()%>
                                        </td>
                                        <td><%=recordTable.getStart_time()%>
                                        </td>
                                         <th><%=new String(df.format(recordDAO.getDeadDate(Reader_id,recordTable.getBook_id())))%>
                                       
                                       
                                        </th>
                                    </tr>

                                    <%
                                            count++;
                                        
                                        }
                                    %>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
		
		
		
		
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>

	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script>
		function logout() {
			var result = confirm("Please make sure.Logout?");
			if (result == true) {
				window.location.href = "home_page.jsp";
			} else {

			}
		}
	</script>
</body>
</html>
</html>