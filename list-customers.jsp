<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Customers</title>

<!-- reference our style sheet -->
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css"/>
	  
</head>
<body>
	
	<div id="wrapper">
		<div id="header">
		<h2>CRM-Customer Relationship Manager</h2></div>
	</div>
	<div id="container">
		<div id="content">
		
		<!-- adding a button  -->
		<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd';return false;"
				class="add-button"/>
		
		<!-- adding a Search box -->
		<form:form action="search" method="GET">
			Search Customer : <input type="text" name="theSearchName"/>
			<input type="submit" value="Search" class="add-button"/>
		</form:form>		
		
		<!-- table -->
		<table>
			<tr>
			<th> First Name </th>
			<th> Last Name </th>
			<th> Email </th>
			<th> Update | Delete</th>
			</tr>
			
			<c:forEach var="tempCustomers" items="${customers}">
			
			<!-- construct an "update" link with customer id -->
			<c:url var="updateLink" value="/customer/showFormForUpdate">
				<c:param name="customerId" value="${tempCustomers.id}"></c:param>
			</c:url>
			
			<!-- construct an "delete" link with customer id -->
			<c:url var="deleteLink" value="/customer/delete">
				<c:param name="Id" value="${tempCustomers.id}"></c:param>
			</c:url>
			<tr>
				<td>${tempCustomers.firstName}</td>
				<td>${tempCustomers.lastName}</td>
				<td>${tempCustomers.email}</td>
				
				<!-- display the update link -->
				<td>
				<a href="${updateLink}">Update</a>
				
				<!-- display the delete link -->
				|
				<a href="${deleteLink}"
					onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
				
				</td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>
