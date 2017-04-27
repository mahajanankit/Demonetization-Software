<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Customer-Home</title>
</head>
<body>
	<form action="">
		<table>
			<thead>
			    <tr>
			        <td width="10%">ISBN</td>
			        <td width="30%">TITLE</td>
			        <td width="30%">AUTHOR</td>
			        <td width="20%">SELECT</td>
			    </tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${reservations}" var="reservation ">  
				<tr>                        
					<td align="left"><input type="text" name="title<c:out value="${reservation.user.person.getFirstName()}"/>"  disabled="true"/></td> 
					<td align="left"><input type="text" name="author<c:out value="${reservation.date}"/>"  disabled="true"/></td> 
					<td align="left"><input type="text" name="price<c:out value="${reservation.timeslot}"/>"  disabled="true"/></td>
					<td align="center"><input type="radio" name="checkboxgroup" value="<c:out value="${reservation.id}"/>"/>  
					</td>  
				</tr>  
			</c:forEach> 
			       
			<input type="submit" value="processapplication">  
			</tbody>
		</table>
    </form>
    <a href='logout.htm'>Logout</a>
</body>
</html>