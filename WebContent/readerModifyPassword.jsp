<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="BiblioSoft.core.*" %>
<%@ page import="BiblioSoft.DAO.*" %>
<%@ page import="BiblioSoft.Table.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<title></title>
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
					<li><a href="readerBorrowHistory.jsp" class=""><i
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
	
	
	 <div class="main">
		<center>
        <h1 style="color:red">Modify Password</h1>
        	<form method="post" action="UpdatePasswordForReader">
                <div class="panel-body">
								<table align="center">
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="oldPassword">Old
													Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="text" name="oldPassword" class="form-control"
													placeholder="Old Password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
											</div>
										</td>
									</tr>
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="newPassword">New
													Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" name="newPassword"
													class="form-control" placeholder="New Password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
											</div>
										</td>
									</tr>
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="ConfirmPassword">Confirm
													New Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" name="ConfirmPassword"
													class="form-control" placeholder="Confirm New Password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
											</div>
										</td>
									</tr>
								</table>
								<div>
									<div style="width: 300px; margin: auto">
										<button type="submit" class="btn btn-primary btn-block">Modify</button>
									</div>
								</div>
							</div>
            </form>
          
   </center>
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