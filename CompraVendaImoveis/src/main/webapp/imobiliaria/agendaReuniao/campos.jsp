<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table border="1">
        <caption>
		<fmt:message key="meetingSchedule"/>
	</caption>
        <tr>
		<td><label for="idimovel"><fmt:message key="offerId"/>:
		</label></td>
		<td><input type="text" id="idproposta" name="idproposta" value="${proposta.id}" readonly style="background-color:gray; color:white" />
                </td>
	</tr>
	<tr>
		<td><label for="data"> <fmt:message key="date"/>
		</label></td>
		<td><input type="date" id="data" name="data"
			required value="" /></td>
	</tr>
	<tr>
		<td><label for="hora"> <fmt:message key="hour"/>
		</label></td>
		<td><input type="time" id="hora" name="hora"
			required value="" /></td>
	</tr>
        <tr>
		<td><label for="link"> Link
		</label></td>
		<td><input type="text" id="link" name="link"
			required value="" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="send"/>" /></td>
	</tr>
</table>
