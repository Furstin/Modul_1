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
				<fmt:message key="button.delete_manager" />
			</div>
			<form action="Controller" method="post">
				<input type="hidden" name="page" value="delete_manager" />
				<table>
					<tr>
						<td><fmt:message key="message.email"></fmt:message></td>
						<td><input type="email" name="email" required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="button"
							value="<fmt:message key="button.delete_manager"/>" /></td>
					</tr>
					<tr>
						<td colspan="2">${mdeleted}${mnosuch}</td>
					</tr>
				</table>
			</form>
		</div>

		<div id="third_section">
			<div id="categories_header">
				<br>
				<fmt:message key="button.create_manager" />
			</div>
			<form action="Controller" method="post">
				<input type="hidden" name="page" value="create_manager" />
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
						<td><input type="password" name="password"
							required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="button"
							value="<fmt:message key="button.create_manager"></fmt:message>"></input></td>
					</tr>
					<tr>
						<td colspan="2">${memailexists}${mcreated}${mnotcreated}</td>
					</tr>
				</table>
			</form>
		</div>

		<div id="third_section">
			<div id="categories_header">
				<br>
				<fmt:message key="button.view_managers" />
			</div>
			<form action="Controller" method="post">
				<input type="hidden" name="page" value="view_managers" /> <input
					type="submit" id="button"
					value="<fmt:message key="button.view_managers"/>" />
			</form>
		</div>
	</center>
</body>
</html>