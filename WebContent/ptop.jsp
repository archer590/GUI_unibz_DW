<!DOCTYPE html>
<%@page import="connection.DBConnection"%>
<%@page import="connection.Enrollment"%>
<%@page import="java.util.*" %>
 <jsp:useBean id="dbConn" scope="session" class="connection.DBConnection"/>
<jsp:setProperty name="dbConn" property="*" />
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
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
           <a href="index.jsp" class="logo">
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
                            <a href="index.jsp">
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

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Comparison enrolled students
                        <small>Query details</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li >Queries</li>
                        <li class="active">Period-To-Period</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                     <div class="box-body">
                                    <div class="box-group" id="accordion">
                                        <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                                        <div class="panel box box-primary">
                                            <div class="box-header">
                                                <h4 class="box-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="">
                                                        Description
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse in">
                                                <div class="box-body">                                                
													Which is the difference of the enrolled students between years 2013 and 2014? 
													<br> 
													Using <b>Period-to-Period</b> query tecnique.
												</div>
                                            </div>
                                        </div>
                                        <div class="panel box box-danger">
                                            <div class="box-header">
                                                <h4 class="box-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed">
                                                        Query
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse" style="height: 0px;">
                                                <div class="box-body">
<b>SELECT</b> count(sd.student_key), sd.enrollment_year<br> <b>FROM</b> admt2014_unibzdw.student_dimension sd<br> <b>WHERE</b> sd.enrollment_year='2014' <b>OR</b> sd.enrollment_year='2013'<br> <b>GROUP BY</b> sd.enrollment_year<br> <b>UNION ALL</b> <br><b>SELECT</b> (<b>SELECT</b> <b>COUNT</b>(ssd.student_key)<br> <b>FROM</b> admt2014_unibzdw.student_dimension ssd<br> <b>WHERE</b> ssd.enrollment_year='2013') - (<b>SELECT</b> <b>COUNT</b>(ssd.student_key)<br> <b>FROM</b> admt2014_unibzdw.student_dimension ssd<br> <b>WHERE</b> ssd.enrollment_year='2014'), NULL;                                   </div>
                </div>
                </div>
                <div class="col-md-6">
                <div class="box box-success">
                                <div class="box-header">
                                    <h3 class="box-title">Results</h3>
                                </div>
                                <div class="box-body">
                                    <table id="rollup" class="table table-bordered table-hover dataTable" aria-describedby="example2_info">
                                        <thead>
                                            <tr role="row"><th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">Students</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending">Enrollment Year</th></tr>
                                        </thead>
                                                                                
                                        <tfoot>
                                            <tr><th rowspan="1" colspan="1">Students</th><th rowspan="1" colspan="1">Enrollment Year</th></tr>
                                        </tfoot>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                        <%
                                        	Vector<Enrollment> enrolled_stud = dbConn.camparison_enrollment_year(); 
                                        int diff = 0;
                                                                                for(int i=0; i<enrolled_stud.size();i++)
                                                                                	{
                                                                                	if(enrolled_stud.get(i).getYear()!=0){
                                        %>
                                        	
                                        	<tr class="odd">
                                                <td class=" sorting_1"><%out.print(enrolled_stud.get(i).getStudents()); %></td>
                                                <td class=" "><%out.print(enrolled_stud.get(i).getYear()); }
                                                                                	else
                                                                                		diff = enrolled_stud.get(i).getStudents();
                                                
                                                %></td>                                                                                               
                                        	</tr>
                                        	<%
                                        	}                                        	
                                        	%></tbody></table>
                                        	<br>
                                        	The difference between year 2013 and 2014 is: <b><%out.print(Math.abs(diff));%></b>
                                </div><!-- /.box-body -->
                            </div>
                            </div>
                            </div>
                            <div class="col-md-6">

							<div class="box box-solid">
                                <div class="box-header">
                                    <i class="fa fa-bar-chart-o"></i>
                                    <h3 class="box-title">Charts</h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-default btn-sm" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-default btn-sm" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <div class="row">
                                    <%
                                        	diff = 0;
                                                                                for(int i=0; i<enrolled_stud.size();i++)
                                                                                	{
                                                                                	if(enrolled_stud.get(i).getYear()!=0){
                                        %>
                                        <div class="col-md-3 col-sm-6 col-xs-6 text-center">
                                            <div style="display: inline; width: 90px; height: 90px;"><canvas width="135" height="135" style="width: 90px; height: 90px;"></canvas><input type="text" class="knob" value="<%out.print(enrolled_stud.get(i).getStudents()); %>" data-skin="tron" data-thickness="0.2" data-width="90" data-height="90" data-fgcolor="#3c8dbc" data-readonly="true" readonly="readonly" style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(60, 141, 188); padding: 0px; -webkit-appearance: none; background: none;"></div>
                                            <div class="knob-label"><%out.print(enrolled_stud.get(i).getYear()); }%></div>
                                        </div><!-- ./col -->
                                        <%}%>
                                        
                                        
                                    </div><!-- /.row -->
                                </div><!-- /.box-body -->
                            </div>


                            	
                            	
                                                
                            </div>
                            </div>
                            </div>
                            
                            
                </section><!-- /.content -->
                
            </aside><!-- /.right-side -->
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
</html>
