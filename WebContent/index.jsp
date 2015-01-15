<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>uniwarehouse</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="//code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- Daterange picker -->
        <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <jsp:useBean id="dbConn" scope="session" class="connection.DBConnection"/>
<jsp:setProperty name="dbConn" property="*" />
    <%
	if (dbConn.getDBConnection()){
%> 
    <body class="skin-blue">
    

        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="main.jsp" class="logo">
            <div class="icon">
                                    <i class="ion-university"></i>
                                    uniwarehouse
                                </div>
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                
            </a>
            
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- Messages: style can be found in dropdown.less-->
                        
                        <!-- Notifications: style can be found in dropdown.less -->
                        
                        <!-- Tasks: style can be found in dropdown.less -->
                        
                        <!-- User Account: style can be found in dropdown.less -->
                        
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                                        
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href="main.jsp">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>
                        
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-bar-chart-o"></i>
                                <span>Queries</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="incoming.jsp"><i class="fa fa-angle-double-right"></i>Incoming students</a></li>
                                <li><a href="outgoing.jsp"><i class="fa fa-angle-double-right"></i>Outgoing students</a></li>
                                <li><a href="grouping_id.jsp"><i class="fa fa-angle-double-right"></i>Internships in company</a></li>
                                <li><a href="ranking.jsp"><i class="fa fa-angle-double-right"></i>Internships salary</a></li>
                                <li><a href="windowing.jsp"><i class="fa fa-angle-double-right"></i>Internships per month</a></li>
                                <li><a href="ptop.jsp"><i class="fa fa-angle-double-right"></i>Enrollements</a></li>
                                <li><a href="lattice3.jsp"><i class="fa fa-angle-double-right"></i>Graduations year/study plan</a></li>
                                <li><a href="lattice2.jsp"><i class="fa fa-angle-double-right"></i>Graduations year/faculty</a></li>
                                <li><a href="lattice.jsp"><i class="fa fa-angle-double-right"></i>Graduations faculty/supervisor</a></li>
                            </ul>
                        </li>
                        
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
        
        
        <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Queries
                        <small>Preview page</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Queries</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <h4 class="page-header">
                        List of queries
                        <small>These queries are used to extract from the university database the information needed for statistica analises.</small>
                    </h4>
                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-blue">
                                <div class="inner">
                                    <h3>
                                        Incoming students
                                    </h3>
                                    <p>
                                        ROLLUP
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-chevron-down"></i>
                                </div>
                                <a href="incoming.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-aqua">
                                <div class="inner">
                                    <h3>
                                        Outgoing students
                                    </h3>
                                    <p>
                                        ROLLUP
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-chevron-up"></i>
                                </div>
                                <a href="outgoing.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-teal">
                                <div class="inner">
                                    <h3>
                                        Internships
                                    </h3>
                                    <p>
                                        Windowing
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-stats-bars"></i>
                                </div>
                                <a href="windowing.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        </div>
                       <div class="row">
                        <!-- Small boxes (Stat box) -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-yellow">
                                <div class="inner">
                                    <h3>
                                        Enrollments <br>2013 and 2014
                                    </h3>
                                    <p>
                                        Period-To-Period
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-edit"></i>
                                </div>
                                <a href="ptop.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                    

                    
                    
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-orange">
                                <div class="inner">
                                    <h3>
                                        Salary <br> internships</sup>
                                    </h3>
                                    <p>
                                        Rank
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-cash"></i>
                                </div>
                                <a href="ranking.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        </div><!-- /.row -->
                        <div class="row">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-red">
                                <div class="inner">
                                    <h3>
                                        Graduations <br> year/study plan
                                    </h3>
                                    <p>
                                        Lattice Framework
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-clipboard"></i>
                                </div>
                                <a href="lattice3.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-purple">
                                <div class="inner">
                                    <h3>
                                        Graduations <br> faculty/supervisor
                                    </h3>
                                    <p>
                                        Lattice Framework
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-android-contact"></i>
                                </div>
                                <a href="lattice.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-maroon">
                                <div class="inner">
                                    <h3>
                                        Graduations <br> year/faculty
                                    </h3>
                                    <p>
                                        Lattice Framework
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-home"></i>
                                </div>
                                <a href="lattice2.jsp" class="small-box-footer">
                                    Execute <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
                        
                    </div><!-- /.row -->

                </section><!-- /.content -->
                
                <section class="content">
                    <h4 class="page-header">
                        List of miscellaneuos queries
                        <small>These queries have been formalized but not implemented in the system.</small>
                    </h4>
                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3>
                                        Internship in <br> a company
                                    </h3>
                                    <p>
                                        GROUPING-ID
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion-aperture"></i>
                                </div>
                                <a href="grouping_id.jsp" class="small-box-footer">
                                    More info <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->                        

                </section><!-- /.content -->
                
            </aside>
        
        </div><!-- ./wrapper -->

        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.11.1/jquery-ui.min.js" type="text/javascript"></script>
        <!-- Morris.js charts -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="js/plugins/morris/morris.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!-- datepicker -->
        <script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>

        <!-- AdminLTE App -->
        <script src="js/AdminLTE/app.js" type="text/javascript"></script>

        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="js/AdminLTE/dashboard.js" type="text/javascript"></script>

        <!-- AdminLTE for demo purposes -->
        <script src="js/AdminLTE/demo.js" type="text/javascript"></script>

    </body>
    <%
} else {
	//out.println("Could not connect to the database!");

// New location to be redirected
String site = new String("index.jsp");
response.setStatus(response.SC_MOVED_TEMPORARILY);
response.setHeader("Location", site); 


} %>
</html>
