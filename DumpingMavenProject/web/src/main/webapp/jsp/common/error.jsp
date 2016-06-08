<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<%@include file="/jsp/common/includes/header.jsp"%>
<body>
	<center>
		<div id="container">
			<br />
			<fmt:message key="message.error" />
			<br />
			<fmt:message key="message.stcode" />${pageContext.errorData.statusCode}
			<br />
			<fmt:message key="message.exception" />${pageContext.exception}
			<br />
		</div>
	</center>
</body>
</html>