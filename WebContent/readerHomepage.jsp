<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%@page
	import="BiblioSoft.DAO.*,java.sql.*,java.util.Collection,BiblioSoft.Table.*"%>
<head>
<title>Login</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
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
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="brand">
			<img src="assets/img/BiblioSoft Logo.png"
				alt="BiblioSoft Logo" class="img-responsive logo">
		</div>
		<div class="container-fluid">
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a class="update-pro" href="ReaderLogin.jsp" title="Login" ><i class="fa fa-rocket"></i> <span>Login</span></a>
					</li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- MAIN -->
		<div>
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
				<br/><br/><br/><br/>
					<div class="panel">
						<div class="panel-heading">
							<br />
							<div class="logo text-center">
									<img src="assets/img/logo-big.png" alt="BiblioSoft Logo">
							</div>
						</div>
						<div class="panel-body">
							<div class="overlay">
							<div class="content text">
								<h1 align="center" class="heading">
									<br />Newest Announcement
								</h1>
								<div class="panel-body">
									<table align="center">
										<tr>
											<td width="120px">
												<div align="center">
													<label style="font-size: 20px" class="control-label"
														for="Announcement">Search</label>
												</div>
											</td>
											<td width="550px">
												<!-- Search Announcement Here And List On This Page -->

												<form method="post" action="">
													<div class="input-group">
														<input class="form-control" type="text"
															name="Announcement" id="Announcement"
															placeholder="Search The Announcement"> <span
															class="input-group-btn">
															<button type="submit" class="btn btn-primary"
																type="button">Search</button>
														</span>
													</div>
												</form>
											</td>
										</tr>
									</table>

									<br/><br/>
									<table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th width="750px">Post Title</th>
                                        <th width="150px">Writer</th>
                                        <th width="350px">Post Time</th>
                                    </tr>
                                    </thead>
                                    
									<%
										PostDAO postdao = new PostDAO();

										try {
											Collection<PostTable> posts = postdao.searchAll();
											
											if(posts==null){
												%>
			                                    <tr>
			                                        <td></td>
			                                    </tr><%
											}else{
												for (PostTable p : posts) {
													String title = p.getTitle();
													int Librarian = p.getLibID();
													String time = p.getTime().toString();
													%>
				                                    <tr>
				                                        <td><a href="announcement_show?title=<%=title %>"><%=title %></a></td>
				                                        <td><%=Librarian %></td>
				                                        <td><%=time %></td>
				                                    </tr><%
												}
											}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									%>

									</table>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->

	<script>
		function checkuse() {
			//在每个函数中定义check变量是为了在表单提交后，能够逐个验证每个函数是否通过，很好很好。（以下同理）
			var check;
			var username = document.getElementById("userid").value;
			if (username.length != 11) {
				alert("reader tele's length is 11");
				//此处甚妙，既然你在此处输入错误，那么按理说当然要在此处继续输入了。（在此处继续获取焦点！）
				document.getElementById("username").focus();
				check = false;
			}
			return check;
		}
	</script>
</body>

</html>
