<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<center>
		<div id="third_section">
			<div id="categories_header">
				<br>
				<fmt:message key="button.delete_goods" />
			</div>
			<form action="Controller" method="post">
				<input type="hidden" name="page" value="delete_goods" />
				<table>
					<tr>
						<td><fmt:message key="message.id"></fmt:message></td>
						<td><input type="text" name="id" required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="button"
							value="<fmt:message key="button.delete_goods"/>" /></td>
					</tr>
					<tr>
						<td colspan="2">${gremoved}${gnotremoved}</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="third_section">
			<div id="categories_header">
				<br>
				<fmt:message key="button.add_goods" />
			</div>
			<form action="${request.contextPath.realPath }/Controller"
				method="post">
				<input type="hidden" name="page" value="add_goods" />
				<table>
					<tr>
						<td><fmt:message key="heading.categories"></fmt:message></td>
						<td><select name="category" required="required">
								<option value="all"><fmt:message key="category.all" /></option>
								<option value="souvenirs"><fmt:message key="category.souvenirs" /></option>
								<option value="calendars"><fmt:message
										key="category.calendars" /></option>
								<option value="printing"><fmt:message key="category.printing" /></option>
								<option value="promostands"><fmt:message
										key="category.promostands" /></option>
								<option value="event"><fmt:message
										key="category.event" /></option>
								<option value="other"><fmt:message key="category.other" /></option>
						</select></td>
					</tr>
					<tr>
						<td><fmt:message key="message.title"></fmt:message></td>
						<td><input type="text" name="title" required="required" /></td>
					</tr>
					<tr>
						<td><fmt:message key="message.price"></fmt:message></td>
						<td><input type="text" name="price" required="required" /></td>
					</tr>
					<tr>
						<td><fmt:message key="message.description"></fmt:message></td>
						<td><input type="text" name="description" required="required" /></td>
					</tr>
					<tr>
						<td><fmt:message key="message.image"></fmt:message></td>
						<td><input type="text" name="image" required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="button"
							value="<fmt:message key="button.add_goods"></fmt:message>"></input></td>
					</tr>
					<tr>
						<td colspan="2">${gadded}${gnotadded}</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="third_section">
			<div id="categories_header">
				<br>
				<fmt:message key="button.ban_customer" />
			</div>
			<form action="${request.contextPath.realPath }/Controller"
				method="post">
				<input type="hidden" name="page" value="ban_customer" />
				<table>
					<tr>
						<td><fmt:message key="message.email"></fmt:message></td>
						<td><input type="email" name="email" required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="button"
							value="<fmt:message key="button.ban_customer"/>" /></td>
					</tr>
					<tr>
						<td colspan="2">${cbanned}${cnotbanned}</td>
					</tr>
				</table>
			</form>
			<form action="${request.contextPath.realPath }/Controller"
				method="post">
				<input type="hidden" name="page" value="view_debtors" />
				<table>
					<tr>
						<td><input type="submit" id="button"
							value="<fmt:message key="message.debtors"/>" /></td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
</html>