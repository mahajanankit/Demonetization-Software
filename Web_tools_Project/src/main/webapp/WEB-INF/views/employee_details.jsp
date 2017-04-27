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
						<a href="employee-details.htm">
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
                                <h4 class="title">Edit Profile</h4>
                            </div>
                            <div class="content">
                                <form action="updateUser.htm" method="post">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>User Id (disabled)</label>
                                                <input type="number" class="form-control"  name="id" disabled  value="${user.id}">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>SSN</label>
                                                <input type="number" class="ssn" pattern="[0-9]+" required value="${user.person.ssn}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Email address</label>
                                                <input type="email" name="user_email" class="form-control" required value="${user.email}">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>First Name</label>
                                                <input type="text" class="form-control" name="fname" pattern="[a-zA-Z]+" value="${user.person.firstName}">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Last Name</label>
                                                <input type="text" class="form-control" name="lName" pattern="[a-zA-Z]+" required value="${user.person.lastName}">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input type="password" name ="psw" class="form-control"  required value="${user.getPassword()}">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <input type="hidden" name="role" value="employee">

                                    <button type="submit" class="btn btn-info btn-fill pull-right">Update Profile</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    

                </div>
            </div>
        </div>
              

</body>
</html>