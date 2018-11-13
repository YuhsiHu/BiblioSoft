<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="BiblioSoft.DAO.*" %>
<%@ page import="BiblioSoft.Table.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%
		String name=(String)session.getAttribute("Librarian_Name");
		if(name == "" || name == null){
			response.sendRedirect("librarianLogin.jsp");
		}
%>

<%
   IncomeDao dao = new IncomeDao();
   double todayIncome = dao.getTodayIncome();
   double weekIncome = dao.getThisWeekIncome();
   double monthIncome = dao.getMonthIncome();
   double yearIncome = dao.getYearIncome();
%>

<%
   String mon = request.getParameter("month");
   String day = request.getParameter("day");
   String type = request.getParameter("type");
   String year= "2018";
   Collection<IncomeTable> income = new ArrayList<IncomeTable>();
   if(type!=null&&type.equals("all")){
	   if(day!=null&&day.equals("all")){
		   String date = year+"-"+mon;
		   income = dao.getIncomeHistoryMonthly(date);
	   }
	   else{
		   String date = year+"-"+mon+"-"+day;
	       income = dao.getIncomeHistoryDaily(date);
	   }
   }
   if(type!=null&&type.equals("deposit")){
	   if(day!=null&&day.equals("all")){
		   String date = year+"-"+mon;
		   income = dao.getDepositHistoryMonthly(date);
	   }
	   else{
		   String date = year+"-"+mon+"-"+day;
	       income = dao.getDepositHistoryDaily(date);
	   }
   }
   if(type!=null&&type.equals("fine")){
	   if(day!=null&&day.equals("all")){
		   String date = year+"-"+mon;
		   income = dao.getFineHistoryMonthly(date);
	   }
	   else{
		   String date = year+"-"+mon+"-"+day;
	       income = dao.getFineHistoryDaily(date);
	   }
   }
   
%>

