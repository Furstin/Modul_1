<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="categories_header">
		<br>
		<fmt:message key="heading.register" />
	</div>
	<form action="Controller" method="post">
		<input type="hidden" name="page" value="customer_register" />
		<table>
			<tr>
				<td><fmt:message key="message.email"></fmt:message></td>
				<td><input type="email" name="email" required="required" /></td>
			</tr>
			<tr>
				<td><fmt:message key="message.firstname"></fmt:message></td>
				<td><input type="text" name="firstName" required="required" /></td>
			</tr>
			<tr>
				<td><fmt:message key="message.lastname"></fmt:message></td>
				<td><input type="text" name="lastName" required="required" /></td>
			</tr>
			<tr>
				<td><fmt:message key="message.phonenumber"></fmt:message></td>
				<td><input type="text" name="phoneNumber" required="required" /></td>
			</tr>
			<tr>
				<td><fmt:message key="message.address"></fmt:message></td>
				<td><input type="text" name="address" required="required" /></td>
			</tr>
			<tr>
				<td><fmt:message key="message.password"></fmt:message></td>
				<td><input type="password" name="password" required="required" /></td>
			</tr>
			<tr>
				<td><input type="submit" id="button"
					value="<fmt:message key="button.register"></fmt:message>"></input></td>
			</tr>
			<tr>
				<td colspan="2">${emailexists}${fillall}</td>
			</tr>
		</table>
	</form>
</body>
</html>