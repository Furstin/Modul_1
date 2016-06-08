<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="categories_header">
		<br>
		<fmt:message key="heading.login" />
	</div>
	<form action="Controller" method="post">
		<input type="hidden" name="page" value="login" />
		<table>
			<tr>
				<td><fmt:message key="message.email"></fmt:message></td>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<td><fmt:message key="message.password"></fmt:message></td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" id="button"
					value="<fmt:message key="button.login" ></fmt:message>"></input></td>
			</tr>
			<tr>
				<td colspan="2">${banned}${checklogin}${fillall}</td>
			</tr>
		</table>
	</form>
</body>
</html>