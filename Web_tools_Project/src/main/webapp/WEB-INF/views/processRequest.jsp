<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<link rel="icon" type="image/png" href="<c:url value="/assets/img/favicon.ico"/>">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
		<title>Demonetization Assistance</title>
	
		<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	    <meta name="viewport" content="width=device-width" />
	
	
	    
	    <link href="<c:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet" />
	
	    
	    <link href="<c:url value="/assets/css/animate.min.css"/>" rel="stylesheet"/>
	
	    
	    <link href="<c:url value="/assets/css/light-bootstrap-dashboard.css"/>" rel="stylesheet"/>
	
	
	    <!--     Fonts and icons     -->
	    <link href="<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"/>" rel="stylesheet">
	    <link href="<c:url value="http://fonts.googleapis.com/css?family=Roboto:400,700,300"/>" rel='stylesheet' type='text/css'>
	    <link href="<c:url value="/assets/css/pe-icon-7-stroke.css"/>" rel="stylesheet" />
	
	</head>
<body>

	<div class="wrapper">
		<div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">


			<div class="sidebar-wrapper">
				<div class="logo">
					<a href="#" class="simple-text">
						${sessionScope.userId}
					</a>
				</div>

				<ul class="nav">
					<li class="active">
						<a href="#">
							<i class="pe-7s-graph"></i>
							<p>Dashboard</p>
						</a>
					</li>
					<li>
						<a href="user-details.htm">
							<i class="pe-7s-user"></i>
							<p>User Profile</p>
						</a>
					</li>
					<li>
						<a href="searchOrder.htm">
							<i class="pe-7s-note2"></i>
							<p>Search Order</p>
						</a>
					</li>
				</ul>
			</div>
		</div>

		<div class="main-panel">
			<nav class="navbar navbar-default navbar-fixed">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Demonetization</a>
					</div>
					<div class="collapse navbar-collapse">
						<ul class="nav navbar-nav navbar-left">
							<li>
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-dashboard"></i>
									<p class="hidden-lg hidden-md">Dashboard</p>
								</a>
							</li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							
							<li>
								<a href="logout.htm">
									<p>Log out</p>
								</a>
							</li>
							<li class="separator hidden-lg hidden-md"></li>
						</ul>
					</div>
				</div>
			</nav>




	        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Reservation details</h4>
                            </div>
                            <div class="content">
                                <form action="completeRequest.htm" method="post">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Reservation id (disabled)</label>
                                                <input type="number" class="form-control"  name="id" disabled  value="${reservation.id}">
                                                <input type="hidden" class="form-control"  name="reservationid" value="${reservation.id}">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Date</label>
                                                <input type="text" class="date" value="${reservation.date}" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1"></label>
                                                <input type="text" name="user_email" class="form-control" value="${reservation.timeslot}" disabled>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>500 Notes:</label>
                                                <input type="number" class="form-control" name="500" value="${reservation1.id.quantity}" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>1000 Notes:</label>
                                                <input type="text" class="form-control" name="1000" value="${reservation2.id.quantity}" disabled>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>User Id</label>
                                                <input type="text" name ="psw" class="form-control" value="${user.id}" disabled>
                                            </div>
                                        </div>
                                    </div>
                                    

                                    <button type="submit" class="btn btn-info btn-fill pull-right">Complete Request</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    

                </div>
            </div>
        </div>
		</div>
	</div>


</body>
	
	
	
	
	
	
	
	  <!--   Core JS Files   -->
    <script src="<c:url value="assets/js/jquery-1.10.2.js"/>" type="text/javascript"></script>
	<script src="<c:url value="assets/js/bootstrapmin.js"/>" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="<c:url value="assets/js/bootstrap-checkbox-radio-switch.js"/>"></script>

	<!--  Charts Plugin -->
	<script src="<c:url value="assets/js/chartist.min.js"/>"></script>

    <!--  Notifications Plugin    -->
    <script src="<c:url value="assets/js/bootstrap-notify.js"/>"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="<c:url value="https://maps.googleapis.com/maps/api/js?sensor=false"/>"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="<c:url value="assets/js/light-bootstrap-dashboard.js"/>"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="<c:url value="assets/js/demo.js"/>"></script>
</html>