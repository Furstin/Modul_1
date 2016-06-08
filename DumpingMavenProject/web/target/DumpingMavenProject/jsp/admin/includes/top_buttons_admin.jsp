<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/jsp/common/includes/set_language.jsp"%>
<body>
	<div id="top_buttons">
		<table align="right">
			<tr>
				<td><fmt:message key="message.greetings" />${user.firstName }</td>
				<td>
					<form action="Controller" method="post">
						<input type="hidden" name="page" value="language" /><input
							type="hidden" name="language" value="en" /><input type="submit"
							id="lang_button"
							value='<fmt:message key="button.en" ></fmt:message>'>
					</form>
				</td>
				<td>
					<form action="Controller" method="post">
						<input type="hidden" name="page" value="language" /><input
							type="hidden" name="language" value="ru" /><input type="submit"
							id="lang_button"
							value='<fmt:message key="button.ru"></fmt:message>'>
					</form>
				</td>
				<td>
					<form action="Controller" method="post">
						<input type="hidden" name="page" value="want_sign_out" /> <input
							type="submit" id="button"
							value='<fmt:message key="button.logout"></fmt:message>'>
					</form>
				</td>
				<td>
					<form action="Controller" method="post">
						<input type="hidden" name="page" value="admin_want_home" /> <input
							type="submit" id="button"
							value="<fmt:message key="button.home"></fmt:message>">
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>