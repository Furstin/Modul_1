<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="categories_header">
		<br>
		<fmt:message key="message.debtors" />
	</div>
	<center>
		<table width="300px">
			<c:forEach var="debtor" items="${debtors}">
				<tr>
					<td>${debtor}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>