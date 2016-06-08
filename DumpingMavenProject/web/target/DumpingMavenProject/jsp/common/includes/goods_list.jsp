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
		<fmt:message key="heading.catalogue" />
	</div>

	<table width="100%" align="left">
		<c:forEach var="goods" items="${catalogue}">
			<tr>
				<td><img alt="${goods.photo}"
					src="${pageContext.request.contextPath}${goods.photo}"
					width="150px"></td>
				<td>${goods.title}</td>
				<td>${goods.price}</td>
				<td><form action="Controller" method="post">
						<input type="hidden" name="page" value="goods_details" /><input
							type="hidden" name="id" value="${goods.goodsId }" /> <input
							type="submit" id="button"
							value="<fmt:message key="button.details"></fmt:message>" />
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<center>
		<table>
			<tr>
				<c:forEach begin="1" end="${pages}" varStatus="loop">
					<td><form action="Controller" method="post">
							<input type="hidden" name="page" value="category" /><input
								type="hidden" name="type" value="${type}" /><input
								type="hidden" name="num" value="${loop.index}" /> <input
								type="submit" id="num" value="${loop.index}" />
						</form></td>
				</c:forEach>
			</tr>
		</table>
	</center>
</body>
</html>