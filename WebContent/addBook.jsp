<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="BiblioSoft.DAO.*" %>
<%@ page import="BiblioSoft.Table.*" %>
<%@ page import="java.util.Collection" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%
	//String name=(String)session.getAttribute("Librarian_Name");
	//if(name == "" || name == null){
	//	response.sendRedirect("librarianLogin.jsp");
	//}
%>

<head>
<title>Add Book</title>
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
			<form class="navbar-form navbar-left">
			</form>
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
					<li><a href="librarianHomepage.jsp" class=""><i
							class="lnr lnr-home"></i> <span>Librarian</span></a></li>
						<li>
							<a href="#subPages1" data-toggle="collapse" class="active"><i class="lnr lnr-cog"></i> <span>Books Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages1" class="collapse in">
								<ul class="nav">
                                    <li><a href="addBook.jsp" class="active">Add Book</a></li>
								    <li><a href="deleteBook.jsp" class="">Delete Book</a></li>
									<li><a href="searchEditBook.jsp" class="">Edit Book</a></li>
									<li><a href="searchBook.jsp" class="">Search Book</a></li>
									<li><a href="searchDeleteRecord.jsp" class="">Delete Book Record</a></li>
									<li><a href="addCategory.jsp" class="">Add Book Category</a></li>
									<li><a href="deleteCategory.jsp" class="">Delete Book Category</a></li>
									<li><a href="addLocation.jsp" class="">Add Book Location</a></li>
									<li><a href="deleteLocation.jsp" class="">Delete Book Location</a></li>
								</ul>
							</div>
						</li>						
						
						<li>
							<a href="#subPages2" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>Readers Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages2" class="collapse ">
								<ul class="nav">
									<li><a href="addReader.jsp" class="">Add Reader</a></li>
									<li><a href="deleteReader.jsp" class="">Delete Reader</a></li>
									<li><a href="librarianEditReader.jsp" class="">Edit Reader</a></li>
									<li><a href="librarianBorrowAndReturnRecord.jsp" class="">Borrow and Return Record</a></li>
									<li><a href="fineRecord.jsp" class="">Fine Record</a></li>
									<li><a href="librarianBorrowAndReturn.jsp" class="">Book Borrow and Return</a></li>
									
								</ul>
							</div>
						</li>						
						
					<li><a href="#" onclick="logout()" class=""><i
							class="lnr lnr-linearicons"></i> <span>Logout</span></a></li>
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
					<h3 class="page-title">Add a new book</h3>
					<div class="row">
						<div class="col-md-12" >
							
							<!-- INPUTS -->
						
								
								<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Input ISBN</h3>
								</div>
								<div class="panel-body">
                                    <div class="col-md-12">
                                    	<form method="post" action="GetBookInfoIsbn">
											<div class="input-group">
												<input class="form-control" name="ISBN" type="text" placeholder="ISBN" v-model = "pfincome" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" maxlength=13 >
												<span class="input-group-btn"><button
														class="btn btn-primary" type="submit">Go!</button></span>
											</div>
										</form>
                                    </div>
								</div>
                            </div>
                            
                            <div class="panel" >
                            <div class="panel-heading">
                                    <h3 class="panel-title">Book Imformation</h3>
                                </div>
								

								<div class="panel-body" >
								<form method="post" action="AddBook">
									<input type="text" class="form-control"  name="ISBN" placeholder="ISBN" value=${sessionScope.ISBN} >
                                    <br>
                                    <input type="text" class="form-control" name="Book Name" placeholder="Book Name" value=${sessionScope.BookName} >
                                    <br>
                                    <input type="text" class="form-control" name="Book Description" placeholder="Book Description" value=${sessionScope.BookDes} >
                                    <br>
                                    <input type="text" class="form-control" name="Edition"  placeholder="Edition">
                                    <br>
                                    <input type="text" class="form-control" name="Language"  placeholder="Language">
                                    <br>
                                    <input type="text" class="form-control" name="Publish Time" placeholder="Publish Time ( Example:2010-10-20 )" value=${sessionScope.PubTime} >
                                    <br>
                                    <input type="text" class="form-control" name="Price" placeholder="Price" value=${sessionScope.Price} >
                                    <br>
                                    <input type="text" class="form-control" name="Page" placeholder="Page" value=${sessionScope.Page} >
                                    <br>
                                    <input type="text" class="form-control" name="Publisher Name" placeholder="Publisher Name" value=${sessionScope.PubName}>
                                    <br>
                                    <input type="text" class="form-control" name="Author" placeholder="Author" value=${sessionScope.Author}  >
									<br>
									<h3 class="panel-title">Book Category</h3>
									<select id="Category" name="Category">
									<%
									CategoryDAO categorydao=new CategoryDAO();
									Collection<CategoryTable> categorylist=categorydao.listAllCategory();
									for(CategoryTable now : categorylist){
									String category=now.getCategory();
									%>
									<option name="Category" value="<%=category%>"><%=category%></option>
									<%} %>
									</select>
									<br>
									<br>
									<h3 class="panel-title">Book Location</h3>
									<select id="Location" name="Location">
									<%
									LocationDAO locationdao=new LocationDAO();
									Collection<LocationTable> locationlist=locationdao.listAlllocation();
									for(LocationTable now : locationlist){
									String location=now.getLocation();
									%>
									<option name="Location" value="<%=location%>"><%=location%></option>
									<%} %>
									</select>
									<br>
									<br>
									<input type="text" class="form-control" name="Number" placeholder="Book Number">
									<br>
									<input type="text" class="form-control" name="Status" value="available">
									<br>
									
                                    <p class="demo-button">
                                            <button type="submit" class="btn btn-success">Add</button>
                                    </p>
                                </form>
								</div>
								</div>
								
								
				
							<!-- END INPUTS -->
							<!-- INPUT SIZING -->
							
							<!-- END INPUT SIZING -->
						</div>
						
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		 
	<div class="clearfix"></div>
	<footer>
	<div class="container-fluid">
		<p class="copyright">
			Copyright &copy; 2018.Company name All rights reserved. <a
				 target="_blank" title="BiblioSoft">BiblioSoft</a>
			- Collect from <a  title="BiblioSoft"
				target="_blank">Software</a>
		</p>

	</div>
	</footer>
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

	function logout(){
		var result = confirm("Please make sure.Logout?");  
	    if (result == true) {  
	    	window.location.href="DestroyLibSession"; 
	    } else {  
	        
	    }
	}
	
	</script>
	
</body>

</html>
