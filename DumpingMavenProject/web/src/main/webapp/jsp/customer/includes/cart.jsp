<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="categories_header">
		<br>
		<fmt:message key="heading.cart" />
	</div>
	<c:if test="${fn:length(order.goods)>0}">
		<table width="80%" align="left">
			<c:forEach var="goods" items="${order.goods}">

				<tr>
					<td><img alt="${goods.photo}"
						src="${pageContext.request.contextPath}${goods.photo}"
						width="150px"></td>
					<td>${goods.title}</td>
					<td>${goods.price}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3"><form action="Controller" method="post">
						<input type="hidden" name="page" value="pay" /><input
							type="hidden" name="order_id" value="${order.id}" /> <input
							type="submit" id="button"
							value="<fmt:message key="button.pay"></fmt:message>" />
					</form></td>
			</tr>
		</table>
	</c:if>
</body>
</html>