<head>
<title>Home</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet"
	href="assets/vendor/chartist/css/chartist-custom.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="brand">
			<li><a href="librarianHomepage.jsp"><img src="assets/img/BiblioSoft Logo.png"
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
							class="img-circle" alt="Avatar"> <span>${sessionScope.Librarian_Name}</span></a></li>
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
					<li><a href="librarianHomepage.jsp" class="active"><i
							class="lnr lnr-home"></i> <span>Librarian</span></a></li>
					<li><a href="#subPages1" data-toggle="collapse"
						class="collapsed"><i class="lnr lnr-cog"></i> <span>Books
								Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
						<div id="subPages1" class="collapse ">
							<ul class="nav">
								    <li><a href="addBook.jsp" class="">Add Book</a></li>
								    <li><a href="deleteBook.jsp" class="">Delete Book</a></li>
									<li><a href="searchEditBook.jsp" class="">Edit Book</a></li>
									<li><a href="searchBook.jsp" class="">Search Book</a></li>
									<li><a href="searchDeleteRecord.jsp" class="">Delete Book Record</a></li>
									<li><a href="addCategory.jsp" class="">Add Book Category</a></li>
									<li><a href="deleteCategory.jsp" class="">Delete Book Category</a></li>
									<li><a href="addLocation.jsp" class="">Add Book Location</a></li>
									<li><a href="deleteLocation.jsp" class="">Delete Book Location</a></li>
							</ul>
						</div></li>

					<li><a href="#subPages2" data-toggle="collapse"
						class="collapsed"><i class="lnr lnr-cog"></i> <span>Readers
								Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
						<div id="subPages2" class="collapse ">
							<ul class="nav">
								<li><a href="addReader.jsp" class="">Add Reader</a></li>
								<li><a href="deleteReader.jsp" class="">Delete Reader</a></li>
								<li><a href="librarianEditReader.jsp" class="">Edit Reader</a></li>
								<li><a href="librarianBorrowAndReturnRecord.jsp" class="">Borrow and Return Record</a></li>
								<li><a href="fineRecord.jsp" class="">Fine Record</a></li>
								<li><a href="librarianBorrowAndReturn.jsp" class="">Book Borrow and Return</a></li>

							</ul>
						</div></li>
						
					<li>
							<a href="#subPages3" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>Posts Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages3" class="collapse">
								<ul class="nav">
									<li><a href="addPost.jsp" class="">Add Post</a></li>
								    <li><a method="post" href="AllPost" class="">Search Post</a></li>
								</ul>
							</div>
						</li>

					<li><a href="#" onclick="logout()" class=""><i
							class="lnr lnr-linearicons"></i> <span>Logout</span></a></li>
					</a>
					</li>
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
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h3 class="panel-title">Income Overview</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-download"></i></span>
										<p>
											<span class="number"><%out.println(todayIncome); %></span> <span class="title">Today</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-download"></i></span>
										<p>
											<span class="number"><%out.println(weekIncome); %></span> <span class="title">This
												Week</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-download"></i></span>
										<p>
											<span class="number"><%out.println(monthIncome); %></span> <span class="title">This
												Month</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-download"></i></span>
										<p>
											<span class="number"><%out.println(yearIncome); %></span> <span class="title">This
												Year</span>
										</p>
									</div>
								</div>
							</div>

						</div>
					</div>


					<div class="panel">
						<div class="panel-heading">
							<h3  class="panel-title">Income Details</h3>
						</div>
						<div class="panel-body">
							<table align="center">
								<tr>
									<td width="150px">Search Date</td>
									<td width="350px">
										<form name="reg_testdate">
										month
											 <select name="month" size="1"> 
                                                <option>01 
                                                <option>02 
                                                <option>03 
                                                <option>04 
                                                <option>05
                                                <option>06
                                                <option>07
                                                <option>08
                                                <option>09
                                                <option>10
                                                <option>11
                                                <option>12
                                              </select> day
                                              <select name="day" size="1"> 
                                                <option>all
                                                <option>01 
                                                <option>02 
                                                <option>03 
                                                <option>04 
                                                <option>05
                                                <option>06
                                                <option>07
                                                <option>08
                                                <option>09
                                                <option>10
                                                <option>11
                                                <option>12
                                                <option>13
                                                <option>14
                                                <option>15
                                                <option>16
                                                <option>17
                                                <option>18
                                                <option>19
                                                <option>20
                                                <option>21
                                                <option>22
                                                <option>23
                                                <option>24
                                                <option>25
                                                <option>26
                                                <option>27
                                                <option>28
                                                <option>29
                                                <option>30
                                                <option>31                                              
                                              </select> type
                                              <select name="type" size="1"> 
                                                <option>all
                                                <option>deposit 
                                                <option>fine                                               
                                              </select> 
                                              
										<input class="btn btn-primary" type="submit" value="Search">
									
										</form>
									</td>
									
									
								</tr>
								
							</table>


						<!-- For Each -->

						</div>
						
						
						
						
						<div class="panel-body">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Income ID</th>
                                        <th>Income Date</th>
                                        <th>Income Value</th>
                                        <th>Income Type</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                            <%
                                             int Reader_id = 0;
                                             RecordDAO recordDAO = new RecordDAO();
                                             int count = 1;
                                             BookDAO bookDAO = new BookDAO();
                                             DefaultValue defaultValue = new DefaultValue();
                                             if(request.getParameter("Reader_id")==""){
			                                 out.print("<script language='javascript'>alert('Librarian ID Can Not Be Empty!');window.location.href='borrowAndReturnRecord.jsp';</script>");
		                                     }else{
                                             if(request.getParameter("Reader_id")!=null)Reader_id=Integer.valueOf(request.getParameter("Reader_id"));


                                            
                                             for(IncomeTable incomeTable:income){
                                             %>
                                    <tr>
                                        <td><%=count%>
                                        </td>
                                        <td><%=incomeTable.getIncome_id()%>
                                        </td>
                                        <td><%=incomeTable.getIncome_date()%>
                                        </td>
                                        <td><%=incomeTable.getIncome_value()%>
                                        </td>
                                        <td><%=incomeTable.getIncome_from()%></td>
                                        
                                    </tr>


                                        <%
                                        count++;}
                                    }
                                            %>                                                                                   
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
	<script
		src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>


	<script>
		function logout() {
			var result = confirm("Please make sure.Logout?");
			if (result == true) {
				window.location.href = "DestroyLibSession";
			} else {

			}
		}
	</script>


</body>

</html>
