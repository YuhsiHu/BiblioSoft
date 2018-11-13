<%@ page import="java.io.*" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%
	String name=(String)session.getAttribute("Librarian_Name");
	if(name == "" || name == null){
		response.sendRedirect("librarianLogin.jsp");
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
					<li><a href="home.jsp" class="active"><i
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
							
							<li>
								<a href="#subPages3" data-toggle="collapse" class="active"><i class="lnr lnr-cog"></i> <span>Posts Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
								<div id="subPages3" class="collapse">
									<ul class="nav">
										<li><a href="addPost.jsp" class="active">Add Post</a></li>
										<li><a method="post" href="AllPost" class="">Search Post</a></li>
									</ul>
								</div>
							</li>								
						
                   <li><a href="#" onclick="logout()" class=""><i
							class="lnr lnr-linearicons"></i> <span>Logout</span></a></li></a></li>
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
						<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">New Book ID</h3>
								</div>
								<div class="panel-body no-padding">
									<table class="table">
										<thead>
											<tr>
												<th>#</th>
												<th>New Book ID</th>
												<th>Book Name</th>
												<th>ISBN</th>
												<th>Author</th>
												<th>BarCode</th>
											</tr>
										</thead>
										
										<c:forEach var="bookid" items="${sessionScope.idStringlist}" varStatus="infoLoopCount">
											<tbody>
												<tr style="font-size:25px;">
													<td>${infoLoopCount.count}</td>
													<td>${bookid}</td>
													<td>${sessionScope.newBookName}</td>
													<td>${sessionScope.newISBN}</td>
													<td>${sessionScope.newAuthor}</td>
													<td><img src='http://barcode.tec-it.com/barcode.ashx?data=${bookid}' alt='Barcode Software by TEC-IT' border='0' /></td>
												</tr>
											</tbody>
										</c:forEach>
										
									</table>
									
								
									<a href="addBook.jsp" class="demo-button" >
                                            <button  class="btn btn-success">confirm</button>
                                    </a>
									
									<div class="qrcode"></div>
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
	<script type="text/javascript" src="jquery.js"></script> 
	<script type="text/javascript" src="jquery.qrcode.min.js"></script>
	
			
		<script>
		
		
			$("#qrcode").qrcode({
				text : "1234567891",
				size : 100
			});

			function logout() {
				var result = confirm("Please make sure.Logout?");
				if (result == true) {
					window.location.href = "DestroyLibSession";
				} else {

				}
			}
			function add() {

				window.location.href = "addBook.jsp";

			}
		</script>
	
</body>