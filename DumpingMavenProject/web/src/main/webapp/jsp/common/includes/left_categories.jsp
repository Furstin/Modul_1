<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="categories_header">
		<br>
		<fmt:message key="heading.categories" />
	</div>
	<div id="categories">
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="all" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.all"/>" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="souvenirs" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.souvenirs" />" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="calendars" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.calendars" />" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="printing" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.printing"/>" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="promostands" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.promostands" />" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="event" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.event" />" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="page" value="category" /><input
				type="hidden" name="type" value="other" /><input type="hidden"
				name="num" value="1" /> <input type="submit" id="category"
				value="<fmt:message key="category.other"/>" />
		</form>
	</div>
</body>
</html>