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
	        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Create Profile</h4>
                            </div>
                            <div class="content">
                                <form action="createUser.htm" method="post">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>User Id (disabled)</label>
                                                <input type="number" class="form-control"  name="id" disabled  placeholder="auto">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>SSN</label>
                                                <input type="number" class="ssn" name="ssn" maxlength=9 autofocus required>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Email address</label>
                                                <input type="email" name="email" class="form-control" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>First Name</label>
                                                <input type="text" class="form-control" name="fname" pattern="[a-zA-Z]+" required>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Last Name</label>
                                                <input type="text" class="form-control" name="lName" pattern="[a-zA-Z]+" required>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Password</label>
                                                <input type="password" name ="psw" class="form-control" required>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Role: </label>
	                                                <select name="role" required>
														<option value="customer">Customer</item>
														<option value="employee">Employee</item>
													</select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    <button type="submit" class="btn btn-info btn-fill pull-right">Create</button>
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
          