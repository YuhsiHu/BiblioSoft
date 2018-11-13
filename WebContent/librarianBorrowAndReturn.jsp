<%@ page import="BiblioSoft.DAO.RecordDAO" %>
<%@ page import="java.util.Collection" %>
<%@ page import="BiblioSoft.Table.RecordTable" %>
<%@ page import="static java.awt.SystemColor.window" %>
<%@ page import="BiblioSoft.DAO.IncomeDao" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <title>Borrow And Return</title>
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
                                                                         class="img-circle" alt="Avatar">
                        <span>Samuel</span></a></li>
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
                        <a href="#subPages1" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>Books Management</span>
                            <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages1" class="collapse ">
                            <ul class="nav">
                                <li><a href="addBook.jsp" class="">Add Book</a></li>
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
                        </div>
                    </li>

                    <li>
                        <a href="#subPages2" data-toggle="collapse" class="active"><i class="lnr lnr-cog"></i> <span>Readers Management</span>
                            <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages2" class="collapse in">
                            <ul class="nav">
                                <li><a href="addReader.jsp" class="">Add Reader</a></li>
                                <li><a href="deleteReader.jsp" class="">Delete Reader</a></li>
                                <li><a href="librarianEditReader.jsp" class="">Edit Reader</a></li>
                                <li><a href="librarianBorrowAndReturnRecord.jsp" class="">Borrow and Return Record</a></li>
                                <li><a href="fineRecord.jsp" class="">Fine Record</a></li>
                                <li><a href="borrowAndReturn.jsp" class="active">Book Borrow and Return</a></li>

                            </ul>
                        </div>
                    </li>
                    <li>
							<a href="#subPages3" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>Posts Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages3" class="collapse">
								<ul class="nav">
									<li><a href="addPost.jsp" class="">Add Post</a></li>
								    <li><a method="post" href="AllPost" class="">Search Post</a></li>
								</ul>
							</div>
						</li>

                    <li><a href="librarianLogin.jsp" class=""><i
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
                <h3 class="page-title">Borrow and return</h3>
                <div class="row">
                    <div class="col-md-12">
                        <!-- TABLE STRIPED -->
                        <div class="panel">
                            <div class="panel-heading">
                                <h3 class="panel-title">Reader ID & Book ID</h3>
                            </div>
                            <div class="panel-body">
                                <form method="POST" action="borrowAndReturn.jsp">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input class="form-control" type="text" name="Reader_id">
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="far fa-book"></i></span>
                                        <input class="form-control" type="text" name="Book_id">
                                    </div>
                                    <input type="submit" class="btn btn-primary" value="Borrow" name="1">
                                    <input type="submit" class="btn btn-success" value="Return" name="2">
                                    <br>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- END TABLE STRIPED -->
                </div>
            </div>
            <%if(request.getParameter("Reader_id")==""||request.getParameter("Book_id")==""){
                out.print("<script language='javascript'>alert('Librarian ID And Bood ID Can Not Be Empty!');window.location.href='borrowAndReturn.jsp';</script>");
            }else{
                if (request.getParameter("1") != null) {
                    int Reader_id = 0;
                    if (request.getParameter("Reader_id") != null)
                        Reader_id = Integer.valueOf(request.getParameter("Reader_id"));
                    int Book_id = 0;
                    if (request.getParameter("Book_id") != null)
                        Book_id = Integer.valueOf(request.getParameter("Book_id"));
                    RecordDAO recordDAO = new RecordDAO();
                    recordDAO.InsertRecordByReaderID(Reader_id, Book_id);
                    boolean insert = false;
                    int borrow = 0;
                    Collection<RecordTable> recordTables = recordDAO.getRecordByReaderID(Reader_id);
                    for (RecordTable recordTable : recordTables) {
                        if (recordTable.getBook_id() == Book_id && recordTable.getReturn_time() == null) {
                            insert = true;
                        }
                        borrow++;
                    }
                    if (borrow != 0) {
                        if (insert)out.print("<script language='javascript'>alert('Borrow Successful!');window.location.href='borrowAndReturn.jsp';</script>");
                        else out.print("<script language='javascript'>alert('Borrow failed!');window.location.href='borrowAndReturn.jsp';</script>");
                    }
                }

                if (request.getParameter("2") != null) {
                        int Reader_id = 0;
                        if (request.getParameter("Reader_id") != null)
                            Reader_id = Integer.valueOf(request.getParameter("Reader_id"));
                        int Book_id = 0;
                        if (request.getParameter("Book_id") != null)
                            Book_id = Integer.valueOf(request.getParameter("Book_id"));
                        RecordDAO recordDAO = new RecordDAO();
                        boolean borrow = false;
                        int borrows = 0;
                        if(recordDAO.ifNeedtoFine(Reader_id,Book_id)){
                        out.print("<script language='javascript'>alert('Please pay a fine!');window.location.href='borrowAndReturn.jsp';</script>");
                        recordDAO.setCurrentFine(Reader_id,Book_id);
                        recordDAO.setReturnTime(Reader_id,Book_id);
                        IncomeDao incomeDao = new IncomeDao();
                        incomeDao.addFine(Reader_id,Book_id);}
                        Collection<RecordTable> recordTables = recordDAO.getRecordByReaderID(Reader_id);
                        for (RecordTable recordTable : recordTables) {
                            if (recordTable.getBook_id() == Book_id && recordTable.getReturn_time()!=null) {
                                borrow = true;
                            }
                            borrows++;
                        }
                        if (borrows != 0) {
                            if (borrow) out.print("<script language='javascript'>alert('Return Successful!');window.location.href='borrowAndReturn.jsp';</script>");
                            else out.print("<script language='javascript'>alert('Return failed!');window.location.href='borrowAndReturn.jsp';</script>");
                        }}
                } %>

        </div>


    </div>
</div>
</div> <!-- END MAIN -->
</div>
<!-- END MAIN CONTENT -->
<!-- END MAIN -->
<div class="clearfix"></div>
<footer>

    <div class="container-fluid">
        <p class="copyright">
            Copyright &copy; 2018.Company name All rights reserved. <a
                target="_blank" title="BiblioSoft">BiblioSoft</a>
            - Collect from <a title="BiblioSoft"
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

</body>

</html>
