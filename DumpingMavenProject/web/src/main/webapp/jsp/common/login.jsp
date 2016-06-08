<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<%@include file="/jsp/common/includes/header.jsp"%>
<body>
	<div id="container">
		<div id="header_section">
			<jsp:include page="/jsp/common/includes/top_buttons.jsp" /></div>
		<div id="content">
			<div id="left_section">
				<jsp:include page="/jsp/common/includes/left_categories.jsp" /></div>
			<div id="right_section">
				<jsp:include page="/jsp/common/includes/login_fields.jsp" /></div>
			<div id="footer_section"></div>
		</div>
	</div>
</body>
</html>