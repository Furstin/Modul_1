<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="categories_header">
		<br>
		<fmt:message key="heading.goods" />
	</div>
	<table width="100%" align="left">
		<tr>
			<td><img alt="${goods.photo}"
				src="${pageContext.request.contextPath}${goods.photo}" width="300px" /></td>
			<td>
				<form action="Controller" method="post">
					<input type="hidden" name="page" value="want_buy" /><input
						type="hidden" name="id" value="${goods.goodsId}" /><input
						type="hidden" name="account_id" value="${user.id}" /> <input
						type="submit" id="button"
						value="<fmt:message key="button.to_cart"/>" />
				</form> <ctg:ginfo price="${goods.price}"
					description="${goods.description}" title="${goods.title}"
					id="${goods.goodsId}" />
			</td>
		</tr>
	</table>


</body>
</html>